/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class DetallesAvaluoDto {
    private String descripcion;
    private BigDecimal valor;
    private BigDecimal factor;
    private List<AvaluoDto> hijos;

    public DetallesAvaluoDto() {
    }

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

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }
}
