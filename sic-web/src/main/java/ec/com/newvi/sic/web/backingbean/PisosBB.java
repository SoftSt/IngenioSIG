/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Pisos;
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
public class PisosBB  extends AdminFichaCatastralBB  {
    
    private Pisos piso;
    private List<Pisos> listaPisos;
    private List<Pisos> listaPisosFiltrados;
    private List<Pisos> listaGruposPisos;
    private EnumPantallaMantenimiento pantallaActual;

    public Pisos getPiso() {
        return piso;
    }

    public void setPiso(Pisos piso) {
        this.piso = piso;
    }

    public List<Pisos> getListaPisos() {
        return listaPisos;
    }

    public void setListaPisos(List<Pisos> listaPisos) {
        this.listaPisos = listaPisos;
    }

    public List<Pisos> getListaGruposPisos() {
        return listaGruposPisos;
    }

    public void setListaGruposPisos(List<Pisos> listaGruposPisos) {
        this.listaGruposPisos = listaGruposPisos;
    }

    public List<Pisos> getListaPisosFiltrados() {
        return listaPisosFiltrados;
    }

    public void setListaPisosFiltrados(List<Pisos> listaPisosFiltrados) {
        this.listaPisosFiltrados = listaPisosFiltrados;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
        this.piso = new Pisos();
        actualizarListadoPisos();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PISOS_LISTA_TITULO,
                EnumEtiquetas.PISOS_LISTA_ICONO,
                EnumEtiquetas.PISOS_LISTA_DESCRIPCION);
    }

    private void actualizarListadoPisos() {
        listaPisos = catastroServicio.consultarPisos();
    }

    public void crearNuevoPiso() {
        this.piso = new Pisos();
        this.piso.setPisEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PISOS_NUEVO_TITULO,
                EnumEtiquetas.PISOS_NUEVO_ICONO,
                EnumEtiquetas.PISOS_NUEVO_DESCRIPCION);
    }

    public void insertarPiso() {
        try {
            catastroServicio.generarNuevoPiso(piso, sesionBean.getSesion());
            actualizarListadoPisos();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF344.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPiso() {
        if (!ComunUtil.esNumeroPositivo(this.piso.getCodPisos())) {
            insertarPiso();
        } else {
            try {
                catastroServicio.actualizarPiso(piso, sesionBean.getSesion());
                actualizarListadoPisos();
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

    public void eliminarPiso(Integer idPiso) {
        try {
            this.seleccionarPisoPorCodigo(idPiso);
            if (!ComunUtil.esNulo(piso)) {
                catastroServicio.eliminarPiso(piso, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF346.presentarMensaje());
                actualizarListadoPisos();

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

    public void seleccionarPiso(Integer idPiso) {
        try {
            this.seleccionarPisoPorCodigo(idPiso);
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

    private void seleccionarPisoPorCodigo(Integer idPiso) throws NewviExcepcion {
        this.piso = catastroServicio.seleccionarPiso(idPiso);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioPiso:opDetallePisos");
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
