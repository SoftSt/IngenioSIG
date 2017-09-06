/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.servicios.impl;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.geo.dao.GeoPredioFacade;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.geo.utils.UtilGeografico;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author israelavila
 */
@Stateless
@PermitAll
public class GeoCatastroServicioImpl implements GeoCatastroServicio {

    @EJB
    private GeoPredioFacade geoPredioFacade;

    @Override
    public String obtenerBordesPredio(Predios predio, BigDecimal alto, BigDecimal ancho, SesionDto sesion) throws NewviExcepcion {
        Geometry poligono = obtenerGeometriaPredio(predio, sesion);
        Envelope borde = UtilGeografico.obtenerMarcoParaImagen(poligono, alto, ancho);
        String bordeTexto = (BigDecimal.valueOf(borde.getMinX()).setScale(6, BigDecimal.ROUND_HALF_EVEN)).toString().concat(",")
                .concat((BigDecimal.valueOf(borde.getMinY()).setScale(6, BigDecimal.ROUND_HALF_EVEN)).toString()).concat(",")
                .concat((BigDecimal.valueOf(borde.getMaxX()).setScale(6, BigDecimal.ROUND_HALF_EVEN)).toString()).concat(",")
                .concat((BigDecimal.valueOf(borde.getMaxY()).setScale(6, BigDecimal.ROUND_HALF_EVEN)).toString());
        return bordeTexto;
    }

    private Geometry obtenerGeometriaPredio(Predios predio, SesionDto sesion) throws NewviExcepcion {
        String wktPredio = geoPredioFacade.obtenerBordePredio(predio.getNomCodigocatastral(), sesion);
        return UtilGeografico.obtenerGeometriaDeTexto(wktPredio);
    }

}
