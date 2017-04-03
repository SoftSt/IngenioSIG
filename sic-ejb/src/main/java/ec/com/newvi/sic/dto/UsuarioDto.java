/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.util.ComunUtil;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author NEWVI
 */
public class UsuarioDto {
    private Integer usuCodigo;
    private String usuNombres;
    private String usuApellidos;
    private String usuDireccion;
    private String usuTelefono;
    private String usuFax;
    private String usuEmail;
    private String usuResponsable;
    private String usuPalabraclave;
    private String usuFoto;
    private Date usuFechanacimiento;
    private Date usuFechaingreso;
    private Integer usuNumeroingreso;
    private String usuCargo;
    private String usuDepartamento;
    private String usuCreacionusuario;
    private String usuModificacionusuario;
    private Date usuFechamodificacion;
    private String usuUsuario;
    private String usuPassword;
    private Boolean usuEstado;
    private Date usuFechacreacion;
    private String usuIp;

    public Boolean getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(Boolean usuEstado) {
        this.usuEstado = usuEstado;
    }

    

    public Date getUsuFechacreacion() {
        return usuFechacreacion;
    }

    public void setUsuFechacreacion(Date usuFechacreacion) {
        this.usuFechacreacion = usuFechacreacion;
    }

    public String getUsuIp() {
        return usuIp;
    }

    public void setUsuIp(String usuIp) {
        this.usuIp = usuIp;
    }
    
    public UsuarioDto(){
        this.usuApellidos = "";
        this.usuNombres = "";
        
    }

    public Integer getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(Integer usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuDireccion() {
        return usuDireccion;
    }

    public void setUsuDireccion(String usuDireccion) {
        this.usuDireccion = usuDireccion;
    }

    public String getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public String getUsuFax() {
        return usuFax;
    }

    public void setUsuFax(String usuFax) {
        this.usuFax = usuFax;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuResponsable() {
        return usuResponsable;
    }

    public void setUsuResponsable(String usuResponsable) {
        this.usuResponsable = usuResponsable;
    }

    public String getUsuPalabraclave() {
        return usuPalabraclave;
    }

    public void setUsuPalabraclave(String usuPalabraclave) {
        this.usuPalabraclave = usuPalabraclave;
    }

    public String getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(String usuFoto) {
        this.usuFoto = usuFoto;
    }

    public Date getUsuFechanacimiento() {
        return usuFechanacimiento;
    }

    public void setUsuFechanacimiento(Date usuFechanacimiento) {
        this.usuFechanacimiento = usuFechanacimiento;
    }

    public Date getUsuFechaingreso() {
        return usuFechaingreso;
    }

    public void setUsuFechaingreso(Date usuFechaingreso) {
        this.usuFechaingreso = usuFechaingreso;
    }

    public Integer getUsuNumeroingreso() {
        return usuNumeroingreso;
    }

    public void setUsuNumeroingreso(Integer usuNumeroingreso) {
        this.usuNumeroingreso = usuNumeroingreso;
    }

    public String getUsuCargo() {
        return usuCargo;
    }

    public void setUsuCargo(String usuCargo) {
        this.usuCargo = usuCargo;
    }

    public String getUsuDepartamento() {
        return usuDepartamento;
    }

    public void setUsuDepartamento(String usuDepartamento) {
        this.usuDepartamento = usuDepartamento;
    }

    public String getUsuCreacionusuario() {
        return usuCreacionusuario;
    }

    public void setUsuCreacionusuario(String usuCreacionusuario) {
        this.usuCreacionusuario = usuCreacionusuario;
    }

    public String getUsuModificacionusuario() {
        return usuModificacionusuario;
    }

    public void setUsuModificacionusuario(String usuModificacionusuario) {
        this.usuModificacionusuario = usuModificacionusuario;
    }

    public Date getUsuFechamodificacion() {
        return usuFechamodificacion;
    }

    public void setUsuFechamodificacion(Date usuFechamodificacion) {
        this.usuFechamodificacion = usuFechamodificacion;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }
    
    
}
