/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.util;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;

/**
 *
 * @author israelavila
 */
public class ComunUtil {

    /**
     * Comprueba si un objeto es nulo
     *
     * @param objeto
     * @return
     */
    public static Boolean esNulo(Object objeto) {
        return objeto == null;
    }

    /**
     * Comprueba si una cadena está vacía
     *
     * @param cadena
     * @return
     */
    public static Boolean esCadenaVacia(String cadena) {
        return esNulo(cadena) || cadena.isEmpty();
    }

    public static Boolean esNumeroPositivo(Integer numero) {
        return !esNulo(numero) && (numero.compareTo(0) >= 0);
    }

    public static BigDecimal obtenerNumeroDecimalDesdeTexto(String texto) throws NewviExcepcion {
        if (!ComunUtil.esNulo(texto) && !ComunUtil.esCadenaVacia(texto)) {
            return new BigDecimal(texto);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR012);
        }
    }
}
