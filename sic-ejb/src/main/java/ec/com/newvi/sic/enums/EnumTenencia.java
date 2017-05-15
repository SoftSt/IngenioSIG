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

    Particular("Particular"),
    Otros("Otros"),
    ND("N/D"),
    Cooperativa("Cooperativa"),
    Comunal("Comunal"),
    Asociacion("Asociacion"),
    Compania("Compañia"),
    Municipal("Municipal"),
    Provincial("Provincial"),
    Estatal("Estatal"),
    Iglesia("Iglesia");

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
