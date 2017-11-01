/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

import ec.com.newvi.sic.util.ComunUtil;

/**
 *
 * @author NEWVI
 */
public enum EnumTenencia {

    ND("N/D"),
    Particular("Particular"),
    Municipal("Municipal"),
    Iglesia("Iglesia"),
    Otros("Otros"),
    Comunal("Comunal"),
    Estatal("Estatal");
    /*Cooperativa("Cooperativa"),
    Asociacion("Asociacion"),
    Compania("Compañia"),
    Provincial("Provincial");*/

    private final String stsTenencia;

    public String getStsTenencia() {
        return stsTenencia;
    }

    private EnumTenencia(String stsTenencia) {
        this.stsTenencia = stsTenencia;
    }

    @Override
    public String toString() {
        return this.stsTenencia;
    }

    public static EnumTenencia obtenerTenencia(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumTenencia tenencia : EnumTenencia.values()) {
                if (tenencia.toString().contentEquals(nombre.trim())) {
                    return tenencia;
                }
            }
            return EnumTenencia.ND;
        } else {
            return EnumTenencia.ND;
        }
    }

}
