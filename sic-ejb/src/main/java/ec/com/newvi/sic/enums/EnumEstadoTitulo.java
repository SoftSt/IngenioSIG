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
public enum EnumEstadoTitulo {
    
    TITULO_EMITIDO("Título Emitido"),
    TITULO_ANULADO("Título Anulado"),
    TITULO_COBRADO("Título Cobrado"),
    TITULO_INEXISTENTE("Título Inexistente"),;
    
    private final String estadoTitulo;

    private EnumEstadoTitulo(String estadoTitulo) {
        this.estadoTitulo = estadoTitulo;
    }

    
    public String getEstadoTitulo() {
        return estadoTitulo;
    }
    
    public static EnumEstadoTitulo obtenerEstadoTitulo(String estadoTituloActual) {
        if (!ComunUtil.esCadenaVacia(estadoTituloActual)) {
            for (EnumEstadoTitulo tipoTitulo : EnumEstadoTitulo.values()) {
                if (tipoTitulo.estadoTitulo.contentEquals(estadoTituloActual.trim())) {
                    return tipoTitulo;
                }
            }
            return EnumEstadoTitulo.TITULO_INEXISTENTE;
        } else {
            return EnumEstadoTitulo.TITULO_INEXISTENTE;
        }
    }
    
    
}
