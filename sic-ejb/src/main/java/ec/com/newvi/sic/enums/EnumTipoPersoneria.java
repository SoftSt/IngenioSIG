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
public enum EnumTipoPersoneria {
    Natural("Persona Natural"),
    Juridica("Persona Juridica");

    private final String descripcionTipoPersoneria;

    private EnumTipoPersoneria(String descripcionTipoPersoneria) {
        this.descripcionTipoPersoneria = descripcionTipoPersoneria;
    }

    public String getDescripcionTipoPersoneria() {
        return descripcionTipoPersoneria;
    }
}
