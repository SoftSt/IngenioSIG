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
@Table(name = "cat_adm_uso_suelo", schema = "public")
public class UsoSuelo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "USOSUELO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_adm_uso_suelo_uss_cod_secuencial_seq", schema = "public")
    @GeneratedValue(generator = "USOSUELO_CODIGO_GENERATOR")
    @Column(name = "uss_cod_secuencial")
    private Integer ussCod;
    
    @Size(max = 100)
    @Column(name = "uss_txt_nombre")
    private String ussNombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "uss_sts_estado")
    private EnumEstadoRegistro ussEstado;
        
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
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsoSuelo)) {
            return false;
        }
        UsoSuelo usoSuelo = (UsoSuelo) object;
        return !((this.ussCod == null && usoSuelo.ussCod != null) || (this.ussCod != null && !this.ussCod.equals(usoSuelo.ussCod)));
    }

    public Integer getUssCod() {
        return ussCod;
    }

    public void setUssCod(Integer ussCod) {
        this.ussCod = ussCod;
    }

    public String getUssNombre() {
        return ussNombre;
    }

    public void setUssNombre(String ussNombre) {
        this.ussNombre = ussNombre;
    }

    public EnumEstadoRegistro getUssEstado() {
        return ussEstado;
    }

    public void setUssEstado(EnumEstadoRegistro ussEstado) {
        this.ussEstado = ussEstado;
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
