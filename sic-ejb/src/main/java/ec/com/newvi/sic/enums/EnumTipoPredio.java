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
public enum EnumTipoPredio {
    
    URBANO("URBANO"),
    RURAL("RURAL");
    
    private final String stsTipo;

    public String getStsTipo() {
        return stsTipo;
    }

    @Override
    public String toString() {
        return this.stsTipo;
    }
    
    private EnumTipoPredio(String stsTipo) {
        this.stsTipo = stsTipo;
    }
    
    
}
