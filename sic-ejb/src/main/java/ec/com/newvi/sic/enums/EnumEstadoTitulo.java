/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author Andrés
 */
public enum EnumEstadoTitulo {
    
    TITULO_EMITIDO("Título Emitido"),
    TITULO_ANULADO("Título Anulado"),
    TITULO_COBRADO("Título Cobrado");
    
    private final String estadoTitulo;

    private EnumEstadoTitulo(String estadoTitulo) {
        this.estadoTitulo = estadoTitulo;
    }

    
    public String getEstadoTitulo() {
        return estadoTitulo;
    }
    
    
}
