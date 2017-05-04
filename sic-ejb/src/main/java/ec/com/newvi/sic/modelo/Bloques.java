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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "cat_cat_bloques", schema = "public")

public class Bloques implements Serializable {

    @OneToMany(mappedBy = "codBloques")
    private Collection<Pisos> pisosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "BLOQUE_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_obras_cod_obras_seq", schema = "public")
    @GeneratedValue(generator = "BLOQUE_CODIGO_GENERATOR")
    @Column(name = "cod_bloques")
    private Integer codBloques;
    @Size(max = 25)
    @Column(name = "nom_bloque")
    private String nomBloque;
    @Size(max = 10)
    @Column(name = "val_nropisos")
    private String valNropisos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_areabloque")
    private BigDecimal valAreabloque;
    @Column(name = "val_bloque")
    private BigDecimal valBloque;
    @Enumerated(EnumType.STRING)
    @Column(name = "blo_estado")
    private EnumEstadoRegistro bloEstado;
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
    @ManyToOne
    private Predios codCatastral;

    public Bloques() {
    }

    public Bloques(Integer codBloques) {
        this.codBloques = codBloques;
    }

    public Integer getCodBloques() {
        return codBloques;
    }

    public void setCodBloques(Integer codBloques) {
        this.codBloques = codBloques;
    }

    public String getNomBloque() {
        return nomBloque;
    }

    public void setNomBloque(String nomBloque) {
        this.nomBloque = nomBloque;
    }

    public String getValNropisos() {
        return valNropisos;
    }

    public void setValNropisos(String valNropisos) {
        this.valNropisos = valNropisos;
    }

    public BigDecimal getValAreabloque() {
        return valAreabloque;
    }

    public void setValAreabloque(BigDecimal valAreabloque) {
        this.valAreabloque = valAreabloque;
    }

    public BigDecimal getValBloque() {
        return valBloque;
    }

    public void setValBloque(BigDecimal valBloque) {
        this.valBloque = valBloque;
    }

    public EnumEstadoRegistro getBloEstado() {
        return bloEstado;
    }

    public void setBloEstado(EnumEstadoRegistro bloEstado) {
        this.bloEstado = bloEstado;
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
        hash += (codBloques != null ? codBloques.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloques)) {
            return false;
        }
        Bloques other = (Bloques) object;
        if ((this.codBloques == null && other.codBloques != null) || (this.codBloques != null && !this.codBloques.equals(other.codBloques))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Bloques[ codBloques=" + codBloques + " ]";
    }
    
    public Boolean esBloqueValido() {
        return (!ComunUtil.esNulo(this.bloEstado));
    }

    @XmlTransient
    public Collection<Pisos> getPisosCollection() {
        return pisosCollection;
    }

    public void setPisosCollection(Collection<Pisos> pisosCollection) {
        this.pisosCollection = pisosCollection;
    }
}
