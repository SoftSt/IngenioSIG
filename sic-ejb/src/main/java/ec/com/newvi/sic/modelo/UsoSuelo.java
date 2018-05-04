/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
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
@Table(name = "cat_cat_usosuelo")
@NamedQueries({
    @NamedQuery(name = "UsoSuelo.findAll", query = "SELECT u FROM UsoSuelo u")})
public class UsoSuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "USO_SUELO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_usosuelo_cod_usopredio_seq", schema = "public")
    @GeneratedValue(generator = "USO_SUELO_CODIGO_GENERATOR")
    @Column(name = "cod_usopredio")
    private Integer codUsopredio;
    @Size(max = 25)
    @Column(name = "sts_codigo")
    private String stsCodigo;
    @Size(max = 100)
    @Column(name = "sts_grupo")
    private String stsGrupo;
    @Size(max = 100)
    @Column(name = "sts_subgrupo")
    private String stsSubgrupo;
    @Size(max = 100)
    @Column(name = "sts_descripcion")
    private String stsDescripcion;
    @Size(max = 25)
    @Column(name = "sts_estado")
    private String stsEstado;
    @Enumerated(EnumType.STRING)
    @Column(name = "uss_estado")
    private EnumEstadoRegistro ussEstado;
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
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne(optional = false)
    private Predios codCatastral;

    public UsoSuelo() {
    }

    public UsoSuelo(Integer codUsopredio) {
        this.codUsopredio = codUsopredio;
    }

    public Integer getCodUsopredio() {
        return codUsopredio;
    }

    public void setCodUsopredio(Integer codUsopredio) {
        this.codUsopredio = codUsopredio;
    }

    public String getStsCodigo() {
        return stsCodigo;
    }

    public void setStsCodigo(String stsCodigo) {
        this.stsCodigo = stsCodigo;
    }

    public String getStsGrupo() {
        return stsGrupo;
    }

    public void setStsGrupo(String stsGrupo) {
        this.stsGrupo = stsGrupo;
    }

    public String getStsSubgrupo() {
        return stsSubgrupo;
    }

    public void setStsSubgrupo(String stsSubgrupo) {
        this.stsSubgrupo = stsSubgrupo;
    }

    public String getStsDescripcion() {
        return stsDescripcion;
    }

    public void setStsDescripcion(String stsDescripcion) {
        this.stsDescripcion = stsDescripcion;
    }

    public String getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(String stsEstado) {
        this.stsEstado = stsEstado;
    }

    public EnumEstadoRegistro getUssEstado() {
        return ussEstado;
    }

    public void setUssEstado(EnumEstadoRegistro ussEstado) {
        this.ussEstado = ussEstado;
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

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsopredio != null ? codUsopredio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsoSuelo)) {
            return false;
        }
        UsoSuelo other = (UsoSuelo) object;
        if ((this.codUsopredio == null && other.codUsopredio != null) || (this.codUsopredio != null && !this.codUsopredio.equals(other.codUsopredio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.UsoSuelo[ codUsopredio=" + codUsopredio + " ]";
    }
    
}
