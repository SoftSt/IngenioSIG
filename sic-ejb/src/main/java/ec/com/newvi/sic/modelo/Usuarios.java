/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author NEWVI
 */
@Entity
@Table(name = "cat_seg_usuarios", schema = "public")

public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "USUARIOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_seg_usuarios_usu_id_seq", schema = "public")
    @GeneratedValue(generator = "USUARIOS_CODIGO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "usu_id")
    private Integer usuId;
    @Size(max = 11)
    @Column(name = "usu_codigo")
    private String usuCodigo;
    @Size(max = 35)
    @Column(name = "usu_apellidos")
    private String usuApellidos;
    @Size(max = 35)
    @Column(name = "usu_nombres")
    private String usuNombres;
    @Size(max = 100)
    @Column(name = "usu_direccion")
    private String usuDireccion;
    @Size(max = 30)
    @Column(name = "usu_telefono")
    private String usuTelefono;
    @Size(max = 30)
    @Column(name = "usu_fax")
    private String usuFax;
    @Size(max = 30)
    @Column(name = "usu_emai")
    private String usuEmai;
    @Size(max = 254)
    @Column(name = "usu_responsable")
    private String usuResponsable;
    @Size(max = 20)
    @Column(name = "usu_palabraclave")
    private String usuPalabraclave;
    @Size(max = 20)
    @Column(name = "usu_usuario")
    private String usuUsuario;
    @Size(max = 20)
    @Column(name = "usu_password")
    private String usuPassword;
    @Size(max = 100)
    @Column(name = "usu_password_hash")
    private String usuPasswordHash;
    @Size(max = 50)
    @Column(name = "usu_foto")
    private String usuFoto;
    @Column(name = "usu_fnacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuFnacimiento;
    @Column(name = "usu_fingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuFingreso;
    @Column(name = "usu_numingreso")
    private Integer usuNumingreso;
    @Size(max = 40)
    @Column(name = "usu_cargo")
    private String usuCargo;
    @Size(max = 30)
    @Column(name = "usu_departamento")
    private String usuDepartamento;
    @Enumerated(EnumType.STRING)
    @Column(name = "usu_estado")
    private EnumEstadoRegistro usuEstado;
    @Size(max = 50)
    @Column(name = "aud_ing_usu")
    private String audIngUsu;
    @Column(name = "aud_ing_fec")
    @Temporal(TemporalType.DATE)
    private Date audIngFec;
    @Size(max = 30)
    @Column(name = "aud_ing_ip")
    private String audIngIp;
    @Size(max = 50)
    @Column(name = "aud_mod_usu")
    private String audModUsu;
    @Column(name = "aud_mod_fec")
    @Temporal(TemporalType.DATE)
    private Date audModFec;
    @Size(max = 30)
    @Column(name = "aud_mod_ip")
    private String audModIp;
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")
    @ManyToOne
    private Permisos perId;

    public Usuarios() {
    }

    public Usuarios(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(String usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
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

    public String getUsuEmai() {
        return usuEmai;
    }

    public void setUsuEmai(String usuEmai) {
        this.usuEmai = usuEmai;
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

    public String getUsuPasswordHash() {
        return usuPasswordHash;
    }

    public void setUsuPasswordHash(String usuPasswordHash) {
        this.usuPasswordHash = usuPasswordHash;
    }
    
    public String getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(String usuFoto) {
        this.usuFoto = usuFoto;
    }

    public Date getUsuFnacimiento() {
        return usuFnacimiento;
    }

    public void setUsuFnacimiento(Date usuFnacimiento) {
        this.usuFnacimiento = usuFnacimiento;
    }

    public Date getUsuFingreso() {
        return usuFingreso;
    }

    public void setUsuFingreso(Date usuFingreso) {
        this.usuFingreso = usuFingreso;
    }

    public Integer getUsuNumingreso() {
        return usuNumingreso;
    }

    public void setUsuNumingreso(Integer usuNumingreso) {
        this.usuNumingreso = usuNumingreso;
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

    public EnumEstadoRegistro getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(EnumEstadoRegistro usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getAudIngUsu() {
        return audIngUsu;
    }

    public void setAudIngUsu(String audIngUsu) {
        this.audIngUsu = audIngUsu;
    }

    public Date getAudIngFec() {
        return audIngFec;
    }

    public void setAudIngFec(Date audIngFec) {
        this.audIngFec = audIngFec;
    }

    public String getAudIngIp() {
        return audIngIp;
    }

    public void setAudIngIp(String audIngIp) {
        this.audIngIp = audIngIp;
    }

    public String getAudModUsu() {
        return audModUsu;
    }

    public void setAudModUsu(String audModUsu) {
        this.audModUsu = audModUsu;
    }

    public Date getAudModFec() {
        return audModFec;
    }

    public void setAudModFec(Date audModFec) {
        this.audModFec = audModFec;
    }

    public String getAudModIp() {
        return audModIp;
    }

    public void setAudModIp(String audModIp) {
        this.audModIp = audModIp;
    }

    public Permisos getPerId() {
        return perId;
    }

    public void setPerId(Permisos perId) {
        this.perId = perId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Usuarios[ usuId=" + usuId + " ]";
    }

    public Boolean esUsuarioValido() {
        return (!ComunUtil.esNulo(this.usuEstado));
    }
    
    public String obtenerImagen(){
        return !ComunUtil.esNulo(this.usuFoto) || !ComunUtil.esCadenaVacia(this.usuFoto)? this.usuFoto : "usuario_desconocido.jpg";
    }
}
