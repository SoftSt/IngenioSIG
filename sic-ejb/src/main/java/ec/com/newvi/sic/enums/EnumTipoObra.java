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
public enum EnumTipoObra {
    
    APR(" Adoquinamiento, Pavimentación o Repavimentación Urbana"),
    Cercas("Cercas"),
    AB("Aceras y Bordillos"),
    Alcantarillado("Alcantarillado"),
    OSAP("Obras y Sistemas de Agua Potable"),
    PPJ("Parques, Plazas y Jardine");
    
    private final String nomObras;
    
    public String getNomObras() {
        return nomObras;
    }
    
    private EnumTipoObra(String nomObras) {
        this.nomObras = nomObras;
    }

    @Override
    public String toString() {
        return this.nomObras;
    }
    
    

    

    

    
}
