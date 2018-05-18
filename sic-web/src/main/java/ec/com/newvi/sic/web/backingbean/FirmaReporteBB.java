/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.FirmaReporte;
import ec.com.newvi.sic.servicios.FirmaReporteServicio;
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
public class FirmaReporteBB extends AdminSistemaBB {
    
    @EJB
    protected FirmaReporteServicio firmaReporteServicio;

    private List<FirmaReporte> listaFirmaReporte;
    private List<FirmaReporte> listaFirmaReporteFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private FirmaReporte firmaReporteSeleccionado;
    private EnumEstadoRegistro[] listaSeleccionEstados;

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.FRIMA_REPORTE);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoFirmasReporte();
        listaSeleccionEstados=EnumEstadoRegistro.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FRR_LISTA_TITULO,
                EnumEtiquetas.FRR_LISTA_ICONO,
                EnumEtiquetas.FRR_LISTA_DESCRIPCION);

    }

    private void actualizarListadoFirmasReporte() {
        try {
            this.listaFirmaReporte = firmaReporteServicio.obtenerListaFirmaReporte(sesionBean.getSesion());
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

    public void crearNuevaFirmaReporte() {
        this.firmaReporteSeleccionado = new FirmaReporte();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FRR_NUEVO_TITULO,
                EnumEtiquetas.FRR_NUEVO_ICONO,
                EnumEtiquetas.FRR_NUEVO_DESCRIPCION);
    }
    
    public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioFirmaReporte:opDetalleFirmaReporte");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.FRR_NUEVO_TITULO,
            EnumEtiquetas.FRR_NUEVO_ICONO,
            EnumEtiquetas.FRR_NUEVO_DESCRIPCION);
    }
   
    public void insertarFirmaReporte() {
        try {
            firmaReporteServicio.generarNuevaFirmaReporte(firmaReporteSeleccionado, sesionBean.getSesion());
            actualizarListadoFirmasReporte();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF714.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarFirmaReporte() {
    if (!ComunUtil.esNumeroPositivo(this.firmaReporteSeleccionado.getFrrCod())) {
        insertarFirmaReporte();
    } else {
        try {
            firmaReporteServicio.actualizarFirmaReporte(firmaReporteSeleccionado, sesionBean.getSesion());
            actualizarListadoFirmasReporte();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF716.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF716.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.FRR_LISTA_TITULO,
            EnumEtiquetas.FRR_LISTA_ICONO,
            EnumEtiquetas.FRR_LISTA_DESCRIPCION);
    }
    public void seleccionarFirmaReporte(Integer idFirmaReporte) {
    try {
        this.seleccionaridFirmaReportePorCodigo(idFirmaReporte);
    } catch (NewviExcepcion e) {
        MensajesFaces.mensajeError(e.getMessage());
    } catch (Exception e) {
        LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
        MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
    establecerTitulo(EnumEtiquetas.SMB_EDITAR_TITULO,
            EnumEtiquetas.SMB_EDITAR_ICONO,
            EnumEtiquetas.SMB_EDITAR_DESCRIPCION);
    }
    private void seleccionaridFirmaReportePorCodigo(Integer codFirmaReporte) throws NewviExcepcion {
        this.firmaReporteSeleccionado=firmaReporteServicio.seleccionarFirmaReporte(codFirmaReporte);
    }

    public List<FirmaReporte> getListaFirmaReporte() {
        return listaFirmaReporte;
    }

    public void setListaFirmaReporte(List<FirmaReporte> listaFirmaReporte) {
        this.listaFirmaReporte = listaFirmaReporte;
    }

    public List<FirmaReporte> getListaFirmaReporteFiltrado() {
        return listaFirmaReporteFiltrado;
    }

    public void setListaFirmaReporteFiltrado(List<FirmaReporte> listaFirmaReporteFiltrado) {
        this.listaFirmaReporteFiltrado = listaFirmaReporteFiltrado;
    }

    public FirmaReporte getFirmaReporteSeleccionado() {
        return firmaReporteSeleccionado;
    }

    public void setFirmaReporteSeleccionado(FirmaReporte firmaReporteSeleccionado) {
        this.firmaReporteSeleccionado = firmaReporteSeleccionado;
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

