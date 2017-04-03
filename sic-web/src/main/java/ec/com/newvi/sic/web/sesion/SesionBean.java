/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.sesion;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class SesionBean {

    private static final String SESION_IP = "sesionIp";
    @EJB
    private SeguridadesServicio seguridadesServicio;

    private String nombreUsuario;
    private String ipUsuario;
    private Usuarios usuarioRegistrado;
    private Boolean esUsuarioRegistrado;

    @PostConstruct
    public void init() {
        try {
            registrarUsuario();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e.getMessage(), null);
        }
    }

    private void obtenerNombreUsuario() {
        if (!ComunUtil.esNulo(WebUtils.obtenerContexto().getExternalContext().getUserPrincipal())) {
            this.nombreUsuario = WebUtils.obtenerContexto().getExternalContext().getUserPrincipal().getName();
        } else {
            this.nombreUsuario = null;
        }
    }

    public Usuarios getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(Usuarios usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }

    public Boolean getEsUsuarioRegistrado() {
        return esUsuarioRegistrado;
    }

    public void setEsUsuarioRegistrado(Boolean esUsuarioRegistrado) {
        this.esUsuarioRegistrado = esUsuarioRegistrado;
    }

    public void eliminarUsuarioRegistrado() {
        this.usuarioRegistrado = null;
        this.esUsuarioRegistrado = false;
    }

    public void registrarUsuario() throws NewviExcepcion {
        obtenerNombreUsuario();
        obtenerIpCliente();
        this.usuarioRegistrado = seguridadesServicio.obtenerUsuarioPorNombreUsuario(nombreUsuario, null);
        this.esUsuarioRegistrado = true;
    }

    public SesionDto obtenerSesionDto() {
        if (esUsuarioRegistrado) {
            String ipCliente = this.ipUsuario;
            String nombreServidor = WebUtils.obtenerPeticion().getRemoteHost();
            return new SesionDto(usuarioRegistrado, ipCliente, nombreServidor);
        } else {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR326.presentarMensajeCodigo(), null);
            return null;
        }

    }

    public void obtenerIpCliente() {
        if (ComunUtil.esCadenaVacia((String) WebUtils.obtenerPeticion().getSession().getAttribute(SESION_IP))) {
            try {
                WebUtils.obtenerPeticion().getSession().setAttribute(SESION_IP, InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(ex.getMessage(), null);
            }
        }
        this.ipUsuario = (String) WebUtils.obtenerPeticion().getSession().getAttribute(SESION_IP);
    }

}
