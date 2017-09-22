/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.CaracteristicasEdificacionesDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.servicios.CatastroServicio;
import ec.com.newvi.sic.servicios.ContribuyentesServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumParametrosReporte;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Andrés
 */
public abstract class AdminFichaCatastralBB extends AdminSistemaBB {

    @EJB
    protected CatastroServicio catastroServicio;
    @EJB
    protected ContribuyentesServicio contribuyentesServicio;
    @EJB
    protected GeoCatastroServicio geoCatastroServicio;

    protected Predios predio;

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }

    public List<Avaluo> generarListaAvaluo() {
        return catastroServicio.consultarListaAvaluosActuales();
    }

    public List<TablaCatastralDto> obtenerListadoAvaluos(List<Avaluo> listaAvaluos) {
        List<TablaCatastralDto> datosImpresion = new ArrayList<>();
        TablaCatastralDto datosAvaluo;
        for (Avaluo avaluo : listaAvaluos) {
            datosAvaluo = new TablaCatastralDto();
            datosAvaluo.setCodigoCatastral(avaluo.getCodCatastral().getCodCatastral().toString());
            datosAvaluo.setNombreCodigoCatastral(avaluo.getNomCodigocatastral());
            datosAvaluo.setPropietario(avaluo.getNomnomape());
            datosAvaluo.setCiRuc(avaluo.getCodCedularuc());
            datosAvaluo.setBarrio(avaluo.getStsBarrio());
            datosAvaluo.setDireccion(avaluo.getTxtDireccion());
            datosAvaluo.setAvaluoTerreno(avaluo.getValTerreno());
            datosAvaluo.setAreaTerreno(avaluo.getValTerreno());
            datosAvaluo.setAreaEdificacion(avaluo.getValAreaconstruccion());
            datosAvaluo.setAvaluoEdificacion(avaluo.getValEdifica());
            datosAvaluo.setAvaluoPredio(avaluo.getValPredio());
            datosAvaluo.setAreaPredio(avaluo.getValAreapredio());
            datosAvaluo.setImpuestoPredial(avaluo.getValImpuesto());
            datosAvaluo.setContribucionEspecialMejoras(avaluo.getValCem());
            datosAvaluo.setTasaRecoleccionBasura(avaluo.getValBasura());
            datosAvaluo.setCostoEmision(avaluo.getValEmision());
            datosAvaluo.setTasaBomberos(avaluo.getValBomberos());
            datosAvaluo.setServiciosAmbientales(avaluo.getValAmbientales());
            datosAvaluo.setTotalAPagar(avaluo.getValImppredial());
            datosAvaluo.setObservaciones(avaluo.getCatCasosespeciales());
            datosImpresion.add(datosAvaluo);
        }
        return datosImpresion;
    }

    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteCatastral(Predios predio) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        tablita.add(new PresentacionFichaCatastralDto(predio));
        return tablita;
    }

    protected DefaultStreamedContent generarReporteCatastro(EnumReporte tipoReporte) {
        try {
            List datosImpresion;
            datosImpresion = obtenerListadoAvaluos(generarListaAvaluo());
            
            Class claseImpresion = TablaCatastralDto.class;
            //BloqueDto bloques;
            CaracteristicasEdificacionesDto bloques;
            Map<String, Object> parametrosReporte = new HashMap<>();
            //datosImpresion = obtenerListadoAvaluos(catastroServicio.consultarAvaluos(fecavFechaavaluo));
            String xPath = "/reporTablaCatastral//tablaCatastral";

            if (EnumReporte.TABLA_CATASTRAL_URBANA.equals(tipoReporte)) {
                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put("TITULO_REPORTE", "TABLA CATASTRAL URBANA");
            }
            if (EnumReporte.TABLA_CATASTRAL_URBANA_CONDENSADA.equals(tipoReporte)) {
                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put("TITULO_REPORTE", "TABLA CATASTRAL URBANA CONDENSADA");
            }
            if (EnumReporte.FICHA_RELEVAMIENTO_PREDIAL_URBANO.equals(tipoReporte)) {
                bloques = new CaracteristicasEdificacionesDto(this.predio);
                datosImpresion = obtenerDatosReporteCatastral(this.predio);
                claseImpresion = PresentacionFichaCatastralDto.class;

                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put(EnumParametrosReporte.TITULO_REPORTE.getNombre(), "FICHA DE RELEVAMIENTO PREDIAL URBANO");
                
                parametrosReporte.put(EnumParametrosReporte.DESCRIPCION_TERRENO.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaDescripcionTerreno());
                parametrosReporte.put(EnumParametrosReporte.INFRAESTRUCTURA_SERVICIOS.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaServicios());
                parametrosReporte.put(EnumParametrosReporte.CARACTERISTICAS_EDIFICACION.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaBloques());
                parametrosReporte.put(EnumParametrosReporte.PISO.getNombre(), bloques.getListadetallesPisoDtoD());
                
                parametrosReporte.put(EnumParametrosReporte.IMAGEN_DELIMITACION_PREDIO.getNombre(), parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.DIRECCION_SERVICIO_IMAGEN_PREDIO, sesionBean.getSesion()).getValor().concat(geoCatastroServicio.obtenerBordesPredio(predio, BigDecimal.valueOf(95), BigDecimal.valueOf(533), sesionBean.getSesion())));
            }

            if (EnumReporte.NOTIFICACION_AVALUO.equals(tipoReporte)) {
                datosImpresion = obtenerDatosReporteCatastral(this.predio);
                claseImpresion = PresentacionFichaCatastralDto.class;
            }
            if (EnumReporte.CERTIFICACION_AVALUO.equals(tipoReporte)) {
                datosImpresion = obtenerDatosReporteCatastral(this.predio);
                claseImpresion = PresentacionFichaCatastralDto.class;
            }
            if (EnumReporte.TITULO_CREDITO.equals(tipoReporte)) {
                datosImpresion = obtenerDatosReporteCatastral(this.predio);
                claseImpresion = PresentacionFichaCatastralDto.class;
            }

            Map<String, Class> paramRepA = new HashMap<String, Class>();
            paramRepA.put("tablaCatastral", claseImpresion);
            paramRepA.put("reporTablaCatastral", List.class);

            return generarReporte(tipoReporte, datosImpresion, paramRepA, xPath, parametrosReporte, ReporteGenerador.FormatoReporte.PDF);

        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            return null;
        }
        return null;
    }

}
