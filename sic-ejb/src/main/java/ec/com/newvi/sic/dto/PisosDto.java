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
import java.util.List;

/**
 *
 * @author Andrés
 */
public class PisosDto {

    private DetallesPisoDto detallesPisoDto;

    public DetallesPisoDto getDetallesPisoDto() {
        return detallesPisoDto;
    }

    public void setDetallesPisoDto(DetallesPisoDto detallesPisoDto) {
        this.detallesPisoDto = detallesPisoDto;
    }

    public PisosDto(Predios predio) {

        List<Bloques> listaBloques = (List<Bloques>) predio.getBloques();
        for (Bloques bloque : listaBloques) {
            List<Pisos> listaPisos = (List<Pisos>) bloque.getPisosCollection();
            for (Pisos piso : listaPisos) {
                List<PisoDetalle> detallesPiso = (List<PisoDetalle>) piso.getDetalles();
                for (PisoDetalle detalle : detallesPiso) {
                    
                }
            }
        }

        //this.detallesPisoDto = new DetallesPisoDto(bloque.getPisosCollection());
    }

}
