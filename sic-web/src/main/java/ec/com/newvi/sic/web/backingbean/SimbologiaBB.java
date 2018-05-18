/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Simbologia;
import ec.com.newvi.sic.modelo.Tipologia;
import ec.com.newvi.sic.modelo.UsoSuelo;
import ec.com.newvi.sic.modelo.Zonificacion;
import ec.com.newvi.sic.servicios.SimbologiaServicio;
import ec.com.newvi.sic.servicios.TipologiaServicio;
import ec.com.newvi.sic.servicios.UsoSueloServicio;
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
public class SimbologiaBB extends AdminSistemaBB {
    
    @EJB
    protected SimbologiaServicio simbologiaServicio;
    
    @EJB
    protected UsoSueloServicio usoSueloServicio;
    
    @EJB
    protected ZonificacionServicio zonificacionServicio;
    
    @EJB
    protected TipologiaServicio tipologiaServicio;

    private List<Simbologia> listaSimbologia;
    private List<Simbologia> listaSimbologiaFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private Simbologia simbologiaSeleccionado;
    private EnumEstadoRegistro[] listaSeleccionEstados;
    
    private List<Tipologia> listaTipologia;
    private List<Zonificacion> listaZonificacion;
    private List<UsoSuelo> listaUsoSuelo;

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.SIMBOLOGIA);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoSimbologias();
        cargarListaZonificacion();
        cargarListaUsoSuelo();
        cargarListaTipologias();
        listaSeleccionEstados=EnumEstadoRegistro.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.SMB_LISTA_TITULO,
                EnumEtiquetas.SMB_LISTA_ICONO,
                EnumEtiquetas.SMB_LISTA_DESCRIPCION);

    }
    
    private void cargarListaZonificacion() {
        this.listaZonificacion=zonificacionServicio.cargarZonificaciones();
    }
    
    private void cargarListaUsoSuelo() {
        this.listaUsoSuelo=usoSueloServicio.cargarUsosSuelo();
    }

    private void cargarListaTipologias() {
        this.listaTipologia=tipologiaServicio.cargarTipologias();
    }
    private void actualizarListadoSimbologias() {
        try {
            this.listaSimbologia = simbologiaServicio.obtenerListaSimbologia(sesionBean.getSesion());
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

    public void crearNuevaSimbologia() {
        this.simbologiaSeleccionado = new Simbologia();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.SMB_NUEVO_TITULO,
                EnumEtiquetas.SMB_NUEVO_ICONO,
                EnumEtiquetas.SMB_NUEVO_DESCRIPCION);
    }
    
    public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioSimbologia:opDetalleSimbologia");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.SMB_NUEVO_TITULO,
            EnumEtiquetas.SMB_NUEVO_ICONO,
            EnumEtiquetas.SMB_NUEVO_DESCRIPCION);
    }
   
    public void insertarSimbologia() {
        try {
            simbologiaServicio.generarNuevaSimbologia(simbologiaSeleccionado, sesionBean.getSesion());
            actualizarListadoSimbologias();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF711.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarSimbologia() {
    if (!ComunUtil.esNumeroPositivo(this.simbologiaSeleccionado.getSmbCod())) {
        insertarSimbologia();
    } else {
        try {
            simbologiaServicio.actualizarSimbologia(simbologiaSeleccionado, sesionBean.getSesion());
            actualizarListadoSimbologias();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF713.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF713.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.SMB_LISTA_TITULO,
            EnumEtiquetas.SMB_LISTA_ICONO,
            EnumEtiquetas.SMB_LISTA_DESCRIPCION);
    }
    public void seleccionarSimbologia(Integer idSimbologia) {
    try {
        this.seleccionarSimbologiaPorCodigo(idSimbologia);
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
    private void seleccionarSimbologiaPorCodigo(Integer codSimbologia) throws NewviExcepcion {
        this.simbologiaSeleccionado=simbologiaServicio.seleccionarSimbologia(codSimbologia);
    }

    public List<Simbologia> getListaSimbologia() {
        return listaSimbologia;
    }

    public void setListaSimbologia(List<Simbologia> listaSimbologia) {
        this.listaSimbologia = listaSimbologia;
    }

    public List<Simbologia> getListaSimbologiaFiltrado() {
        return listaSimbologiaFiltrado;
    }

    public void setListaSimbologiaFiltrado(List<Simbologia> listaSimbologiaFiltrado) {
        this.listaSimbologiaFiltrado = listaSimbologiaFiltrado;
    }

    public Simbologia getSimbologiaSeleccionado() {
        return simbologiaSeleccionado;
    }

    public void setSimbologiaSeleccionado(Simbologia simbologiaSeleccionado) {
        this.simbologiaSeleccionado = simbologiaSeleccionado;
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

    public List<Tipologia> getListaTipologia() {
        return listaTipologia;
    }

    public void setListaTipologia(List<Tipologia> listaTipologia) {
        this.listaTipologia = listaTipologia;
    }

    public List<UsoSuelo> getListaUsoSuelo() {
        return listaUsoSuelo;
    }

    public void setListaUsoSuelo(List<UsoSuelo> listaUsoSuelo) {
        this.listaUsoSuelo = listaUsoSuelo;
    }

    public List<Zonificacion> getListaZonificacion() {
        return listaZonificacion;
    }

    public void setListaZonificacion(List<Zonificacion> listaZonificacion) {
        this.listaZonificacion = listaZonificacion;
    }

}

