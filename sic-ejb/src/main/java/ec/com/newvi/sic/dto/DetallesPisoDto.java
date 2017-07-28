/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class DetallesPisoDto {
    
    private List<PisoDetalle> listaDetallesPiso;

    public List<PisoDetalle> getListaDetallesPiso() {
        return listaDetallesPiso;
    }

    public void setListaDetallesPiso(List<PisoDetalle> listaDetallesPiso) {
        this.listaDetallesPiso = listaDetallesPiso;
    }
    

    public DetallesPisoDto() {
    }
    public DetallesPisoDto(Pisos piso) {
        this.listaDetallesPiso = (List<PisoDetalle>)piso.getDetalles();
    }
    
    
}
