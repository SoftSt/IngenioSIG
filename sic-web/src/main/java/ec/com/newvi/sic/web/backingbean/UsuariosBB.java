/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class UsuariosBB extends SeguridadesBB {

    private Usuarios usuario;
    private List<Usuarios> listaUsuarios;
    private List<Usuarios> listaUsuariosFiltrado;

    private List<Permisos> listaPermisos;

    private EnumPantallaMantenimiento pantallaActual;

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> ListaUsuarios) {
        this.listaUsuarios = ListaUsuarios;
    }

    public List<Usuarios> getListaUsuariosFiltrado() {
        return listaUsuariosFiltrado;
    }

    public void setListaUsuariosFiltrado(List<Usuarios> listaUsuariosFiltrado) {
        this.listaUsuariosFiltrado = listaUsuariosFiltrado;
    }

    public List<Permisos> getListaPermisos() {
        return listaPermisos;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
        this.usuario = new Usuarios();
        actualizarListadoUsuarios();
        actualizarListadoPermisos();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.USUARIOS_LISTA_TITULO,
                EnumEtiquetas.USUARIOS_LISTA_ICONO,
                EnumEtiquetas.USUARIOS_LISTA_DESCRIPCION);
    }

    private void actualizarListadoUsuarios() {
        listaUsuarios = seguridadesServicio.consultarUsuarios();
    }

    private void actualizarListadoPermisos() {
        listaPermisos = seguridadesServicio.consultarPermisos();
    }

    public void crearNuevoUsuario() {
        this.usuario = new Usuarios();
        this.usuario.setUsuEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.USUARIOS_NUEVO_TITULO,
                EnumEtiquetas.USUARIOS_NUEVO_ICONO,
                EnumEtiquetas.USUARIOS_NUEVO_DESCRIPCION);
    }

    public void insertarUsuario() {
        try {
            encriptarPassword();
            seguridadesServicio.generarNuevoUsuario(usuario, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF302.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF302.presentarMensaje());
            actualizarListadoUsuarios();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarUsuario() {
        if (!ComunUtil.esNumeroPositivo(this.usuario.getUsuId())) {
            insertarUsuario();
        } else {
            try {
                encriptarPassword();
                seguridadesServicio.actualizarUsuario(usuario, sesionBean.obtenerSesionDto());
                actualizarListadoUsuarios();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF305.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF305.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.USUARIOS_LISTA_TITULO,
                EnumEtiquetas.USUARIOS_LISTA_ICONO,
                EnumEtiquetas.USUARIOS_LISTA_DESCRIPCION);
    }

    private void encriptarPassword() throws NewviExcepcion {
        usuario.setUsuPasswordHash(WebUtils.encriptarCadenaEnSHA1(usuario.getUsuPassword()));
    }

    public void eliminarUsuario(Integer idUsuario) {
        try {
            this.seleccionarUsuarioPorCodigo(idUsuario);
            if (!ComunUtil.esNulo(usuario)) {
                seguridadesServicio.eliminarUsuario(usuario, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF304.presentarMensaje());
                actualizarListadoUsuarios();

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

    public void seleccionarUsuario(Integer idUsuario) {
        try {
            this.seleccionarUsuarioPorCodigo(idUsuario);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.USUARIOS_EDITAR_TITULO,
                EnumEtiquetas.USUARIOS_EDITAR_ICONO,
                EnumEtiquetas.USUARIOS_EDITAR_DESCRIPCION);
    }

    private void seleccionarUsuarioPorCodigo(Integer idUsuario) throws NewviExcepcion {
        this.usuario = seguridadesServicio.seleccionarUsuario(idUsuario);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioUsuario:opDetalleUsuarios");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.USUARIOS_LISTA_TITULO,
                EnumEtiquetas.USUARIOS_LISTA_ICONO,
                EnumEtiquetas.USUARIOS_LISTA_DESCRIPCION);
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

    public void validarUsuarioRepetido(FacesContext arg0, UIComponent arg1, Object arg2) throws NewviExcepcion {
        String usuCodigo =arg2.toString();
        try {
            if (seguridadesServicio.esUsuarioRepetido(usuCodigo)) {
                throw MensajesFaces.lanzarExcepcionValidacion(EnumNewviExcepciones.ERR327);
            }

        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void validarEmail(FacesContext arg0, UIComponent arg1, Object arg2) throws NewviExcepcion {
        String usuEmail =arg2.toString();
        try {
            // Compiles the given regular expression into a pattern.
            Pattern pattern = Pattern.compile(PATTERN_EMAIL);
            // Match the given input against this pattern
            Matcher matcher = pattern.matcher(usuEmail);
            if (!matcher.matches()) {
                throw MensajesFaces.lanzarExcepcionValidacion(EnumNewviExcepciones.ERR328);
            }else if (seguridadesServicio.esEmailRepetido(arg2.toString())) {
                throw MensajesFaces.lanzarExcepcionValidacion(EnumNewviExcepciones.ERR327);
            }

        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

}
