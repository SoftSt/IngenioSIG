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
public enum EnumTraslacion {

    ND("N/D"),
    COMPRAVENTA("COMPRA VENTA"),
    PREMUTA("PREMUTA"),
    OTROS("OTROS"),
    HERENCIA("HERENCIA"),
    DONACION("DONACIÓN"),
    ADJUDICACION("ADJUDICACIÓN"),
    POSESION("POSESIÓN"),
    PARTICION("PARTICIÓN"),
    PROTOCOLIZACION("PROTOCOLIZACIÓN"),
    COMPENSACION("COMPENSACIÓN"),
    REMATE("REMATE");
    /*PROPIETARIO("PROPIETARIO"),
    ARRENDATARIO("ARRENDATARIO"),
    USUFRUCTUARIO("USUFRUCTUARIO");*/

    private final String stsTransferenciadominio;

    private EnumTraslacion(String stsTransferenciadominio) {
        this.stsTransferenciadominio = stsTransferenciadominio;
    }

    public String getStsTransferenciadominio() {
        return this.stsTransferenciadominio;
    }

    @Override
    public String toString() {
        return this.stsTransferenciadominio;
    }

    public static EnumTraslacion obtenerTraslacion(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumTraslacion traslacion : EnumTraslacion.values()) {
                if (traslacion.toString().contentEquals(nombre.trim())) {
                    return traslacion;
                }
            }
            return EnumTraslacion.ND;
        } else {
            return EnumTraslacion.ND;
        }
    }

}
