/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author israelavila
 */
public enum EnumCaracteristicasAvaluo {
    // PREDIO
    // BLOQUE
    BLOQUE_VALORACION("Valoración del bloque", "N"),
    BLOQUE_AREA("Area del bloque", "N"),
    // PISO
    PISO_CODIGO("Codigo", "N"),
    PISO_EDAD("Edad", "S"),
    PISO_ESTADO("Estado", "S"),
    PISO_AREA("Area", "S"),
    PISO_FACTOR_DEPRECIACION("Factor Depreciación", "S"),
    PISO_DETALLE("Detalles piso", "S"),
    PISO_VALORACION("Valoración del piso", "S"),
    //DETALLE
    DETALLE_ESTRUCTURA("Estructura", "S"),
    DETALLE_ACABADOS("Acabados", "S"),
    DETALLE_EXTRAS("Extras", "S"),
    DETALLE_VALORACION("Total", "N"),
    DETALLE_VALORACION_METRO("Valor m2", "N"),
    DETALLE_FACTORES("Total Factores", "N"),
    ;

    private final String titulo;
    private final String esVisible;

    public String getTitulo() {
        return titulo;
    }

    public String getEsVisible() {
        return esVisible;
    }
    
    private EnumCaracteristicasAvaluo(String titulo, String esVisible) {
        this.titulo = titulo;
        this.esVisible = esVisible;
    }
}
