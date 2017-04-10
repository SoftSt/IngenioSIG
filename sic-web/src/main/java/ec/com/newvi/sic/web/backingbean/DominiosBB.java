/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
import ec.com.newvi.sic.modelo.Dominios;
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
public class DominiosBB extends ParametrosBB {
    
    private Dominios dominio;
    private List<Dominios> listaDominios;
    private List<Dominios> listaDominiosFiltrados;
    private List<Dominios> listaGruposDominios;
    private EnumRelacionDominios[] listaRelacionDominios;
    private EnumPantallaMantenimiento pantallaActual;

    

    public Dominios getDominio() {
        return dominio;
    }

    public void setDominio(Dominios dominio) {
        this.dominio = dominio;
    }

    public List<Dominios> getListaDominios() {
        return listaDominios;
    }

    public void setListaDominios(List<Dominios> listaDominios) {
        this.listaDominios = listaDominios;
    }
    
    public EnumRelacionDominios[] getListaRelacionDominios() {
        return listaRelacionDominios;
    }

    public void setListaRelacionDominios(EnumRelacionDominios[] listaRelacionDominios) {
        this.listaRelacionDominios = listaRelacionDominios;
    }

    public List<Dominios> getListaGruposDominios() {
        return listaGruposDominios;
    }

    public void setListaGruposDominios(List<Dominios> listaGruposDominios) {
        this.listaGruposDominios = listaGruposDominios;
    }

    public List<Dominios> getListaDominiosFiltrados() {
        return listaDominiosFiltrados;
    }

    public void setListaDominiosFiltrados(List<Dominios> listaDominiosFiltrados) {
        this.listaDominiosFiltrados = listaDominiosFiltrados;
    }

   

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }
    
    
    
    @PostConstruct
    public void init() {
        this.dominio = new Dominios();
        actualizarListadoDominios();
        actualizarListadoGruposDominios();
        listaRelacionDominios = EnumRelacionDominios.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DOMINIOS_LISTA_TITULO, 
                EnumEtiquetas.DOMINIOS_LISTA_ICONO,
                EnumEtiquetas.DOMINIOS_LISTA_DESCRIPCION);
    }

    private void actualizarListadoDominios() {
        listaDominios = parametrosServicio.consultarDominios();
    }

    private void actualizarListadoGruposDominios() {
        listaGruposDominios = parametrosServicio.consultarGruposDominios();
    }

    /*private void actualizarListadoPermisos() {
        listaPermisos = seguridadesServicio.consultarPermisos();
    }*/

    public void crearNuevoDominio() {
        this.dominio = new Dominios();
        this.dominio.setDomiEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.DOMINIOS_NUEVO_TITULO, 
                EnumEtiquetas.DOMINIOS_NUEVO_ICONO,
                EnumEtiquetas.DOMINIOS_NUEVO_DESCRIPCION);
    }

    public void insertarDominio() {
        try {
            parametrosServicio.generaNuevoDominio(dominio, sesionBean.obtenerSesionDto());
            actualizarListadoDominios();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF323.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarDominio() {
        if (!ComunUtil.esNumeroPositivo(this.dominio.getDomiId())) {
            insertarDominio();
        } else {
            try {
                parametrosServicio.actualizarDominio(dominio, sesionBean.obtenerSesionDto());
                actualizarListadoDominios();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF324.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DOMINIOS_LISTA_TITULO, 
                EnumEtiquetas.DOMINIOS_LISTA_ICONO,
                EnumEtiquetas.DOMINIOS_LISTA_DESCRIPCION);
    }

    public void eliminarDominio(Integer idDominio) {
        try {
            this.seleccionarDominioPorCodigo(idDominio);
            if (!ComunUtil.esNulo(dominio)) {
                parametrosServicio.eliminarDominio(dominio, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF325.presentarMensaje());
                actualizarListadoDominios();

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

    public void seleccionarDominio(Integer idDominio) {
        try {
            this.seleccionarDominioPorCodigo(idDominio);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.DOMINIOS_EDITAR_TITULO, 
                EnumEtiquetas.DOMINIOS_EDITAR_ICONO,
                EnumEtiquetas.DOMINIOS_EDITAR_DESCRIPCION);
    }

    private void seleccionarDominioPorCodigo(Integer idDominio) throws NewviExcepcion {
        this.dominio = parametrosServicio.seleccionarDominio(idDominio);
    }
    
    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioDominio:opDetalleDominios");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DOMINIOS_LISTA_TITULO, 
                EnumEtiquetas.DOMINIOS_LISTA_ICONO,
                EnumEtiquetas.DOMINIOS_LISTA_DESCRIPCION);
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
}
