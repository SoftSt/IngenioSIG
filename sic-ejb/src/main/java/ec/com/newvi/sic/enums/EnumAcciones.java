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
    Edicion_General("Edición General"),
    Agregacion_Servicio("Agregación de servicios");
    
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
