/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.dto.FichaCatastralDto;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Andrés
 */
public class LazySorter implements Comparator<FichaCatastralDto>{

    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    } 
    @Override
    public int compare(FichaCatastralDto ficha1, FichaCatastralDto ficha2) {
        try {
            Object value1 = FichaCatastralDto.class.getField(this.sortField).get(ficha1);
            Object value2 = FichaCatastralDto.class.getField(this.sortField).get(ficha2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
    
}
