/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumTipoPermisos;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author NEWVI
 */
@ManagedBean
@ViewScoped
public class AsignacionPermisosBB extends AdminSeguridadesBB {

    private AsignacionPermisos asignacionPermisos;
    private List<AsignacionPermisos> listaAsignaciones;

    public AsignacionPermisos getAsignacionPermisos() {
        return asignacionPermisos;
    }

    public void setAsignacionPermisos(AsignacionPermisos asignacionPermisos) {
        this.asignacionPermisos = asignacionPermisos;
    }

    public List<AsignacionPermisos> getListaAsignaciones() {
        return listaAsignaciones;
    }

    public void setListaAsignaciones(List<AsignacionPermisos> listaAsignaciones) {
        this.listaAsignaciones = listaAsignaciones;
    }

    @PostConstruct
    public void init() {
        this.asignacionPermisos = new AsignacionPermisos();
        actualizarListadoAsignaciones();
    }

    private void actualizarListadoAsignaciones() {
        listaAsignaciones = seguridadesServicio.consultarAsignaciones();
    }

    public void crearNuevoAsignacion() {
        this.asignacionPermisos = new AsignacionPermisos();
        this.asignacionPermisos.setPefEstado(EnumEstadoRegistro.A);
    }
    
        public void insertarAsignacion() {
        try {
            seguridadesServicio.generarNuevaAsignacion(asignacionPermisos, sesionBean.getSesion());
            actualizarListadoAsignaciones();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF314.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarAsignacion() {
        if (!ComunUtil.esNumeroPositivo(this.asignacionPermisos.getPefId())) {
            insertarAsignacion();
        } else {
            try {
                seguridadesServicio.actualizarAsignacion(asignacionPermisos, sesionBean.getSesion());
                actualizarListadoAsignaciones();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF316.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

    public void eliminarAsignacion(Integer idAsignacion) {
        try {
            this.seleccionarAsignacionPorCodigo(idAsignacion);
            if (!ComunUtil.esNulo(asignacionPermisos)) {
                seguridadesServicio.eliminarAsignacion(asignacionPermisos, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF312.presentarMensaje());
                actualizarListadoAsignaciones();
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

    public void seleccionarAsignacion(Integer idAsignacion) {
        try {
            this.seleccionarAsignacionPorCodigo(idAsignacion);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
    }

    private void seleccionarAsignacionPorCodigo(Integer idAsignacion) throws NewviExcepcion {
        this.asignacionPermisos = seguridadesServicio.seleccionarAsignacion(idAsignacion);
    }
}
