/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author NEWVI
 */
public enum EnumRelacionDominios {
    
    Nodo("Nodo"),
    SubNodo("SubNodo"),
    Hijo("Hijo");
    
    private final String descripcionRelacionDominios;

    private EnumRelacionDominios(String descripcionRelacionDominios) {
        this.descripcionRelacionDominios = descripcionRelacionDominios;
    }

    public String getDescripcionRelacionDominios() {
        return descripcionRelacionDominios;
    }
    
}
