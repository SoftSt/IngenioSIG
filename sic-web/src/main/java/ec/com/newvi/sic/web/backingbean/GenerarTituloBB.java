/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class GenerarTituloBB extends AdminFichaCatastralBB {

    private EnumPantallaMantenimiento pantallaActual;
    
    private List<Avaluo> listaAvaluos;
    private List<Avaluo> listaAvaluosFiltrados;
    private List<FechaAvaluo> listaFechaAvaluos;
    private FechaAvaluo fechaAvaluoActual;
    private String fechaActualPrueba;
    private Boolean esActivo;

    

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

    public String getFechaActualPrueba() {
        return fechaActualPrueba;
    }

    public void setFechaActualPrueba(String fechaActualPrueba) {
        this.fechaActualPrueba = fechaActualPrueba;
    }

    public List<FechaAvaluo> getListaFechaAvaluos() {
        return listaFechaAvaluos;
    }

    public void setListaFechaAvaluos(List<FechaAvaluo> listaFechaAvaluos) {
        this.listaFechaAvaluos = listaFechaAvaluos;
    }

    public List<Avaluo> getListaAvaluos() {
        return listaAvaluos;
    }

    public void setListaAvaluos(List<Avaluo> listaAvaluos) {
        this.listaAvaluos = listaAvaluos;
    }

    public FechaAvaluo getFechaAvaluoActual() {
        return fechaAvaluoActual;
    }

    public void setFechaAvaluoActual(FechaAvaluo fechaAvaluoActual) {
        this.fechaAvaluoActual = fechaAvaluoActual;
    }

    public List<Avaluo> getListaAvaluosFiltrados() {
        return listaAvaluosFiltrados;
    }

    public void setListaAvaluosFiltrados(List<Avaluo> listaAvaluosFiltrados) {
        this.listaAvaluosFiltrados = listaAvaluosFiltrados;
    }

    @PostConstruct
    public void init() {
        this.esActivo = false;
        
        
        fechaAvaluoActual = new FechaAvaluo();
        listaAvaluos = new ArrayList<>();
        listaFechaAvaluos = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.GENERARION_TITULO_LISTA_TITULO,
                EnumEtiquetas.GENERARION_TITULO_LISTA_ICONO,
                EnumEtiquetas.GENERARION_TITULO_LISTA_DESCRIPCION);
        actualizarListadoFechaAvaluos();
        //actualizarListadoAvaluos();

    }

    public FechaAvaluo generarFechaAvaluo() throws NewviExcepcion {
        FechaAvaluo fechaAvaluo = new FechaAvaluo();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = Calendar.getInstance().getTime();

        fechaAvaluo.setFecavFechaavaluo(fecha);
        fechaAvaluo.setFecavEstado(EnumEstadoRegistro.A);
        fechaAvaluo.setFechaDescripcion(formato.format(fecha));

        return catastroServicio.generarNuevaFechaAvaluo(fechaAvaluo, sesionBean.getSesion());
    }

    public List<FichaCatastralDto> generarListaFichaCatastral() {
        List<Predios> listaPredios;
        List<FichaCatastralDto> listaFichas = new ArrayList<>();

        listaPredios = catastroServicio.consultarPredios();

        for (Predios elementoPredio : listaPredios) {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        }

        return listaFichas;
    }

    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }

    public Boolean esPantallaActual(String pantallaEsperada) {
        try {
            return this.pantallaActual.equals(EnumPantallaMantenimiento.obtenerPantallaPorNombre(pantallaEsperada));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }

    public void actualizarListadoFechaAvaluos() {
        listaFechaAvaluos = catastroServicio.consultarFechaAvaluos();
    }

    public void actualizarListadoAvaluos() {
        listaAvaluos = catastroServicio.consultarListaAvaluosActuales();
    }

    public void actualizarListaAvaluosPorFecha(String fechaDescripcion) {
        //DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //listaAvaluos = catastroServicio.consultarListaAvaluosPorFecha(formato.parse(fechaDescripcion));
            listaAvaluos = catastroServicio.consultarListaAvaluosPorFecha(fechaDescripcion);
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR001.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte) {
        return generarReporteCatastro(tipoReporte);
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

    public List<Avaluo> generarListaAvaluo() {
        return catastroServicio.consultarListaAvaluosActuales();
    }
    
    public void generarTitulo(){
    }

}
