/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.enums;

/**
 *
 * @author israelavila
 */
public enum EnumParametrosReporte {
    //PARAMETROS GENERALES
    TITULO_REPORTE("TITULO_REPORTE"),
    RUTA_REPORTES("RUTA_REPORTES"),
    DATASOURCE_EXPRESSION("DATASOURCE_EXPRESSION"),
    LOCALE("LOCALE"),
    REPORT_TIME_ZONE("REPORT_TIME_ZONE"),
    NOMBRE_SISTEMA("NOMBRE_SISTEMA"),
    NOMBRE_MUNICIPIO("NOMBRE_MUNICIPIO"),
    NOMBRE_MODULO("NOMBRE_MODULO"),
    NOMBRE_USUARIO("NOMBRE_USUARIO"),
    RUTA_IMAGENES_REPORTES("RUTA_IMAGENES"),
    
    // PARAMETROS PARTICULARES
    IMAGEN_DELIMITACION_PREDIO("IMAGEN_DELIMITACION_PREDIO"),
    DESCRIPCION_TERRENO("DESCRIPCION_TERRENO"),
    INFRAESTRUCTURA_SERVICIOS("INFRAESTRUCTURA_SERVICIOS"),
    CARACTERISTICAS_EDIFICACION("CARACTERISTICAS_EDIFICACION"),
    PISO("PISO"),
    ;
    
    private final String nombre;

    public String getNombre() {
        return nombre;
    }
    
    private EnumParametrosReporte(String nombreReporte) {
        this.nombre = nombreReporte;
    }
    
}
