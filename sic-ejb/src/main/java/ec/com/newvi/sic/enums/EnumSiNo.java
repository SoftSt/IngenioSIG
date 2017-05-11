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
public enum EnumSiNo {
    
    PROPIETARIO("PROPIETARIO"),
    ARRENDATARIO("ARRENDATARIO"),
    POSESIONARIO("POSESIONARIO"),
    USUFRUCTUARIO("USUFRUCTUARIO");

    private final String stsEscritura;

    private EnumSiNo(String stsEscritura) {
        this.stsEscritura = stsEscritura;
    }

    public String getStsEscritura() {
        return stsEscritura;
    }

}