/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.CatConConstantesinteresmora;
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
 * @author NEWVI
 */
@ManagedBean
@ViewScoped
public class MultasBB extends AdminTesoreriaBB {
    
    private CatConConstantesinteresmora constantesInteresMora;
    private List<CatConConstantesinteresmora> listaConstantesInteresMora;    
    private EnumPantallaMantenimiento pantallaActual;

    public CatConConstantesinteresmora getConstantesInteresMora() {
        return constantesInteresMora;
    }

    public void setConstantesInteresMora(CatConConstantesinteresmora constantesInteresMora) {
        this.constantesInteresMora = constantesInteresMora;
    }

    public List<CatConConstantesinteresmora> getListaConstantesInteresMora() {
        return listaConstantesInteresMora;
    }

    public void setListaConstantesInteresMora(List<CatConConstantesinteresmora> listaConstantesInteresMora) {
        this.listaConstantesInteresMora = listaConstantesInteresMora;
    }
    
    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }
    
    @PostConstruct
    public void init() {
        this.constantesInteresMora = new CatConConstantesinteresmora();
        actualizarListadoDescuentos();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.MULTA_LISTA_TITULO, 
                EnumEtiquetas.MULTA_LISTA_ICONO,
                EnumEtiquetas.MULTA_LISTA_DESCRIPCION);
    }

    private void actualizarListadoDescuentos() {
        listaConstantesInteresMora = parametrosTesoreria.consultarMultas();
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
    
    public void seleccionarMulta(Integer idMulta) {
        try {
            this.seleccionarMultaPorCodigo(idMulta);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.MULTA_EDITAR_TITULO,
                EnumEtiquetas.MULTA_EDITAR_ICONO,
                EnumEtiquetas.MULTA_EDITAR_DESCRIPCION);
    }

    private void seleccionarMultaPorCodigo(Integer idMulta) throws NewviExcepcion {
        this.constantesInteresMora = parametrosTesoreria.seleccionarMulta(idMulta);
    }
    
    public void actualizarMulta() {
        if (!ComunUtil.esNumeroPositivo(this.constantesInteresMora.getCodInteresmora())) {
            insertarMulta();
        } else {
            try {
                parametrosTesoreria.actualizarDescuento(constantesInteresMora, sesionBean.getSesion());
                actualizarListadoDescuentos();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF701.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF701.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.MULTA_LISTA_TITULO,
                EnumEtiquetas.MULTA_LISTA_ICONO,
                EnumEtiquetas.MULTA_LISTA_DESCRIPCION);
    }
    
    public void insertarMulta() {
        try {
            parametrosTesoreria.generarNuevaMulta(constantesInteresMora, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF302.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF302.presentarMensaje());
            actualizarListadoDescuentos();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    
    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioMultas:opMultas");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.MULTA_LISTA_TITULO,
                EnumEtiquetas.MULTA_LISTA_ICONO,
                EnumEtiquetas.MULTA_LISTA_DESCRIPCION);
    }
}
