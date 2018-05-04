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
@Table(name = "cat_cat_usotierra")

public class UsoTierra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "USO_TIERRA_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_usotierra_cod_usotierra_seq", schema = "public")
    @GeneratedValue(generator = "USO_TIERRA_CODIGO_GENERATOR")
    @Column(name = "cod_usotierra")
    private Integer codUsotierra;
    @Size(max = 25)
    @Column(name = "cod_uso")
    private String codUso;
    @Size(max = 100)
    @Column(name = "sts_tipo")
    private String stsTipo;
    @Size(max = 100)
    @Column(name = "nom_detalleuso")
    private String nomDetalleuso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_area")
    private BigDecimal valArea;
    @Column(name = "val_edad")
    private Integer valEdad;
    @Size(max = 25)
    @Column(name = "sts_estado")
    private String stsEstado;
    @Column(name = "val_densidad")
    private BigDecimal valDensidad;
    @Column(name = "val_valorunitario")
    private BigDecimal valValorunitario;
    @Column(name = "val_superficieconriego")
    private BigDecimal valSuperficieconriego;
    @Column(name = "val_superficiesinriego")
    private BigDecimal valSuperficiesinriego;
    @Size(max = 25)
    @Column(name = "sts_clasetierra")
    private String stsClasetierra;
    @Size(max = 100)
    @Column(name = "sts_grupo")
    private String stsGrupo;
    @Column(name = "val_valortotal")
    private BigDecimal valValortotal;
    @Column(name = "val_distanciaplantas")
    private BigDecimal valDistanciaplantas;
    @Column(name = "val_totalplantas")
    private Integer valTotalplantas;
    @Column(name = "val_distanciasurco")
    private BigDecimal valDistanciasurco;
    @Size(max = 25)
    @Column(name = "sts_zonaagroecologica")
    private String stsZonaagroecologica;
    @Size(max = 25)
    @Column(name = "sts_geoeconomica")
    private String stsGeoeconomica;
    @Enumerated(EnumType.STRING)
    @Column(name = "ust_estado")
    private EnumEstadoRegistro ustEstado;
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

    public UsoTierra() {
    }

    public UsoTierra(Integer codUsotierra) {
        this.codUsotierra = codUsotierra;
    }

    public Integer getCodUsotierra() {
        return codUsotierra;
    }

    public void setCodUsotierra(Integer codUsotierra) {
        this.codUsotierra = codUsotierra;
    }

    public String getCodUso() {
        return codUso;
    }

    public void setCodUso(String codUso) {
        this.codUso = codUso;
    }

    public String getStsTipo() {
        return stsTipo;
    }

    public void setStsTipo(String stsTipo) {
        this.stsTipo = stsTipo;
    }

    public String getNomDetalleuso() {
        return nomDetalleuso;
    }

    public void setNomDetalleuso(String nomDetalleuso) {
        this.nomDetalleuso = nomDetalleuso;
    }

    public BigDecimal getValArea() {
        return valArea;
    }

    public void setValArea(BigDecimal valArea) {
        this.valArea = valArea;
    }

    public Integer getValEdad() {
        return valEdad;
    }

    public void setValEdad(Integer valEdad) {
        this.valEdad = valEdad;
    }

    public String getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(String stsEstado) {
        this.stsEstado = stsEstado;
    }

    public BigDecimal getValDensidad() {
        return valDensidad;
    }

    public void setValDensidad(BigDecimal valDensidad) {
        this.valDensidad = valDensidad;
    }

    public BigDecimal getValValorunitario() {
        return valValorunitario;
    }

    public void setValValorunitario(BigDecimal valValorunitario) {
        this.valValorunitario = valValorunitario;
    }

    public BigDecimal getValSuperficieconriego() {
        return valSuperficieconriego;
    }

    public void setValSuperficieconriego(BigDecimal valSuperficieconriego) {
        this.valSuperficieconriego = valSuperficieconriego;
    }

    public BigDecimal getValSuperficiesinriego() {
        return valSuperficiesinriego;
    }

    public void setValSuperficiesinriego(BigDecimal valSuperficiesinriego) {
        this.valSuperficiesinriego = valSuperficiesinriego;
    }

    public String getStsClasetierra() {
        return stsClasetierra;
    }

    public void setStsClasetierra(String stsClasetierra) {
        this.stsClasetierra = stsClasetierra;
    }

    public String getStsGrupo() {
        return stsGrupo;
    }

    public void setStsGrupo(String stsGrupo) {
        this.stsGrupo = stsGrupo;
    }

    public BigDecimal getValValortotal() {
        return valValortotal;
    }

    public void setValValortotal(BigDecimal valValortotal) {
        this.valValortotal = valValortotal;
    }

    public BigDecimal getValDistanciaplantas() {
        return valDistanciaplantas;
    }

    public void setValDistanciaplantas(BigDecimal valDistanciaplantas) {
        this.valDistanciaplantas = valDistanciaplantas;
    }

    public Integer getValTotalplantas() {
        return valTotalplantas;
    }

    public void setValTotalplantas(Integer valTotalplantas) {
        this.valTotalplantas = valTotalplantas;
    }

    public BigDecimal getValDistanciasurco() {
        return valDistanciasurco;
    }

    public void setValDistanciasurco(BigDecimal valDistanciasurco) {
        this.valDistanciasurco = valDistanciasurco;
    }

    public String getStsZonaagroecologica() {
        return stsZonaagroecologica;
    }

    public void setStsZonaagroecologica(String stsZonaagroecologica) {
        this.stsZonaagroecologica = stsZonaagroecologica;
    }

    public String getStsGeoeconomica() {
        return stsGeoeconomica;
    }

    public void setStsGeoeconomica(String stsGeoeconomica) {
        this.stsGeoeconomica = stsGeoeconomica;
    }

    public EnumEstadoRegistro getUstEstado() {
        return ustEstado;
    }

    public void setUstEstado(EnumEstadoRegistro ustEstado) {
        this.ustEstado = ustEstado;
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
        hash += (codUsotierra != null ? codUsotierra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsoTierra)) {
            return false;
        }
        UsoTierra other = (UsoTierra) object;
        if ((this.codUsotierra == null && other.codUsotierra != null) || (this.codUsotierra != null && !this.codUsotierra.equals(other.codUsotierra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.UsoTierra[ codUsotierra=" + codUsotierra + " ]";
    }
    
}
