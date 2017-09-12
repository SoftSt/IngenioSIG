/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumGrupoParametroSistema;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.modelo.ParametroSistema;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class ParametrosSistemaBB extends AdminSistemaBB {

    private List<ParametroSistema> listaParametros;
    private List<ParametroSistema> listaParametrosFiltrado;
    private EnumPantallaMantenimiento pantallaActual;
    private ParametroSistema parametroSeleccionado;
    private EnumParametroSistema[] listaSeleccionParametros;
    private EnumGrupoParametroSistema[] listaSeleccionGrupoParametros;

    public List<ParametroSistema> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List<ParametroSistema> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public List<ParametroSistema> getListaParametrosFiltrado() {
        return listaParametrosFiltrado;
    }

    public void setListaParametrosFiltrado(List<ParametroSistema> listaParametrosFiltrado) {
        this.listaParametrosFiltrado = listaParametrosFiltrado;
    }

    public ParametroSistema getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setParametroSeleccionado(ParametroSistema parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public EnumParametroSistema[] getListaSeleccionParametros() {
        return listaSeleccionParametros;
    }

    public EnumGrupoParametroSistema[] getListaSeleccionGrupoParametros() {
        return listaSeleccionGrupoParametros;
    }

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.PARAMETROS_DEL_SISTEMA);
        RequestContext.getCurrentInstance().update("formCabecera");
        
        actualizarListadoParametros();
        listaSeleccionParametros = EnumParametroSistema.values();
        listaSeleccionGrupoParametros = EnumGrupoParametroSistema.values();

        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_TITULO,
                EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_ICONO,
                EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_DESCRIPCION);

    }

    private void actualizarListadoParametros() {
        try {
            this.listaParametros = parametrosServicio.obtenerListaParametrosSistema(sesionBean.getSesion());
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

    public void crearNuevoParametroSistema() {
        this.parametroSeleccionado = new ParametroSistema();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PARAMETROS_SISTEMA_NUEVO_TITULO,
                EnumEtiquetas.PARAMETROS_SISTEMA_NUEVO_ICONO,
                EnumEtiquetas.PARAMETROS_SISTEMA_NUEVO_DESCRIPCION);
    }

    public void seleccionarParametroSistema(Integer idUsuario) {
        try {
            this.seleccionarParametroSistemaPorCodigo(idUsuario);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PARAMETROS_SISTEMA_EDITAR_TITULO,
                EnumEtiquetas.PARAMETROS_SISTEMA_EDITAR_ICONO,
                EnumEtiquetas.PARAMETROS_SISTEMA_EDITAR_DESCRIPCION);
    }

    private void seleccionarParametroSistemaPorCodigo(Integer codParametro) throws NewviExcepcion {
        this.parametroSeleccionado = parametrosServicio.seleccionarParametroSistema(codParametro);
    }

    public void insertarParametroSistema() {
        try {
            parametrosServicio.generarNuevoParametroSistema(parametroSeleccionado, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF452.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF452.presentarMensaje());
            actualizarListadoParametros();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarParametroSistema() {
        if (!ComunUtil.esNumeroPositivo(this.parametroSeleccionado.getCodParametro())) {
            insertarParametroSistema();
        } else {
            try {
                parametrosServicio.actualizarParametroSistema(parametroSeleccionado, sesionBean.getSesion());
                actualizarListadoParametros();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF454.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF454.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_TITULO,
                EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_ICONO,
                EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_DESCRIPCION);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioParametroSistema:opDetalleParametroSistema");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_TITULO,
                EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_ICONO,
                EnumEtiquetas.PARAMETROS_SISTEMA_LISTA_DESCRIPCION);
    }
}
