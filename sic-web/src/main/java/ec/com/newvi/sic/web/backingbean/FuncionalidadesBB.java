/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.backingbean.utils.SeguridadesUtil;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class FuncionalidadesBB extends AdminSeguridadesBB {

    private Funcionalidades funcionalidad;

    private List<Funcionalidades> listaFuncionalidades;

    private TreeNode listaArbolFuncionalidades;

    private EnumPantallaMantenimiento pantallaActual;

    public Funcionalidades getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Funcionalidades funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public List<Funcionalidades> getListaFuncionalidades() {
        return listaFuncionalidades;
    }

    public TreeNode getListaArbolFuncionalidades() {
        return listaArbolFuncionalidades;
    }

    public void setListaArbolFuncionalidades(TreeNode listaArbolFuncionalidades) {
        this.listaArbolFuncionalidades = listaArbolFuncionalidades;
    }

    public void setListaFuncionalidades(List<Funcionalidades> listaFuncionalidades) {
        this.listaFuncionalidades = listaFuncionalidades;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }
    
    @PostConstruct
    public void init() {
        this.funcionalidad = new Funcionalidades();
        actualizarListadoFuncionalidades();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FUNCIONALIDADES_LISTA_TITULO,
                EnumEtiquetas.FUNCIONALIDADES_LISTA_ICONO,
                EnumEtiquetas.FUNCIONALIDADES_LISTA_DESCRIPCION);
    }

    private void actualizarListadoFuncionalidades() {
        listaFuncionalidades = seguridadesServicio.consultarFuncionalidadesPadre();
        try {
            listaArbolFuncionalidades = new DefaultTreeNode();
            listaArbolFuncionalidades = WebUtils.generarArbol(listaFuncionalidades, listaArbolFuncionalidades, "getListaSubFuncionalidadesActivas");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void crearNuevoFuncionalidad() {
        this.funcionalidad = new Funcionalidades();
        this.funcionalidad.setFunEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FUNCIONALIDADES_NUEVO_TITULO,
                EnumEtiquetas.FUNCIONALIDADES_NUEVO_ICONO,
                EnumEtiquetas.FUNCIONALIDADES_NUEVO_DESCRIPCION);
    }

    public void crearNuevaSubFuncionalidad(Integer idFuncionalidad) {
        try {
            this.seleccionarFuncionalidad(idFuncionalidad);
            Funcionalidades funcionalidadPadre = this.funcionalidad;
            this.funcionalidad = new Funcionalidades();
            this.funcionalidad.setFunEstado(EnumEstadoRegistro.A);
            this.funcionalidad.setFunIdPadre(funcionalidadPadre);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
        establecerTitulo(EnumEtiquetas.FUNCIONALIDADES_SUB_NUEVO_TITULO,
                EnumEtiquetas.FUNCIONALIDADES_SUB_NUEVO_ICONO,
                EnumEtiquetas.FUNCIONALIDADES_SUB_NUEVO_DESCRIPCION);
        
    }

    public void insertarFuncionalidad() {
        try {
            seguridadesServicio.generarNuevaFuncionalidad(funcionalidad, sesionBean.obtenerSesionDto());
            actualizarListadoFuncionalidades();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF311.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarFuncionalidad() {
        if (!ComunUtil.esNumeroPositivo(this.funcionalidad.getFunId())) {
            insertarFuncionalidad();
        } else {
            try {
                seguridadesServicio.actualizarFuncionalidad(funcionalidad, sesionBean.obtenerSesionDto());
                actualizarListadoFuncionalidades();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF313.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FUNCIONALIDADES_LISTA_TITULO,
                EnumEtiquetas.FUNCIONALIDADES_LISTA_ICONO,
                EnumEtiquetas.FUNCIONALIDADES_LISTA_DESCRIPCION);
    }

    public void eliminarFuncionalidad(Integer idFuncionalidad) {
        try {
            this.funcionalidad = SeguridadesUtil.seleccionarFuncionalidadPorCodigo(idFuncionalidad, seguridadesServicio);
            if (!ComunUtil.esNulo(funcionalidad)) {
                seguridadesServicio.eliminarFuncionalidad(funcionalidad, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF312.presentarMensaje());
                actualizarListadoFuncionalidades();
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

    public void seleccionarFuncionalidad(Integer idFuncionalidad) throws NewviExcepcion {
        this.funcionalidad = SeguridadesUtil.seleccionarFuncionalidadPorCodigo(idFuncionalidad, seguridadesServicio);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FUNCIONALIDADES_EDITAR_TITULO,
                EnumEtiquetas.FUNCIONALIDADES_EDITAR_ICONO,
                EnumEtiquetas.FUNCIONALIDADES_EDITAR_DESCRIPCION);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioFuncionalidad:opDetalleFuncionalidades");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FUNCIONALIDADES_LISTA_TITULO,
                EnumEtiquetas.FUNCIONALIDADES_LISTA_ICONO,
                EnumEtiquetas.FUNCIONALIDADES_LISTA_DESCRIPCION);
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
