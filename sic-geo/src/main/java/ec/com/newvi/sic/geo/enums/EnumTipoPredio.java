/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.enums;

/**
 *
 * @author israelavila
 */
public enum EnumTipoPredio {
    URBANO("Urbano"),
    RURAL("Rural");
    
    private final String tipoPredio;

    public String getTipoPredio() {
        return tipoPredio;
    }
    
    private EnumTipoPredio(String tipoPredio) {
        this.tipoPredio = tipoPredio;
    }
}
