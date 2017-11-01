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
public enum EnumSiNo {

    ND("N/D"),
    SI("SI"),
    NO("NO");

    private final String stsEscritura;

    private EnumSiNo(String stsEscritura) {
        this.stsEscritura = stsEscritura;
    }

    public String getStsEscritura() {
        return stsEscritura;
    }

    @Override
    public String toString() {
        return this.stsEscritura;
    }

    public static EnumSiNo obtenerEscritura(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumSiNo escritura : EnumSiNo.values()) {
                if (escritura.toString().contentEquals(nombre.trim())) {
                    return escritura;
                }
            }
            return EnumSiNo.ND;
        } else {
            return EnumSiNo.ND;
        }

    }

}
