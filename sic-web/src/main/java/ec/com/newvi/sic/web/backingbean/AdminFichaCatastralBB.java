/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.CaracteristicasEdificacionesDto;
import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.ModeloPredioLazy;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.CatastroServicio;
import ec.com.newvi.sic.servicios.ContribuyentesServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.enums.EnumParametrosReporte;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;

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

    protected List<FichaCatastralDto> listaFichas;
    protected List<FichaCatastralDto> listaFichasFiltradas;
    protected LazyDataModel<FichaCatastralDto> listaFichasLazy;

    protected List<Contribuyentes> listaContribuyentes;
    protected List<Contribuyentes> listaContribuyentesFiltrado;

    protected Predios predio;
    protected Contribuyentes contribuyente;

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }

    public Contribuyentes getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyentes contribuyente) {
        this.contribuyente = contribuyente;
    }

    public LazyDataModel<FichaCatastralDto> getListaFichasLazy() {
        return listaFichasLazy;
    }

    public void setListaFichasLazy(LazyDataModel<FichaCatastralDto> listaFichasLazy) {
        this.listaFichasLazy = listaFichasLazy;
    }

    public List<FichaCatastralDto> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaCatastralDto> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public List<FichaCatastralDto> getListaFichasFiltradas() {
        return listaFichasFiltradas;
    }

    public void setListaFichasFiltradas(List<FichaCatastralDto> listaFichasFiltradas) {
        this.listaFichasFiltradas = listaFichasFiltradas;
    }

    public List<Contribuyentes> getListaContribuyentes() {
        return listaContribuyentes;
    }

    public void setListaContribuyentes(List<Contribuyentes> listaContribuyentes) {
        this.listaContribuyentes = listaContribuyentes;
    }

    public List<Contribuyentes> getlistaContribuyentesFiltrado() {
        return listaContribuyentesFiltrado;
    }

    public void setlistaContribuyentesFiltrado(List<Contribuyentes> listaContribuyentesFiltrado) {
        this.listaContribuyentesFiltrado = listaContribuyentesFiltrado;
    }

    protected void actualizarListadoPredios() {
        List<Predios> listaPredios = catastroServicio.consultarPredios();
        //String sql = ComunUtil.generarScriptTenencia(listaPredios, parametrosServicio);
        listaFichas = new ArrayList<>();
        listaPredios.forEach((elementoPredio) -> {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        });
        listaFichasLazy = new ModeloPredioLazy(listaFichas);
    }

    protected void actualizarListadoContribuyentes() {
        listaContribuyentes = contribuyentesServicio.consultarContribuyentes();
    }

    protected void seleccionarPredioPorCodigo(Integer idPredio) throws NewviExcepcion {
        this.predio = catastroServicio.seleccionarPredio(idPredio);
    }

    protected void seleccionarContribuyentePorCodigo(Integer idContribuyente) throws NewviExcepcion {
        this.contribuyente = contribuyentesServicio.seleccionarContribuyente(idContribuyente);
    }

    public void seleccionarPredio(Integer idPredio) {
        try {
            this.seleccionarPredioPorCodigo(idPredio);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void seleccionarContribuyente(Integer idContribuyente) {
        try {
            this.seleccionarContribuyentePorCodigo(idContribuyente);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
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
            datosAvaluo.setAreaTerreno(avaluo.getValAreapredio());
            datosAvaluo.setAreaEdificacion(avaluo.getValAreaconstruccion());
            datosAvaluo.setAvaluoEdificacion(avaluo.getValEdifica());
            datosAvaluo.setAvaluoPredio(avaluo.getValPredio());
            datosAvaluo.setAreaPredio(avaluo.getValAreapredio());
            datosAvaluo.setImpuestoPredial(avaluo.getValImpuesto());
            datosAvaluo.setContribucionEspecialMejoras(avaluo.getValCem());
            datosAvaluo.setTasaNoEdificacion(avaluo.getValNoEdificacion());
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
    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteTitulos(Titulos titulo) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        tablita.add(new PresentacionFichaCatastralDto(titulo));
        return tablita;
    }

    protected DefaultStreamedContent generarReporteCatastro(EnumReporte tipoReporte, ReporteGenerador.FormatoReporte formatoReporte, List datosImpresion, Class claseImpresion) {
        try {

            CaracteristicasEdificacionesDto bloques;
            Map<String, Object> parametrosReporte = new HashMap<>();
            String xPath = "/lista".concat(claseImpresion.getSimpleName()).concat("//").concat(claseImpresion.getSimpleName());

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

                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put(EnumParametrosReporte.TITULO_REPORTE.getNombre(), "FICHA DE RELEVAMIENTO PREDIAL URBANO");

                parametrosReporte.put(EnumParametrosReporte.DESCRIPCION_TERRENO.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaDescripcionTerreno());
                parametrosReporte.put(EnumParametrosReporte.INFRAESTRUCTURA_SERVICIOS.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaServicios());
                parametrosReporte.put(EnumParametrosReporte.CARACTERISTICAS_EDIFICACION.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaBloques());
                parametrosReporte.put(EnumParametrosReporte.PISO.getNombre(), bloques.getListadetallesPisoDtoD());

                parametrosReporte.put(EnumParametrosReporte.IMAGEN_DELIMITACION_PREDIO.getNombre(), parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.DIRECCION_SERVICIO_IMAGEN_PREDIO, sesionBean.getSesion()).getValor().concat(geoCatastroServicio.obtenerBordesPredio(predio, BigDecimal.valueOf(95), BigDecimal.valueOf(533), sesionBean.getSesion())));
            }

            if (EnumReporte.NOTIFICACION_AVALUO.equals(tipoReporte)) {
                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put("TITULO_REPORTE", "NOTIFICACIÓN AVALÚO");
            }
            if (EnumReporte.CERTIFICACION_AVALUO.equals(tipoReporte)) {
                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put("TITULO_REPORTE", "CERTIFICACIÓN AVALÚO");
            }
            if (EnumReporte.TITULO_CREDITO.equals(tipoReporte)) {
                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put("TITULO_REPORTE", "TÍTULO CRÉDITO");
            }
            if (EnumReporte.TITULO_GENERADO.equals(tipoReporte)) {
                parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");
                parametrosReporte.put("TITULO_REPORTE", "TÍTULO GENERADO");
            }

            Map<String, Class> paramRepA = new HashMap<String, Class>();
            paramRepA.put(claseImpresion.getSimpleName(), claseImpresion);
            paramRepA.put("lista".concat(claseImpresion.getSimpleName()), List.class);

            return generarReporte(tipoReporte, datosImpresion, paramRepA, xPath, parametrosReporte, formatoReporte);

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
