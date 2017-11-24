/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
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
public class DescuentosBB extends AdminTesoreriaBB {
    
    private CatConConstantesdescuentos catConConstantesdescuentos;
    private List<CatConConstantesdescuentos> listacatConConstantesdescuentos;    
    private EnumPantallaMantenimiento pantallaActual;

    public CatConConstantesdescuentos getCatConConstantesdescuentos() {
        return catConConstantesdescuentos;
    }

    public void setCatConConstantesdescuentos(CatConConstantesdescuentos catConConstantesdescuentos) {
        this.catConConstantesdescuentos = catConConstantesdescuentos;
    }

    public List<CatConConstantesdescuentos> getListacatConConstantesdescuentos() {
        return listacatConConstantesdescuentos;
    }

    public void setListacatConConstantesdescuentos(List<CatConConstantesdescuentos> listacatConConstantesdescuentos) {
        this.listacatConConstantesdescuentos = listacatConConstantesdescuentos;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }
    
    @PostConstruct
    public void init() {
        this.catConConstantesdescuentos = new CatConConstantesdescuentos();
        actualizarListadoDescuentos();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DESCUENTO_LISTA_TITULO, 
                EnumEtiquetas.DESCUENTO_LISTA_ICONO,
                EnumEtiquetas.DESCUENTO_LISTA_DESCRIPCION);
    }

    private void actualizarListadoDescuentos() {
        listacatConConstantesdescuentos = parametrosTesoreria.consultarDescuentos();
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
    
    public void seleccionarDescuento(Integer idDescuento) {
        try {
            this.seleccionarDescuentoPorCodigo(idDescuento);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.DESCUENTO_EDITAR_TITULO,
                EnumEtiquetas.DESCUENTO_EDITAR_ICONO,
                EnumEtiquetas.DESCUENTO_EDITAR_DESCRIPCION);
    }

    private void seleccionarDescuentoPorCodigo(Integer idDescuento) throws NewviExcepcion {
        this.catConConstantesdescuentos = parametrosTesoreria.seleccionarDescuento(idDescuento);
    }
    
    public void actualizarDescuento() {
        if (!ComunUtil.esNumeroPositivo(this.catConConstantesdescuentos.getCodConstantesdescuentos())) {
            insertarDescuento();
        } else {
            try {
                parametrosTesoreria.actualizarDescuento(catConConstantesdescuentos, sesionBean.getSesion());
                actualizarListadoDescuentos();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF700.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF700.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DESCUENTO_LISTA_TITULO,
                EnumEtiquetas.DESCUENTO_LISTA_ICONO,
                EnumEtiquetas.DESCUENTO_LISTA_DESCRIPCION);
    }
    
    public void insertarDescuento() {
        try {
            parametrosTesoreria.generarNuevoDescuentos(catConConstantesdescuentos, sesionBean.getSesion());
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
        WebUtils.obtenerContextoPeticion().reset("formularioDescuentos:opDescuentos");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DESCUENTO_LISTA_TITULO,
                EnumEtiquetas.DESCUENTO_LISTA_ICONO,
                EnumEtiquetas.DESCUENTO_LISTA_DESCRIPCION);
    }
}
