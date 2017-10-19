/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.util;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String generarFormatoMoneda(BigDecimal valor, String formato) throws NewviExcepcion {
        DecimalFormat formatoMoneda = new DecimalFormat(formato);
        try {
            return formatoMoneda.format(valor.setScale(2, RoundingMode.HALF_UP));
        } catch (IllegalArgumentException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR208, ex);
        }
    }

    public static String reemplazarTokens(String text,
            Map<String, String> replacements) {
        Pattern pattern = Pattern.compile("\\[(.+?)\\]");
        Matcher matcher = pattern.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String replacement = replacements.get(matcher.group(1));
            if (replacement != null) {
                // matcher.appendReplacement(buffer, replacement);
                // see comment 
                matcher.appendReplacement(buffer, "");
                buffer.append(replacement);
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
