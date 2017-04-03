/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.utils.WebUtils;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class LoginBB extends SeguridadesBB {

    private HttpServletRequest peticion;

    private String usuario;
    private String clave;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene la petición actual.
     */
    public void obtenerPeticion() {
        peticion = WebUtils.obtenerPeticion();
    }

    /**
     * Genera el registro en el sistema, dado los datos ingresados.
     */
    public void login() {
        if (!ComunUtil.esCadenaVacia(this.usuario) && !ComunUtil.esCadenaVacia(this.clave)) {
            this.logoutInterno();
            obtenerPeticion();
            try {
                this.peticion.login(this.usuario, this.clave);
                sesionBean.registrarUsuario();
                WebUtils.redireccionar("/privado/index.xhtml");
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF320.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF320.presentarMensajeCodigo());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (ServletException e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR319.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR319.presentarMensajeCodigo());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
            }
        } else {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR317.presentarMensajeCodigo(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR317.presentarMensajeCodigo());
        }
    }

    /**
     * Genera la salida del sistema.
     */
    public void logout() {
        logoutInterno();
        try {
            WebUtils.redireccionar("/publico/login.xhtml");
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void logoutInterno() {
        obtenerPeticion();
        if (!ComunUtil.esNulo(peticion.getUserPrincipal())) {
            try {
                peticion.logout();
            } catch (ServletException e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR317.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR317.presentarMensajeCodigo());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
            }
        }
        sesionBean.eliminarUsuarioRegistrado();
    }
}
