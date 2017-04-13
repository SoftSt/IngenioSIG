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
@Table(name = "cat_cat_obrasdetalle", schema = "public")

public class ObrasDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "OBRAS_DETALLE_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_obrasdetalle_cod_obrasdetalle_seq", schema = "public")
    @GeneratedValue(generator = "OBRAS_DETALLE_CODIGO_GENERATOR")
    @Column(name = "cod_obrasdetalle")
    private Integer codObrasdetalle;
    @Column(name = "cod_predio")
    private Integer codPredio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "obr_valor")
    private BigDecimal obrValor;
    @Column(name = "val_areafrente")
    private BigDecimal valAreafrente;
    @Column(name = "val_predio")
    private BigDecimal valPredio;
    @Enumerated(EnumType.STRING)
    @Column(name = "obd_estado")
    private EnumEstadoRegistro obdEstado;
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
    @JoinColumn(name = "cod_obras", referencedColumnName = "cod_obras")
    @ManyToOne
    private ContribucionMejoras codObras;
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;
    @JoinColumn(name = "nom_codigocatastral", referencedColumnName = "nom_codigocatastral")
    @ManyToOne
    private Predios nomCodigocatastral;

    public ObrasDetalle() {
    }

    public ObrasDetalle(Integer codObrasdetalle) {
        this.codObrasdetalle = codObrasdetalle;
    }

    public Integer getCodObrasdetalle() {
        return codObrasdetalle;
    }

    public void setCodObrasdetalle(Integer codObrasdetalle) {
        this.codObrasdetalle = codObrasdetalle;
    }

    public Integer getCodPredio() {
        return codPredio;
    }

    public void setCodPredio(Integer codPredio) {
        this.codPredio = codPredio;
    }

    public BigDecimal getObrValor() {
        return obrValor;
    }

    public void setObrValor(BigDecimal obrValor) {
        this.obrValor = obrValor;
    }

    public BigDecimal getValAreafrente() {
        return valAreafrente;
    }

    public void setValAreafrente(BigDecimal valAreafrente) {
        this.valAreafrente = valAreafrente;
    }

    public BigDecimal getValPredio() {
        return valPredio;
    }

    public void setValPredio(BigDecimal valPredio) {
        this.valPredio = valPredio;
    }

    public EnumEstadoRegistro getObdEstado() {
        return obdEstado;
    }

    public void setObdEstado(EnumEstadoRegistro obdEstado) {
        this.obdEstado = obdEstado;
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

    public ContribucionMejoras getCodObras() {
        return codObras;
    }

    public void setCodObras(ContribucionMejoras codObras) {
        this.codObras = codObras;
    }

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    public Predios getNomCodigocatastral() {
        return nomCodigocatastral;
    }

    public void setNomCodigocatastral(Predios nomCodigocatastral) {
        this.nomCodigocatastral = nomCodigocatastral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codObrasdetalle != null ? codObrasdetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObrasDetalle)) {
            return false;
        }
        ObrasDetalle other = (ObrasDetalle) object;
        if ((this.codObrasdetalle == null && other.codObrasdetalle != null) || (this.codObrasdetalle != null && !this.codObrasdetalle.equals(other.codObrasdetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Obrasdetalle[ codObrasdetalle=" + codObrasdetalle + " ]";
    }
    
    public Boolean esObraDetalleValida(){
        return (!ComunUtil.esNulo(this.obdEstado));
    }
    
}
