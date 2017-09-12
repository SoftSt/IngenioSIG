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
import ec.com.newvi.sic.modelo.Propiedad;
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
public class PropiedadBB extends AdminContribuyentesBB {

    private Propiedad propiedad;
    private List<Propiedad> listaPropiedad;
    private List<Propiedad> listaPropiedadFiltrados;
    private EnumPantallaMantenimiento pantallaActual;
    private EnumTenencia[] listaTipoTenencia;
    private EnumTraslacion[] listaTipoTraslacion;
    private EnumSitActual[] listaTipoSitActual;
    private EnumSiNo[] listaTipoEscritura;

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public List<Propiedad> getListaPropiedad() {
        return listaPropiedad;
    }

    public void setListaPropiedad(List<Propiedad> listaPropiedad) {
        this.listaPropiedad = listaPropiedad;
    }

    public List<Propiedad> getListaPropiedadFiltrados() {
        return listaPropiedadFiltrados;
    }

    public void setListaPropiedadFiltrados(List<Propiedad> listaPropiedadFiltrados) {
        this.listaPropiedadFiltrados = listaPropiedadFiltrados;
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
        this.propiedad = new Propiedad();
        actualizarListadoPropiedad();
        listaTipoTenencia = EnumTenencia.values();
        listaTipoTraslacion = EnumTraslacion.values();
        listaTipoEscritura = EnumSiNo.values();
        listaTipoSitActual = EnumSitActual.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_LISTA_TITULO,
                EnumEtiquetas.PROPIETARIO_LISTA_ICONO,
                EnumEtiquetas.PROPIETARIO_LISTA_DESCRIPCION);
    }

    private void actualizarListadoPropiedad() {
        listaPropiedad = contribuyentesServicio.consultarPropiedad();
    }

    public void crearNuevoPropiedad() {
        this.propiedad = new Propiedad();
        this.propiedad.setProEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_NUEVO_TITULO,
                EnumEtiquetas.PROPIETARIO_NUEVO_ICONO,
                EnumEtiquetas.PROPIETARIO_NUEVO_DESCRIPCION);
    }

    public void insertarPropiedad() {
        try {
            contribuyentesServicio.generarNuevoPropiedad(propiedad, sesionBean.getSesion());
            actualizarListadoPropiedad();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF348.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPropiedad() {
        if (!ComunUtil.esNumeroPositivo(this.propiedad.getCodPropiedad())) {
            insertarPropiedad();
        } else {
            try {
                contribuyentesServicio.actualizarPropiedad(propiedad, sesionBean.getSesion());
                actualizarListadoPropiedad();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF349.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_LISTA_TITULO,
                EnumEtiquetas.PROPIETARIO_LISTA_ICONO,
                EnumEtiquetas.PROPIETARIO_LISTA_DESCRIPCION);
    }

    public void eliminarPropiedad(Integer idPropiedad) {
        try {
            this.seleccionarPropiedadPorCodigo(idPropiedad);
            if (!ComunUtil.esNulo(propiedad)) {
                contribuyentesServicio.eliminarPropiedad(propiedad, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF350.presentarMensaje());
                actualizarListadoPropiedad();

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

    public void seleccionarPropiedad(Integer idPropiedad) {
        try {
            this.seleccionarPropiedadPorCodigo(idPropiedad);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PROPIETARIO_EDITAR_TITULO,
                EnumEtiquetas.PROPIETARIO_EDITAR_ICONO,
                EnumEtiquetas.PROPIETARIO_EDITAR_DESCRIPCION);
    }

    private void seleccionarPropiedadPorCodigo(Integer idPropiedad) throws NewviExcepcion {
        this.propiedad = contribuyentesServicio.seleccionarPropiedad(idPropiedad);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioPropiedad:opDetallePropiedad");
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
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }

}
