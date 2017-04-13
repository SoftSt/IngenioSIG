/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "cat_cat_obras", schema = "public")

public class ContribucionMejoras implements Serializable {

    
    @OneToMany(mappedBy = "codObras")
    private Collection<ObrasDetalle> obrasdetalleCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CONTRIBUCION_MEJORAS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_obras_cod_obras_seq", schema = "public")
    @GeneratedValue(generator = "CONTRIBUCION_MEJORAS_CODIGO_GENERATOR")
    @Column(name = "cod_obras")
    private Integer codObras;
    @Size(max = 250)
    @Column(name = "nom_obras")
    private String nomObras;
    @Column(name = "val_anioaplicacion")
    private Integer valAnioaplicacion;
    @Column(name = "val_anioobra")
    private Integer valAnioobra;
    @Column(name = "val_aniodeprecia")
    private Integer valAniodeprecia;
    @Size(max = 2147483647)
    @Column(name = "txt_detalle")
    private String txtDetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_porcentaje")
    private BigDecimal valPorcentaje;
    @Column(name = "val_valor")
    private BigDecimal valValor;
    @Size(max = 25)
    @Column(name = "sts_aplicacionforma")
    private String stsAplicacionforma;
    @Column(name = "val_acobrar")
    private BigDecimal valAcobrar;
    @Column(name = "val_porcentajefrentistas")
    private BigDecimal valPorcentajefrentistas;
    @Column(name = "val_porcentajeavaluo")
    private BigDecimal valPorcentajeavaluo;
    @Column(name = "sts_porcentajeaplica")
    private Short stsPorcentajeaplica;
    @Enumerated(EnumType.STRING)
    @Column(name = "obr_estado")
    private EnumEstadoRegistro obrEstado;
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

    public ContribucionMejoras() {
    }

    public ContribucionMejoras(Integer codObras) {
        this.codObras = codObras;
    }

    public Integer getCodObras() {
        return codObras;
    }

    public void setCodObras(Integer codObras) {
        this.codObras = codObras;
    }

    public String getNomObras() {
        return nomObras;
    }

    public void setNomObras(String nomObras) {
        this.nomObras = nomObras;
    }

    public Integer getValAnioaplicacion() {
        return valAnioaplicacion;
    }

    public void setValAnioaplicacion(Integer valAnioaplicacion) {
        this.valAnioaplicacion = valAnioaplicacion;
    }

    public Integer getValAnioobra() {
        return valAnioobra;
    }

    public void setValAnioobra(Integer valAnioobra) {
        this.valAnioobra = valAnioobra;
    }

    public Integer getValAniodeprecia() {
        return valAniodeprecia;
    }

    public void setValAniodeprecia(Integer valAniodeprecia) {
        this.valAniodeprecia = valAniodeprecia;
    }

    public String getTxtDetalle() {
        return txtDetalle;
    }

    public void setTxtDetalle(String txtDetalle) {
        this.txtDetalle = txtDetalle;
    }

    public BigDecimal getValPorcentaje() {
        return valPorcentaje;
    }

    public void setValPorcentaje(BigDecimal valPorcentaje) {
        this.valPorcentaje = valPorcentaje;
    }

    public BigDecimal getValValor() {
        return valValor;
    }

    public void setValValor(BigDecimal valValor) {
        this.valValor = valValor;
    }

    public String getStsAplicacionforma() {
        return stsAplicacionforma;
    }

    public void setStsAplicacionforma(String stsAplicacionforma) {
        this.stsAplicacionforma = stsAplicacionforma;
    }

    public BigDecimal getValAcobrar() {
        return valAcobrar;
    }

    public void setValAcobrar(BigDecimal valAcobrar) {
        this.valAcobrar = valAcobrar;
    }

    public BigDecimal getValPorcentajefrentistas() {
        return valPorcentajefrentistas;
    }

    public void setValPorcentajefrentistas(BigDecimal valPorcentajefrentistas) {
        this.valPorcentajefrentistas = valPorcentajefrentistas;
    }

    public BigDecimal getValPorcentajeavaluo() {
        return valPorcentajeavaluo;
    }

    public void setValPorcentajeavaluo(BigDecimal valPorcentajeavaluo) {
        this.valPorcentajeavaluo = valPorcentajeavaluo;
    }

    public Short getStsPorcentajeaplica() {
        return stsPorcentajeaplica;
    }

    public void setStsPorcentajeaplica(Short stsPorcentajeaplica) {
        this.stsPorcentajeaplica = stsPorcentajeaplica;
    }

    public EnumEstadoRegistro getObrEstado() {
        return obrEstado;
    }

    public void setObrEstado(EnumEstadoRegistro obrEstado) {
        this.obrEstado = obrEstado;
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
        hash += (codObras != null ? codObras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContribucionMejoras)) {
            return false;
        }
        ContribucionMejoras other = (ContribucionMejoras) object;
        if ((this.codObras == null && other.codObras != null) || (this.codObras != null && !this.codObras.equals(other.codObras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.ContribucionMejoras[ codObras=" + codObras + " ]";
    }
    
    public Boolean esContribucionValida(){
        return (!ComunUtil.esNulo(this.obrEstado));
    }

    public Collection<ObrasDetalle> getObrasdetalleCollection() {
        return obrasdetalleCollection;
    }

    public void setObrasdetalleCollection(Collection<ObrasDetalle> obrasdetalleCollection) {
        this.obrasdetalleCollection = obrasdetalleCollection;
    }
    
}
