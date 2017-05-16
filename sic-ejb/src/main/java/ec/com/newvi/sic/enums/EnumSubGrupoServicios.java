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
public enum EnumSubGrupoServicios {
    ALCANTARILLADO("ALCANTARILLADO"),
    VIAS("VÍAS"),
    OTROS("OTROS"),
    ABASTECIMIENTO_DE_AGUA("ABASTECIMIENTO DE AGUA"),
    INSTALACIONES_DEPORTIVAS("INSTALACIONES DEPORTIVAS"),
    ENERGIA_ELECTRICA("ENERGÍA ELÉCTRICA"),
    USO("USO"),
    MATERIAL("MATERIAL"),
    CERRAMIENTO("CERRAMIENTO");

    private final String stsSubgrupo;

    private EnumSubGrupoServicios(String stsSubgrupo) {
        this.stsSubgrupo = stsSubgrupo;
    }

    public String getStsSubgrupo() {
        return stsSubgrupo;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static EnumSubGrupoServicios obtenerSubGrupoServicio(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumSubGrupoServicios subGrupoServicio : EnumSubGrupoServicios.values()) {
                if (subGrupoServicio.toString().contentEquals(nombre.trim())) {
                    return subGrupoServicio;
                }
            }
            return EnumSubGrupoServicios.OTROS;
        } else {
            return EnumSubGrupoServicios.OTROS;
        }
    }
}
