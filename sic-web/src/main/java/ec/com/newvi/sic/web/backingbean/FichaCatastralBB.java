/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.ValidacionUtils;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés
 */

@ManagedBean
@ViewScoped

public class FichaCatastralBB extends AdminFichaCatastralBB {

    private Predios predio;
    private List<Predios> listaPredios;
    private List<Predios> listaPrediosFiltrados;
    private EnumPantallaMantenimiento pantallaActual;

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }

    public List<Predios> getListaPredios() {
        return listaPredios;
    }

    public void setListaPredios(List<Predios> listaPredios) {
        this.listaPredios = listaPredios;
    }

    public List<Predios> getListaPrediosFiltrados() {
        return listaPrediosFiltrados;
    }

    public void setListaPrediosFiltrados(List<Predios> listaPrediosFiltrados) {
        this.listaPrediosFiltrados = listaPrediosFiltrados;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
    this.predio = new Predios();
    actualizarListadoPredios();
     conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);
    }
    
        private void actualizarListadoPredios() {
        listaPredios = catastroServicio.consultarPredios();
    }

    public void crearNuevoPredio() {
        this.predio = new Predios();
        this.predio.setCatEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_NUEVO_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_NUEVO_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_NUEVO_DESCRIPCION);
    }

    public void insertarPredio() {
        try {
            catastroServicio.generarNuevoPredio(predio, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF330.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF330.presentarMensaje());
            actualizarListadoPredios();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPredio() {
        if (!ComunUtil.esNumeroPositivo(this.predio.getCodCatastral())) {
            insertarPredio();
        } else {
            try {
                catastroServicio.actualizarPredio(predio, sesionBean.obtenerSesionDto());
                actualizarListadoPredios();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF331.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF331.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);
    }

    public void eliminarPredio(Integer idPredio) {
        try {
            this.seleccionarPredioPorCodigo(idPredio);
            if (!ComunUtil.esNulo(predio)) {
                catastroServicio.eliminarPredio(predio, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF332.presentarMensaje());
                actualizarListadoPredios();

            } else {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
            }
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void seleccionarPredio(Integer idPredio) {
        try {
            this.seleccionarPredioPorCodigo(idPredio);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_EDITAR_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_EDITAR_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_EDITAR_DESCRIPCION);
    }

    private void seleccionarPredioPorCodigo(Integer idPredio) throws NewviExcepcion {
        this.predio = catastroServicio.seleccionarPredio(idPredio);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioFichaCatastral:opDetalleFichaCatastral");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);
    }

    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }

    public Boolean esPantallaActual(String pantallaEsperada) {
        try {
            return this.pantallaActual.equals(EnumPantallaMantenimiento.obtenerPantallaPorNombre(pantallaEsperada));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }

    public void validarEmail(FacesContext arg0, UIComponent arg1, Object arg2) throws NewviExcepcion {
        String usuEmail = arg2.toString();
        if (!ValidacionUtils.validarCorreoElectronico(usuEmail.trim())) {
            throw ValidacionUtils.lanzarExcepcionValidacion(EnumNewviExcepciones.ERR251);
        }
    }
}