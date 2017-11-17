/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

import ec.com.newvi.sic.util.ComunUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author israelavila
 */
public enum EnumCaracteristicasAvaluo {
    // PREDIO
    PREDIO_TERRENO_AREA("Área","S","valAreapredio", "", ""),
    PREDIO_TERRENO_FRENTE("Frente","S","", "", ""),
    PREDIO_TERRENO_FONDO_RELATIVO("Fondo relativo","S","", "", ""),
    PREDIO_TERRENO_FACTOR_FRENTE_FONDO("Factor frente fondo","S","", "", ""),
    PREDIO_TERRENO_TOPOGRAFIA("Topografía","S","", "TOPOGRAFIA", "DESCRIPCION DEL TERRENO"),
    PREDIO_TERRENO_LOCALIZACION("Localización","S","", "LOCALIZACION", "DESCRIPCION DEL TERRENO"),
    PREDIO_TERRENO_FORMA("Forma","S","", "FORMA", "DESCRIPCION DEL TERRENO"),
    PREDIO_TERRENO_UBICACION("Ubicación","S","", "OCUPACION", "DESCRIPCION DEL TERRENO"),
    PREDIO_TERRENO_AREA_CONSTRUCCION("Área de construcción","N","valAreaconstruccion", "", ""),
    
    PREDIO_SERVICIOS_COEFICIENTE_RED_VIAL_USO("Red Vial - Uso", "S", "", "USO", "INFRAESTRUCTURA DE SERVICIOS"),
    PREDIO_SERVICIOS_COEFICIENTE_RED_VIAL_MATERIAL("Red Vial - Material", "S", "", "MATERIAL", "INFRAESTRUCTURA DE SERVICIOS"),
    PREDIO_SERVICIOS_COEFICIENTE_ENERGIA_ELECTRICA("Energía Eléctrica", "S", "", "ENERGÍA ELÉCTRICA", "INFRAESTRUCTURA DE SERVICIOS"),
    PREDIO_SERVICIOS_COEFICIENTE_ABASTECIMIENTO_AGUA("Abastecimiento de Agua", "S", "", "ABASTECIMIENTO DE AGUA", "INFRAESTRUCTURA DE SERVICIOS"),
    PREDIO_SERVICIOS_COEFICIENTE_ALCANTARILLADO("Alcantarillado", "S", "", "ALCANTARILLADO", "INFRAESTRUCTURA DE SERVICIOS"),    
    
    PREDIO_PROMEDIO_FACTORES("FACTORES DE AFECTACIÓN","S","valPromFactores", "", ""),
    PREDIO_PRECIO_BASE("PRECIO BASE","N","valPrecioBase", "", ""),
    PREDIO_VALOR_TERRENO("Valor del terreno (VT)","S","valTerreno", "", ""),
    PREDIO_VALOR_EDIFICACION("Valor de la edificación (VE)","S","valEdifica", "", ""),
    PREDIO_VALOR_PREDIO("Valor del predio (VT + VE)","S","valPredio", "", ""),
    PREDIO_IMPUESTO_PREDIAL("Impuesto predial","S","valImpuesto", "", ""),
    PREDIO_A_PAGAR("A pagar","S","valImppredial", "", ""),
    // BLOQUE
    BLOQUE_VALORACION("Valoración del bloque", "N","", "", ""),
    BLOQUE_AREA("Área del bloque", "N","", "", ""),
    BLOQUE_COSTO_TOTAL("Costo total bloque", "N","", "", ""),
    // PISO
    PISO_CODIGO("Código", "N","", "", ""),
    PISO_EDAD("Edad", "S","", "", ""),
    PISO_ESTADO("Estado", "S","", "", ""),
    PISO_AREA("Área", "S","", "", ""),
    PISO_FACTOR_DEPRECIACION("Factor Depreciación", "S","", "", ""),
    PISO_DETALLE("Detalles piso", "S","", "", ""),
    PISO_VALORACION("Valoración del piso", "S","", "", ""),
    //DETALLE
    DETALLE_ESTRUCTURA("Estructura", "S","", "", ""),
    DETALLE_ACABADOS("Acabados", "S","", "", ""),
    DETALLE_EXTRAS("Extras", "S","", "", ""),
    DETALLE_VALORACION("Total", "N","", "", ""),
    DETALLE_VALORACION_METRO("Valor m2", "N","", "", ""),
    DETALLE_FACTORES("Total Factores", "N","", "", ""),
    
    DETALLE_COSTO_METRO_REFERENCIAL("Costo metro referencial", "S","", "", ""),
    
    //IMPUESTPS
    IMPUESTOS_BOMBEROS("Bomberos", "S","valBomberos", "", ""),
    IMPUESTOS_COSTO_EMISION("Costo emisión", "S","valEmision", "", ""),
    IMPUESTOS_CEM("CEM", "S","valCem", "", ""),
    IMPUESTOS_SERVICIOS_AMBIENTALES("Servicios ambientales", "S","valAmbientales", "", ""),
    IMPUESTOS_SOLAR_NO_EDIFICADO("Solar no edificado", "S","", "", ""),
    IMPUESTOS_OTROS_VALORES("OTROS RUBROS", "S", "tieneHijos", "", ""),
    IMPUESTOS_OTROS_VALORES_TOTAL("Total Otros Rubros", "N", "", "", ""),
    IMPUESTOS_EXONERACIONES("EXONERACIONES", "S", "valDescuentosExoneraciones", "", ""),
    IMPUESTOS_EXONERACIONES_TOTAL("Total Exoneraciones", "N", "", "", ""),
    ;
    

    private final String titulo;
    private final String esVisible;
    private final String campo;
    private final String calculo;
    private final String grupo;

    public String getCampo() {
        return campo;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getEsVisible() {
        return esVisible;
    }

    public String getCalculo() {
        return calculo;
    }

    public String getGrupo() {
        return grupo;
    }
    
    private EnumCaracteristicasAvaluo(String titulo, String esVisible, String campo, String calculo, String grupo) {
        this.titulo = titulo;
        this.esVisible = esVisible;
        this.campo = campo;
        this.calculo = calculo;
        this.grupo = grupo;
    }
    
    public static List<EnumCaracteristicasAvaluo> obtenerCaracteristicasAvaluoPorGrupo(String grupo) {
        List<EnumCaracteristicasAvaluo> listaCaracteristicasAvaluo = new ArrayList<>();
        for (EnumCaracteristicasAvaluo enumCaracteristicasAvaluo : EnumCaracteristicasAvaluo.values()) {
            if (enumCaracteristicasAvaluo.getGrupo().contains(grupo)) {
                listaCaracteristicasAvaluo.add(enumCaracteristicasAvaluo);
            }
        }
        return listaCaracteristicasAvaluo;
    } 
    
    public static List<EnumCaracteristicasAvaluo> obtenerCaracteristicasAvaluoConCampo() {
        List<EnumCaracteristicasAvaluo> listaCaracteristicasAvaluo = new ArrayList<>();
        for (EnumCaracteristicasAvaluo enumCaracteristicasAvaluo : EnumCaracteristicasAvaluo.values()) {
            if (!ComunUtil.esCadenaVacia(enumCaracteristicasAvaluo.getCampo())) {
                listaCaracteristicasAvaluo.add(enumCaracteristicasAvaluo);
            }
        }
        return listaCaracteristicasAvaluo;
    } 
}
