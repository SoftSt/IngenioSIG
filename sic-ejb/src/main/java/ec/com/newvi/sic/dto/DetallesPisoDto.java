/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class DetallesPisoDto {
    
    
    
    /*detalles piso*/
    private String subgrupo;
    private String descripcion;
    private String estadoDetalle;
    
    
    private String nomBloque;
    private String nomPiso;
    private String valNropisos;
    private BigDecimal valAreapiso;
    private Integer valAnioconstruccion;
    private Integer valAnioreparacion;

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(String estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public String getNomBloque() {
        return nomBloque;
    }

    public void setNomBloque(String nomBloque) {
        this.nomBloque = nomBloque;
    }

    public String getNomPiso() {
        return nomPiso;
    }

    public void setNomPiso(String nomPiso) {
        this.nomPiso = nomPiso;
    }

    public String getValNropisos() {
        return valNropisos;
    }

    public void setValNropisos(String valNropisos) {
        this.valNropisos = valNropisos;
    }

    public BigDecimal getValAreapiso() {
        return valAreapiso;
    }

    public void setValAreapiso(BigDecimal valAreapiso) {
        this.valAreapiso = valAreapiso;
    }

    public Integer getValAnioconstruccion() {
        return valAnioconstruccion;
    }

    public void setValAnioconstruccion(Integer valAnioconstruccion) {
        this.valAnioconstruccion = valAnioconstruccion;
    }

    public Integer getValAnioreparacion() {
        return valAnioreparacion;
    }

    public void setValAnioreparacion(Integer valAnioreparacion) {
        this.valAnioreparacion = valAnioreparacion;
    }

    public DetallesPisoDto() {
    }
    
    
}
