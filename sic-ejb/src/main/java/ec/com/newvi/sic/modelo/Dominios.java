/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NEWVI
 */
@Entity
@Table(name = "cat_cat_dominios", schema = "public")

public class Dominios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "DOMINIOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_dominios_domi_id_seq", schema = "public")
    @GeneratedValue(generator = "DOMINIOS_CODIGO_GENERATOR")
    @Column(name = "domi_id")
    private Integer domiId;
    @Basic(optional = false)
    @NotNull
    @Size(max = 16)
    @Column(name = "domi_codigo")
    private String domiCodigo;
    @Size(max = 16)
    @Column(name = "domi_padre")
    private String domiPadre;
    @Enumerated(EnumType.STRING)
    @Column(name = "domi_relacion")
    private EnumRelacionDominios domiRelacion;
    @Size(max = 100)
    @Column(name = "domi_descripcion")
    private String domiDescripcion;
    @Size(max = 30)
    @Column(name = "domi_grupos")
    private String domiGrupos;
    @Size(max = 50)
    @Column(name = "domi_calculo")
    private String domiCalculo;
    @Size(max = 10)
    @Column(name = "domi_depreciacion")
    private String domiDepreciacion;
    @Size(max = 10)
    @Column(name = "domi_codame")
    private String domiCodame;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "domi_coefic")
    private BigDecimal domiCoefic;
    @Size(max = 16)
    @Column(name = "domi_unidad")
    private String domiUnidad;
    @Enumerated(EnumType.STRING)
    @Column(name = "domi_estado")
    private EnumEstadoRegistro domiEstado;
    @Size(max = 16)
    @Column(name = "domi_ficha")
    private String domiFicha;
    @Size(max = 10)
    @Column(name = "domi_tipo")
    private String domiTipo;
    @Column(name = "domi_minimo")
    private BigDecimal domiMinimo;
    @Column(name = "domi_maximo")
    private BigDecimal domiMaximo;
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

    public Dominios() {
    }

    public Dominios(Integer domiId) {
        this.domiId = domiId;
    }

    public Dominios(Integer domiId, String domiCodigo) {
        this.domiId = domiId;
        this.domiCodigo = domiCodigo;
    }

    public Integer getDomiId() {
        return domiId;
    }

    public void setDomiId(Integer domiId) {
        this.domiId = domiId;
    }

    public String getDomiCodigo() {
        return domiCodigo;
    }

    public void setDomiCodigo(String domiCodigo) {
        this.domiCodigo = domiCodigo;
    }

    public String getDomiPadre() {
        return domiPadre;
    }

    public void setDomiPadre(String domiPadre) {
        this.domiPadre = domiPadre;
    }

    public EnumRelacionDominios getDomiRelacion() {
        return domiRelacion;
    }

    public void setDomiRelacion(EnumRelacionDominios domiRelacion) {
        this.domiRelacion = domiRelacion;
    }

    public String getDomiDescripcion() {
        return domiDescripcion;
    }

    public void setDomiDescripcion(String domiDescripcion) {
        this.domiDescripcion = domiDescripcion;
    }

    public String getDomiGrupos() {
        return domiGrupos;
    }

    public void setDomiGrupos(String domiGrupos) {
        this.domiGrupos = domiGrupos;
    }

    public String getDomiCalculo() {
        return domiCalculo;
    }

    public void setDomiCalculo(String domiCalculo) {
        this.domiCalculo = domiCalculo;
    }

    public String getDomiDepreciacion() {
        return domiDepreciacion;
    }

    public void setDomiDepreciacion(String domiDepreciacion) {
        this.domiDepreciacion = domiDepreciacion;
    }

    public String getDomiCodame() {
        return domiCodame;
    }

    public void setDomiCodame(String domiCodame) {
        this.domiCodame = domiCodame;
    }

    public BigDecimal getDomiCoefic() {
        return domiCoefic;
    }

    public void setDomiCoefic(BigDecimal domiCoefic) {
        this.domiCoefic = domiCoefic;
    }

    public String getDomiUnidad() {
        return domiUnidad;
    }

    public void setDomiUnidad(String domiUnidad) {
        this.domiUnidad = domiUnidad;
    }

    public EnumEstadoRegistro getDomiEstado() {
        return domiEstado;
    }

    public void setDomiEstado(EnumEstadoRegistro domiEstado) {
        this.domiEstado = domiEstado;
    }

    public String getDomiFicha() {
        return domiFicha;
    }

    public void setDomiFicha(String domiFicha) {
        this.domiFicha = domiFicha;
    }

    public String getDomiTipo() {
        return domiTipo;
    }

    public void setDomiTipo(String domiTipo) {
        this.domiTipo = domiTipo;
    }

    public BigDecimal getDomiMinimo() {
        return domiMinimo;
    }

    public void setDomiMinimo(BigDecimal domiMinimo) {
        this.domiMinimo = domiMinimo;
    }

    public BigDecimal getDomiMaximo() {
        return domiMaximo;
    }

    public void setDomiMaximo(BigDecimal domiMaximo) {
        this.domiMaximo = domiMaximo;
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
        hash += (domiId != null ? domiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dominios)) {
            return false;
        }
        Dominios other = (Dominios) object;
        if ((this.domiId == null && other.domiId != null) || (this.domiId != null && !this.domiId.equals(other.domiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Dominios[ domiId=" + domiId + " ]";
    }
    
    public Boolean esDominioValido(){
        return (!ComunUtil.esNulo(this.domiCodigo));
    }
    
}
