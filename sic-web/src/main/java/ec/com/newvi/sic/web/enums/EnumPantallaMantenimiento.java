/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.enums;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;

/**
 *
 * @author israelavila
 */
public enum EnumPantallaMantenimiento {
    
    PANTALLA_LISTADO("PANTALLA_LISTADO"), 
    PANTALLA_EDICION("PANTALLA_EDICION"), 
    PANTALLA_ASIGNACION("PANTALLA_ASIGNACION");
    
    private final String nombrePantalla;

    public String getNombrePantalla() {
        return nombrePantalla;
    }
    
    private EnumPantallaMantenimiento(String nombrePantalla) {
        this.nombrePantalla = nombrePantalla;
    }
    
    public static EnumPantallaMantenimiento obtenerPantallaPorNombre(String nombrePantalla) throws NewviExcepcion {
        for (EnumPantallaMantenimiento pantalla : EnumPantallaMantenimiento.values()) {
            if (pantalla.getNombrePantalla().contentEquals(nombrePantalla)) {
                return pantalla;
            }
        }
        throw new NewviExcepcion(EnumNewviExcepciones.ERR202);
    }
}
