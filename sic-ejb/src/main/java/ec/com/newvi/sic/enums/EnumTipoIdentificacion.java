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
public enum EnumTipoIdentificacion {
    
    CEDULA_RUC_NATURAL("CEDULA_RUC_NATURAL"),
    RUC_SOCIEDAD_PRIVADA("RUC_SOCIEDAD_PRIVADA"),
    RUC_SOCIEDAD_PUBLICA("RUC_SOCIEDAD_PUBLICA"),;

    @Override
    public String toString() {
        return this.tipoIdentificacion;
    }
    
    private final String tipoIdentificacion;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    
    private EnumTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    
    
}
