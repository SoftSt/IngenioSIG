/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.utils;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import org.geotools.geometry.jts.JTSFactoryFinder;

/**
 *
 * @author israelavila
 */
public class UtilGeografico {
    
    public static Geometry obtenerGeometriaDeTexto(String wktGeometria) throws NewviExcepcion {
        try {
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
            WKTReader reader = new WKTReader(geometryFactory);
            return (Geometry) reader.read(wktGeometria);
        } catch (ParseException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, ex);
        }
    }
    
    public static Envelope obtenerMarcoParaImagen(Geometry geometria, BigDecimal altoImagen, BigDecimal anchoImagen) {
        Envelope borde = obtenerMarcoParaRecuadro(geometria);
        BigDecimal minX = BigDecimal.valueOf(borde.getMinX());
        BigDecimal minY = BigDecimal.valueOf(borde.getMinY());
        BigDecimal maxX = BigDecimal.valueOf(borde.getMaxX());
        BigDecimal maxY = BigDecimal.valueOf(borde.getMaxY());
        BigDecimal diferenciaPixeles;
        BigDecimal ajuste;
        if (anchoImagen.compareTo(altoImagen) > 0) {
            diferenciaPixeles = anchoImagen.subtract(altoImagen);
            ajuste = valorMapa(diferenciaPixeles, BigDecimal.ZERO, anchoImagen, BigDecimal.ZERO, maxX.subtract(minX));
            minX = minX.subtract(ajuste);
            maxX = maxX.add(ajuste);
        } else {
            diferenciaPixeles = altoImagen.subtract(anchoImagen);
            ajuste = valorMapa(diferenciaPixeles, BigDecimal.ZERO, altoImagen, BigDecimal.ZERO, maxY.subtract(minY));
            minY = minY.subtract(ajuste);
            maxY = maxY.add(ajuste);
        }
        return new Envelope(minX.doubleValue(), maxX.doubleValue(), minY.doubleValue(), maxY.doubleValue());
    }
    
    private static BigDecimal valorMapa(BigDecimal valor, 
            BigDecimal valorOrigenMin, BigDecimal valorOrigenMax, 
            BigDecimal valorDestinoMin, BigDecimal valorDestinoMax) {
        BigDecimal diferenciaOrigen = valorOrigenMax.subtract(valorOrigenMin);
        BigDecimal diferenciaDestino = valorDestinoMax.subtract(valorDestinoMin);
        BigDecimal diferenciaValor = valor.subtract(valorOrigenMin);
        BigDecimal valorReal = valorDestinoMin.add(diferenciaDestino.multiply(diferenciaValor.divide(diferenciaOrigen, 18, BigDecimal.ROUND_HALF_EVEN)));
        return valorReal;
    }
    
    private static Envelope obtenerMarcoParaRecuadro(Geometry geometria) {
        Envelope borde = geometria.getEnvelopeInternal();
        BigDecimal minX = BigDecimal.valueOf(borde.getMinX());
        BigDecimal minY = BigDecimal.valueOf(borde.getMinY());
        BigDecimal maxX = BigDecimal.valueOf(borde.getMaxX());
        BigDecimal maxY = BigDecimal.valueOf(borde.getMaxY());
        BigDecimal deltaX = maxX.subtract(minX);
        BigDecimal deltaY = maxY.subtract(minY);
        BigDecimal ajuste;
        if (deltaX.compareTo(deltaY) > 0) {
            ajuste = deltaX.subtract(deltaY);
            minY = minY.subtract(ajuste.divide(BigDecimal.valueOf(2.0)));
            maxY = maxY.add(ajuste.divide(BigDecimal.valueOf(2.0)));
        } else {
            ajuste = deltaY.subtract(deltaX);
            minX = minX.subtract(ajuste.divide(BigDecimal.valueOf(2.0)));
            maxX = maxX.add(ajuste.divide(BigDecimal.valueOf(2.0)));
        }
        return new Envelope(minX.doubleValue(), maxX.doubleValue(), minY.doubleValue(), maxY.doubleValue());
    }
}
