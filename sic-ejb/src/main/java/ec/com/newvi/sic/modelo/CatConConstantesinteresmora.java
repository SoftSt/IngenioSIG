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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Spectro-PC
 */
@Entity
@Table(name = "cat_con_constantesinteresmora")
public class CatConConstantesinteresmora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_interesmora")
    private Integer codInteresmora;
    @Size(max = 5)
    @Column(name = "sts_anio")
    private String stsAnio;
    @Size(max = 5)
    @Column(name = "sts_anioaplica")
    private String stsAnioaplica;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_monto")
    private BigDecimal valMonto;

    public CatConConstantesinteresmora() {
    }

    public CatConConstantesinteresmora(Integer codInteresmora) {
        this.codInteresmora = codInteresmora;
    }

    public Integer getCodInteresmora() {
        return codInteresmora;
    }

    public void setCodInteresmora(Integer codInteresmora) {
        this.codInteresmora = codInteresmora;
    }

    public String getStsAnio() {
        return stsAnio;
    }

    public void setStsAnio(String stsAnio) {
        this.stsAnio = stsAnio;
    }

    public String getStsAnioaplica() {
        return stsAnioaplica;
    }

    public void setStsAnioaplica(String stsAnioaplica) {
        this.stsAnioaplica = stsAnioaplica;
    }

    public BigDecimal getValMonto() {
        return valMonto;
    }

    public void setValMonto(BigDecimal valMonto) {
        this.valMonto = valMonto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codInteresmora != null ? codInteresmora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatConConstantesinteresmora)) {
            return false;
        }
        CatConConstantesinteresmora other = (CatConConstantesinteresmora) object;
        if ((this.codInteresmora == null && other.codInteresmora != null) || (this.codInteresmora != null && !this.codInteresmora.equals(other.codInteresmora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.CatConConstantesinteresmora[ codInteresmora=" + codInteresmora + " ]";
    }
    
}
