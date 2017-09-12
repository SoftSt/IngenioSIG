/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

import ec.com.newvi.sic.util.ComunUtil;

/**
 *
 * @author Andrés
 */
public enum EnumAplicacion {
    
    Total("Total"),
    Parcial("Parcial");
    
    private final String aplicacion;

    public String getAplicacion() {
        return aplicacion;
    }

    private EnumAplicacion(String aplicacion) {
        this.aplicacion = aplicacion.trim();
    }
    
    public static EnumAplicacion obtenerAplicacion(String aplicacion) {
        if (!ComunUtil.esCadenaVacia(aplicacion)) {
            for (EnumAplicacion aplicaciones : EnumAplicacion.values()) {
                if (aplicaciones.toString().contentEquals(aplicacion.trim())) {
                    return aplicaciones;
                }
            }
            return EnumAplicacion.Total;
        } else {
            return EnumAplicacion.Total;
        }

    }
    
}
