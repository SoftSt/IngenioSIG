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
public enum EnumZonaInfluencia {
    
    A("A"),
    B("B"),
    C("C"),
    D("D");
    
    private final String codZonaInfluencia;

    public String getCodZonaInfluencia() {
        return codZonaInfluencia;
    }

    private EnumZonaInfluencia(String codZonaInfluencia) {
        this.codZonaInfluencia = codZonaInfluencia;
    }
    
}
