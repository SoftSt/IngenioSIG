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
    PREDIO_AREA("Area","S","valAreapredio"),
    PREDIO_FRENTE("Frente","S",""),
    PREDIO_FONDO_RELATIVO("Fondo relativo","S",""),
    PREDIO_FACTOR_FRENTE_FONDO("Factor frente fondo","S",""),
    PREDIO_TOPOGRAFIA("Topografía","S",""),
    PREDIO_EROSION("Erosión","S",""),
    PREDIO_FORMA("Forma","S",""),
    PREDIO_UBICACION("Ubicación","S",""),
    PREDIO_AREA_CONSTRUCCION("Area de contruccion","N","valAreaconstruccion"),
    PREDIO_PROMEDIO_FACTORES("Promedio de factores","S",""),
    PREDIO_VALOR_TERRENO("Valor del terreno","S","valTerreno"),
    PREDIO_VALOR_EDIFICACION("Valor de la edificacion","S","valEdifica"),
    PREDIO_VALOR_PREDIO("Valor del predio","S","valPredio"),
    PREDIO_IMPUESTO_PREDIAL("Impuesto predial","S","valImpuesto"),
    PREDIO_A_PAGAR("A pagar","S","valImppredial"),
    // BLOQUE
    BLOQUE_VALORACION("Valoración del bloque", "N",""),
    BLOQUE_AREA("Area del bloque", "N",""),
    BLOQUE_COSTO_TOTAL("Costo total bloque", "N",""),
    // PISO
    PISO_CODIGO("Codigo", "N",""),
    PISO_EDAD("Edad", "S",""),
    PISO_ESTADO("Estado", "S",""),
    PISO_AREA("Area", "S",""),
    PISO_FACTOR_DEPRECIACION("Factor Depreciación", "S",""),
    PISO_DETALLE("Detalles piso", "S",""),
    PISO_VALORACION("Valoración del piso", "S",""),
    //DETALLE
    DETALLE_ESTRUCTURA("Estructura", "S",""),
    DETALLE_ACABADOS("Acabados", "S",""),
    DETALLE_EXTRAS("Extras", "S",""),
    DETALLE_VALORACION("Total", "N",""),
    DETALLE_VALORACION_METRO("Valor m2", "N",""),
    DETALLE_FACTORES("Total Factores", "N",""),
    
    DETALLE_COSTO_METRO_REFERENCIAL("Costo metro referencial", "S",""),
    
    //IMPUESTPS
    IMPUESTOS_BOMBEROS("Bomberos", "S","valBomberos"),
    IMPUESTOS_COSTO_EMISION("Costo emisión", "S","valEmision"),
    IMPUESTOS_CEM("CEM", "S","valCem"),
    IMPUESTOS_SERVICIOS_AMBIENTALES("Servicios ambientales", "S","valAmbientales"),
    IMPUESTOS_SOLAR_NO_EDIFICADO("Solar no edificado", "S",""),
    IMPUESTOS_OTROS_VALORES("OTROS RUBROS", "S","Tiene hijos"),
    
    
    
    ;
    

    private final String titulo;
    private final String esVisible;
    private final String campo;

    public String getCampo() {
        return campo;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getEsVisible() {
        return esVisible;
    }
    
    private EnumCaracteristicasAvaluo(String titulo, String esVisible, String campo) {
        this.titulo = titulo;
        this.esVisible = esVisible;
        this.campo = campo;
    }
}
