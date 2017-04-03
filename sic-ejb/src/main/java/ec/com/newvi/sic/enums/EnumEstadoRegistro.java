/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author israelavila
 */
public enum EnumEstadoRegistro {
    
    A ('A'),
    E ('E'),
    I ('I');
    
    private final Character descripcionEstado;

    public Character getDescripcionEstado() {
        return descripcionEstado;
    }
    
    private EnumEstadoRegistro(Character descripcionEstado){
        this.descripcionEstado = descripcionEstado;
    }
}
