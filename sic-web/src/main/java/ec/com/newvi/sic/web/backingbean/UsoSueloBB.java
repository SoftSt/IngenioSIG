/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.UsoSuelo;
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
public class UsoSueloBB extends AdminSistemaBB {
    
    @EJB
    protected UsoSueloServicio usoSueloServicio;
    
    private List<UsoSuelo> listaUsoSuelo;
    private List<UsoSuelo> listaUsoSueloFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private UsoSuelo usoSueloSeleccionado;
    private EnumEstadoRegistro[] listaSeleccionEstados;
    
  

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.USO_SUELO);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoUsoSuelos();
        listaSeleccionEstados=EnumEstadoRegistro.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.USS_LISTA_TITULO,
                EnumEtiquetas.USS_LISTA_ICONO,
                EnumEtiquetas.USS_LISTA_DESCRIPCION);

    }

    private void actualizarListadoUsoSuelos() {
        try {
            this.listaUsoSuelo = usoSueloServicio.obtenerListaUsoSuelo(sesionBean.getSesion());
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

    public void crearNuevoUsoSuelo() {
        this.usoSueloSeleccionado = new UsoSuelo();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.USS_NUEVO_TITULO,
                EnumEtiquetas.USS_NUEVO_ICONO,
                EnumEtiquetas.USS_NUEVO_DESCRIPCION);
    }
    
    public void cancelarEdicion() {
    WebUtils.obtenerContextoPeticion().reset("formularioUsoSuelo:opDetalleUsoSuelo");
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.USS_NUEVO_TITULO,
            EnumEtiquetas.USS_NUEVO_ICONO,
            EnumEtiquetas.USS_NUEVO_DESCRIPCION);
    }
   
    public void insertarUsoSuelo() {
        try {
            usoSueloServicio.generarNuevoUsoSuelo(usoSueloSeleccionado, sesionBean.getSesion());
            actualizarListadoUsoSuelos();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF705.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarUsoSuelo() {
    if (!ComunUtil.esNumeroPositivo(this.usoSueloSeleccionado.getUssCod())) {
        insertarUsoSuelo();
    } else {
        try {
            usoSueloServicio.actualizarUsoSuelo(usoSueloSeleccionado, sesionBean.getSesion());
            actualizarListadoUsoSuelos();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF707.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF707.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
    establecerTitulo(EnumEtiquetas.USS_LISTA_TITULO,
            EnumEtiquetas.USS_LISTA_ICONO,
            EnumEtiquetas.USS_LISTA_DESCRIPCION);
    }
    public void seleccionarUsoSuelo(Integer idUsoSuelo) {
    try {
        this.seleccionarUsoSueloPorCodigo(idUsoSuelo);
    } catch (NewviExcepcion e) {
        MensajesFaces.mensajeError(e.getMessage());
    } catch (Exception e) {
        LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
        MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
    }
    conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
    establecerTitulo(EnumEtiquetas.USS_EDITAR_TITULO,
            EnumEtiquetas.USS_EDITAR_ICONO,
            EnumEtiquetas.USS_EDITAR_DESCRIPCION);
    }
    private void seleccionarUsoSueloPorCodigo(Integer codUsoSuelo) throws NewviExcepcion {
        this.usoSueloSeleccionado=usoSueloServicio.seleccionarUsoSuelo(codUsoSuelo);
    }

    public List<UsoSuelo> getListaUsoSuelo() {
        return listaUsoSuelo;
    }

    public void setListaUsoSuelo(List<UsoSuelo> listaUsoSuelo) {
        this.listaUsoSuelo = listaUsoSuelo;
    }

    public List<UsoSuelo> getListaUsoSueloFiltrado() {
        return listaUsoSueloFiltrado;
    }

    public void setListaUsoSueloFiltrado(List<UsoSuelo> listaUsoSueloFiltrado) {
        this.listaUsoSueloFiltrado = listaUsoSueloFiltrado;
    }

    public UsoSuelo getUsoSueloSeleccionado() {
        return usoSueloSeleccionado;
    }

    public void setUsoSueloSeleccionado(UsoSuelo usoSueloSeleccionado) {
        this.usoSueloSeleccionado = usoSueloSeleccionado;
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

