/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fecav_id")
    private Integer fecavId;
    @Column(name = "fecav_fechaavaluo")
    @Temporal(TemporalType.DATE)
    private Date fecavFechaavaluo;
    @Column(name = "fecav_estado")
    private Character fecavEstado;
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
    @OneToMany(mappedBy = "fecavId")
    private Collection<Avaluo> avaluoCollection;

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

    public Character getFecavEstado() {
        return fecavEstado;
    }

    public void setFecavEstado(Character fecavEstado) {
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

    public Collection<Avaluo> getAvaluoCollection() {
        return avaluoCollection;
    }

    public void setAvaluoCollection(Collection<Avaluo> avaluoCollection) {
        this.avaluoCollection = avaluoCollection;
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
    
}
