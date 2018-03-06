/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.modelo.TituloMovimientos;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.ComunUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Andrés
 */
public abstract class AdminTitulos extends AdminSistemaBB {

    @EJB
    protected RentasServicio rentasServicio;

    protected List<Titulos> listaTitulosRegistrados;
    protected List<Titulos> listaTitulosRegistradosFiltrados;
    
    protected Date fechaMinimaRecaudacion;
    protected Date fechaMaximaRecaudacion;

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

    protected BigDecimal obtenerTotalesTitulos(List<Titulos> listadoTitulos) {
        BigDecimal totalPorCobrar = BigDecimal.ZERO;
        for (Titulos titulo : listadoTitulos) {
            totalPorCobrar = totalPorCobrar.add(titulo.getValTotalapagar());
        }
        return totalPorCobrar;
    }
    
    protected Boolean sonFechasValidas(Date fechaMinima, Date fechaMaxima) {
        if (!ComunUtil.esNulo(fechaMinima) && !ComunUtil.esNulo(fechaMaxima)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    
    protected Date quitarHoraAFecha(Date fecha) {

        Calendar calendar = Calendar.getInstance();
        if (!ComunUtil.esNulo(fecha)) {
            calendar.setTime(fecha);
        }

        return new Date(calendar.get(Calendar.YEAR), calendar.get(calendar.get(Calendar.MONTH)), calendar.get(Calendar.DAY_OF_YEAR));
    }
    
    protected Boolean estaDentroRangoFecha(Date fechaRegistradaActual, Date fechaMinima, Date fechaMaxima) {
        
        if (fechaMinima.compareTo(fechaRegistradaActual) * fechaRegistradaActual.compareTo(fechaMaxima) >= 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    
    public List<Titulos> aplicarFiltros(List<Titulos> listaTitulos, EnumEstadoTitulo estadoTitulo) {
        listaTitulos = filtrarPorFechaMovimientoYEstadoTitulo(listaTitulos, estadoTitulo);
        return listaTitulos;
    }
    
    public List<Titulos> filtrarPorFechaMovimientoYEstadoTitulo(List<Titulos> titulosAFiltrar, EnumEstadoTitulo estadoTitulo) {
        if (sonFechasValidas(this.fechaMinimaRecaudacion, this.fechaMaximaRecaudacion)) {
            return obtenerTitulosPorFechaMovimientoYEstadoTitulo(titulosAFiltrar, estadoTitulo);
        } else {
            return titulosAFiltrar;
        }
    }
    
    public List<Titulos> obtenerTitulosPorFechaMovimientoYEstadoTitulo(List<Titulos> titulosAFiltrar, EnumEstadoTitulo estadoTitulo) {
        List<Titulos> titulosFiltrados = new ArrayList<>();
        for (Titulos titulo : titulosAFiltrar) {
            Date fechaRegistrada = obtenerFechaUltimoMovimiento(titulo, estadoTitulo);
            if (estaDentroRangoFecha(quitarHoraAFecha(fechaRegistrada), quitarHoraAFecha(this.fechaMinimaRecaudacion), quitarHoraAFecha(this.fechaMaximaRecaudacion)) && titulo.getStsEstado().equals(estadoTitulo)) {
                titulosFiltrados.add(titulo);
            }
        }
        return titulosFiltrados;
    }
    
    private Date obtenerFechaUltimoMovimiento(Titulos titulo, EnumEstadoTitulo estadoTitulo) {
        Date fecMovRegistrada = null;
        for (TituloMovimientos movimiento : titulo.getListaMovimientosTitulo()) {
            if (movimiento.getEstadoTitulo().equals(estadoTitulo)) {
                fecMovRegistrada = movimiento.getFecMovimiento();
            }
        }
        return fecMovRegistrada;
    }
    
    protected ReporteGenerador.FormatoReporte obtenerFormatoReporte(String tipoReporte) {
        if (tipoReporte.equals("PDF")) {
            return ReporteGenerador.FormatoReporte.PDF;
        } else if (tipoReporte.equals("XLSX")) {
            return ReporteGenerador.FormatoReporte.XLSX;
        } else {
            return ReporteGenerador.FormatoReporte.DOCX;
        }
    }

}
