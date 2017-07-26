/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
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
public class ConstantesImpuestoBB extends AdminParametrosBB{
    private ConstantesImpuestos constantesImpuestos;
    private List<ConstantesImpuestos> listaConstantes;
    private List<ConstantesImpuestos> listaConstantesFiltrados;
    private EnumPantallaMantenimiento pantallaActual;

    public ConstantesImpuestos getConstantesImpuestos() {
        return constantesImpuestos;
    }

    public void setConstantesImpuestos(ConstantesImpuestos constantesImpuestos) {
        this.constantesImpuestos = constantesImpuestos;
    }

    public List<ConstantesImpuestos> getListaConstantes() {
        return listaConstantes;
    }

    public void setListaConstantes(List<ConstantesImpuestos> listaConstantes) {
        this.listaConstantes = listaConstantes;
    }

    public List<ConstantesImpuestos> getListaConstantesFiltrados() {
        return listaConstantesFiltrados;
    }

    public void setListaConstantesFiltrados(List<ConstantesImpuestos> listaConstantesFiltrados) {
        this.listaConstantesFiltrados = listaConstantesFiltrados;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }
    
    @PostConstruct
    public void init() {
        this.constantesImpuestos = new ConstantesImpuestos();
        actualizarListadoConstanteImpuestos();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.BLOQUES_LISTA_TITULO,
                EnumEtiquetas.BLOQUES_LISTA_ICONO,
                EnumEtiquetas.BLOQUES_LISTA_DESCRIPCION);
    }
    
    private void actualizarListadoConstanteImpuestos() {
        listaConstantes = parametrosServicio.consultarConstantesImpuestos();
    }
    
    public void crearNuevoConstanteImpuesto() {
        this.constantesImpuestos = new ConstantesImpuestos();
        this.constantesImpuestos.setconImpuestoEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.BLOQUES_NUEVO_TITULO,
                EnumEtiquetas.BLOQUES_NUEVO_ICONO,
                EnumEtiquetas.BLOQUES_NUEVO_DESCRIPCION);
    }

    public void insertarConstanteImpuesto() {
        try {
            parametrosServicio.generaNuevoConstanteImpuesto(constantesImpuestos, sesionBean.obtenerSesionDto());
            actualizarListadoConstanteImpuestos();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF340.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarConstanteImpuesto() {
        if (!ComunUtil.esNumeroPositivo(this.constantesImpuestos.getCodConstantesimpuestos())) {
            insertarConstanteImpuesto();
        } else {
            try {
                parametrosServicio.actualizarConstanteImpuesto(constantesImpuestos, sesionBean.obtenerSesionDto());
                actualizarListadoConstanteImpuestos();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF341.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.BLOQUES_LISTA_TITULO,
                EnumEtiquetas.BLOQUES_LISTA_ICONO,
                EnumEtiquetas.BLOQUES_LISTA_DESCRIPCION);
    }

    public void eliminarConstanteImpuesto(Integer idConstanteImpuesto) {
        try {
            this.seleccionarConstanteImpuestoPorCodigo(idConstanteImpuesto);
            if (!ComunUtil.esNulo(idConstanteImpuesto)) {
                parametrosServicio.eliminarConstanteImpuesto(constantesImpuestos, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF342.presentarMensaje());
                actualizarListadoConstanteImpuestos();

            } else {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
            }
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void seleccionarConstanteImpuesto(Integer idConstanteImpuesto) {
        try {
            this.seleccionarConstanteImpuestoPorCodigo(idConstanteImpuesto);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.BLOQUES_EDITAR_TITULO,
                EnumEtiquetas.BLOQUES_EDITAR_ICONO,
                EnumEtiquetas.BLOQUES_EDITAR_DESCRIPCION);
    }

    private void seleccionarConstanteImpuestoPorCodigo(Integer idConstanteImpuesto) throws NewviExcepcion {
        this.constantesImpuestos = parametrosServicio.seleccionarConstanteImpuestos(idConstanteImpuesto);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioConstanteImpuesto:opDetalleConstanteImpuesto");
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
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }
    
}
