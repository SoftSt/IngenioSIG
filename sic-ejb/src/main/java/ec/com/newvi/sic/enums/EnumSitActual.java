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
public enum EnumSitActual {

    ND("N/D"),
    USUFRUCTUARIO("USUFRUCTUARIO"),
    ARRENDATARIO("ARRENDATARIO"),
    PROPIETARIO("PROPIETARIO"),
    POSESIONARIO("POSESIONARIO");

    private final String stsSituacion;

    private EnumSitActual(String stsSituacion) {
        this.stsSituacion = stsSituacion;
    }

    public String getStsSituacion() {
        return stsSituacion;
    }

    @Override
    public String toString() {
        return this.stsSituacion;
    }

    public static EnumSitActual obtenerSituacionActual(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumSitActual situacionActual : EnumSitActual.values()) {
                if (situacionActual.toString().contentEquals(nombre.trim())) {
                    return situacionActual;
                }
            }
            return EnumSitActual.ND;
        } else {
            return EnumSitActual.ND;
        }
    }

}
