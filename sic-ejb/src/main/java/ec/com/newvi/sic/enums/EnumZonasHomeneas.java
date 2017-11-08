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
public enum EnumZonasHomeneas {
    ZONA("Z"),
    SECTOR("S"),
    MANZANA("M"),
    PREDIO("P");
    private final String tipoZona;

    private EnumZonasHomeneas(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }
}
