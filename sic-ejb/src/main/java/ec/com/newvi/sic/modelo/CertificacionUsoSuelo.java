/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumTipoPredio;
import java.io.Serializable;
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
 * @author Rolando Soria by Excor
 * 
 */
@Entity
@Table(name = "cat_pla_certificado_uso_suelo", schema = "public")
public class CertificacionUsoSuelo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "CERTIFICADO_US_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_pla_cert_uso_suelo_css_cod_secuencial_seq", schema = "public")
    @GeneratedValue(generator = "CERTIFICADO_US_CODIGO_GENERATOR")
    @Column(name = "css_cod_secuencial")
    private Integer cssCod;
    
    @Size(max = 1000)
    @Column(name = "css_txt_nombre_local")
    private String cssNombreLocal;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "css_txt_tipo_predio")
    private EnumTipoPredio cssTipoPredio;
    
    @Size(max = 1000)
    @Column(name = "css_txt_direccion")
    private String cssDireccion;
    
    @Column(name = "css_val_lote")
    private Integer cssLote;
    
    @Column(name = "css_val_manzana")
    private Integer cssManzana;
    
    @Size(max = 50)
    @Column(name = "css_txt_motivo_consulta")
    private String cssMotivoConsulta;
    
    @Size(max = 50)
    @Column(name = "css_txt_destino_edificacion")
    private String cssDestinoEdificacion;
    
     @Size(max = 200)
    @Column(name = "css_txt_apellido_nombre")
    private String cssRepresentanteLegal;
    
    @Size(max = 1000)
    @Column(name = "css_txt_domicilio_tributario")
    private String cssDomicilioTributario;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "css_sts_estado")
    private EnumEstadoRegistro cssEstado;
        
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

    public Integer getCssCod() {
        return cssCod;
    }

    public void setCssCod(Integer cssCod) {
        this.cssCod = cssCod;
    }

    public String getCssNombreLocal() {
        return cssNombreLocal;
    }

    public void setCssNombreLocal(String cssNombreLocal) {
        this.cssNombreLocal = cssNombreLocal;
    }

    public EnumTipoPredio getCssTipoPredio() {
        return cssTipoPredio;
    }

    public void setCssTipoPredio(EnumTipoPredio cssTipoPredio) {
        this.cssTipoPredio = cssTipoPredio;
    }

    public String getCssDireccion() {
        return cssDireccion;
    }

    public void setCssDireccion(String cssDireccion) {
        this.cssDireccion = cssDireccion;
    }

    public Integer getCssLote() {
        return cssLote;
    }

    public void setCssLote(Integer cssLote) {
        this.cssLote = cssLote;
    }

    public Integer getCssManzana() {
        return cssManzana;
    }

    public void setCssManzana(Integer cssManzana) {
        this.cssManzana = cssManzana;
    }

    public String getCssMotivoConsulta() {
        return cssMotivoConsulta;
    }

    public void setCssMotivoConsulta(String cssMotivoConsulta) {
        this.cssMotivoConsulta = cssMotivoConsulta;
    }

    public String getCssDestinoEdificacion() {
        return cssDestinoEdificacion;
    }

    public void setCssDestinoEdificacion(String cssDestinoEdificacion) {
        this.cssDestinoEdificacion = cssDestinoEdificacion;
    }

    

    public String getCssRepresentanteLegal() {
        return cssRepresentanteLegal;
    }

    public void setCssRepresentanteLegal(String cssRepresentanteLegal) {
        this.cssRepresentanteLegal = cssRepresentanteLegal;
    }

    public String getCssDomicilioTributario() {
        return cssDomicilioTributario;
    }

    public void setCssDomicilioTributario(String cssDomicilioTributario) {
        this.cssDomicilioTributario = cssDomicilioTributario;
    }

    public EnumEstadoRegistro getCssEstado() {
        return cssEstado;
    }

    public void setCssEstado(EnumEstadoRegistro cssEstado) {
        this.cssEstado = cssEstado;
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
}
