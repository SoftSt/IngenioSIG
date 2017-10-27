/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.DetallesAvaluo;
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
    private String valor;
    private String factor;
    private List<AvaluoDto> hijos;
    private DetallesAvaluo detallesAvaluo;

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public AvaluoDto() {
    }

    public AvaluoDto(DetallesAvaluo detalleAvaluo, CatastroServicio catastroServicio) {
        this.descripcion = detalleAvaluo.getDavalDescripcion();
        this.factor = detalleAvaluo.getDavalFactor();
        this.valor = detalleAvaluo.getDavalValor();
        this.detallesAvaluo = detalleAvaluo;
        obtenerHijos(catastroServicio);
    }
    
    public AvaluoDto(String descripcion, String valor, String factor, List<AvaluoDto> hijos) {
        this.descripcion = descripcion.trim();
        this.valor = valor;
        this.hijos = hijos;
        this.factor = factor;
    }

    private void obtenerHijos(CatastroServicio catastroServicio) {
        this.hijos = new ArrayList<>();
        List<DetallesAvaluo> detallesHijos = catastroServicio.consultarHijosDetallesAvaluo(detallesAvaluo);
        for (DetallesAvaluo nuevoHijo : detallesHijos) {
            if(!nuevoHijo.getDavalRelacion().equals("Nodo"))
            this.hijos.add(new AvaluoDto(nuevoHijo,catastroServicio));
        }
    }
    

}
