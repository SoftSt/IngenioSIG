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
public enum EnumGrupoServicios {
    INFRAESTRUCTURA_DE_SERVICIOS("INFRAESTRUCTURA DE SERVICIOS"),
    CERRAMIENTO("CERRAMIENTO");

    private final String stsCodigo;

    private EnumGrupoServicios(String stsCodigo) {
        this.stsCodigo = stsCodigo;
    }

    public String getStsCodigo() {
        return stsCodigo;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static EnumGrupoServicios obtenerGrupoServicio(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumGrupoServicios grupoServicio : EnumGrupoServicios.values()) {
                if (grupoServicio.toString().contentEquals(nombre.trim())) {
                    return grupoServicio;
                }
            }
            return EnumGrupoServicios.CERRAMIENTO;
        } else {
            return EnumGrupoServicios.CERRAMIENTO;
        }
    }
}
