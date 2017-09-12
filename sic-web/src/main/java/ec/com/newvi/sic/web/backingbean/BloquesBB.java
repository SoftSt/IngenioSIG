/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Bloques;
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
public class BloquesBB extends AdminFichaCatastralBB {

    private Bloques bloque;
    private List<Bloques> listaBloques;
    private List<Bloques> listaBloquesFiltrados;
    private List<Bloques> listaGruposBloques;
    private EnumPantallaMantenimiento pantallaActual;

    public Bloques getBloque() {
        return bloque;
    }

    public void setBloque(Bloques bloque) {
        this.bloque = bloque;
    }

    public List<Bloques> getListaBloques() {
        return listaBloques;
    }

    public void setListaBloques(List<Bloques> listaBloques) {
        this.listaBloques = listaBloques;
    }

    public List<Bloques> getListaGruposBloques() {
        return listaGruposBloques;
    }

    public void setListaGruposBloques(List<Bloques> listaGruposBloques) {
        this.listaGruposBloques = listaGruposBloques;
    }

    public List<Bloques> getListaBloquesFiltrados() {
        return listaBloquesFiltrados;
    }

    public void setListaBloquesFiltrados(List<Bloques> listaBloquesFiltrados) {
        this.listaBloquesFiltrados = listaBloquesFiltrados;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
        this.bloque = new Bloques();
        actualizarListadoBloques();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.BLOQUES_LISTA_TITULO,
                EnumEtiquetas.BLOQUES_LISTA_ICONO,
                EnumEtiquetas.BLOQUES_LISTA_DESCRIPCION);
    }

    private void actualizarListadoBloques() {
        listaBloques = catastroServicio.consultarBloques();
    }

    public void crearNuevoBloque() {
        this.bloque = new Bloques();
        this.bloque.setBloEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.BLOQUES_NUEVO_TITULO,
                EnumEtiquetas.BLOQUES_NUEVO_ICONO,
                EnumEtiquetas.BLOQUES_NUEVO_DESCRIPCION);
    }

    public void insertarBloque() {
        try {
            catastroServicio.generarNuevoBloque(bloque, sesionBean.getSesion());
            actualizarListadoBloques();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF340.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarBloque() {
        if (!ComunUtil.esNumeroPositivo(this.bloque.getCodBloques())) {
            insertarBloque();
        } else {
            try {
                catastroServicio.actualizarBloque(bloque, sesionBean.getSesion());
                actualizarListadoBloques();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF341.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.BLOQUES_LISTA_TITULO,
                EnumEtiquetas.BLOQUES_LISTA_ICONO,
                EnumEtiquetas.BLOQUES_LISTA_DESCRIPCION);
    }

    public void eliminarBloque(Integer idBloque) {
        try {
            this.seleccionarBloquePorCodigo(idBloque);
            if (!ComunUtil.esNulo(bloque)) {
                catastroServicio.eliminarBloque(bloque, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF342.presentarMensaje());
                actualizarListadoBloques();

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

    public void seleccionarBloque(Integer idBloque) {
        try {
            this.seleccionarBloquePorCodigo(idBloque);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.BLOQUES_EDITAR_TITULO,
                EnumEtiquetas.BLOQUES_EDITAR_ICONO,
                EnumEtiquetas.BLOQUES_EDITAR_DESCRIPCION);
    }

    private void seleccionarBloquePorCodigo(Integer idBloque) throws NewviExcepcion {
        this.bloque = catastroServicio.seleccionarBloque(idBloque);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioBloque:opDetalleBloques");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.BLOQUES_LISTA_TITULO,
                EnumEtiquetas.BLOQUES_LISTA_ICONO,
                EnumEtiquetas.BLOQUES_LISTA_DESCRIPCION);
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
