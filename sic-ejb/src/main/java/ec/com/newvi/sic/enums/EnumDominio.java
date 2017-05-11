/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author NEWVI
 */
public enum EnumDominio {
    
    Particular("Particular"),
    Otros("Otros"),
    ND("N/D"),
    Cooperativa("Cooperativa"),
    Comunal("Comunal"),
    Asociacion("Asociacion"),
    Compañia("Compañia"),
    Municipal("Municipal"),
    Provincial("Provincial"),
    Estatal("Estatal"),
    Iglesia("Iglesia");
    
    private final String stsTenencia;

    public String getStsTenencia() {
        return stsTenencia;
    }

    private EnumDominio(String stsTenencia) {
        this.stsTenencia = stsTenencia;
    }

}