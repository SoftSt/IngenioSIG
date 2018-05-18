/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Tipologia;
import ec.com.newvi.sic.modelo.UsoSuelo;
import ec.com.newvi.sic.servicios.TipologiaServicio;
import ec.com.newvi.sic.servicios.UsoSueloServicio;
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
public class TipologiaBB extends AdminSistemaBB {
    
    @EJB
    protected TipologiaServicio tipologiaServicio;
    
    private List<Tipologia> listaTipologia;
    private List<Tipologia> listaTipologiaFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private Tipologia tipologiaSeleccionado;
    private EnumEstadoRegistro[] listaSeleccionEstados;
    
    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.TIPOLOGIA);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoTipologias();
        listaSeleccionEstados=EnumEstadoRegistro.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.TPL_LISTA_TITULO,
                EnumEtiquetas.TPL_LISTA_ICONO,
                EnumEtiquetas.TPL_LISTA_DESCRIPCION);

    }
    
    
    private void actualizarListadoTipologias() {
        try {
            this.listaTipologia = tipologiaServicio.obtenerListaTipologia(sesionBean.getSesion());
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

    public void crearNuevaTipologia() {
        this.tipologiaSeleccionado = new Tipologia();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.TPL_NUEVO_TITULO,
                EnumEtiquetas.TPL_NUEVO_ICONO,
                EnumEtiquetas.TPL_NUEVO_DESCRIPCION);
    }
    
    public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioTipologia:opDetalleTipologia");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.TPL_NUEVO_TITULO,
            EnumEtiquetas.TPL_NUEVO_ICONO,
            EnumEtiquetas.TPL_NUEVO_DESCRIPCION);
    }
   
    public void insertarTipologia() {
        try {
            tipologiaServicio.generarNuevaTipologia(tipologiaSeleccionado, sesionBean.getSesion());
            actualizarListadoTipologias();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF708.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarTipologia() {
    if (!ComunUtil.esNumeroPositivo(this.tipologiaSeleccionado.getTplCod())) {
        insertarTipologia();
    } else {
        try {
            tipologiaServicio.actualizarTipologia(tipologiaSeleccionado, sesionBean.getSesion());
            actualizarListadoTipologias();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF710.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF710.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.TPL_LISTA_TITULO,
            EnumEtiquetas.TPL_LISTA_ICONO,
            EnumEtiquetas.TPL_LISTA_DESCRIPCION);
    }
    public void seleccionarTipologia(Integer idTipologia) {
    try {
        this.seleccionarTipologiaPorCodigo(idTipologia);
    } catch (NewviExcepcion e) {
        MensajesFaces.mensajeError(e.getMessage());
    } catch (Exception e) {
        LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
        MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
    establecerTitulo(EnumEtiquetas.TPL_EDITAR_TITULO,
            EnumEtiquetas.TPL_EDITAR_ICONO,
            EnumEtiquetas.TPL_EDITAR_DESCRIPCION);
    }
    private void seleccionarTipologiaPorCodigo(Integer codTipologia) throws NewviExcepcion {
        this.tipologiaSeleccionado=tipologiaServicio.seleccionarTipologia(codTipologia);
    }

    public List<Tipologia> getListaTipologia() {
        return listaTipologia;
    }

    public void setListaTipologia(List<Tipologia> listaTipologia) {
        this.listaTipologia = listaTipologia;
    }

    public List<Tipologia> getListaTipologiaFiltrado() {
        return listaTipologiaFiltrado;
    }

    public void setListaTipologiaFiltrado(List<Tipologia> listaTipologiaFiltrado) {
        this.listaTipologiaFiltrado = listaTipologiaFiltrado;
    }

    public Tipologia getTipologiaSeleccionado() {
        return tipologiaSeleccionado;
    }

    public void setTipologiaSeleccionado(Tipologia tipologiaSeleccionado) {
        this.tipologiaSeleccionado = tipologiaSeleccionado;
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

