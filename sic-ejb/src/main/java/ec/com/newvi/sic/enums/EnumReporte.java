/*
 * (c) 2017 NewVi Cia. Ltda.
 *  * 
 */
package ec.com.newvi.sic.enums;

import ec.com.newvi.sic.util.ComunUtil;

/**
 *
 * Provee de los tipos de reporte existentes en el sistema. Se utiliza para la
 * selección de reportes en el sistema
 *
 * @author israelavila
 */
public enum EnumReporte {
    
    TABLA_CATASTRAL_URBANA("TABLA_CATASTRAL_URBANA"),
    TABLA_CATASTRAL_URBANA_CONDENSADA("TABLA_CATASTRAL_URBANA_CONDENSADA"),
    TABLA_CATASTRAL_URBANA_PAGINADA("TABLA_CATASTRAL_URBANA_PAGINADA"),
    FICHA_RELEVAMIENTO_PREDIAL_URBANO("FICHA_RELEVAMIENTO_PREDIAL_URBANO"),
    NOTIFICACION_AVALUO("NOTIFICACION_AVALUO"),
    CERTIFICACION_AVALUO("CERTIFICACION_AVALUO"),
    TITULO_CREDITO("TITULO_CREDITO"),
    TITULO_GENERADO("TITULO_GENERADO"),
    LISTA_TITULOS_COMPLETA("LISTA_TITULOS_COMPLETA"),
    LISTA_LOG_PREDIOS("LISTA_LOG_PREDIOS"),;
    
    private final String nombreReporte;

    public String getNombreReporte() {
        return nombreReporte;
    }

    private EnumReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
    
    @Override
    public String toString() {
        return this.name();
    }

    public static EnumReporte obtenerNombreReporte(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumReporte reporte : EnumReporte.values()) {
                if (reporte.toString().contentEquals(nombre.trim())) {
                    return reporte;
                }
            }
            return EnumReporte.TABLA_CATASTRAL_URBANA;
        } else {
            return EnumReporte.TABLA_CATASTRAL_URBANA;
        }
    }

}
