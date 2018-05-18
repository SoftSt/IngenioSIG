/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.servicios.impl;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.geo.dao.GeoPredioFacade;
import ec.com.newvi.sic.geo.modelo.GeoPredio;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.geo.utils.UtilGeografico;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String bordeTexto = (BigDecimal.valueOf(borde.getMinX()).setScale(12, BigDecimal.ROUND_HALF_EVEN)).toString().concat(",")
                .concat((BigDecimal.valueOf(borde.getMinY()).setScale(12, BigDecimal.ROUND_HALF_EVEN)).toString()).concat(",")
                .concat((BigDecimal.valueOf(borde.getMaxX()).setScale(12, BigDecimal.ROUND_HALF_EVEN)).toString()).concat(",")
                .concat((BigDecimal.valueOf(borde.getMaxY()).setScale(12, BigDecimal.ROUND_HALF_EVEN)).toString());
        return bordeTexto;
    }

    private Geometry obtenerGeometriaPredio(Predios predio, SesionDto sesion) throws NewviExcepcion {
        String codigoLote = predio.getNomCodigocatastral().trim();
        codigoLote = codigoLote.substring(0, 10).concat(codigoLote.substring(11, 19));

        String wktPredio = geoPredioFacade.obtenerBordePredio(codigoLote, sesion);
        return UtilGeografico.obtenerGeometriaDeTexto(wktPredio);
    }

    @Override
    public List<GeoPredio> obtenerListadoGeoPrediosHuerfanos(List<Predios> prediosRegistrados, SesionDto sesion) throws NewviExcepcion {
        return geoPredioFacade.obtenerListadoPrediosHuerfanos(obtenerCodigosPredios(prediosRegistrados), sesion);
    }

    private List<String> obtenerCodigosPredios(List<Predios> prediosRegistrados) {
        List<String> listaCodigosPredios = new ArrayList<>();
        prediosRegistrados.forEach((predioRegistrado) -> {
            String codigoLote = predioRegistrado.getNomCodigocatastral().trim();
            codigoLote = codigoLote.substring(0, 10).concat(codigoLote.substring(11, 19));
            listaCodigosPredios.add(codigoLote);
        });
        return listaCodigosPredios;
    }

    private Predios obtenerPredioDesdeGeoPredio(GeoPredio geoPredio, SesionDto sesion) throws NewviExcepcion {
        Predios nuevoPredio = new Predios();
        //nuevoPredio.setCodCatastral(geoPredio.getId());
        //nuevoPredio.setCodDpa(geoPredio.getCodigoDPA());
        nuevoPredio.setCodZona(geoPredio.getCodigoZona());
        nuevoPredio.setCodSector(geoPredio.getCodigoSector());
        nuevoPredio.setCodManzana(geoPredio.getCodigoManzana());
        //nuevoPredio.setCodPredio(geoPredio.getNumeroLote());
        nuevoPredio.setNomCodigocatastral(geoPredio.getCodigoPredio());
        //nuevoPredio.setValAreaPredio(!ComunUtil.esNulo(geoPredio.getAreaPredio())? new BigDecimal(geoPredio.getAreaPredio()): null);
        //nuevoPredio.setValAreaPredio(geoPredioFacade.obtenerAreaPredioDesdeGeometria(geoPredio.getCodigoCampoPredio(), sesion));
        //nuevoPredio.setCodCampo(geoPredio.getCodigoCampoPredio());
        return nuevoPredio;
    }

    @Override
    public List<Predios> obtenerListaPrediosDesdeGeoPredio(List<GeoPredio> geoPredios, SesionDto sesion) {
        List<Predios> listaNuevosPredios = new ArrayList<>();
        Date fechaCreacion = ComunUtil.hoy();

        try {
            for (GeoPredio geoPredio : geoPredios) {
                Predios nuevoPredio;
                nuevoPredio = obtenerPredioDesdeGeoPredio(geoPredio, sesion);
                if (!ComunUtil.esNulo(nuevoPredio)) {
                    //Registramos la auditoria de ingreso
                    nuevoPredio.setAudIngIp(sesion.getDireccionIP());
                    nuevoPredio.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
                    nuevoPredio.setAudIngFec(fechaCreacion);
                    listaNuevosPredios.add(nuevoPredio);
                }
            }
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesion);
        }

        return listaNuevosPredios;
    }

}
