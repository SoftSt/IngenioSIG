/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumSN;
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
@Table(name = "tes_titulo")

public class CabeceraTitulo implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @SequenceGenerator(name = "CABECERA_TITULO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "tes_titulo_sec_titulo_seq", schema = "public")
    @GeneratedValue(generator = "CABECERA_TITULO_CODIGO_GENERATOR")
    @Column(name = "sec_titulo")
    private Integer secTitulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_descuentos")
    private BigDecimal valDescuentos;
    @Column(name = "val_interes")
    private BigDecimal valInteres;
    @Column(name = "val_recargos")
    private BigDecimal valRecargos;
    @Column(name = "val_exoneraciones")
    private BigDecimal valExoneraciones;
    @Column(name = "val_pago")
    private BigDecimal valPago;
    @Size(max = 50)
    @Column(name = "cod_referencia")
    private String codReferencia;
    @Size(min = 1, max = 15)
    @Column(name = "cod_titulo")
    private String codTitulo;
    @Column(name = "fec_emision")
    @Temporal(TemporalType.DATE)
    private Date fecEmision;
    @Column(name = "fec_pago")
    @Temporal(TemporalType.DATE)
    private Date fecPago;
    @Column(name = "val_titulo")
    private BigDecimal valTitulo;
    @Size(max = 2147483647)
    @Column(name = "txt_titulo_concepto")
    private String txtTituloConcepto;
    @Enumerated(EnumType.STRING)
    @Column(name = "sts_exonerado")
    private EnumSN stsExonerado;
    @Column(name = "sts_titulo")
    private Character stsTitulo;
    @Enumerated(EnumType.STRING)
    @Column(name = "sts_registro")
    private EnumEstadoRegistro stsRegistro;
    @Enumerated(EnumType.STRING)
    @Column(name = "sts_en_coactiva")
    private EnumSN stsEnCoactiva;
    @JoinColumn(name = "sec_contribuyente", referencedColumnName = "cod_personeria")
    @ManyToOne(optional = false)
    private Contribuyentes secContribuyente;
    @JoinColumn(name = "sec_usuario_emite", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuarios secUsuarioEmite;

    public CabeceraTitulo() {
    }

    public CabeceraTitulo(Integer secTitulo) {
        this.secTitulo = secTitulo;
    }

    public Integer getSecTitulo() {
        return secTitulo;
    }

    public void setSecTitulo(Integer secTitulo) {
        this.secTitulo = secTitulo;
    }

    public BigDecimal getValDescuentos() {
        return valDescuentos;
    }

    public void setValDescuentos(BigDecimal valDescuentos) {
        this.valDescuentos = valDescuentos;
    }

    public BigDecimal getValInteres() {
        return valInteres;
    }

    public void setValInteres(BigDecimal valInteres) {
        this.valInteres = valInteres;
    }

    public BigDecimal getValRecargos() {
        return valRecargos;
    }

    public void setValRecargos(BigDecimal valRecargos) {
        this.valRecargos = valRecargos;
    }

    public BigDecimal getValExoneraciones() {
        return valExoneraciones;
    }

    public void setValExoneraciones(BigDecimal valExoneraciones) {
        this.valExoneraciones = valExoneraciones;
    }

    public BigDecimal getValPago() {
        return valPago;
    }

    public void setValPago(BigDecimal valPago) {
        this.valPago = valPago;
    }

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }

    public String getCodTitulo() {
        return codTitulo;
    }

    public void setCodTitulo(String codTitulo) {
        this.codTitulo = codTitulo;
    }

    public Date getFecEmision() {
        return fecEmision;
    }

    public void setFecEmision(Date fecEmision) {
        this.fecEmision = fecEmision;
    }

    public Date getFecPago() {
        return fecPago;
    }

    public void setFecPago(Date fecPago) {
        this.fecPago = fecPago;
    }

    public BigDecimal getValTitulo() {
        return valTitulo;
    }

    public void setValTitulo(BigDecimal valTitulo) {
        this.valTitulo = valTitulo;
    }

    public String getTxtTituloConcepto() {
        return txtTituloConcepto;
    }

    public void setTxtTituloConcepto(String txtTituloConcepto) {
        this.txtTituloConcepto = txtTituloConcepto;
    }

    public EnumSN getStsExonerado() {
        return stsExonerado;
    }

    public void setStsExonerado(EnumSN stsExonerado) {
        this.stsExonerado = stsExonerado;
    }

    public Character getStsTitulo() {
        return stsTitulo;
    }

    public void setStsTitulo(Character stsTitulo) {
        this.stsTitulo = stsTitulo;
    }

    public EnumEstadoRegistro getStsRegistro() {
        return stsRegistro;
    }

    public void setStsRegistro(EnumEstadoRegistro stsRegistro) {
        this.stsRegistro = stsRegistro;
    }

    public EnumSN getStsEnCoactiva() {
        return stsEnCoactiva;
    }

    public void setStsEnCoactiva(EnumSN stsEnCoactiva) {
        this.stsEnCoactiva = stsEnCoactiva;
    }

    public Contribuyentes getSecContribuyente() {
        return secContribuyente;
    }

    public void setSecContribuyente(Contribuyentes secContribuyente) {
        this.secContribuyente = secContribuyente;
    }

    public Usuarios getSecUsuarioEmite() {
        return secUsuarioEmite;
    }

    public void setSecUsuarioEmite(Usuarios secUsuarioEmite) {
        this.secUsuarioEmite = secUsuarioEmite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secTitulo != null ? secTitulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabeceraTitulo)) {
            return false;
        }
        CabeceraTitulo other = (CabeceraTitulo) object;
        if ((this.secTitulo == null && other.secTitulo != null) || (this.secTitulo != null && !this.secTitulo.equals(other.secTitulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.CabeceraTitulo[ secTitulo=" + secTitulo + " ]";
    }
    
    public Boolean esCabeceraTituloValida() {
        return (!ComunUtil.esNulo(this.stsRegistro));
    }
    
}
