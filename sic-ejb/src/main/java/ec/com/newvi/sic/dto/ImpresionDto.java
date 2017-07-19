/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import java.math.BigDecimal;

/**
 *
 * @author Theo Herreria
 */
public class ImpresionDto {
    
    private String codigoCatastral; 
    private String propietario; 
    private String ciRuc; 
    private String barrio; 
    private String direccion; 
    private BigDecimal avaluoTerreno; 
    private BigDecimal areaTerreno; 
    private BigDecimal avaluoEdificacion; 
    private BigDecimal areaEdificacion; 
    private BigDecimal avaluoPredio; 
    private BigDecimal impuestoPredial; 
    private BigDecimal contribucionEspecialMejoras; 
    private BigDecimal tasaRecoleccionBasura; 
    private BigDecimal costoEmision; 
    private BigDecimal tasaBomberos; 
    private BigDecimal serviciosAmbientales; 
    private BigDecimal totalAPagar; 
    private String observaciones; 

    public String getCodigoCatastral() {
        return codigoCatastral;
    }

    public void setCodigoCatastral(String codigoCatastral) {
        this.codigoCatastral = codigoCatastral;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCiRuc() {
        return ciRuc;
    }

    public void setCiRuc(String ciRuc) {
        this.ciRuc = ciRuc;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getAvaluoTerreno() {
        return avaluoTerreno;
    }

    public void setAvaluoTerreno(BigDecimal avaluoTerreno) {
        this.avaluoTerreno = avaluoTerreno;
    }

    public BigDecimal getAreaTerreno() {
        return areaTerreno;
    }

    public void setAreaTerreno(BigDecimal areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public BigDecimal getAvaluoEdificacion() {
        return avaluoEdificacion;
    }

    public void setAvaluoEdificacion(BigDecimal avaluoEdificacion) {
        this.avaluoEdificacion = avaluoEdificacion;
    }

    public BigDecimal getAreaEdificacion() {
        return areaEdificacion;
    }

    public void setAreaEdificacion(BigDecimal areaEdificacion) {
        this.areaEdificacion = areaEdificacion;
    }

    public BigDecimal getAvaluoPredio() {
        return avaluoPredio;
    }

    public void setAvaluoPredio(BigDecimal avaluoPredio) {
        this.avaluoPredio = avaluoPredio;
    }

    public BigDecimal getImpuestoPredial() {
        return impuestoPredial;
    }

    public void setImpuestoPredial(BigDecimal impuestoPredial) {
        this.impuestoPredial = impuestoPredial;
    }

    public BigDecimal getContribucionEspecialMejoras() {
        return contribucionEspecialMejoras;
    }

    public void setContribucionEspecialMejoras(BigDecimal contribucionEspecialMejoras) {
        this.contribucionEspecialMejoras = contribucionEspecialMejoras;
    }

    public BigDecimal getTasaRecoleccionBasura() {
        return tasaRecoleccionBasura;
    }

    public void setTasaRecoleccionBasura(BigDecimal tasaRecoleccionBasura) {
        this.tasaRecoleccionBasura = tasaRecoleccionBasura;
    }

    public BigDecimal getCostoEmision() {
        return costoEmision;
    }

    public void setCostoEmision(BigDecimal costoEmision) {
        this.costoEmision = costoEmision;
    }

    public BigDecimal getTasaBomberos() {
        return tasaBomberos;
    }

    public void setTasaBomberos(BigDecimal tasaBomberos) {
        this.tasaBomberos = tasaBomberos;
    }

    public BigDecimal getServiciosAmbientales() {
        return serviciosAmbientales;
    }

    public void setServiciosAmbientales(BigDecimal serviciosAmbientales) {
        this.serviciosAmbientales = serviciosAmbientales;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ImpresionDto() {
    }
    
}
