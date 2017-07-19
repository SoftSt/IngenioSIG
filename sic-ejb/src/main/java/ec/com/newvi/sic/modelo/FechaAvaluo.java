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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "cat_cat_fechaavaluo")

public class FechaAvaluo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "FECHAAVALUO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_fechaavaluo_fecav_id_seq", schema = "public")
    @GeneratedValue(generator = "FECHAAVALUO_CODIGO_GENERATOR")
    @Column(name = "fecav_id")
    private Integer fecavId;
    @Column(name = "fecav_fechaavaluo")
    @Temporal(TemporalType.DATE)
    private Date fecavFechaavaluo;
    @Enumerated(EnumType.STRING)
    @Column(name = "fecav_estado")
    private EnumEstadoRegistro fecavEstado;
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

    public FechaAvaluo() {
    }

    public FechaAvaluo(Integer fecavId) {
        this.fecavId = fecavId;
    }

    public Integer getFecavId() {
        return fecavId;
    }

    public void setFecavId(Integer fecavId) {
        this.fecavId = fecavId;
    }

    public Date getFecavFechaavaluo() {
        return fecavFechaavaluo;
    }

    public void setFecavFechaavaluo(Date fecavFechaavaluo) {
        this.fecavFechaavaluo = fecavFechaavaluo;
    }

    public EnumEstadoRegistro getFecavEstado() {
        return fecavEstado;
    }

    public void setFecavEstado(EnumEstadoRegistro fecavEstado) {
        this.fecavEstado = fecavEstado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecavId != null ? fecavId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaAvaluo)) {
            return false;
        }
        FechaAvaluo other = (FechaAvaluo) object;
        if ((this.fecavId == null && other.fecavId != null) || (this.fecavId != null && !this.fecavId.equals(other.fecavId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.FechaAvaluo[ fecavId=" + fecavId + " ]";
    }
    
    public Boolean esFechaAvaluoValido() {
        return (!ComunUtil.esNulo(this.fecavEstado));
    }
}
