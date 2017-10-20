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
    PREDIO_AREA("Area","S"),
    PREDIO_FRENTE("Frente","S"),
    PREDIO_FONDO_RELATIVO("Fondo relativo","S"),
    PREDIO_FACTOR_FRENTE_FONDO("Factor frente fondo","S"),
    PREDIO_TOPOGRAFIA("Topografía","S"),
    PREDIO_EROSION("Erosión","S"),
    PREDIO_FORMA("Forma","S"),
    PREDIO_UBICACION("Ubicación","S"),
    PREDIO_PROMEDIO_FACTORES("Promedio de factores","S"),
    PREDIO_VALOR_TERRENO("Valor del terreno","S"),
    PREDIO_VALOR_EDIFICACION("Valor de la edificacion","S"),
    PREDIO_VALOR_PREDIO("Valor del predio","S"),
    PREDIO_IMPUESTO_PREDIAL("Impuesto predial","S"),
    PREDIO_A_PAGAR("A pagar","S"),
    // BLOQUE
    BLOQUE_VALORACION("Valoración del bloque", "N"),
    BLOQUE_AREA("Area del bloque", "N"),
    BLOQUE_COSTO_TOTAL("Costo total bloque", "N"),
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
    
    DETALLE_COSTO_METRO_REFERENCIAL("Costo metro referencial", "S"),
    
    //IMPUESTPS
    IMPUESTOS_BOMBEROS("Bomberos", "S"),
    IMPUESTOS_COSTO_EMISION("Costo emisión", "S"),
    IMPUESTOS_CEM("CEM", "S"),
    IMPUESTOS_SERVICIOS_AMBIENTALES("Servicios ambientales", "S"),
    IMPUESTOS_RECOLECION_BASURA("Tasa recolección basura", "S"),
    IMPUESTOS_OTROS_VALORES("OTROS RUBROS", "S"),
    
    
    
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
