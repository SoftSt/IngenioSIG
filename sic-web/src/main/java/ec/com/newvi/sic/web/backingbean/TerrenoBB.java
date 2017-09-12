/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class TerrenoBB extends AdminFichaCatastralBB {
    private Terreno terreno;
    private List<Terreno> listaTerreno;
    private List<Terreno> listaTerrenoFiltrados;
    private List<Terreno> listaGruposTerreno;
    private EnumPantallaMantenimiento pantallaActual;

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public List<Terreno> getListaTerreno() {
        return listaTerreno;
    }

    public void setListaTerreno(List<Terreno> listaTerreno) {
        this.listaTerreno = listaTerreno;
    }

    public List<Terreno> getListaGruposTerreno() {
        return listaGruposTerreno;
    }

    public void setListaGruposTerreno(List<Terreno> listaGruposTerreno) {
        this.listaGruposTerreno = listaGruposTerreno;
    }

    public List<Terreno> getListaTerrenoFiltrados() {
        return listaTerrenoFiltrados;
    }

    public void setListaTerrenoFiltrados(List<Terreno> listaTerrenoFiltrados) {
        this.listaTerrenoFiltrados = listaTerrenoFiltrados;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
        this.terreno = new Terreno();
        actualizarListadoTerreno();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PISOS_LISTA_TITULO,
                EnumEtiquetas.PISOS_LISTA_ICONO,
                EnumEtiquetas.PISOS_LISTA_DESCRIPCION);
    }

    private void actualizarListadoTerreno() {
        listaTerreno = catastroServicio.consultarTerreno();
    }

    public void crearNuevoTerreno() {
        this.terreno = new Terreno();
        this.terreno.setTerEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PISOS_NUEVO_TITULO,
                EnumEtiquetas.PISOS_NUEVO_ICONO,
                EnumEtiquetas.PISOS_NUEVO_DESCRIPCION);
    }

    public void insertarTerreno() {
        try {
            catastroServicio.generarNuevoTerreno(terreno, sesionBean.getSesion());
            actualizarListadoTerreno();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF344.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarTerreno() {
        if (!ComunUtil.esNumeroPositivo(this.terreno.getCodTerrenodetalle())) {
            insertarTerreno();
        } else {
            try {
                catastroServicio.actualizarTerreno(terreno, sesionBean.getSesion());
                actualizarListadoTerreno();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF345.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PISOS_LISTA_TITULO,
                EnumEtiquetas.PISOS_LISTA_ICONO,
                EnumEtiquetas.PISOS_LISTA_DESCRIPCION);
    }

    public void eliminarTerreno(Integer idTerreno) {
        try {
            this.seleccionarTerrenoPorCodigo(idTerreno);
            if (!ComunUtil.esNulo(terreno)) {
                catastroServicio.eliminarTerreno(terreno, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF346.presentarMensaje());
                actualizarListadoTerreno();

            } else {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.getSesion());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
            }
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void seleccionarTerreno(Integer idTerreno) {
        try {
            this.seleccionarTerrenoPorCodigo(idTerreno);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PISOS_EDITAR_TITULO,
                EnumEtiquetas.PISOS_EDITAR_ICONO,
                EnumEtiquetas.PISOS_EDITAR_DESCRIPCION);
    }

    private void seleccionarTerrenoPorCodigo(Integer idTerreno) throws NewviExcepcion {
        this.terreno = catastroServicio.seleccionarTerreno(idTerreno);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioTerreno:opDetalleTerreno");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PISOS_LISTA_TITULO,
                EnumEtiquetas.PISOS_LISTA_ICONO,
                EnumEtiquetas.PISOS_LISTA_DESCRIPCION);
    }

    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }

    public Boolean esPantallaActual(String pantallaEsperada) {
        try {
            return this.pantallaActual.equals(EnumPantallaMantenimiento.obtenerPantallaPorNombre(pantallaEsperada));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }
    
}
