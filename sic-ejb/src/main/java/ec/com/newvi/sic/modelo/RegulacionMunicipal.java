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
@Table(name = "cat_pla_regulacionmunicipal", schema = "public")
public class RegulacionMunicipal implements Serializable {
    
    @Id
    @SequenceGenerator(name = "REGULACIONMUNICIPAL_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_pla_regulacionmunicipal_irm_cod_secuencial_seq", schema = "public")
    @GeneratedValue(generator = "REGULACIONMUNICIPAL_CODIGO_GENERATOR")
    @Column(name = "irm_cod_secuencial")
    private Integer codIRM;
    
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;
    
    @Size(max = 32)
    @Column(name = "irm_cod_informe")
    private String codigoInforme;

    @Column(name = "irm_txt_afectaciones")
    private String afectaciones;
    
    @Size(max = 150)
    @Column(name = "irm_txt_archivo")
    private String urlArchivo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "irm_sts_estado")
    private EnumEstadoRegistro estadoInforme;
        
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
    
    public Integer getCodIRM() {
        return codIRM;
    }

    public void setCodIRM(Integer codIRM) {
        this.codIRM = codIRM;
    }
    
    public String getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(String codigoInforme) {
        this.codigoInforme = codigoInforme;
    }

    public String getAfectaciones() {
        return afectaciones;
    }

    public void setAfectaciones(String afectaciones) {
        this.afectaciones = afectaciones;
    }  
    
    public String getUrlArchivo() {
        return urlArchivo;
    }
    
    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public EnumEstadoRegistro getEstadoInforme() {
        return estadoInforme;
    }

    public void setEstadoInforme(EnumEstadoRegistro estadoInforme) {
        this.estadoInforme = estadoInforme;
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

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }
    
    
}
