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
public enum EnumEstadoPisoDetalle {
    Blank("          "),
    A_Reparar("A reparar"),
    Obsoleto("Obsoleto"),
    Estable("Estable");

    private final String estadoDetalle;

    private EnumEstadoPisoDetalle(String estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public String getEstadoDetalle() {
        return estadoDetalle;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static EnumEstadoPisoDetalle obtenerEstadoDetalle(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumEstadoPisoDetalle estadoDetalle : EnumEstadoPisoDetalle.values()) {
                if (estadoDetalle.toString().contentEquals(nombre.trim())) {
                    return estadoDetalle;
                }
            }
            return EnumEstadoPisoDetalle.Blank;
        } else {
            return EnumEstadoPisoDetalle.Blank;
        }
    }
    
}
