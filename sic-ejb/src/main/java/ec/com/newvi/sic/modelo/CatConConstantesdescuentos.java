/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Spectro-PC
 */
@Entity
@Table(name = "cat_con_constantesdescuentos")
@NamedQueries({
    @NamedQuery(name = "CatConConstantesdescuentos.findAll", query = "SELECT c FROM CatConConstantesdescuentos c")})
public class CatConConstantesdescuentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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

    public CatConConstantesdescuentos() {
    }

    public CatConConstantesdescuentos(Integer codConstantesdescuentos) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codConstantesdescuentos != null ? codConstantesdescuentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatConConstantesdescuentos)) {
            return false;
        }
        CatConConstantesdescuentos other = (CatConConstantesdescuentos) object;
        if ((this.codConstantesdescuentos == null && other.codConstantesdescuentos != null) || (this.codConstantesdescuentos != null && !this.codConstantesdescuentos.equals(other.codConstantesdescuentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.CatConConstantesdescuentos[ codConstantesdescuentos=" + codConstantesdescuentos + " ]";
    }
    
}
