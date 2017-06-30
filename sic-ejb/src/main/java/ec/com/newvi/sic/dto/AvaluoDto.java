/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.servicios.CatastroServicio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class AvaluoDto {
    

    private String descripcion;
    private BigDecimal valor;
    private List<AvaluoDto> hijos;
    private Object valor2;

    public List<AvaluoDto> getHijos() {
        return hijos;
    }

    public void setHijos(List<AvaluoDto> hijos) {
        this.hijos = hijos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Object getValor2() {
        return valor2;
    }

    public void setValor2(Object valor2) {
        this.valor2 = valor2;
    }

    public AvaluoDto() {
        //AvaluoDto raiz = new AvaluoDto();
    }
    
    
    
    
}
