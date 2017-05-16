/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author israelavila
 */
@Entity
@Table(name = "cat_cat_pisosdetalle")
public class PisoDetalle implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_pisodetalle")
    private Integer codPisoDetalle;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_pisos", referencedColumnName = "cod_pisos")
    private Pisos piso;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sts_codigo", referencedColumnName = "domi_codigo")
    private Dominios dominio;
    
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

    public Integer getCodPisoDetalle() {
        return codPisoDetalle;
    }

    public void setCodPisoDetalle(Integer codPisoDetalle) {
        this.codPisoDetalle = codPisoDetalle;
    }

    public Pisos getPiso() {
        return piso;
    }

    public void setPiso(Pisos piso) {
        this.piso = piso;
    }

    public Dominios getDominio() {
        return dominio;
    }

    public void setDominio(Dominios dominio) {
        this.dominio = dominio;
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
        hash += (codPisoDetalle != null ? codPisoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pisos)) {
            return false;
        }
        PisoDetalle other = (PisoDetalle) object;
        return !((this.codPisoDetalle == null && other.codPisoDetalle != null) || (this.codPisoDetalle != null && !this.codPisoDetalle.equals(other.codPisoDetalle)));
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Pisos[ codPisos=" + codPisoDetalle + " ]";
    }
    public Boolean esPisoValido() {
        return (!ComunUtil.esNulo(this.codPisoDetalle));
    }
    

    
}
