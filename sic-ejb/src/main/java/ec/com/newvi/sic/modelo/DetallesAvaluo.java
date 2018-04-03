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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cat_cat_detalles_avaluo")

public class DetallesAvaluo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "DETALLE_AVALUO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_detalles_avaluo_daval_id_seq", schema = "public")
    @GeneratedValue(generator = "DETALLE_AVALUO_CODIGO_GENERATOR")
    @Column(name = "daval_id")
    private Integer davalId;
    @Size(max = 16)
    @Column(name = "daval_padre")
    private String davalPadre;
    @Size(max = 10)
    @Column(name = "daval_relacion")
    private String davalRelacion;
    @Size(max = 100)
    @Column(name = "daval_valor")
    private String davalValor;
    @Size(max = 16)
    @Column(name = "daval_factor")
    private String davalFactor;
    @Size(max = 100)
    @Column(name = "daval_descripcion")
    private String davalDescripcion;
    @Enumerated(EnumType.STRING)
    @Column(name = "daval_estado")
    private EnumEstadoRegistro davalEstado;
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
    @JoinColumn(name = "domi_id", referencedColumnName = "domi_id")
    @ManyToOne
    private Dominios domiId;
    @JoinColumn(name = "fecav_id", referencedColumnName = "fecav_id")
    @ManyToOne
    private FechaAvaluo fecavId;
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;

    public DetallesAvaluo() {
    }

    public DetallesAvaluo(Integer davalId) {
        this.davalId = davalId;
    }

    public Integer getDavalId() {
        return davalId;
    }

    public void setDavalId(Integer davalId) {
        this.davalId = davalId;
    }

    public String getDavalPadre() {
        return davalPadre;
    }

    public void setDavalPadre(String davalPadre) {
        this.davalPadre = davalPadre;
    }

    public String getDavalRelacion() {
        return davalRelacion;
    }

    public void setDavalRelacion(String davalRelacion) {
        this.davalRelacion = davalRelacion;
    }

    public String getDavalValor() {
        return davalValor;
    }

    public void setDavalValor(String davalValor) {
        this.davalValor = davalValor;
    }

    public String getDavalFactor() {
        return davalFactor;
    }

    public void setDavalFactor(String davalFactor) {
        this.davalFactor = davalFactor;
    }

    public String getDavalDescripcion() {
        return davalDescripcion;
    }

    public void setDavalDescripcion(String davalDescripcion) {
        this.davalDescripcion = davalDescripcion;
    }

    public EnumEstadoRegistro getDavalEstado() {
        return davalEstado;
    }

    public void setDavalEstado(EnumEstadoRegistro davalEstado) {
        this.davalEstado = davalEstado;
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

    public Dominios getDomiId() {
        return domiId;
    }

    public void setDomiId(Dominios domiId) {
        this.domiId = domiId;
    }

    public FechaAvaluo getFecavId() {
        return fecavId;
    }

    public void setFecavId(FechaAvaluo fecavId) {
        this.fecavId = fecavId;
    }

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (davalId != null ? davalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesAvaluo)) {
            return false;
        }
        DetallesAvaluo other = (DetallesAvaluo) object;
        if ((this.davalId == null && other.davalId != null) || (this.davalId != null && !this.davalId.equals(other.davalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.DetallesAvaluo[ davalId=" + davalId + " ]";
    }
    
    public Boolean esAvaluoValido() {
        return (!ComunUtil.esNulo(this.davalEstado));
    }
    
}
