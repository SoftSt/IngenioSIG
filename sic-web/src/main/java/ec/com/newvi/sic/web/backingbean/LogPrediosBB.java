/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.LogPredio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
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
public class LogPrediosBB extends AdminFichaCatastralBB {
    
    private EnumPantallaMantenimiento pantallaActual;
    
    private List<LogPredio> listaLogPredios;
    private List<LogPredio> listaLogPrediosFiltrados;

    public List<LogPredio> getListaLogPredios() {
        return listaLogPredios;
    }

    public void setListaLogPredios(List<LogPredio> listaLogPredios) {
        this.listaLogPredios = listaLogPredios;
    }

    public List<LogPredio> getListaLogPrediosFiltrados() {
        return listaLogPrediosFiltrados;
    }

    public void setListaLogPrediosFiltrados(List<LogPredio> listaLogPrediosFiltrados) {
        this.listaLogPrediosFiltrados = listaLogPrediosFiltrados;
    }
    
    
    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.LOG_PREDIOS_TITULO,
                EnumEtiquetas.LOG_PREDIOS_ICONO,
                EnumEtiquetas.LOG_PREDIOS_DESCRIPCION);
        this.listaLogPredios = catastroServicio.consultarLogPredio();
        
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
    
     private ReporteGenerador.FormatoReporte obtenerFormatoReporte(String tipoReporte) {
        if (tipoReporte.equals("PDF")) {
            return ReporteGenerador.FormatoReporte.PDF;
        } else if (tipoReporte.equals("XLSX")) {
            return ReporteGenerador.FormatoReporte.XLSX;
        } else {
            return ReporteGenerador.FormatoReporte.DOCX;
        }
    }
    
    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, String formatoReporte) throws NewviExcepcion {
        return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerDatosReporteListaLogPredios(this.listaLogPredios), PresentacionFichaCatastralDto.class);
    }
    
}
