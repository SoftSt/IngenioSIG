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
public enum EnumAcciones {
    Edicion_General("Edición General del Predio"),
    Agregacion_Servicio("Agregación de servicios"),
    Edicion_Servicio("Edición de servicios"),
    Agregacion_Descripcion_Terreno("Agregación de descripción de terreno"),
    Agregacion_Bloques("Agregación de bloque"),
    Agregacion_Pisos("Agregación de pisos"),
    Agregacion_Detalles_Pisos("Agregación de detalles de pisos");
    
    private final String accion;

    public String getAccion() {
        return accion;
    }

    private EnumAcciones(String accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return this.accion;
    }
    
    
    
}
