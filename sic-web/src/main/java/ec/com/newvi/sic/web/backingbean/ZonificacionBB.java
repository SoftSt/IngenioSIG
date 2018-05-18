/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Zonificacion;
import ec.com.newvi.sic.servicios.ZonificacionServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumFuncionalidad;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;


/**
 *
 * @author Rolando Soria by Excor
 */
@ManagedBean
@ViewScoped
public class ZonificacionBB extends AdminSistemaBB {
    
    @EJB
    protected ZonificacionServicio zonificacionServicio;

    private List<Zonificacion> listaZonificacion;
    private List<Zonificacion> listaZonificacionFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private Zonificacion zonificacionSeleccionado;
    private EnumEstadoRegistro[] listaSeleccionEstados;

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.ZONIFICACION);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoZonas();
        listaSeleccionEstados=EnumEstadoRegistro.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.ZNF_LISTA_TITULO,
                EnumEtiquetas.ZNF_LISTA_ICONO,
                EnumEtiquetas.ZNF_LISTA_DESCRIPCION);

    }

    private void actualizarListadoZonas() {
        try {
            this.listaZonificacion = zonificacionServicio.obtenerListaZonificacion(sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
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

    public void crearNuevaZonificacion() {
        this.zonificacionSeleccionado = new Zonificacion();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.ZNF_NUEVO_TITULO,
                EnumEtiquetas.ZNF_NUEVO_ICONO,
                EnumEtiquetas.ZNF_NUEVO_DESCRIPCION);
    }
    
    public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioZonificacion:opDetalleZonificacion");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.ZNF_NUEVO_TITULO,
            EnumEtiquetas.ZNF_NUEVO_ICONO,
            EnumEtiquetas.ZNF_NUEVO_DESCRIPCION);
    }
   
    public void insertarZonificacion() {
        try {
            zonificacionServicio.generarNuevaZonificacion(zonificacionSeleccionado, sesionBean.getSesion());
            actualizarListadoZonas();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF702.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarZonificacion() {
    if (!ComunUtil.esNumeroPositivo(this.zonificacionSeleccionado.getZnfCod())) {
        insertarZonificacion();
    } else {
        try {
            zonificacionServicio.actualizarZonificacion(zonificacionSeleccionado, sesionBean.getSesion());
            actualizarListadoZonas();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF704.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF704.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.ZNF_LISTA_TITULO,
            EnumEtiquetas.ZNF_LISTA_ICONO,
            EnumEtiquetas.ZNF_LISTA_DESCRIPCION);
    }
    public void seleccionarZonificacion(Integer idZona) {
    try {
        this.seleccionarZonificacionPorCodigo(idZona);
    } catch (NewviExcepcion e) {
        MensajesFaces.mensajeError(e.getMessage());
    } catch (Exception e) {
        LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
        MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
    establecerTitulo(EnumEtiquetas.ZNF_EDITAR_TITULO,
            EnumEtiquetas.ZNF_EDITAR_ICONO,
            EnumEtiquetas.ZNF_EDITAR_DESCRIPCION);
    }
    private void seleccionarZonificacionPorCodigo(Integer codZona) throws NewviExcepcion {
        this.zonificacionSeleccionado=zonificacionServicio.seleccionarZonificacion(codZona);
    }

    public List<Zonificacion> getListaZonificacion() {
        return listaZonificacion;
    }

    public void setListaZonificacion(List<Zonificacion> listaZonificacion) {
        this.listaZonificacion = listaZonificacion;
    }

    public List<Zonificacion> getListaZonificacionFiltrado() {
        return listaZonificacionFiltrado;
    }

    public void setListaZonificacionFiltrado(List<Zonificacion> listaZonificacionFiltrado) {
        this.listaZonificacionFiltrado = listaZonificacionFiltrado;
    }

    public Zonificacion getZonificacionSeleccionado() {
        return zonificacionSeleccionado;
    }

    public void setZonificacionSeleccionado(Zonificacion zonificacionSeleccionado) {
        this.zonificacionSeleccionado = zonificacionSeleccionado;
    }
    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    } 

    public EnumEstadoRegistro[] getListaSeleccionEstados() {
        return listaSeleccionEstados;
    }

    public void setListaSeleccionEstados(EnumEstadoRegistro[] listaSeleccionEstados) {
        this.listaSeleccionEstados = listaSeleccionEstados;
    }

 
    
}

