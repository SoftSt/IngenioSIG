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
public enum EnumMeses {

    ENERO(1, "Enero"),
    FEBRERO(2, "Febrero"),
    MARZO(3, "Marzo"),
    ABRIL(4, "Abril"),
    MAYO(5, "Mayo"),
    JUNIO(6, "Junio"),
    JULIO(7, "Julio"),
    AGOSTO(8, "Agosto"),
    SEPTIEMBRE(9, "Septiembre"),
    OCTUBRE(10, "Octubre"),
    NOVIEMBRE(11, "Noviembre"),
    DICIEMBRE(12, "Diciembre");

    private final String descripcion;
    private final Integer mes;

    private EnumMeses(Integer mes, String descripcion) {
        this.descripcion = descripcion;
        this.mes = mes;
    }

    public Integer getMes() {
        return mes;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static String obtenerDescripcionMes(Integer mes) {
        if (!ComunUtil.esNulo(mes)&&ComunUtil.esNumeroPositivo(mes)) {
            for (EnumMeses mesActual : EnumMeses.values()) {
                if (mesActual.getMes().equals(mes)) {
                    return mesActual.getDescripcion();
                }
            }
            return EnumMeses.DICIEMBRE.descripcion;
        } else {
            return EnumMeses.DICIEMBRE.descripcion;
        }
    }

}
