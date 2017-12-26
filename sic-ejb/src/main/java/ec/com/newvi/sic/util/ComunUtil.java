/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.util;

import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static BigDecimal obtenerNumeroDecimalDesdeTexto(String texto, String formatoMonedaSistema) throws NewviExcepcion {
        if (!ComunUtil.esNulo(texto) && !ComunUtil.esCadenaVacia(texto)) {
            if (texto.contains("$")) {
                return generarValorFormatoMoneda(texto, formatoMonedaSistema);
            } else {
                return new BigDecimal(texto);
            }
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

    public static BigDecimal generarValorFormatoMoneda(String texto, String formato) throws NewviExcepcion {
        DecimalFormat formatoMoneda = new DecimalFormat(formato);
        formatoMoneda.setParseBigDecimal(true);
        try {
            return new BigDecimal(formatoMoneda.parse(texto).toString());
        } catch (ParseException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR013);
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

    public static List<DominioDto> obtenerSubNodosHijos(ParametrosServicio parametrosServicio) {
        return parametrosServicio.listarDominiosDto("TENENCIA", "Hijo");
    }

    public static String obtenerCodigoTenencia(List<DominioDto> lista, String descripcion, String codigo) {
        for (DominioDto dominioDto : lista) {
            if ((dominioDto.getDominio().getDomiPadre()).trim().equals(codigo.trim()) && (dominioDto.getDescripcion()).trim().equals(descripcion.trim())) {
                return dominioDto.getDominio().getDomiCodigo().trim();
            }
        }
        return null;
    }

    public static String obtenerDescuento(Contribuyentes contribuyente) {
        if (!esCadenaVacia(contribuyente.getStsEspeciales()) && ("Predios Municipales").equals((contribuyente.getStsEspeciales().trim()))) {
            return contribuyente.getStsEspeciales().trim();
        }
        return "Ninguno";
    }

    public static String generarScriptTenencia(List<Predios> listaPredios, ParametrosServicio parametrosServicio) {
        List<DominioDto> dominios = obtenerSubNodosHijos(parametrosServicio);
        String sql = "";
        Integer cont = 0;
        for (Predios predioNuevo : listaPredios) {
            FichaCatastralDto a = new FichaCatastralDto(predioNuevo);
            Propiedad propiedad = a.getPropiedad();
            String transDomi = propiedad.getStsTransferenciadominio().getStsTransferenciadominio();
            String sitAct = propiedad.getStsSituacion().getStsSituacion();
            String tenencia = propiedad.getStsTenencia().getStsTenencia();
            String escritura = propiedad.getStsEscritura().getStsEscritura();
            String descuento = obtenerDescuento(a.getContribuyentePropiedad());
            String urbanoMarginal = "NO";
            sql += "\ninsert into cat_ciu_tenencia VALUES (" + (++cont) + "," + propiedad.getCodPropiedad() + ",'" + obtenerCodigoTenencia(dominios, transDomi, "1201") + "','TENENCIA','TRANSFERENCIA DOMINIO','" + transDomi + "', 'A', NULL, NULL, NULL, NULL, NULL, NULL);"
                    + "\ninsert into cat_ciu_tenencia VALUES (" + (++cont) + "," + propiedad.getCodPropiedad() + ",'" + obtenerCodigoTenencia(dominios, sitAct, "1202") + "','TENENCIA','SITUACION ACTUAL','" + sitAct + "', 'A', NULL, NULL, NULL, NULL, NULL, NULL);"
                    + "\ninsert into cat_ciu_tenencia VALUES (" + (++cont) + "," + propiedad.getCodPropiedad() + ",'" + obtenerCodigoTenencia(dominios, tenencia, "1203") + "','TENENCIA','TENENCIA DOMINIO','" + tenencia + "', 'A', NULL, NULL, NULL, NULL, NULL, NULL);"
                    + "\ninsert into cat_ciu_tenencia VALUES (" + (++cont) + "," + propiedad.getCodPropiedad() + ",'" + obtenerCodigoTenencia(dominios, escritura, "1204") + "','TENENCIA','ESCRITURA','" + escritura + "', 'A', NULL, NULL, NULL, NULL, NULL, NULL);"
                    + "\ninsert into cat_ciu_tenencia VALUES (" + (++cont) + "," + propiedad.getCodPropiedad() + ",'" + obtenerCodigoTenencia(dominios, descuento, "1205") + "','TENENCIA','Descuentos Especiales','" + descuento + "', 'A', NULL, NULL, NULL, NULL, NULL, NULL);"
                    + "\ninsert into cat_ciu_tenencia VALUES (" + (++cont) + "," + propiedad.getCodPropiedad() + ",'" + obtenerCodigoTenencia(dominios, urbanoMarginal, "1206") + "','TENENCIA','URBANO MARGINAL','" + urbanoMarginal + "', 'A', NULL, NULL, NULL, NULL, NULL, NULL);";
        }

        return sql;
    }

    public static Date hoy() {
        Date fechaIngreso = Calendar.getInstance().getTime();
        return fechaIngreso;
    }
}
