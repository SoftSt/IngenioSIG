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
public enum EnumSN {

    S('S'),
    N('N');
    private final Character stsEstatus;

    public Character getStsEstatus() {
        return stsEstatus;
    }

    private EnumSN(Character stsEstatus) {
        this.stsEstatus = stsEstatus;
    }

}
