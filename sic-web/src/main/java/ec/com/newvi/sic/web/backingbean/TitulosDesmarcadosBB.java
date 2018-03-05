/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class TitulosDesmarcadosBB extends AdminFichaCatastralBB {

    @EJB
    private RentasServicio rentasServicio;

    private EnumPantallaMantenimiento pantallaActual;

    private Boolean hayFechaEmision;
    private Boolean hayFechaRecaudacion;

    private List<Titulos> listaTitulosRegistrados;
    private List<Titulos> listaTitulosRegistradosFiltrados;

    private BigDecimal totalCobrardoTitulos;

    private Date fechaMinimaEmision;
    private Date fechaMaximaEmision;
    private Date fechaMinimaRecaudacion;
    private Date fechaMaximaRecaudacion;

    public Date getFechaMinimaEmision() {
        return fechaMinimaEmision;
    }

    public void setFechaMinimaEmision(Date fechaMinimaEmision) {
        this.fechaMinimaEmision = fechaMinimaEmision;
    }

    public Date getFechaMaximaEmision() {
        return fechaMaximaEmision;
    }

    public void setFechaMaximaEmision(Date fechaMaximaEmision) {
        this.fechaMaximaEmision = fechaMaximaEmision;
    }

    public Date getFechaMinimaRecaudacion() {
        return fechaMinimaRecaudacion;
    }

    public void setFechaMinimaRecaudacion(Date fechaMinimaRecaudacion) {
        this.fechaMinimaRecaudacion = fechaMinimaRecaudacion;
    }

    public Date getFechaMaximaRecaudacion() {
        return fechaMaximaRecaudacion;
    }

    public void setFechaMaximaRecaudacion(Date fechaMaximaRecaudacion) {
        this.fechaMaximaRecaudacion = fechaMaximaRecaudacion;
    }

    public BigDecimal getTotalCobrardoTitulos() {
        return totalCobrardoTitulos;
    }

    public void setTotalCobrardoTitulos(BigDecimal totalCobrardoTitulos) {
        this.totalCobrardoTitulos = totalCobrardoTitulos;
    }

    public List<Titulos> getListaTitulosRegistrados() {
        return listaTitulosRegistrados;
    }

    public void setListaTitulosRegistrados(List<Titulos> listaTitulosRegistrados) {
        this.listaTitulosRegistrados = listaTitulosRegistrados;
    }

    public List<Titulos> getListaTitulosRegistradosFiltrados() {
        return listaTitulosRegistradosFiltrados;
    }

    public void setListaTitulosRegistradosFiltrados(List<Titulos> listaTitulosRegistradosFiltrados) {
        this.listaTitulosRegistradosFiltrados = listaTitulosRegistradosFiltrados;
    }

    public Boolean getHayFechaEmision() {
        return hayFechaEmision;
    }

    public void setHayFechaEmision(Boolean hayFechaEmision) {
        this.hayFechaEmision = hayFechaEmision;
    }

    public Boolean getHayFechaRecaudacion() {
        return hayFechaRecaudacion;
    }

    public void setHayFechaRecaudacion(Boolean hayFechaRecaudacion) {
        this.hayFechaRecaudacion = hayFechaRecaudacion;
    }

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONSULTA_TITULOS_DESMARCADOS_LISTA_TITULO,
                EnumEtiquetas.CONSULTA_TITULOS_DESMARCADOS_LISTA_ICONO,
                EnumEtiquetas.CONSULTA_TITULOS_DESMARCADOS_LISTA_DESCRIPCION);

        this.hayFechaRecaudacion = Boolean.TRUE;
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

    private BigDecimal obtenerTotalesTitulos(List<Titulos> listadoTitulos) {
        BigDecimal totalPorCobrar = BigDecimal.ZERO;
        for (Titulos titulo : listadoTitulos) {
            totalPorCobrar = totalPorCobrar.add(titulo.getValTotalapagar());
        }
        return totalPorCobrar;
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
        return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerDatosReporteListaTitulos(this.listaTitulosRegistrados), PresentacionFichaCatastralDto.class);
    }

    public void buscarTituloPorTipo() {
        this.listaTitulosRegistrados = aplicarFiltros(rentasServicio.consultarTitulos());
        this.totalCobrardoTitulos = obtenerTotalesTitulos(this.listaTitulosRegistrados);
    }

    public List<Titulos> aplicarFiltros(List<Titulos> listaTitulos) {
        listaTitulos = filtrarPorFechaRecaudacion(listaTitulos);
        return listaTitulos;
    }

    public Boolean sonFechasValidas(Date fechaMinima, Date fechaMaxima) {
        if (!ComunUtil.esNulo(fechaMinima) && !ComunUtil.esNulo(fechaMaxima)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Titulos> filtrarPorFechaRecaudacion(List<Titulos> titulosAFiltrar) {
        if (sonFechasValidas(this.fechaMinimaRecaudacion, this.fechaMaximaRecaudacion)) {
            return obtenerTitulosPorFechaRecaudacion(titulosAFiltrar);
        } else {
            return titulosAFiltrar;
        }
    }

    public Boolean estaDentroRangoFecha(Date fechaRegistradaActual, Date fechaMinima, Date fechaMaxima) {
        if (!ComunUtil.esNulo(fechaRegistradaActual)
                && fechaRegistradaActual.after(fechaMinima)
                && fechaRegistradaActual.before(fechaMaxima)
                || (fechaRegistradaActual.equals(fechaMinima) && fechaRegistradaActual.equals(fechaMaxima))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Titulos> obtenerTitulosPorFechaRecaudacion(List<Titulos> titulosAFiltrar) {
        List<Titulos> titulosFiltrados = new ArrayList<>();
        for (Titulos titulo : titulosAFiltrar) {
            Date fechaRegistrada = titulo.getFecFpago();
            if (estaDentroRangoFecha(quitarHoraAFecha(fechaRegistrada), quitarHoraAFecha(this.fechaMinimaRecaudacion), quitarHoraAFecha(this.fechaMaximaRecaudacion)) && titulo.getStsEstado().equals(EnumEstadoTitulo.TITULO_DESMARCADO)) {
                titulosFiltrados.add(titulo);
            }
        }
        return titulosFiltrados;
    }

    private Date quitarHoraAFecha(Date fecha) {

        Calendar calendar = Calendar.getInstance();
        if (!ComunUtil.esNulo(fecha)) {
            calendar.setTime(fecha);
        }

        return new Date(calendar.get(Calendar.YEAR), calendar.get(calendar.get(Calendar.MONTH)), calendar.get(Calendar.DAY_OF_YEAR));

    }

}
