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
public enum EnumDescuentosCatastro {

    Niguno("Ninguno"),
    Ley_de_anciano("Ley de anciano"),
    Predios_Municipales("Predios Municipales"),
    Otros_de_excepcion("Otros de excepción"),
    Ley_de_discapacidad("Ley de discapacidad");
    
    private final String descuentosCatastro;

    private EnumDescuentosCatastro(String descuentosCatastro) {
        this.descuentosCatastro = descuentosCatastro;
    }

    public String getDescuentosCatastro() {
        return this.descuentosCatastro;
    }

    @Override
    public String toString() {
        return this.descuentosCatastro;
    }
    
    public static EnumDescuentosCatastro obtenerDescuento(String descuento) {
        if (!ComunUtil.esCadenaVacia(descuento)) {
            for (EnumDescuentosCatastro descuentoCatastro : EnumDescuentosCatastro.values()) {
                if (descuentoCatastro.toString().contentEquals(descuento.trim())) {
                    return descuentoCatastro;
                }
            }
            return EnumDescuentosCatastro.Niguno;
        } else {
            return EnumDescuentosCatastro.Niguno;
        }
    }

}
