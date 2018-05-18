/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.CertificadoDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.enums.EnumTipoPredio;
import ec.com.newvi.sic.modelo.CertificacionUsoSuelo;
import ec.com.newvi.sic.servicios.CertificacionUsoSueloServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumFuncionalidad;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;


/**
 *
 * @author Rolando Soria by Excor
 */
@ManagedBean
@ViewScoped
public class CertificacionUsoSueloBB extends AdminSistemaBB {
    
    @EJB
    protected CertificacionUsoSueloServicio certificacionServicio;

    private List<CertificacionUsoSuelo> listaCertificacion;
    private List<CertificacionUsoSuelo> listaCertificacionFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private CertificacionUsoSuelo certificacionSeleccionado;
    private EnumEstadoRegistro[] listaSeleccionEstados;
    private EnumTipoPredio[] listaSeleccionTipoPredio;

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.CERTIFICADO_USO_SUELO);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoCertificaciones();
        listaSeleccionEstados=EnumEstadoRegistro.values();
        listaSeleccionTipoPredio=EnumTipoPredio.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CSS_LISTA_TITULO,
                EnumEtiquetas.CSS_LISTA_ICONO,
                EnumEtiquetas.CSS_LISTA_DESCRIPCION);

    }

    private void actualizarListadoCertificaciones() {
        try {
            this.listaCertificacion = certificacionServicio.obtenerListaCertificaciones(sesionBean.getSesion());
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

    public void crearNuevaCertificacion() {
        this.certificacionSeleccionado = new CertificacionUsoSuelo();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.CSS_NUEVO_TITULO,
                EnumEtiquetas.CSS_NUEVO_ICONO,
                EnumEtiquetas.CSS_NUEVO_DESCRIPCION);
    }
    
    public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioCertificacion:opDetalleCertificacion");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.CSS_NUEVO_TITULO,
            EnumEtiquetas.CSS_NUEVO_ICONO,
            EnumEtiquetas.CSS_NUEVO_DESCRIPCION);
    }
   
    public void insertarCertificacion() {
        try {
            certificacionServicio.generarNuevaCertificacion(certificacionSeleccionado, sesionBean.getSesion());
            actualizarListadoCertificaciones();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF720.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarCertificacion() {
    if (!ComunUtil.esNumeroPositivo(this.certificacionSeleccionado.getCssCod())) {
        insertarCertificacion();
    } else {
        try {
            certificacionServicio.actualizarCertificacion(certificacionSeleccionado, sesionBean.getSesion());
            actualizarListadoCertificaciones();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF722.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF722.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.CSS_LISTA_TITULO,
            EnumEtiquetas.CSS_LISTA_ICONO,
            EnumEtiquetas.CSS_LISTA_DESCRIPCION);
    }
    public void seleccionarCertificacion(Integer idCertificacion) {
    try {
        this.seleccionarCertificacionPorCodigo(idCertificacion);
    } catch (NewviExcepcion e) {
        MensajesFaces.mensajeError(e.getMessage());
    } catch (Exception e) {
        LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
        MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
    establecerTitulo(EnumEtiquetas.CSS_EDITAR_TITULO,
            EnumEtiquetas.CSS_EDITAR_ICONO,
            EnumEtiquetas.CSS_EDITAR_DESCRIPCION);
    }
    private void seleccionarCertificacionPorCodigo(Integer idCertificacion) throws NewviExcepcion {
        this.certificacionSeleccionado=certificacionServicio.seleccionarCertificacion(idCertificacion);
    }

    public List<CertificacionUsoSuelo> getListaCertificacion() {
        return listaCertificacion;
    }
    
    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, String formatoReporte) {
        ArrayList<CertificadoDto> listadoCertificadoImpresion=new ArrayList<>();
        CertificadoDto certificadoImpresion=new CertificadoDto();
        certificadoImpresion.setComercio("X");
        listadoCertificadoImpresion.add(certificadoImpresion);
        return generarReporteCertificado(tipoReporte, obtenerFormatoReporte(formatoReporte), listadoCertificadoImpresion, CertificadoDto.class);
    }

    public void setListaCertificacion(List<CertificacionUsoSuelo> listaCertificacion) {
        this.listaCertificacion = listaCertificacion;
    }

    public List<CertificacionUsoSuelo> getListaCertificacionFiltrado() {
        return listaCertificacionFiltrado;
    }

    public void setListaCertificacionFiltrado(List<CertificacionUsoSuelo> listaCertificacionFiltrado) {
        this.listaCertificacionFiltrado = listaCertificacionFiltrado;
    }

    public CertificacionUsoSuelo getCertificacionSeleccionado() {
        return certificacionSeleccionado;
    }

    public void setCertificacionSeleccionado(CertificacionUsoSuelo certificacionSeleccionado) {
        this.certificacionSeleccionado = certificacionSeleccionado;
    }

    public EnumTipoPredio[] getListaSeleccionTipoPredio() {
        return listaSeleccionTipoPredio;
    }

    public void setListaSeleccionTipoPredio(EnumTipoPredio[] listaSeleccionTipoPredio) {
        this.listaSeleccionTipoPredio = listaSeleccionTipoPredio;
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
    
    public ReporteGenerador.FormatoReporte obtenerFormatoReporte(String tipoReporte) {
        if (tipoReporte.equals("PDF")) {
            return ReporteGenerador.FormatoReporte.PDF;
        } else if (tipoReporte.equals("XLSX")) {
            return ReporteGenerador.FormatoReporte.XLSX;
        } else {
            return ReporteGenerador.FormatoReporte.DOCX;
        }
    }

 
    
}

