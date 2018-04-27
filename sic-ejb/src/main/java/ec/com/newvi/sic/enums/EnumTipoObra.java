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
public enum EnumTipoObra {

    /*APR("Adoquinamiento, Pavimentación o Repavimentación Urbana"),
    Cercas("Cercas"),
    AB("Aceras y Bordillos"),
    Alcantarillado("Alcantarillado"),
    OSAP("Obras y Sistemas de Agua Potable"),
    PPJ("Parques, Plazas y Jardine");*/
    ADOQUINAMIENTO("Adoquinamiento"),
    PAVIMENTACION_REPAVIMENTACION("Pavimentación o Repavimentación"),
    REMODELACION_OBRAS("Remodelación de obras"),
    CONSTRUCCION_OBRAS("Construcción de obras"),
    ALCANTARILLADO("Alcantarillado"),
    ACERAS_BORDILLOS("Aceras y bordillos"),
    CERCAS("Cercas"),
    OBRA_SIN_CLASIFICACION("Obra sin clasificación"),
    SISTEMAS_AGUA_POTABLE("Sistemas de Agua Potable"),;

    private final String nomObras;

    public String getNomObras() {
        return nomObras.trim();
    }

    private EnumTipoObra(String nomObras) {
        this.nomObras = nomObras;
    }

    @Override
    public String toString() {
        return this.nomObras;
    }

    public static EnumTipoObra obtenerTipoObras(String nomObraCorto) {

        if (!ComunUtil.esNulo(nomObraCorto) && !ComunUtil.esCadenaVacia(nomObraCorto)) {
            for (EnumTipoObra enumTipoObra : EnumTipoObra.values()) {
                if (enumTipoObra.getNomObras().contains(nomObraCorto.trim())) {
                    return enumTipoObra;
                }
            }
        }

        return EnumTipoObra.OBRA_SIN_CLASIFICACION;
    }

}
