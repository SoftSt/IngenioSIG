/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumTipoPersoneria;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "cat_ciu_personeria", schema = "public")
public class Contribuyentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CONTRIBUYENTES_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_ciu_personeria_cod_personeria_seq", schema = "public")
    @GeneratedValue(generator = "CONTRIBUYENTES_CODIGO_GENERATOR")
    @Column(name = "cod_personeria")
    private Integer codPersoneria;
    @Column(name = "sts_tipopersoneria")
    private String stsTipopersoneria;
    @Size(max = 15)
    @Column(name = "cod_cedularuc")
    private String codCedularuc;
    @Size(max = 50)
    @Column(name = "nom_apellidos")
    private String nomApellidos;
    @Size(max = 50)
    @Column(name = "nom_nombres")
    private String nomNombres;
    @Size(max = 100)
    @Column(name = "nom_razonsocial")
    private String nomRazonsocial;
    @Size(max = 50)
    @Column(name = "txt_nacionalidad")
    private String txtNacionalidad;
    @Column(name = "fec_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fecNacimiento;
    @Size(max = 20)
    @Column(name = "sts_estadocivil")
    private String stsEstadocivil;
    @Size(max = 150)
    @Column(name = "txt_direccion")
    private String txtDireccion;
    @Size(max = 50)
    @Column(name = "txt_telefono")
    private String txtTelefono;
    @Size(max = 50)
    @Column(name = "txt_email")
    private String txtEmail;
    @Enumerated(EnumType.STRING)
    @Column(name = "sts_personeria")
    private EnumEstadoRegistro stsPersoneria;
    @Size(max = 100)
    @Column(name = "nom_ciudaddomicilio")
    private String nomCiudadDomicilio;
    @Size(max = 100)
    @Column(name = "txt_ciudad")
    private String txtCiudad;
    @Size(max = 50)
    @Column(name = "txt_representante")
    private String nombreRepresentante;
    @Size(max = 10)
    @Column(name = "cod_representantecedula")
    private String cedulaRepresentante;
    @Size(max = 100)
    @Column(name = "txt_representantedireccion")
    private String direccionRepresentante;
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

    @Size(max = 50)
    @Column(name = "sts_especiales")
    private String stsEspeciales;

    public Contribuyentes() {
    }

    public Contribuyentes(Integer codPersoneria) {
        this.codPersoneria = codPersoneria;
    }

    public Integer getCodPersoneria() {
        return codPersoneria;
    }

    public void setCodPersoneria(Integer codPersoneria) {
        this.codPersoneria = codPersoneria;
    }

    public EnumTipoPersoneria getStsTipopersoneria() {
        return EnumTipoPersoneria.obtenerTipoPersoneriaPorDescripcion(stsTipopersoneria);
    }

    public void setStsTipopersoneria(EnumTipoPersoneria stsTipopersoneria) {
        this.stsTipopersoneria = stsTipopersoneria.getDescripcionTipoPersoneria();
    }

    public String getCodCedularuc() {
        return codCedularuc;
    }

    public void setCodCedularuc(String codCedularuc) {
        this.codCedularuc = codCedularuc;
    }

    public String getNomApellidos() {
        return nomApellidos;
    }

    public void setNomApellidos(String nomApellidos) {
        this.nomApellidos = nomApellidos;
    }

    public String getNomNombres() {
        return nomNombres;
    }

    public void setNomNombres(String nomNombres) {
        this.nomNombres = nomNombres;
    }

    public String getNomRazonsocial() {
        return nomRazonsocial;
    }

    public void setNomRazonsocial(String nomRazonsocial) {
        this.nomRazonsocial = nomRazonsocial;
    }

    public String getTxtNacionalidad() {
        return txtNacionalidad;
    }

    public void setTxtNacionalidad(String txtNacionalidad) {
        this.txtNacionalidad = txtNacionalidad;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getStsEstadocivil() {
        return stsEstadocivil;
    }

    public void setStsEstadocivil(String stsEstadocivil) {
        this.stsEstadocivil = stsEstadocivil;
    }

    public String getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public String getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(String txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public EnumEstadoRegistro getStsPersoneria() {
        return stsPersoneria;
    }

    public void setStsPersoneria(EnumEstadoRegistro stsPersoneria) {
        this.stsPersoneria = stsPersoneria;
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

    public String getNomCiudadDomicilio() {
        return nomCiudadDomicilio;
    }

    public void setNomCiudadDomicilio(String nomCiudadDomicilio) {
        this.nomCiudadDomicilio = nomCiudadDomicilio;
    }

    public String getTxtCiudad() {
        return txtCiudad;
    }

    public void setTxtCiudad(String txtCiudad) {
        this.txtCiudad = txtCiudad;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public String getDireccionRepresentante() {
        return direccionRepresentante;
    }

    public void setDireccionRepresentante(String direccionRepresentante) {
        this.direccionRepresentante = direccionRepresentante;
    }

    public String getStsEspeciales() {
        return stsEspeciales;
    }

    public void setStsEspeciales(String stsEspeciales) {
        this.stsEspeciales = stsEspeciales;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPersoneria != null ? codPersoneria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribuyentes)) {
            return false;
        }
        Contribuyentes other = (Contribuyentes) object;
        if ((this.codPersoneria == null && other.codPersoneria != null) || (this.codPersoneria != null && !this.codPersoneria.equals(other.codPersoneria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Contribuyentes[ codPersoneria=" + codPersoneria + " ]";
    }

    public Boolean esContribuyenteValido() {
        return (!ComunUtil.esNulo(this.codCedularuc));
    }
}
