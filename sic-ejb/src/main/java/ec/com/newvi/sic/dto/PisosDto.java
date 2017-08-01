/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class PisosDto {

    private String nomBloque;
    private String nomPiso;
    private String valNropisos;
    private BigDecimal valAreapiso;
    private Integer valAnioconstruccion;
    private Integer valAnioreparacion;
    private List<PisoDetalle> listaDetallesPisos;

    public List<PisoDetalle> getListaDetallesPisos() {
        return listaDetallesPisos;
    }

    public void setListaDetallesPisos(List<PisoDetalle> listaDetallesPisos) {
        this.listaDetallesPisos = listaDetallesPisos;
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
    
    public PisosDto() {
    }

}
