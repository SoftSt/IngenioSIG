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
@Table(name = "cat_cat_avaluo")

public class Avaluo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "AVALUO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_avaluo_aval_id_seq", schema = "public")
    @GeneratedValue(generator = "AVALUO_CODIGO_GENERATOR")
    @Column(name = "aval_id")
    private Integer avalId;
    @Size(max = 100)
    @Column(name = "nomnomape")
    private String nomnomape;
    @Size(max = 15)
    @Column(name = "cod_cedularuc")
    private String codCedularuc;
    @Size(max = 25)
    @Column(name = "nom_codigocatastral")
    private String nomCodigocatastral;
    @Size(max = 150)
    @Column(name = "sts_barrio")
    private String stsBarrio;
    @Size(max = 100)
    @Column(name = "txt_direccion")
    private String txtDireccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_areapredio")
    private BigDecimal valAreapredio;
    @Column(name = "val_terreno")
    private BigDecimal valTerreno;
    @Column(name = "val_areaconstruccion")
    private BigDecimal valAreaconstruccion;
    @Column(name = "val_edifica")
    private BigDecimal valEdifica;
    @Column(name = "val_predio")
    private BigDecimal valPredio;
    @Column(name = "val_cem")
    private BigDecimal valCem;
    @Column(name = "val_noedifica")
    private BigDecimal valNoEdificacion;
    @Column(name = "val_construccion_obsoleta")
    private BigDecimal valConstruccionObsoleta;
    @Column(name = "val_emision")
    private BigDecimal valEmision;
    @Column(name = "val_bomberos")
    private BigDecimal valBomberos;
    @Column(name = "val_ambientales")
    private BigDecimal valAmbientales;
    @Column(name = "val_imppredial")
    private BigDecimal valImppredial;
    @Column(name = "val_impuesto")
    private BigDecimal valImpuesto;
    @Column(name = "val_descuento_exoneracion")
    private BigDecimal valDescuentosExoneraciones;
    @Column(name = "val_prom_factores")
    private BigDecimal valPromFactores;
    @Column(name = "val_precio_base")
    private BigDecimal valPrecioBase;
    @Size(max = 50)
    @Column(name = "cat_casosespeciales")
    private String catCasosespeciales;
    @Enumerated(EnumType.STRING)
    @Column(name = "aval_estado")
    private EnumEstadoRegistro avalEstado;
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
    @JoinColumn(name = "fecav_id", referencedColumnName = "fecav_id")
    @ManyToOne
    private FechaAvaluo fecavId;
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;

    public Avaluo() {
    }

    public Avaluo(Integer avalId) {
        this.avalId = avalId;
    }

    public Integer getAvalId() {
        return avalId;
    }

    public void setAvalId(Integer avalId) {
        this.avalId = avalId;
    }

    public String getNomnomape() {
        return nomnomape;
    }

    public void setNomnomape(String nomnomape) {
        this.nomnomape = nomnomape;
    }

    public String getCodCedularuc() {
        return codCedularuc;
    }

    public void setCodCedularuc(String codCedularuc) {
        this.codCedularuc = codCedularuc;
    }

    public String getStsBarrio() {
        return stsBarrio;
    }

    public void setStsBarrio(String stsBarrio) {
        this.stsBarrio = stsBarrio;
    }

    public String getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public BigDecimal getValAreapredio() {
        return valAreapredio;
    }

    public void setValAreapredio(BigDecimal valAreapredio) {
        this.valAreapredio = valAreapredio;
    }

    public BigDecimal getValTerreno() {
        return valTerreno;
    }

    public void setValTerreno(BigDecimal valTerreno) {
        this.valTerreno = valTerreno;
    }

    public BigDecimal getValAreaconstruccion() {
        return valAreaconstruccion;
    }

    public void setValAreaconstruccion(BigDecimal valAreaconstruccion) {
        this.valAreaconstruccion = valAreaconstruccion;
    }

    public BigDecimal getValEdifica() {
        return valEdifica;
    }

    public void setValEdifica(BigDecimal valEdifica) {
        this.valEdifica = valEdifica;
    }

    public String getNomCodigocatastral() {
        return nomCodigocatastral;
    }

    public void setNomCodigocatastral(String nomCodigocatastral) {
        this.nomCodigocatastral = nomCodigocatastral;
    }

    public BigDecimal getValPredio() {
        return valPredio;
    }

    public void setValPredio(BigDecimal valPredio) {
        this.valPredio = valPredio;
    }

    public BigDecimal getValCem() {
        return valCem;
    }

    public void setValCem(BigDecimal valCem) {
        this.valCem = valCem;
    }

    public BigDecimal getValImpuesto() {
        return valImpuesto;
    }

    public void setValImpuesto(BigDecimal valImpuesto) {
        this.valImpuesto = valImpuesto;
    }

    public BigDecimal getValNoEdificacion() {
        return valNoEdificacion;
    }

    public void setValNoEdificacion(BigDecimal valNoEdificacion) {
        this.valNoEdificacion = valNoEdificacion;
    }

    public BigDecimal getValConstruccionObsoleta() {
        return valConstruccionObsoleta;
    }

    public void setValConstruccionObsoleta(BigDecimal valConstruccionObsoleta) {
        this.valConstruccionObsoleta = valConstruccionObsoleta;
    }
    
    public BigDecimal getValEmision() {
        return valEmision;
    }

    public void setValEmision(BigDecimal valEmision) {
        this.valEmision = valEmision;
    }

    public BigDecimal getValBomberos() {
        return valBomberos;
    }

    public void setValBomberos(BigDecimal valBomberos) {
        this.valBomberos = valBomberos;
    }

    public BigDecimal getValAmbientales() {
        return valAmbientales;
    }

    public void setValAmbientales(BigDecimal valAmbientales) {
        this.valAmbientales = valAmbientales;
    }

    public BigDecimal getValImppredial() {
        return valImppredial;
    }

    public void setValImppredial(BigDecimal valImppredial) {
        this.valImppredial = valImppredial;
    }

    public String getCatCasosespeciales() {
        return catCasosespeciales;
    }

    public void setCatCasosespeciales(String catCasosespeciales) {
        this.catCasosespeciales = catCasosespeciales;
    }

    public BigDecimal getValDescuentosExoneraciones() {
        return valDescuentosExoneraciones;
    }

    public void setValDescuentosExoneraciones(BigDecimal valDescuentosExoneraciones) {
        this.valDescuentosExoneraciones = valDescuentosExoneraciones;
    }
    
    public EnumEstadoRegistro getAvalEstado() {
        return avalEstado;
    }

    public void setAvalEstado(EnumEstadoRegistro avalEstado) {
        this.avalEstado = avalEstado;
    }

    public BigDecimal getValPromFactores() {
        return valPromFactores;
    }

    public void setValPromFactores(BigDecimal valPromFactores) {
        this.valPromFactores = valPromFactores;
    }

    public BigDecimal getValPrecioBase() {
        return valPrecioBase;
    }

    public void setValPrecioBase(BigDecimal valPrecioBase) {
        this.valPrecioBase = valPrecioBase;
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
        hash += (avalId != null ? avalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaluo)) {
            return false;
        }
        Avaluo other = (Avaluo) object;
        if ((this.avalId == null && other.avalId != null) || (this.avalId != null && !this.avalId.equals(other.avalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Avaluo[ avalId=" + avalId + " ]";
    }
    
    public Boolean esAvaluoValido() {
        return (!ComunUtil.esNulo(this.avalEstado));
    }
    
}
