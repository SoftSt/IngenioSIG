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
import ec.com.newvi.sic.geo.modelo.GeoPredio;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.LogPredio;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    protected List<FichaCatastralDto> listaFichas;
    protected List<FichaCatastralDto> listaFichasFiltradas;
    protected LazyDataModel<FichaCatastralDto> listaFichasLazy;

    protected List<Contribuyentes> listaContribuyentes;

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

    /*public List<Contribuyentes> getlistaContribuyentesFiltrado() {
        return listaContribuyentesFiltrado;
    }

    public void setlistaContribuyentesFiltrado(List<Contribuyentes> listaContribuyentesFiltrado) {
        this.listaContribuyentesFiltrado = listaContribuyentesFiltrado;
    }*/
    protected void actualizarListadoPredios() {
        FichaCatastralDto nuevaFicha;
        List<Predios> listaPredios = catastroServicio.consultarPredios();
        //String sql = ComunUtil.generarScriptTenencia(listaPredios, parametrosServicio);
        listaFichas = new ArrayList<>();

        /*for (Predios predioLista : listaPredios) {
            nuevaFicha = new FichaCatastralDto(predioLista);
            //nuevaFicha = new FichaCatastralDto(listaPredios.get(3826));
            String cedulaPredio = nuevaFicha.getContribuyentePropiedad().getCodCedularuc().trim();
            String codigoCatastral = nuevaFicha.getPredio().getNomCodigocatastral().trim();
            //&&
            //if (ComunUtil.esCedulaValida(cedulaPredio) && !codigoCatastral.isEmpty()) {
            if (!ComunUtil.esCedulaValida(cedulaPredio)) {
                //if (cedulaPredio.length() > 10&& !cedulaPredio.equals("1760009530001")) {
                listaFichas.add(nuevaFicha);
            }
        }*/

        listaPredios.forEach((elementoPredio) -> {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        });
        listaFichasLazy = new ModeloPredioLazy(listaFichas);
        try {
            List<GeoPredio> nuevoPre = geoCatastroServicio.obtenerListadoGeoPrediosHuerfanos(listaPredios, sesionBean.getSesion());
            nuevoPre.size();
        } catch (NewviExcepcion ex) {
            Logger.getLogger(AdminFichaCatastralBB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            datosAvaluo.setExoneraciones(avaluo.getValDescuentosExoneraciones());
            datosAvaluo.setContribucionEspecialMejoras(avaluo.getValCem());
            datosAvaluo.setTasaNoEdificacion(avaluo.getValNoEdificacion());
            datosAvaluo.setTasaConstruccionObsoleta(avaluo.getValConstruccionObsoleta());
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

    protected List<PresentacionFichaCatastralDto> obtenerListaFichas(List<FichaCatastralDto> fichas) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        for (FichaCatastralDto ficha : fichas) {
            tablita.add(new PresentacionFichaCatastralDto(ficha));
        }
        return tablita;
    }

    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteTitulos(Titulos titulo) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        tablita.add(new PresentacionFichaCatastralDto(titulo));
        return tablita;
    }

    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteListaTitulos(List<Titulos> listaTitulos) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        for (Titulos titulo : listaTitulos) {
            tablita.add(new PresentacionFichaCatastralDto(titulo));
        }
        return tablita;
    }

    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteListaTitulosDesmarcados(List<Titulos> listaTitulos) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        for (Titulos titulo : listaTitulos) {
            tablita.add(new PresentacionFichaCatastralDto(titulo, Boolean.TRUE));
        }
        return tablita;
    }

    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteListaLogPredios(List<LogPredio> listaLogPredios) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        for (LogPredio logPredio : listaLogPredios) {
            tablita.add(new PresentacionFichaCatastralDto(logPredio));
        }
        return tablita;
    }

}
