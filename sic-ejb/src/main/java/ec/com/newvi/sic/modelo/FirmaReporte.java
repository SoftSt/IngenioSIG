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
@Table(name = "cat_adm_firma_reporte", schema = "public")
public class FirmaReporte implements Serializable {
    
    @Id
    @SequenceGenerator(name = "FIRMAREPORTE_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_adm_firma_reporte_frr_cod_secuencia_seq", schema = "public")
    @GeneratedValue(generator = "FIRMAREPORTE_CODIGO_GENERATOR")
    @Column(name = "frr_cod_secuencial")
    private Integer frrCod;
    
    @Size(max = 150)
    @Column(name = "frr_txt_nombre")
    private String frrNombre;
    
    @Size(max = 150)
    @Column(name = "frr_txt_departamento")
    private String frrDepartamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "frr_sts_estado")
    private EnumEstadoRegistro frrEstado;
        
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

    public Integer getFrrCod() {
        return frrCod;
    }

    public void setFrrCod(Integer frrCod) {
        this.frrCod = frrCod;
    }

    public String getFrrNombre() {
        return frrNombre;
    }

    public void setFrrNombre(String frrNombre) {
        this.frrNombre = frrNombre;
    }

    public String getFrrDepartamento() {
        return frrDepartamento;
    }

    public void setFrrDepartamento(String frrDepartamento) {
        this.frrDepartamento = frrDepartamento;
    }

    public EnumEstadoRegistro getFrrEstado() {
        return frrEstado;
    }

    public void setFrrEstado(EnumEstadoRegistro frrEstado) {
        this.frrEstado = frrEstado;
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
}
