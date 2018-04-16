/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    private UploadedFile imagenCargada;
    private Boolean esParametroDeImagen;
    private Boolean esCargaDeImagen;

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

    public UploadedFile getImagenCargada() {
        return imagenCargada;
    }

    public void setImagenCargada(UploadedFile imagenCargada) {
        this.imagenCargada = imagenCargada;
    }

    public Boolean getEsParametroDeImagen() {
        return esParametroDeImagen;
    }

    public Boolean getEsCargaDeImagen() {
        return esCargaDeImagen;
    }

    public void setEsCargaDeImagen(Boolean esCargaDeImagen) {
        this.esCargaDeImagen = esCargaDeImagen;
    }

    @PostConstruct
    public void init() {
        obtenerFuncionalidadActual(EnumFuncionalidad.PARAMETROS_DEL_SISTEMA);
        RequestContext.getCurrentInstance().update("formCabecera");

        actualizarListadoParametros();
        listaSeleccionParametros = EnumParametroSistema.values();

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
        esParametroDeImagen = false;
        esCargaDeImagen = false;
        this.parametroSeleccionado = new ParametroSistema();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PARAMETROS_SISTEMA_NUEVO_TITULO,
                EnumEtiquetas.PARAMETROS_SISTEMA_NUEVO_ICONO,
                EnumEtiquetas.PARAMETROS_SISTEMA_NUEVO_DESCRIPCION);
    }

    public void seleccionarParametroSistema(Integer idUsuario) {
        try {
            this.seleccionarParametroSistemaPorCodigo(idUsuario);
            esParametroDeImagen = EnumParametroSistema.EnumTipoParametro.IMAGEN.equals(parametroSeleccionado.getParametro().getTipoParametro());
            esCargaDeImagen = false;
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

    public void cargarImagen(FileUploadEvent event) {
        this.imagenCargada = event.getFile();
        if (!ComunUtil.esNulo(this.imagenCargada)) {
            try {
                String nombreArchivoGuardado = parametrosServicio.guardarImagenParametroSistema(parametroSeleccionado, this.imagenCargada.getContents(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion("La imagen ".concat(nombreArchivoGuardado).concat(" se ha almacenado correctamente. "));
                this.parametroSeleccionado.setValor(nombreArchivoGuardado);
                this.esCargaDeImagen = false;
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError("Ocurrió un error al intentar cargar la imagen. ".concat(e.getMessage()));
            }
        } else {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
        }
    }

    public void alCambiarParametro() {
        this.parametroSeleccionado.setValor(parametroSeleccionado.getParametro().getValorPorDefecto());
        esParametroDeImagen = EnumParametroSistema.EnumTipoParametro.IMAGEN.equals(parametroSeleccionado.getParametro().getTipoParametro());
        esCargaDeImagen = false;
    }
    
    public void iniciarCargaDeImagen() {
        this.esCargaDeImagen = true;
    }

}
