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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "cat_con_constantesimpuestos")

public class ConstantesImpuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CONSTANTES_IMPUESTOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_con_constantesimpuestos_cod_constantesimpuestos_seq", schema = "public")
    @GeneratedValue(generator = "CONSTANTES_IMPUESTOS_CODIGO_GENERATOR")
    @Column(name = "cod_constantesimpuestos")
    private Integer codConstantesimpuestos;
    @Size(max = 5)
    @Column(name = "sts_anio")
    private String stsAnio;
    @Size(max = 25)
    @Column(name = "sts_tipo")
    private String stsTipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_bomberos")
    private BigDecimal valBomberos;
    @Column(name = "val_serviciosadministrativos")
    private BigDecimal valServiciosadministrativos;
    @Column(name = "val_cem")
    private BigDecimal valCem;
    @Column(name = "val_basura")
    private BigDecimal valBasura;
    @Column(name = "val_ambientales")
    private BigDecimal valAmbientales;
    @Column(name = "val_tasaaplicada")
    private BigDecimal valTasaaplicada;
    @Column(name = "val_construcion")
    private BigDecimal valConstrucion;
    @Column(name = "val_forestales")
    private BigDecimal valForestales;
    @Column(name = "val_saludpublica")
    private BigDecimal valSaludpublica;
    @Column(name = "val_educacion")
    private BigDecimal valEducacion;
    @Column(name = "val_rrp")
    private BigDecimal valRrp;
    @Column(name = "val_acilo")
    private BigDecimal valAcilo;
    @Column(name = "val_patrimonio")
    private BigDecimal valPatrimonio;
    @Column(name = "val_noedifica")
    private BigDecimal valNoedifica;
    @Column(name = "val_timiteinferior")
    private BigDecimal valTimiteinferior;
    @Column(name = "val_seguridad")
    private BigDecimal valSeguridad;
    @Column(name = "val_rebajag")
    private BigDecimal valRebajag;
    @Enumerated(EnumType.STRING)
    @Column(name = "conimp_estado")
    private EnumEstadoRegistro conImpuestoEstado;
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

    public ConstantesImpuestos() {
    }

    public ConstantesImpuestos(Integer codConstantesimpuestos) {
        this.codConstantesimpuestos = codConstantesimpuestos;
    }

    public EnumEstadoRegistro getconImpuestoEstado() {
        return conImpuestoEstado;
    }

    public void setconImpuestoEstado(EnumEstadoRegistro conImpuestoEstado) {
        this.conImpuestoEstado = conImpuestoEstado;
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
    
    

    public Integer getCodConstantesimpuestos() {
        return codConstantesimpuestos;
    }

    public void setCodConstantesimpuestos(Integer codConstantesimpuestos) {
        this.codConstantesimpuestos = codConstantesimpuestos;
    }

    public String getStsAnio() {
        return stsAnio;
    }

    public void setStsAnio(String stsAnio) {
        this.stsAnio = stsAnio;
    }

    public String getStsTipo() {
        return stsTipo;
    }

    public void setStsTipo(String stsTipo) {
        this.stsTipo = stsTipo;
    }

    public BigDecimal getValBomberos() {
        return valBomberos;
    }

    public void setValBomberos(BigDecimal valBomberos) {
        this.valBomberos = valBomberos;
    }

    public BigDecimal getValServiciosadministrativos() {
        return valServiciosadministrativos;
    }

    public void setValServiciosadministrativos(BigDecimal valServiciosadministrativos) {
        this.valServiciosadministrativos = valServiciosadministrativos;
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

    public BigDecimal getValTasaaplicada() {
        return valTasaaplicada;
    }

    public void setValTasaaplicada(BigDecimal valTasaaplicada) {
        this.valTasaaplicada = valTasaaplicada;
    }

    public BigDecimal getValConstrucion() {
        return valConstrucion;
    }

    public void setValConstrucion(BigDecimal valConstrucion) {
        this.valConstrucion = valConstrucion;
    }

    public BigDecimal getValForestales() {
        return valForestales;
    }

    public void setValForestales(BigDecimal valForestales) {
        this.valForestales = valForestales;
    }

    public BigDecimal getValSaludpublica() {
        return valSaludpublica;
    }

    public void setValSaludpublica(BigDecimal valSaludpublica) {
        this.valSaludpublica = valSaludpublica;
    }

    public BigDecimal getValEducacion() {
        return valEducacion;
    }

    public void setValEducacion(BigDecimal valEducacion) {
        this.valEducacion = valEducacion;
    }

    public BigDecimal getValRrp() {
        return valRrp;
    }

    public void setValRrp(BigDecimal valRrp) {
        this.valRrp = valRrp;
    }

    public BigDecimal getValAcilo() {
        return valAcilo;
    }

    public void setValAcilo(BigDecimal valAcilo) {
        this.valAcilo = valAcilo;
    }

    public BigDecimal getValPatrimonio() {
        return valPatrimonio;
    }

    public void setValPatrimonio(BigDecimal valPatrimonio) {
        this.valPatrimonio = valPatrimonio;
    }

    public BigDecimal getValNoedifica() {
        return valNoedifica;
    }

    public void setValNoedifica(BigDecimal valNoedifica) {
        this.valNoedifica = valNoedifica;
    }

    public BigDecimal getValTimiteinferior() {
        return valTimiteinferior;
    }

    public void setValTimiteinferior(BigDecimal valTimiteinferior) {
        this.valTimiteinferior = valTimiteinferior;
    }

    public BigDecimal getValSeguridad() {
        return valSeguridad;
    }

    public void setValSeguridad(BigDecimal valSeguridad) {
        this.valSeguridad = valSeguridad;
    }

    public BigDecimal getValRebajag() {
        return valRebajag;
    }

    public void setValRebajag(BigDecimal valRebajag) {
        this.valRebajag = valRebajag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codConstantesimpuestos != null ? codConstantesimpuestos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstantesImpuestos)) {
            return false;
        }
        ConstantesImpuestos other = (ConstantesImpuestos) object;
        if ((this.codConstantesimpuestos == null && other.codConstantesimpuestos != null) || (this.codConstantesimpuestos != null && !this.codConstantesimpuestos.equals(other.codConstantesimpuestos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.ConstantesImpuestos[ codConstantesimpuestos=" + codConstantesimpuestos + " ]";
    }
    
    public Boolean esConstanteImpuestosValido(){
        return (!ComunUtil.esNulo(this.conImpuestoEstado));
    }
    
}
