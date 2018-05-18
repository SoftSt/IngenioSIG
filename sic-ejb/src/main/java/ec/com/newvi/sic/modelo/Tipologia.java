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
@Table(name = "cat_adm_tipologia", schema = "public")
public class Tipologia implements Serializable {
    
    @Id
    @SequenceGenerator(name = "TIPOLOGIA_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_adm_tipologia_tpl_cod_secuencial_seq", schema = "public")
    @GeneratedValue(generator = "TIPOLOGIA_CODIGO_GENERATOR")
    @Column(name = "tpl_cod_secuencial")
    private Integer tplCod;
    
    @Size(max = 100)
    @Column(name = "tpl_txt_nombre")
    private String tplNombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tpl_sts_estado")
    private EnumEstadoRegistro tplEstado;
        
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
        if (!(object instanceof Tipologia)) {
            return false;
        }
        Tipologia tipologia = (Tipologia) object;
        return !((this.tplCod == null && tipologia.tplCod != null) || (this.tplCod != null && !this.tplCod.equals(tipologia.tplCod)));
    }

    public Integer getTplCod() {
        return tplCod;
    }

    public void setTplCod(Integer tplCod) {
        this.tplCod = tplCod;
    }

    public String getTplNombre() {
        return tplNombre;
    }

    public void setTplNombre(String tplNombre) {
        this.tplNombre = tplNombre;
    }

    public EnumEstadoRegistro getTplEstado() {
        return tplEstado;
    }

    public void setTplEstado(EnumEstadoRegistro tplEstado) {
        this.tplEstado = tplEstado;
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
