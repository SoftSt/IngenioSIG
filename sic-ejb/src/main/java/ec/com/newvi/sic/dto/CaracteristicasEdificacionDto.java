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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class CaracteristicasEdificacionDto {
    
    List<DetallesPisoDto> listadetallesPisoDtoD;

    public List<DetallesPisoDto> getListadetallesPisoDtoD() {
        return listadetallesPisoDtoD;
    }

    public void setListadetallesPisoDtoD(List<DetallesPisoDto> listadetallesPisoDtoD) {
        this.listadetallesPisoDtoD = listadetallesPisoDtoD;
    }

    public CaracteristicasEdificacionDto(Predios predio) {
        
        listadetallesPisoDtoD = new ArrayList<>();
        List<Bloques> listaBloques = (List<Bloques>) predio.getBloques();
        for (Bloques bloque : listaBloques) {
            List<Pisos> listaPisos = (List<Pisos>) bloque.getPisosCollection();
            for (Pisos piso : listaPisos) {
                List<PisoDetalle> listaPisosDetalle = (List<PisoDetalle>) piso.getDetalles();
                
                for (PisoDetalle pisoDetalle : listaPisosDetalle) {
                    
                    DetallesPisoDto detallesPisoDto = new DetallesPisoDto();
                    detallesPisoDto.setNomBloque(bloque.getNomBloque());
                    detallesPisoDto.setValNropisos(bloque.getValNropisos());
                    detallesPisoDto.setNomPiso(piso.getNomPiso());
                    detallesPisoDto.setValAnioconstruccion(piso.getValAnioconstruccion());
                    detallesPisoDto.setValAnioreparacion(piso.getValAnioreparacion());
                    detallesPisoDto.setValAreapiso(piso.getValAreapiso());
                    detallesPisoDto.setSubgrupo(pisoDetalle.getSubgrupo());
                    detallesPisoDto.setDescripcion(pisoDetalle.getDescripcion());
                    detallesPisoDto.setEstadoDetalle(pisoDetalle.getEstadoDetalle());

                    listadetallesPisoDtoD.add(detallesPisoDto);
                }
                
                
            }
        }
    }
    
    
}
