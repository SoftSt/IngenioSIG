/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.RegulacionMunicipal;
import ec.com.newvi.sic.servicios.RegulacionMunicipalServicio;
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
public class RegulacionMunicipalBB extends AdminSistemaBB {

    @EJB
    protected RegulacionMunicipalServicio regulacionMunicipalServicio;
    
    private List<RegulacionMunicipal> listaIRM;
    private List<RegulacionMunicipal> listaIRMFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private RegulacionMunicipal irmSeleccionado;
    
    private EnumEstadoRegistro[] listaSeleccionEstados;
    
    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.IRM);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoRegulaciones();
         listaSeleccionEstados=EnumEstadoRegistro.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.IRM_LISTA_TITULO,
                EnumEtiquetas.IRM_LISTA_ICONO,
                EnumEtiquetas.IRM_LISTA_DESCRIPCION);

    }

    private void actualizarListadoRegulaciones() {
        try {
            this.listaIRM = regulacionMunicipalServicio.obtenerListaIRM(sesionBean.getSesion());
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

    public void crearNuevaRegulacionMunicipal() {
        this.irmSeleccionado = new RegulacionMunicipal();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.IRM_NUEVO_TITULO,
                EnumEtiquetas.IRM_NUEVO_ICONO,
                EnumEtiquetas.IRM_NUEVO_DESCRIPCION);
    }
    
     public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioRegulacion:opDetalleRegulacion");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.IRM_NUEVO_TITULO,
            EnumEtiquetas.IRM_NUEVO_ICONO,
            EnumEtiquetas.IRM_NUEVO_DESCRIPCION);
    }
   
    public void insertarRegulacion() {
        try {
            regulacionMunicipalServicio.generarNuevoIRM(irmSeleccionado, sesionBean.getSesion());
            actualizarListadoRegulaciones();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF717.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarRegulacion() {
    if (!ComunUtil.esNumeroPositivo(this.irmSeleccionado.getCodIRM())) {
        insertarRegulacion();
    } else {
        try {
            regulacionMunicipalServicio.actualizarIRM(irmSeleccionado, sesionBean.getSesion());
            actualizarListadoRegulaciones();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF719.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF719.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.IRM_LISTA_TITULO,
            EnumEtiquetas.IRM_LISTA_ICONO,
            EnumEtiquetas.IRM_LISTA_DESCRIPCION);
    }
    public void seleccionarRegulacion(Integer idIrm) {
    try {
        this.seleccionarRegulacionPorCodigo(idIrm);
    } catch (NewviExcepcion e) {
        MensajesFaces.mensajeError(e.getMessage());
    } catch (Exception e) {
        LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
        MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
    establecerTitulo(EnumEtiquetas.IRM_EDITAR_TITULO,
            EnumEtiquetas.IRM_EDITAR_ICONO,
            EnumEtiquetas.IRM_EDITAR_DESCRIPCION);
    }
    private void seleccionarRegulacionPorCodigo(Integer codIrm) throws NewviExcepcion {
        this.irmSeleccionado=regulacionMunicipalServicio.seleccionarRegulacion(codIrm);
    }

    public List<RegulacionMunicipal> getListaIRM() {
        return listaIRM;
    }

    public void setListaIRM(List<RegulacionMunicipal> listaIRM) {
        this.listaIRM = listaIRM;
    }

    public RegulacionMunicipal getIrmSeleccionado() {
        return irmSeleccionado;
    }

    public void setIrmSeleccionado(RegulacionMunicipal irmSeleccionado) {
        this.irmSeleccionado = irmSeleccionado;
    }
    
    public List<RegulacionMunicipal> getListaIRMFiltrado() {
        return listaIRMFiltrado;
    }

    public void setListaIRMFiltrado(List<RegulacionMunicipal> listaIRMFiltrado) {
        this.listaIRMFiltrado = listaIRMFiltrado;
    }

    public RegulacionMunicipal getIRMSeleccionado() {
        return irmSeleccionado;
    }

    public void setIRMSeleccionado(RegulacionMunicipal irmSeleccionado) {
        this.irmSeleccionado = irmSeleccionado;
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
