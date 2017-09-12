/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumTipoPersoneria;
import ec.com.newvi.sic.modelo.Contribuyentes;
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

public class ContribuyentesBB extends AdminContribuyentesBB {

    private Contribuyentes contribuyente;
    private List<Contribuyentes> listaContribuyentes;
    private List<Contribuyentes> listaContribuyentesFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private EnumTipoPersoneria[] listaTipoPersoneria; 

    public Contribuyentes getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyentes contribuyente) {
        this.contribuyente = contribuyente;
    }

    public List<Contribuyentes> getListaContribuyentes() {
        return listaContribuyentes;
    }

    public void setListaContribuyentes(List<Contribuyentes> listaContribuyentes) {
        this.listaContribuyentes = listaContribuyentes;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public List<Contribuyentes> getlistaContribuyentesFiltrado() {
        return listaContribuyentesFiltrado;
    }

    public void setlistaContribuyentesFiltrado(List<Contribuyentes> listaContribuyentesFiltrado) {
        this.listaContribuyentesFiltrado = listaContribuyentesFiltrado;
    }

    public EnumTipoPersoneria[] getListaTipoPersoneria() {
        return listaTipoPersoneria;
    }

    public void setListaTipoPersoneria(EnumTipoPersoneria[] listaTipoPersoneria) {
        this.listaTipoPersoneria = listaTipoPersoneria;
    }
    
    

    @PostConstruct
    public void init() {
        this.contribuyente = new Contribuyentes();
        actualizarListadoContribuyentes();
        listaTipoPersoneria= EnumTipoPersoneria.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUYENTES_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUYENTES_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUYENTES_LISTA_DESCRIPCION);
    }

    private void actualizarListadoContribuyentes() {
        listaContribuyentes = contribuyentesServicio.consultarContribuyentes();
    }

    public void crearNuevoContribuyente() {
        this.contribuyente = new Contribuyentes();
        this.contribuyente.setStsPersoneria(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.CONTRIBUYENTES_NUEVO_TITULO,
                EnumEtiquetas.CONTRIBUYENTES_NUEVO_ICONO,
                EnumEtiquetas.CONTRIBUYENTES_NUEVO_DESCRIPCION);
    }

    public void insertarContribuyente() {
        try {
            contribuyentesServicio.generarNuevoContribuyente(contribuyente, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF330.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF330.presentarMensaje());
            actualizarListadoContribuyentes();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarContribuyente() {
        if (!ComunUtil.esNumeroPositivo(this.contribuyente.getCodPersoneria())) {
            insertarContribuyente();
        } else {
            try {
                contribuyentesServicio.actualizarContribuyente(contribuyente, sesionBean.getSesion());
                actualizarListadoContribuyentes();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF331.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF331.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUYENTES_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUYENTES_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUYENTES_LISTA_DESCRIPCION);
    }

    public void eliminarContribuyente(Integer idContribuyente) {
        try {
            this.seleccionarContribuyentePorCodigo(idContribuyente);
            if (!ComunUtil.esNulo(contribuyente)) {
                contribuyentesServicio.eliminarContribuyente(contribuyente, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF332.presentarMensaje());
                actualizarListadoContribuyentes();

            } else {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.getSesion());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
            }
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void seleccionarContribuyente(Integer idContribuyente) {
        try {
            this.seleccionarContribuyentePorCodigo(idContribuyente);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.CONTRIBUYENTES_EDITAR_TITULO,
                EnumEtiquetas.CONTRIBUYENTES_EDITAR_ICONO,
                EnumEtiquetas.CONTRIBUYENTES_EDITAR_DESCRIPCION);
    }

    private void seleccionarContribuyentePorCodigo(Integer idContribuyente) throws NewviExcepcion {
        this.contribuyente = contribuyentesServicio.seleccionarContribuyente(idContribuyente);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioContribuyente:opDetalleContribuyentes");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUYENTES_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUYENTES_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUYENTES_LISTA_DESCRIPCION);
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

    public void validarEmail(FacesContext arg0, UIComponent arg1, Object arg2) throws NewviExcepcion {
        String usuEmail = arg2.toString();
        if (!ValidacionUtils.validarCorreoElectronico(usuEmail.trim())) {
            throw ValidacionUtils.lanzarExcepcionValidacion(EnumNewviExcepciones.ERR251);
        }
    }

}
