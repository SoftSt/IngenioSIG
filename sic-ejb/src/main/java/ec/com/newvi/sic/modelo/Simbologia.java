/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import java.io.Serializable;
import java.util.Date;
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
 * @author Rolando Soria by Excor
 * 
 */
@Entity
@Table(name = "cat_adm_simbologia", schema = "public")
public class Simbologia implements Serializable {
    
    @Id
    @SequenceGenerator(name = "SIMBOLOGIA_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_adm_simbologia_smb_cod_secuencia_seq", schema = "public")
    @GeneratedValue(generator = "SIMBOLOGIA_CODIGO_GENERATOR")
    @Column(name = "smb_cod_secuencial")
    private Integer smbCod;
    
    @Size(max = 50)
    @Column(name = "smb_txt_nombre")
    private String smbNombre;
    
    @Size(max = 2500)
    @Column(name = "smb_txt_descripcion")
    private String smbDescripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "smb_sts_estado")
    private EnumEstadoRegistro smbEstado;
        
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
    
    @JoinColumn(name = "znf_cod_secuencial", referencedColumnName = "znf_cod_secuencial")
    @ManyToOne
    private Zonificacion zonificacion;
    
    @JoinColumn(name = "uss_cod_secuencial", referencedColumnName = "uss_cod_secuencial")
    @ManyToOne
    private UsoSuelo usoSuelo;
    
    @JoinColumn(name = "tpl_cod_secuencial", referencedColumnName = "tpl_cod_secuencial")
    @ManyToOne
    private Tipologia tipologia;
    
    public Integer getSmbCod() {
        return smbCod;
    }

    public void setSmbCod(Integer smbCod) {
        this.smbCod = smbCod;
    }

    public String getSmbNombre() {
        return smbNombre;
    }

    public void setSmbNombre(String smbNombre) {
        this.smbNombre = smbNombre;
    }

    public String getSmbDescripcion() {
        return smbDescripcion;
    }

    public void setSmbDescripcion(String smbDescripcion) {
        this.smbDescripcion = smbDescripcion;
    }

    public EnumEstadoRegistro getSmbEstado() {
        return smbEstado;
    }

    public void setSmbEstado(EnumEstadoRegistro smbEstado) {
        this.smbEstado = smbEstado;
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

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public UsoSuelo getUsoSuelo() {
        return usoSuelo;
    }

    public void setUsoSuelo(UsoSuelo usoSuelo) {
        this.usoSuelo = usoSuelo;
    }

    public Zonificacion getZonificacion() {
        return zonificacion;
    }

    public void setZonificacion(Zonificacion zonificacion) {
        this.zonificacion = zonificacion;
    }
    
}
