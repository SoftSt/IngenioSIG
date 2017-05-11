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
public enum EnumSitActual {
    
    PROPIETARIO("PROPIETARIO"),
    ARRENDATARIO("ARRENDATARIO"),
    POSESIONARIO("POSESIONARIO"),
    USUFRUCTUARIO("USUFRUCTUARIO");

    private final String stsSituacion;

    private EnumSitActual(String stsSituacion) {
        this.stsSituacion = stsSituacion;
    }

    public String getStsSituacion() {
        return stsSituacion;
    }

}