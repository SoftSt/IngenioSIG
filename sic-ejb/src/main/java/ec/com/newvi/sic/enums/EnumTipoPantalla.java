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
public enum EnumTipoPantalla {
    eliminacionFicha ("eliminacionFicha"),
    eliminacionFichaRural ("eliminacionFichaRural"),
    edicionFicha ("edicionFicha"),
    edicionFichaRural ("edicionFichaRural"),
    nuevaFicha ("nuevaFicha"),
    formulariosEconomicos ("formulariosEconomicos");
    
    private final String tipoPantalla;

    private EnumTipoPantalla(String tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    public String getTipoPantalla() {
        return tipoPantalla;
    }

    @Override
    public String toString() {
        return this.tipoPantalla;
    }
    
    
}
