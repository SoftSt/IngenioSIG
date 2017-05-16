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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

    private static final long serialVersionUID = -1L;
    
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
    @Size(max = 2)
    @Column(name = "cod_manzana")
    private String codManzana;
    @Size(max = 4)
    @Column(name = "cod_predio")
    private String codPredio;
    @Size(max = 2)
    @Column(name = "cod_regimentenencia")
    private String codRegimentenencia;
    @Size(max = 3)
    @Column(name = "cod_horizontal")
    private String codHorizontal;
    @Size(max = 150)
    @Column(name = "sts_barrio")
    private String stsBarrio;
    @Size(max = 100)
    @Column(name = "txt_direccion")
    private String txtDireccion;
    @Size(max = 20)
    @Column(name = "nom_numero")
    private String nomNumero;
    @Size(max = 100)
    @Column(name = "txt_ubicacion")
    private String txtUbicacion;
    @Size(max = 50)
    @Column(name = "nom_cartografia")
    private String nomCartografia;
    @Size(max = 50)
    @Column(name = "nom_fotoaerea")
    private String nomFotoAerea;
    @Size(max = 50)
    @Column(name = "nom_cartootros")
    private String nomcCartografiaOtros;
    @Column(name = "val_coordenadaeste")
    private BigDecimal valCoordenadaEste;
    @Column(name = "val_coordenadanorte")
    private BigDecimal valCoordenadaNorte;    
    @Size(max = 100)
    @Column(name = "txt_norte")
    private String txtNorte;
    @Size(max = 100)
    @Column(name = "txt_sur")
    private String txtSur;
    @Size(max = 100)
    @Column(name = "txt_este")
    private String txtEste;
    @Size(max = 100)
    @Column(name = "txt_oeste")
    private String txtOeste;
    @Size(max = 25)
    @Column(name = "txt_dominio")
    private String txtDominio;
    @Size(max = 50)
    @Column(name = "nom_informante")
    private String nomInformante;
    @Size(max = 50)
    @Column(name = "nom_intervenido")
    private String nomIntervenido;
    @Column(name = "val_areafrente")
    private BigDecimal valAreaFrente;    
    @Column(name = "val_areaconstruccion")
    private BigDecimal valAreaConstruccion;
    @Column(name = "val_areapredio")
    private BigDecimal valAreaPredio;    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private Collection<Terreno> caracteristicasTerreno;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private Collection<Bloques> bloques;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private Collection<Propietario> historicoPropietarios;
    
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

    public String getNomNumero() {
        return nomNumero;
    }

    public void setNomNumero(String nomNumero) {
        this.nomNumero = nomNumero;
    }

    public String getTxtUbicacion() {
        return txtUbicacion;
    }

    public void setTxtUbicacion(String txtUbicacion) {
        this.txtUbicacion = txtUbicacion;
    }

    public String getNomCartografia() {
        return nomCartografia;
    }

    public void setNomCartografia(String nomCartografia) {
        this.nomCartografia = nomCartografia;
    }

    public String getNomFotoAerea() {
        return nomFotoAerea;
    }

    public void setNomFotoAerea(String nomFotoAerea) {
        this.nomFotoAerea = nomFotoAerea;
    }

    public String getNomcCartografiaOtros() {
        return nomcCartografiaOtros;
    }

    public void setNomcCartografiaOtros(String nomcCartografiaOtros) {
        this.nomcCartografiaOtros = nomcCartografiaOtros;
    }

    public BigDecimal getValCoordenadaEste() {
        return valCoordenadaEste;
    }

    public void setValCoordenadaEste(BigDecimal valCoordenadaEste) {
        this.valCoordenadaEste = valCoordenadaEste;
    }

    public BigDecimal getValCoordenadaNorte() {
        return valCoordenadaNorte;
    }

    public void setValCoordenadaNorte(BigDecimal valCoordenadaNorte) {
        this.valCoordenadaNorte = valCoordenadaNorte;
    }

    public String getTxtNorte() {
        return txtNorte;
    }

    public void setTxtNorte(String txtNorte) {
        this.txtNorte = txtNorte;
    }

    public String getTxtSur() {
        return txtSur;
    }

    public void setTxtSur(String txtSur) {
        this.txtSur = txtSur;
    }

    public String getTxtEste() {
        return txtEste;
    }

    public void setTxtEste(String txtEste) {
        this.txtEste = txtEste;
    }

    public String getTxtOeste() {
        return txtOeste;
    }

    public void setTxtOeste(String txtOeste) {
        this.txtOeste = txtOeste;
    }

    public String getTxtDominio() {
        return txtDominio;
    }

    public void setTxtDominio(String txtDominio) {
        this.txtDominio = txtDominio;
    }

    public String getNomInformante() {
        return nomInformante;
    }

    public void setNomInformante(String nomInformante) {
        this.nomInformante = nomInformante;
    }

    public String getNomIntervenido() {
        return nomIntervenido;
    }

    public void setNomIntervenido(String nomIntervenido) {
        this.nomIntervenido = nomIntervenido;
    }

    public BigDecimal getValAreaFrente() {
        return valAreaFrente;
    }

    public void setValAreaFrente(BigDecimal valAreaFrente) {
        this.valAreaFrente = valAreaFrente;
    }

    public BigDecimal getValAreaConstruccion() {
        return valAreaConstruccion;
    }

    public void setValAreaConstruccion(BigDecimal valAreaConstruccion) {
        this.valAreaConstruccion = valAreaConstruccion;
    }

    public BigDecimal getValAreaPredio() {
        return valAreaPredio;
    }

    public void setValAreaPredio(BigDecimal valAreaPredio) {
        this.valAreaPredio = valAreaPredio;
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

    public Collection<Terreno> getCaracteristicasTerreno() {
        return caracteristicasTerreno;
    }

    public void setCaracteristicasTerreno(Collection<Terreno> caracteristicasTerreno) {
        this.caracteristicasTerreno = caracteristicasTerreno;
    }

    public Collection<Bloques> getBloques() {
        return bloques;
    }

    public void setBloques(Collection<Bloques> bloques) {
        this.bloques = bloques;
    }

    public Collection<Propietario> getHistoricoPropietarios() {
        return historicoPropietarios;
    }

    public void setHistoricoPropietarios(Collection<Propietario> historicoPropietarios) {
        this.historicoPropietarios = historicoPropietarios;
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
    
    public void actualizarCodigoPredio() {
        this.nomCodigocatastral = this.codDpa.concat(this.codZona).concat(this.codSector).concat(this.codManzana).concat(this.codPredio).concat(this.codRegimentenencia).concat(this.codHorizontal);
    }

}
