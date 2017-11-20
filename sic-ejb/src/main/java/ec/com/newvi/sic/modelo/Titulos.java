/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.util.ComunUtil;
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
@Table(name = "cat_cat_titulos")

public class Titulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "TITULO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_titulos_cod_titulos_seq", schema = "public")
    @GeneratedValue(generator = "TITULO_CODIGO_GENERATOR")
    @Column(name = "cod_titulos")
    private Integer codTitulos;
    @Size(max = 25)
    @Column(name = "nom_codigocatastral")
    private String nomCodigocatastral;
    @Size(max = 25)
    @Column(name = "sts_casosexcepcion")
    private String stsCasosexcepcion;
    @Column(name = "usu_codigo")
    private Integer usuCodigo;
    @Column(name = "fec_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecEmision;
    @Column(name = "fec_ultimomovimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecUltimomovimiento;
    @Column(name = "fec_fpago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecFpago;
    @Size(max = 5)
    @Column(name = "sts_anioavaluo")
    private String stsAnioavaluo;
    @Size(max = 25)
    @Column(name = "sts_tipo")
    private String stsTipo;
    @Size(max = 30)
    @Column(name = "cod_secuencial")
    private String codSecuencial;
    @Enumerated(EnumType.STRING)
    @Column(name = "sts_estado")
    private EnumEstadoTitulo stsEstado;
    @Column(name = "val_nroimpresion")
    private Integer valNroimpresion;
    @Size(max = 100)
    @Column(name = "txt_barrio")
    private String txtBarrio;
    @Size(max = 100)
    @Column(name = "txt_direccion")
    private String txtDireccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_areaterreno")
    private BigDecimal valAreaterreno;
    @Column(name = "val_areaconstruccion")
    private BigDecimal valAreaconstruccion;
    @Column(name = "val_valorterreno")
    private BigDecimal valValorterreno;
    @Column(name = "val_construccion")
    private BigDecimal valConstruccion;
    @Column(name = "val_baseimponible")
    private BigDecimal valBaseimponible;
    @Column(name = "val_impuestopredial")
    private BigDecimal valImpuestopredial;
    @Column(name = "val_bomberos")
    private BigDecimal valBomberos;
    @Column(name = "val_saludpublica")
    private BigDecimal valSaludpublica;
    @Column(name = "val_educacion")
    private BigDecimal valEducacion;
    @Column(name = "val_acilo")
    private BigDecimal valAcilo;
    @Column(name = "val_rrp")
    private BigDecimal valRrp;
    @Column(name = "val_interes")
    private BigDecimal valInteres;
    @Column(name = "val_descuento")
    private BigDecimal valDescuento;
    @Column(name = "val_prestamo")
    private BigDecimal valPrestamo;
    @Column(name = "val_serviciosadministrativos")
    private BigDecimal valServiciosadministrativos;
    @Column(name = "val_recoleccionbasura")
    private BigDecimal valRecoleccionbasura;
    @Column(name = "val_impuestomasadicionales")
    private BigDecimal valImpuestomasadicionales;
    @Column(name = "val_seguridad")
    private BigDecimal valSeguridad;
    @Column(name = "val_cem")
    private BigDecimal valCem;
    @Column(name = "val_noconstruido")
    private BigDecimal valNoconstruido;
    @Column(name = "val_totalapagar")
    private BigDecimal valTotalapagar;
    @Column(name = "val_descuentos")
    private BigDecimal valDescuentos;
    @Column(name = "val_pagado")
    private BigDecimal valPagado;
    @Column(name = "val_rebaja")
    private BigDecimal valRebaja;
    @Column(name = "val_totalexcepciones")
    private BigDecimal valTotalexcepciones;
    @Column(name = "val_interesaplicado")
    private BigDecimal valInteresaplicado;
    @Column(name = "val_descuentoaplicado")
    private BigDecimal valDescuentoaplicado;
    @Column(name = "val_descuento_exoneracion")
    private BigDecimal valDescuentoExoneracion;
    @Column(name = "val_construccion_obsoleta")
    private BigDecimal valContruccionObsoleta;
    @Enumerated(EnumType.STRING)
    @Column(name = "titulo_estado")
    private EnumEstadoRegistro tituloEstado;
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
    @JoinColumn(name = "cod_propietarios", referencedColumnName = "cod_propietarios")
    @ManyToOne
    private Propiedad codPropietarios;

    public Titulos() {
    }

    public Titulos(Integer codTitulos) {
        this.codTitulos = codTitulos;
    }

    public Integer getCodTitulos() {
        return codTitulos;
    }

    public void setCodTitulos(Integer codTitulos) {
        this.codTitulos = codTitulos;
    }

    public String getNomCodigocatastral() {
        return nomCodigocatastral;
    }

    public void setNomCodigocatastral(String nomCodigocatastral) {
        this.nomCodigocatastral = nomCodigocatastral;
    }

    public String getStsCasosexcepcion() {
        return stsCasosexcepcion;
    }

    public void setStsCasosexcepcion(String stsCasosexcepcion) {
        this.stsCasosexcepcion = stsCasosexcepcion;
    }

    public Integer getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(Integer usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public Date getFecEmision() {
        return fecEmision;
    }

    public void setFecEmision(Date fecEmision) {
        this.fecEmision = fecEmision;
    }

    public Date getFecUltimomovimiento() {
        return fecUltimomovimiento;
    }

    public void setFecUltimomovimiento(Date fecUltimomovimiento) {
        this.fecUltimomovimiento = fecUltimomovimiento;
    }

    public Date getFecFpago() {
        return fecFpago;
    }

    public void setFecFpago(Date fecFpago) {
        this.fecFpago = fecFpago;
    }

    public String getStsAnioavaluo() {
        return stsAnioavaluo;
    }

    public void setStsAnioavaluo(String stsAnioavaluo) {
        this.stsAnioavaluo = stsAnioavaluo;
    }

    public String getStsTipo() {
        return stsTipo;
    }

    public void setStsTipo(String stsTipo) {
        this.stsTipo = stsTipo;
    }

    public EnumEstadoTitulo getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(EnumEstadoTitulo stsEstado) {
        this.stsEstado = stsEstado;
    }

    public Integer getValNroimpresion() {
        return valNroimpresion;
    }

    public void setValNroimpresion(Integer valNroimpresion) {
        this.valNroimpresion = valNroimpresion;
    }

    public String getTxtBarrio() {
        return txtBarrio;
    }

    public void setTxtBarrio(String txtBarrio) {
        this.txtBarrio = txtBarrio;
    }

    public String getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public BigDecimal getValAreaterreno() {
        return valAreaterreno;
    }

    public void setValAreaterreno(BigDecimal valAreaterreno) {
        this.valAreaterreno = valAreaterreno;
    }

    public BigDecimal getValAreaconstruccion() {
        return valAreaconstruccion;
    }

    public void setValAreaconstruccion(BigDecimal valAreaconstruccion) {
        this.valAreaconstruccion = valAreaconstruccion;
    }

    public BigDecimal getValValorterreno() {
        return valValorterreno;
    }

    public void setValValorterreno(BigDecimal valValorterreno) {
        this.valValorterreno = valValorterreno;
    }

    public BigDecimal getValConstruccion() {
        return valConstruccion;
    }

    public void setValConstruccion(BigDecimal valConstruccion) {
        this.valConstruccion = valConstruccion;
    }

    public BigDecimal getValBaseimponible() {
        return valBaseimponible;
    }

    public void setValBaseimponible(BigDecimal valBaseimponible) {
        this.valBaseimponible = valBaseimponible;
    }

    public BigDecimal getValImpuestopredial() {
        return valImpuestopredial;
    }

    public void setValImpuestopredial(BigDecimal valImpuestopredial) {
        this.valImpuestopredial = valImpuestopredial;
    }

    public BigDecimal getValBomberos() {
        return valBomberos;
    }

    public void setValBomberos(BigDecimal valBomberos) {
        this.valBomberos = valBomberos;
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

    public BigDecimal getValAcilo() {
        return valAcilo;
    }

    public void setValAcilo(BigDecimal valAcilo) {
        this.valAcilo = valAcilo;
    }

    public BigDecimal getValRrp() {
        return valRrp;
    }

    public void setValRrp(BigDecimal valRrp) {
        this.valRrp = valRrp;
    }

    public BigDecimal getValInteres() {
        return valInteres;
    }

    public void setValInteres(BigDecimal valInteres) {
        this.valInteres = valInteres;
    }

    public BigDecimal getValDescuento() {
        return valDescuento;
    }

    public void setValDescuento(BigDecimal valDescuento) {
        this.valDescuento = valDescuento;
    }

    public BigDecimal getValPrestamo() {
        return valPrestamo;
    }

    public void setValPrestamo(BigDecimal valPrestamo) {
        this.valPrestamo = valPrestamo;
    }

    public BigDecimal getValServiciosadministrativos() {
        return valServiciosadministrativos;
    }

    public void setValServiciosadministrativos(BigDecimal valServiciosadministrativos) {
        this.valServiciosadministrativos = valServiciosadministrativos;
    }

    public BigDecimal getValRecoleccionbasura() {
        return valRecoleccionbasura;
    }

    public void setValRecoleccionbasura(BigDecimal valRecoleccionbasura) {
        this.valRecoleccionbasura = valRecoleccionbasura;
    }

    public BigDecimal getValImpuestomasadicionales() {
        return valImpuestomasadicionales;
    }

    public void setValImpuestomasadicionales(BigDecimal valImpuestomasadicionales) {
        this.valImpuestomasadicionales = valImpuestomasadicionales;
    }

    public BigDecimal getValSeguridad() {
        return valSeguridad;
    }

    public void setValSeguridad(BigDecimal valSeguridad) {
        this.valSeguridad = valSeguridad;
    }

    public BigDecimal getValCem() {
        return valCem;
    }

    public void setValCem(BigDecimal valCem) {
        this.valCem = valCem;
    }

    public BigDecimal getValNoconstruido() {
        return valNoconstruido;
    }

    public void setValNoconstruido(BigDecimal valNoconstruido) {
        this.valNoconstruido = valNoconstruido;
    }

    public BigDecimal getValTotalapagar() {
        return valTotalapagar;
    }

    public void setValTotalapagar(BigDecimal valTotalapagar) {
        this.valTotalapagar = valTotalapagar;
    }

    public BigDecimal getValDescuentos() {
        return valDescuentos;
    }

    public void setValDescuentos(BigDecimal valDescuentos) {
        this.valDescuentos = valDescuentos;
    }

    public BigDecimal getValPagado() {
        return valPagado;
    }

    public void setValPagado(BigDecimal valPagado) {
        this.valPagado = valPagado;
    }

    public BigDecimal getValRebaja() {
        return valRebaja;
    }

    public void setValRebaja(BigDecimal valRebaja) {
        this.valRebaja = valRebaja;
    }

    public BigDecimal getValTotalexcepciones() {
        return valTotalexcepciones;
    }

    public void setValTotalexcepciones(BigDecimal valTotalexcepciones) {
        this.valTotalexcepciones = valTotalexcepciones;
    }

    public BigDecimal getValInteresaplicado() {
        return valInteresaplicado;
    }

    public void setValInteresaplicado(BigDecimal valInteresaplicado) {
        this.valInteresaplicado = valInteresaplicado;
    }

    public BigDecimal getValDescuentoaplicado() {
        return valDescuentoaplicado;
    }

    public void setValDescuentoaplicado(BigDecimal valDescuentoaplicado) {
        this.valDescuentoaplicado = valDescuentoaplicado;
    }

    public EnumEstadoRegistro getTituloEstado() {
        return tituloEstado;
    }

    public void setTituloEstado(EnumEstadoRegistro tituloEstado) {
        this.tituloEstado = tituloEstado;
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

    public Propiedad getCodPropietarios() {
        return codPropietarios;
    }

    public void setCodPropietarios(Propiedad codPropietarios) {
        this.codPropietarios = codPropietarios;
    }

    public String getCodSecuencial() {
        return codSecuencial;
    }

    public void setCodSecuencial(String codSecuencial) {
        this.codSecuencial = codSecuencial;
    }

    public BigDecimal getValDescuentoExoneracion() {
        return valDescuentoExoneracion;
    }

    public void setValDescuentoExoneracion(BigDecimal valDescuentoExoneracion) {
        this.valDescuentoExoneracion = valDescuentoExoneracion;
    }

    public BigDecimal getValContruccionObsoleta() {
        return valContruccionObsoleta;
    }

    public void setValContruccionObsoleta(BigDecimal valContruccionObsoleta) {
        this.valContruccionObsoleta = valContruccionObsoleta;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTitulos != null ? codTitulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titulos)) {
            return false;
        }
        Titulos other = (Titulos) object;
        if ((this.codTitulos == null && other.codTitulos != null) || (this.codTitulos != null && !this.codTitulos.equals(other.codTitulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Titulos[ codTitulos=" + codTitulos + " ]";
    }
    
    public Boolean esTituloValido() {
        return (!ComunUtil.esNulo(this.tituloEstado));
    }
    
}
