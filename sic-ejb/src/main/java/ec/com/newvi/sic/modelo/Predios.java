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
@Table(name = "cat_cat_predios", schema = "public")

public class Predios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PREDIO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_predios_cod_catastral_seq", schema = "public")
    @GeneratedValue(generator = "PREDIO_CODIGO_GENERATOR")
    @Column(name = "cod_catastral")
    private Integer codCatastral;
    @Size(max = 25)
    @Column(name = "sts_tipo")
    private String stsTipo;
    @Size(max = 25)
    @Column(name = "nom_codigocatastral")
    private String nomCodigocatastral;
    @Size(max = 25)
    @Column(name = "nom_codigocatastralanterior")
    private String nomCodigocatastralanterior;
    @Size(max = 6)
    @Column(name = "cod_dpa")
    private String codDpa;
    @Size(max = 2)
    @Column(name = "cod_zona")
    private String codZona;
    @Size(max = 2)
    @Column(name = "cod_sector")
    private String codSector;
    @Size(max = 3)
    @Column(name = "cod_manzana")
    private String codManzana;
    @Size(max = 3)
    @Column(name = "cod_predio")
    private String codPredio;
    @Size(max = 2)
    @Column(name = "cod_regimentenencia")
    private String codRegimentenencia;
    @Size(max = 3)
    @Column(name = "cod_horizontal")
    private String codHorizontal;
    @Size(max = 100)
    @Column(name = "nom_predio")
    private String nomPredio;
    @Size(max = 25)
    @Column(name = "sts_urbanomarginal")
    private String stsUrbanomarginal;
    @Size(max = 25)
    @Column(name = "sts_sectorhomogeneo")
    private String stsSectorhomogeneo;
    @Size(max = 25)
    @Column(name = "sts_barrio")
    private String stsBarrio;
    @Size(max = 100)
    @Column(name = "txt_direccion")
    private String txtDireccion;
    @Size(max = 100)
    @Column(name = "txt_ubicacion")
    private String txtUbicacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_areapredio")
    private BigDecimal valAreapredio;
    @Column(name = "val_areafrente")
    private BigDecimal valAreafrente;
    @Column(name = "val_areafondo")
    private BigDecimal valAreafondo;
    @Column(name = "val_areaconstruccion")
    private BigDecimal valAreaconstruccion;
    @Size(max = 25)
    @Column(name = "sts_planospredio")
    private String stsPlanospredio;
    @Column(name = "vat_terreno")
    private BigDecimal vatTerreno;
    @Column(name = "val_edifica")
    private BigDecimal valEdifica;
    @Column(name = "val_predio")
    private BigDecimal valPredio;
    @Column(name = "val_impuesto")
    private BigDecimal valImpuesto;
    @Column(name = "val_bomberos")
    private BigDecimal valBomberos;
    @Column(name = "val_emision")
    private BigDecimal valEmision;
    @Column(name = "val_cem")
    private BigDecimal valCem;
    @Column(name = "val_basura")
    private BigDecimal valBasura;
    @Column(name = "val_ambientales")
    private BigDecimal valAmbientales;
    @Column(name = "val_imppredial")
    private BigDecimal valImppredial;
    @Column(name = "val_otro1")
    private BigDecimal valOtro1;
    @Column(name = "val_otro2")
    private BigDecimal valOtro2;
    @Size(max = 50)
    @Column(name = "cat_casosespeciales")
    private String catCasosespeciales;
    @Size(max = 2147483647)
    @Column(name = "txt_observacion")
    private String txtObservacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "cat_estado")
    private EnumEstadoRegistro catEstado;
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
    @OneToMany(mappedBy = "codCatastral")
    private Collection<ObrasDetalle> obrasdetalleCollection;
    @OneToMany(mappedBy = "nomCodigocatastral")
    private Collection<ObrasDetalle> obrasdetalleCollection1;

    public Predios() {
    }

    public Predios(Integer codCatastral) {
        this.codCatastral = codCatastral;
    }

    public Integer getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Integer codCatastral) {
        this.codCatastral = codCatastral;
    }

    public String getStsTipo() {
        return stsTipo;
    }

    public void setStsTipo(String stsTipo) {
        this.stsTipo = stsTipo;
    }

    public String getNomCodigocatastral() {
        return nomCodigocatastral;
    }

    public void setNomCodigocatastral(String nomCodigocatastral) {
        this.nomCodigocatastral = nomCodigocatastral;
    }

    public String getNomCodigocatastralanterior() {
        return nomCodigocatastralanterior;
    }

    public void setNomCodigocatastralanterior(String nomCodigocatastralanterior) {
        this.nomCodigocatastralanterior = nomCodigocatastralanterior;
    }

    public String getCodDpa() {
        return codDpa;
    }

    public void setCodDpa(String codDpa) {
        this.codDpa = codDpa;
    }

    public String getCodZona() {
        return codZona;
    }

    public void setCodZona(String codZona) {
        this.codZona = codZona;
    }

    public String getCodSector() {
        return codSector;
    }

    public void setCodSector(String codSector) {
        this.codSector = codSector;
    }

    public String getCodManzana() {
        return codManzana;
    }

    public void setCodManzana(String codManzana) {
        this.codManzana = codManzana;
    }

    public String getCodPredio() {
        return codPredio;
    }

    public void setCodPredio(String codPredio) {
        this.codPredio = codPredio;
    }

    public String getCodRegimentenencia() {
        return codRegimentenencia;
    }

    public void setCodRegimentenencia(String codRegimentenencia) {
        this.codRegimentenencia = codRegimentenencia;
    }

    public String getCodHorizontal() {
        return codHorizontal;
    }

    public void setCodHorizontal(String codHorizontal) {
        this.codHorizontal = codHorizontal;
    }

    public String getNomPredio() {
        return nomPredio;
    }

    public void setNomPredio(String nomPredio) {
        this.nomPredio = nomPredio;
    }

    public String getStsUrbanomarginal() {
        return stsUrbanomarginal;
    }

    public void setStsUrbanomarginal(String stsUrbanomarginal) {
        this.stsUrbanomarginal = stsUrbanomarginal;
    }

    public String getStsSectorhomogeneo() {
        return stsSectorhomogeneo;
    }

    public void setStsSectorhomogeneo(String stsSectorhomogeneo) {
        this.stsSectorhomogeneo = stsSectorhomogeneo;
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

    public String getTxtUbicacion() {
        return txtUbicacion;
    }

    public void setTxtUbicacion(String txtUbicacion) {
        this.txtUbicacion = txtUbicacion;
    }

    public BigDecimal getValAreapredio() {
        return valAreapredio;
    }

    public void setValAreapredio(BigDecimal valAreapredio) {
        this.valAreapredio = valAreapredio;
    }

    public BigDecimal getValAreafrente() {
        return valAreafrente;
    }

    public void setValAreafrente(BigDecimal valAreafrente) {
        this.valAreafrente = valAreafrente;
    }

    public BigDecimal getValAreafondo() {
        return valAreafondo;
    }

    public void setValAreafondo(BigDecimal valAreafondo) {
        this.valAreafondo = valAreafondo;
    }

    public BigDecimal getValAreaconstruccion() {
        return valAreaconstruccion;
    }

    public void setValAreaconstruccion(BigDecimal valAreaconstruccion) {
        this.valAreaconstruccion = valAreaconstruccion;
    }

    public String getStsPlanospredio() {
        return stsPlanospredio;
    }

    public void setStsPlanospredio(String stsPlanospredio) {
        this.stsPlanospredio = stsPlanospredio;
    }

    public BigDecimal getVatTerreno() {
        return vatTerreno;
    }

    public void setVatTerreno(BigDecimal vatTerreno) {
        this.vatTerreno = vatTerreno;
    }

    public BigDecimal getValEdifica() {
        return valEdifica;
    }

    public void setValEdifica(BigDecimal valEdifica) {
        this.valEdifica = valEdifica;
    }

    public BigDecimal getValPredio() {
        return valPredio;
    }

    public void setValPredio(BigDecimal valPredio) {
        this.valPredio = valPredio;
    }

    public BigDecimal getValImpuesto() {
        return valImpuesto;
    }

    public void setValImpuesto(BigDecimal valImpuesto) {
        this.valImpuesto = valImpuesto;
    }

    public BigDecimal getValBomberos() {
        return valBomberos;
    }

    public void setValBomberos(BigDecimal valBomberos) {
        this.valBomberos = valBomberos;
    }

    public BigDecimal getValEmision() {
        return valEmision;
    }

    public void setValEmision(BigDecimal valEmision) {
        this.valEmision = valEmision;
    }

    public BigDecimal getValCem() {
        return valCem;
    }

    public void setValCem(BigDecimal valCem) {
        this.valCem = valCem;
    }

    public BigDecimal getValBasura() {
        return valBasura;
    }

    public void setValBasura(BigDecimal valBasura) {
        this.valBasura = valBasura;
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

    public BigDecimal getValOtro1() {
        return valOtro1;
    }

    public void setValOtro1(BigDecimal valOtro1) {
        this.valOtro1 = valOtro1;
    }

    public BigDecimal getValOtro2() {
        return valOtro2;
    }

    public void setValOtro2(BigDecimal valOtro2) {
        this.valOtro2 = valOtro2;
    }

    public String getCatCasosespeciales() {
        return catCasosespeciales;
    }

    public void setCatCasosespeciales(String catCasosespeciales) {
        this.catCasosespeciales = catCasosespeciales;
    }

    public String getTxtObservacion() {
        return txtObservacion;
    }

    public void setTxtObservacion(String txtObservacion) {
        this.txtObservacion = txtObservacion;
    }

    public EnumEstadoRegistro getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(EnumEstadoRegistro catEstado) {
        this.catEstado = catEstado;
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

    public Collection<ObrasDetalle> getObrasdetalleCollection() {
        return obrasdetalleCollection;
    }

    public void setObrasdetalleCollection(Collection<ObrasDetalle> obrasdetalleCollection) {
        this.obrasdetalleCollection = obrasdetalleCollection;
    }

    public Collection<ObrasDetalle> getObrasdetalleCollection1() {
        return obrasdetalleCollection1;
    }

    public void setObrasdetalleCollection1(Collection<ObrasDetalle> obrasdetalleCollection1) {
        this.obrasdetalleCollection1 = obrasdetalleCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCatastral != null ? codCatastral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predios)) {
            return false;
        }
        Predios other = (Predios) object;
        if ((this.codCatastral == null && other.codCatastral != null) || (this.codCatastral != null && !this.codCatastral.equals(other.codCatastral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Predios[ codCatastral=" + codCatastral + " ]";
    }

    public Boolean esPredioValido() {
        return (!ComunUtil.esNulo(this.catEstado));
    }

}
