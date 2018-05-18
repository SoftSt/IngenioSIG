/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.enums.EnumTipoPermisos;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.CertificacionUsoSuelo;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NEWVI
 */
public class CertificadoDto {

    private CertificacionUsoSuelo certificacionUsoSuelo;
    
    
    private String css_cod_secuencial;
    private String css_txt_nombre_local;
    private String css_txt_tipo_predio;
    private String css_txt_direccion;
    private String css_val_lote;
    private String css_val_manzana;
    private String css_txt_apellido_nombre;
    private String css_txt_domicilio_tributario;
    private String construir;
    private String renovar;
    private String ampliar;
    private String reformar;
    private String demoler;
    private String mensurar;
    private String subdividir;
    private String lotear;
    private String otro;
    private String unifam;
    private String colect;
    private String deposito;
    private String taller;
    private String comercio;
    private String servicio;
    private String industria;
    private String pub;
    

    //public CertificadoDto(CertificacionUsoSuelo certificacionUsoSuelo) {
    //    this.css_txt_apellido_nombre = certificacionUsoSuelo.getCssRepresentanteLegal();
    //}

    public CertificacionUsoSuelo getCertificacionUsoSuelo() {
        return certificacionUsoSuelo;
    }

    public void setCertificacionUsoSuelo(CertificacionUsoSuelo certificacionUsoSuelo) {
        this.certificacionUsoSuelo = certificacionUsoSuelo;
    }

    public String getCss_cod_secuencial() {
        return css_cod_secuencial;
    }

    public void setCss_cod_secuencial(String css_cod_secuencial) {
        this.css_cod_secuencial = css_cod_secuencial;
    }

    public String getCss_txt_nombre_local() {
        return css_txt_nombre_local;
    }

    public void setCss_txt_nombre_local(String css_txt_nombre_local) {
        this.css_txt_nombre_local = css_txt_nombre_local;
    }

    public String getCss_txt_tipo_predio() {
        return css_txt_tipo_predio;
    }

    public void setCss_txt_tipo_predio(String css_txt_tipo_predio) {
        this.css_txt_tipo_predio = css_txt_tipo_predio;
    }

    public String getCss_txt_direccion() {
        return css_txt_direccion;
    }

    public void setCss_txt_direccion(String css_txt_direccion) {
        this.css_txt_direccion = css_txt_direccion;
    }

    public String getCss_val_lote() {
        return css_val_lote;
    }

    public void setCss_val_lote(String css_val_lote) {
        this.css_val_lote = css_val_lote;
    }

    public String getCss_val_manzana() {
        return css_val_manzana;
    }

    public void setCss_val_manzana(String css_val_manzana) {
        this.css_val_manzana = css_val_manzana;
    }

    public String getCss_txt_apellido_nombre() {
        return css_txt_apellido_nombre;
    }

    public void setCss_txt_apellido_nombre(String css_txt_apellido_nombre) {
        this.css_txt_apellido_nombre = css_txt_apellido_nombre;
    }

    public String getCss_txt_domicilio_tributario() {
        return css_txt_domicilio_tributario;
    }

    public void setCss_txt_domicilio_tributario(String css_txt_domicilio_tributario) {
        this.css_txt_domicilio_tributario = css_txt_domicilio_tributario;
    }

    public String getConstruir() {
        return construir;
    }

    public void setConstruir(String construir) {
        this.construir = construir;
    }

    public String getRenovar() {
        return renovar;
    }

    public void setRenovar(String renovar) {
        this.renovar = renovar;
    }

    public String getAmpliar() {
        return ampliar;
    }

    public void setAmpliar(String ampliar) {
        this.ampliar = ampliar;
    }

    public String getReformar() {
        return reformar;
    }

    public void setReformar(String reformar) {
        this.reformar = reformar;
    }

    public String getDemoler() {
        return demoler;
    }

    public void setDemoler(String demoler) {
        this.demoler = demoler;
    }

    public String getMensurar() {
        return mensurar;
    }

    public void setMensurar(String mensurar) {
        this.mensurar = mensurar;
    }

    public String getSubdividir() {
        return subdividir;
    }

    public void setSubdividir(String subdividir) {
        this.subdividir = subdividir;
    }

    public String getLotear() {
        return lotear;
    }

    public void setLotear(String lotear) {
        this.lotear = lotear;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public String getUnifam() {
        return unifam;
    }

    public void setUnifam(String unifam) {
        this.unifam = unifam;
    }

    public String getColect() {
        return colect;
    }

    public void setColect(String colect) {
        this.colect = colect;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getIndustria() {
        return industria;
    }

    public void setIndustria(String industria) {
        this.industria = industria;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }
    

    

    

    
}
