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
 * @author Spectro-PC
 */
@Entity
@Table(name = "cat_con_constantesdescuentos")

public class ConstantesDescuentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CONSTANTES_DESCUENTOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_con_constantesdescuentos_cod_constantesdescuentos_seq", schema = "public")
    @GeneratedValue(generator = "CONSTANTES_DESCUENTOS_CODIGO_GENERATOR")
    @Column(name = "cod_constantesdescuentos")
    private Integer codConstantesdescuentos;
    @Size(max = 5)
    @Column(name = "sts_anio")
    private String stsAnio;
    @Size(max = 25)
    @Column(name = "sts_mes")
    private String stsMes;
    @Size(max = 25)
    @Column(name = "sts_quincena")
    private String stsQuincena;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_valor")
    private BigDecimal valValor;
    @Size(max = 25)
    @Column(name = "sts_estado")
    private String stsEstado;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_descuento")
    private EnumEstadoRegistro estadoDescuento;
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

    public ConstantesDescuentos() {
    }

    public ConstantesDescuentos(Integer codConstantesdescuentos) {
        this.codConstantesdescuentos = codConstantesdescuentos;
    }

    public Integer getCodConstantesdescuentos() {
        return codConstantesdescuentos;
    }

    public void setCodConstantesdescuentos(Integer codConstantesdescuentos) {
        this.codConstantesdescuentos = codConstantesdescuentos;
    }

    public String getStsAnio() {
        return stsAnio;
    }

    public void setStsAnio(String stsAnio) {
        this.stsAnio = stsAnio;
    }

    public String getStsMes() {
        return stsMes;
    }

    public void setStsMes(String stsMes) {
        this.stsMes = stsMes;
    }

    public String getStsQuincena() {
        return stsQuincena;
    }

    public void setStsQuincena(String stsQuincena) {
        this.stsQuincena = stsQuincena;
    }

    public BigDecimal getValValor() {
        return valValor;
    }

    public void setValValor(BigDecimal valValor) {
        this.valValor = valValor;
    }

    public String getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(String stsEstado) {
        this.stsEstado = stsEstado;
    }

    public EnumEstadoRegistro getEstadoDescuento() {
        return estadoDescuento;
    }

    public void setEstadoDescuento(EnumEstadoRegistro estadoDescuento) {
        this.estadoDescuento = estadoDescuento;
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
        hash += (codConstantesdescuentos != null ? codConstantesdescuentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstantesDescuentos)) {
            return false;
        }
        ConstantesDescuentos other = (ConstantesDescuentos) object;
        if ((this.codConstantesdescuentos == null && other.codConstantesdescuentos != null) || (this.codConstantesdescuentos != null && !this.codConstantesdescuentos.equals(other.codConstantesdescuentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.CatConConstantesdescuentos[ codConstantesdescuentos=" + codConstantesdescuentos + " ]";
    }
    
    public Boolean esDescuentoValido() {
        return (!ComunUtil.esNulo(this.stsEstado));
    }
}
