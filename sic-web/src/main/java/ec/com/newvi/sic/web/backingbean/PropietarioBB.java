/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumSiNo;
import ec.com.newvi.sic.enums.EnumSitActual;
import ec.com.newvi.sic.enums.EnumTenencia;
import ec.com.newvi.sic.enums.EnumTraslacion;
import ec.com.newvi.sic.modelo.Propietario;
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
public class PropietarioBB extends AdminContribuyentesBB {

    private Propietario propietario;
    private List<Propietario> listaPropietarios;
    private List<Propietario> listaPropietariosFiltrados;
    private EnumPantallaMantenimiento pantallaActual;
    private EnumTenencia[] listaTipoTenencia;
    private EnumTraslacion[] listaTipoTraslacion;
    private EnumSitActual[] listaTipoSitActual;
    private EnumSiNo[] listaTipoEscritura;

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public void setListaPropietarios(List<Propietario> listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }

    public List<Propietario> getListaPropietariosFiltrados() {
        return listaPropietariosFiltrados;
    }

    public void setListaPropietariosFiltrados(List<Propietario> listaPropietariosFiltrados) {
        this.listaPropietariosFiltrados = listaPropietariosFiltrados;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public EnumTenencia[] getListaTipoTenencia() {
        return listaTipoTenencia;
    }

    public void setListaTipoTenencia(EnumTenencia[] listaTipoTenencia) {
        this.listaTipoTenencia = listaTipoTenencia;
    }

    public EnumTraslacion[] getListaTipoTraslacion() {
        return listaTipoTraslacion;
    }

    public void setListaTipoTraslacion(EnumTraslacion[] listaTipoTraslacion) {
        this.listaTipoTraslacion = listaTipoTraslacion;
    }

    public EnumSitActual[] getListaTipoSitActual() {
        return listaTipoSitActual;
    }

    public void setListaTipoSitActual(EnumSitActual[] listaTipoSitActual) {
        this.listaTipoSitActual = listaTipoSitActual;
    }

    public EnumSiNo[] getListaTipoEscritura() {
        return listaTipoEscritura;
    }

    public void setListaTipoEscritura(EnumSiNo[] listaTipoEscritura) {
        this.listaTipoEscritura = listaTipoEscritura;
    }
    

    @PostConstruct
    public void init() {
        this.propietario = new Propietario();
        actualizarListadoPropietario();
        listaTipoTenencia = EnumTenencia.values();
        listaTipoTraslacion = EnumTraslacion.values();
        listaTipoEscritura = EnumSiNo.values();
        listaTipoSitActual = EnumSitActual.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_LISTA_TITULO,
                EnumEtiquetas.PROPIETARIO_LISTA_ICONO,
                EnumEtiquetas.PROPIETARIO_LISTA_DESCRIPCION);
    }

    private void actualizarListadoPropietario() {
        listaPropietarios = contribuyentesServicio.consultarPropietario();
    }

    public void crearNuevoPropietario() {
        this.propietario = new Propietario();
        this.propietario.setProEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_NUEVO_TITULO,
                EnumEtiquetas.PROPIETARIO_NUEVO_ICONO,
                EnumEtiquetas.PROPIETARIO_NUEVO_DESCRIPCION);
    }

    public void insertarPropietario() {
        try {
            contribuyentesServicio.generarNuevoPropietario(propietario, sesionBean.obtenerSesionDto());
            actualizarListadoPropietario();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF348.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPropietario() {
        if (!ComunUtil.esNumeroPositivo(this.propietario.getCodPropietario())) {
            insertarPropietario();
        } else {
            try {
                contribuyentesServicio.actualizarPropietario(propietario, sesionBean.obtenerSesionDto());
                actualizarListadoPropietario();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF349.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_LISTA_TITULO,
                EnumEtiquetas.PROPIETARIO_LISTA_ICONO,
                EnumEtiquetas.PROPIETARIO_LISTA_DESCRIPCION);
    }

    public void eliminarPropietario(Integer idPropietario) {
        try {
            this.seleccionarPropietarioPorCodigo(idPropietario);
            if (!ComunUtil.esNulo(propietario)) {
                contribuyentesServicio.eliminarPropietario(propietario, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF350.presentarMensaje());
                actualizarListadoPropietario();

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

    public void seleccionarPropietario(Integer idPropietario) {
        try {
            this.seleccionarPropietarioPorCodigo(idPropietario);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_EDITAR_TITULO,
                EnumEtiquetas.PROPIETARIO_EDITAR_ICONO,
                EnumEtiquetas.PROPIETARIO_EDITAR_DESCRIPCION);
    }

    private void seleccionarPropietarioPorCodigo(Integer idPropietario) throws NewviExcepcion {
        this.propietario = contribuyentesServicio.seleccionarPropietario(idPropietario);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioPropietario:opDetallePropietario");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_LISTA_TITULO,
                EnumEtiquetas.PROPIETARIO_LISTA_ICONO,
                EnumEtiquetas.PROPIETARIO_LISTA_DESCRIPCION);
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
