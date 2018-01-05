/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "cat_con_constantesinteresmora")
public class ConstantesInteresMora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CONSTANTES_INTERESES_MORA_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_con_constantesinteresmora_cod_interesmora_seq", schema = "public")
    @GeneratedValue(generator = "CONSTANTES_INTERESES_MORA_CODIGO_GENERATOR")
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
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_interesmora")
    private EnumEstadoRegistro estadointeresmora;
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
    @Column(name = "num_anios")
    private Integer numeroAnios;

    public ConstantesInteresMora() {
    }

    public ConstantesInteresMora(Integer codInteresmora) {
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

    public EnumEstadoRegistro getEstadointeresmora() {
        return estadointeresmora;
    }

    public void setEstadointeresmora(EnumEstadoRegistro estadointeresmora) {
        this.estadointeresmora = estadointeresmora;
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

    public Integer getNumeroAnios() {
        return numeroAnios;
    }

    public void setNumeroAnios(Integer numeroAnios) {
        this.numeroAnios = numeroAnios;
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
        if (!(object instanceof ConstantesInteresMora)) {
            return false;
        }
        ConstantesInteresMora other = (ConstantesInteresMora) object;
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
