/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.sesion;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author israelavila
 */
@Named
@SessionScoped
public class SesionBean implements Serializable {

    private static final String SESION_IP = "sesionIp";
    private static final String SESION_LOCALHOST = "sesionLocalhost";

    @EJB
    private SeguridadesServicio seguridadesServicio;

    @EJB
    private ParametrosServicio parametrosServicio;

    private String nombreUsuario;
    private String ipUsuario;
    private String nombreEquipoUsuario;
    private Usuarios usuarioRegistrado;
    private Boolean esUsuarioRegistrado;
    private SesionDto sesion;

    public SesionDto getSesion() {
        return sesion;
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

    private void obtenerNombreUsuario() {
        if (!ComunUtil.esNulo(WebUtils.obtenerContexto().getExternalContext().getUserPrincipal())) {
            this.nombreUsuario = WebUtils.obtenerContexto().getExternalContext().getUserPrincipal().getName();
        } else {
            this.nombreUsuario = null;
        }
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
        sesion = obtenerSesionDto();
    }

    private SesionDto obtenerSesionDto() {
        if (esUsuarioRegistrado) {
            String ipCliente = this.ipUsuario;
            String nombreServidor = WebUtils.obtenerPeticion().getRemoteHost();
            return new SesionDto(usuarioRegistrado, ipCliente, nombreEquipoUsuario, nombreServidor);
        } else {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR326.presentarMensajeCodigo(), null);
            return null;
        }

    }

    public void obtenerIpCliente() {
        if (ComunUtil.esCadenaVacia((String) WebUtils.obtenerPeticion().getSession().getAttribute(SESION_IP))) {
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                WebUtils.obtenerPeticion().getSession().setAttribute(SESION_IP, localhost.getHostAddress());
                WebUtils.obtenerPeticion().getSession().setAttribute(SESION_LOCALHOST, localhost.getHostName());
            } catch (UnknownHostException ex) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(ex.getMessage(), null);
            }
        }
        this.ipUsuario = (String) WebUtils.obtenerPeticion().getSession().getAttribute(SESION_IP);
        this.nombreEquipoUsuario = (String) WebUtils.obtenerPeticion().getSession().getAttribute(SESION_LOCALHOST);
    }

    public StreamedContent obtenerImagen() {

        if (!WebUtils.obtenerContexto().getRenderResponse()) {
            try {
                String direccionImagen = WebUtils.obtenerContextoExterno().getRequestParameterMap().get("imagen");
                String contentType = WebUtils.obtenerContextoExterno().getMimeType(direccionImagen);
                FileInputStream archivoFotografia = new FileInputStream(direccionImagen);
                StreamedContent contenidoFotografia = new DefaultStreamedContent(archivoFotografia, contentType);
                return contenidoFotografia;
            } catch (FileNotFoundException | NullPointerException ex) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(ex, this.getSesion());
                return new DefaultStreamedContent();
            }
        }
        return new DefaultStreamedContent();

    }
}
