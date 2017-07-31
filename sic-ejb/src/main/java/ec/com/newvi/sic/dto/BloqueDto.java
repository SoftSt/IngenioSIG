/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class BloqueDto {

    private List<PisosDto> listaPisosDto;

    public List<PisosDto> getListaPisosDto() {
        return listaPisosDto;
    }

    public void setListaPisosDto(List<PisosDto> listaPisosDto) {
        this.listaPisosDto = listaPisosDto;
    }

    public BloqueDto(Predios predio) {
        listaPisosDto = new ArrayList<>();
        List<Bloques> listaBloques = (List<Bloques>) predio.getBloques();
        for (Bloques bloque : listaBloques) {
            List<Pisos> listaPisos = (List<Pisos>) bloque.getPisosCollection();
            for (Pisos piso : listaPisos) {
                PisosDto pisoDto = new PisosDto();
                pisoDto.setNomBloque(bloque.getNomBloque());
                pisoDto.setNomPiso(piso.getNomPiso());
                pisoDto.setValAnioconstruccion(piso.getValAnioconstruccion());
                pisoDto.setValAnioreparacion(piso.getValAnioreparacion());
                pisoDto.setValAreapiso(piso.getValAreapiso());
                pisoDto.setValNropisos(bloque.getValNropisos());
                
                listaPisosDto.add(pisoDto);
            }
        }
    }

}
