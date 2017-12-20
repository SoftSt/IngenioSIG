/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

import ec.com.newvi.sic.util.ComunUtil;

/**
 *
 * @author Andrés
 */
public enum EnumAplicacionCEM {

    APLICA("APLICA", Boolean.TRUE),
    NO_APLICA("NO_APLICA", Boolean.FALSE);

    private final String aplicacionPorcentajes;
    private final Boolean esAplicado;

    public String getAplicacionPorcentajes() {
        return aplicacionPorcentajes;
    }

    public Boolean getEsAplicado() {
        return esAplicado;
    }
    
    @Override
    public String toString() {
        return this.aplicacionPorcentajes;
    }

    private EnumAplicacionCEM(String aplicacionPorcentajes, Boolean esAplicado) {
        this.aplicacionPorcentajes = aplicacionPorcentajes;
        this.esAplicado = esAplicado;
    }

    public static EnumAplicacionCEM obtenerAplicacionCEM(String aplicacionPorcentajes) {
        if (!ComunUtil.esCadenaVacia(aplicacionPorcentajes)) {
            for (EnumAplicacionCEM aplicacion : EnumAplicacionCEM.values()) {
                if (aplicacion.toString().contentEquals(aplicacionPorcentajes.trim())) {
                    return aplicacion;
                }
            }
            return EnumAplicacionCEM.APLICA;
        } else {
            return EnumAplicacionCEM.APLICA;
        }
    }

    public static Boolean obtenerEstadoAplicacionCEM(String aplicacionPorcentajes) {
        if (!ComunUtil.esCadenaVacia(aplicacionPorcentajes)) {
            for (EnumAplicacionCEM aplicacion : EnumAplicacionCEM.values()) {
                if (aplicacion.toString().contentEquals(aplicacionPorcentajes.trim())) {
                    return aplicacion.getEsAplicado();
                }
            }
            return Boolean.FALSE;
        } else {
            return Boolean.FALSE;
        }
    }
    
    public static String obtenerDescripcionAplicacionCEM(EnumAplicacionCEM aplicacionPorcentajes){
        return aplicacionPorcentajes.getAplicacionPorcentajes();
    }
    

}
