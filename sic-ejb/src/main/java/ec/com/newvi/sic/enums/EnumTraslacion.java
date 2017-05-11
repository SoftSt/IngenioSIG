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
public enum EnumTraslacion {
    
    ND("N/D"),
    COMPRAVENTA("COMPRA VENTA"),
    HERENCIA("HERENCIA"),
    DONACIÓN("DONACIÓN"),
    POSESIÓN("POSESIÓN"),
    PREMUTA("Asociacion"),
    ADJUDICACIÓN("ADJUDICACIÓN"),
    REMATE("REMATE"),
    PARTICIÓN("PARTICIÓN"),
    COMPENSACIÓN("COMPENSACIÓN"),
    OTROS("OTROS"),
    PROPIETARIO("PROPIETARIO"),
    ARRENDATARIO("ARRENDATARIO"),
    USUFRUCTUARIO("USUFRUCTUARIO"),
    PROTOCOLIZACIÓN("PROTOCOLIZACIÓN");
    
    private final String stsTransferenciadominio;

    private EnumTraslacion(String stsTransferenciadominio) {
        this.stsTransferenciadominio = stsTransferenciadominio;
    }

    public String getStsTransferenciadominio() {
        return stsTransferenciadominio;
    }

}