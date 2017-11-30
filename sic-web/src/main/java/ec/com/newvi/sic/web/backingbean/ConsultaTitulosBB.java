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
import ec.com.newvi.sic.web.utils.WebUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class ConsultaTitulosBB extends AdminFichaCatastralBB {

    @EJB
    private RentasServicio rentasServicio;
    private EnumPantallaMantenimiento pantallaActual;
    private String tipoTituloActual;

    private List<Titulos> listaTitulosRegistrados;
    private List<Titulos> listaTitulosRegistradosFiltrados;

    private Date fechaMinimaEmision;
    private Date fechaMaximaEmision;
    private Date fechaMinimaRecaudacion;
    private Date fechaMaximaRecaudacion;

    private Boolean hayTipos;
    private Boolean hayFechaEmision;
    private Boolean hayFechaRecaudacion;
    private Boolean hayMonto;
    private Boolean esPanelFiltros;

    private BigDecimal montoMinimo;
    private BigDecimal montoMaximo;

    private EnumEstadoTitulo[] listaEstadosTitulo;

    private BigDecimal totalCobrardoTitulos;

    public String getTipoTituloActual() {
        return tipoTituloActual;
    }

    public void setTipoTituloActual(String tipoTituloActual) {
        this.tipoTituloActual = tipoTituloActual;
    }

    public EnumEstadoTitulo[] getListaEstadosTitulo() {
        return listaEstadosTitulo;
    }

    public BigDecimal getTotalCobrardoTitulos() {
        return totalCobrardoTitulos;
    }

    public List<Titulos> getListaTitulosRegistrados() {
        return listaTitulosRegistrados;
    }

    public List<Titulos> getListaTitulosRegistradosFiltrados() {
        return listaTitulosRegistradosFiltrados;
    }

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

    public Boolean getEsPanelFiltros() {
        return esPanelFiltros;
    }

    public void setEsPanelFiltros(Boolean esPanelFiltros) {
        this.esPanelFiltros = esPanelFiltros;
    }
    
    public Boolean getHayTipos() {
        return hayTipos;
    }

    public void setHayTipos(Boolean hayTipos) {
        this.hayTipos = hayTipos;
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

    public Boolean getHayMonto() {
        return hayMonto;
    }

    public void setHayMonto(Boolean hayMonto) {
        this.hayMonto = hayMonto;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONSULTA_TITULO_CARACTERISITCAS_LISTA_TITULO,
                EnumEtiquetas.CONSULTA_TITULO_CARACTERISITCAS_LISTA_ICONO,
                EnumEtiquetas.CONSULTA_TITULO_CARACTERISITCAS_LISTA_DESCRIPCION);

        this.listaEstadosTitulo = EnumEstadoTitulo.values();
        this.hayTipos = Boolean.FALSE;
        this.hayFechaEmision = Boolean.FALSE;
        this.hayFechaRecaudacion = Boolean.FALSE;
        this.hayMonto = Boolean.FALSE;
        this.esPanelFiltros = Boolean.FALSE;
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

    public Boolean esEstadoTituloIgual(EnumEstadoTitulo estadoTituloRegistrado, String estadoTitulo) {
        if (!ComunUtil.esNulo(estadoTituloRegistrado)
                && estadoTituloRegistrado.equals(EnumEstadoTitulo.obtenerEstadoTitulo(estadoTitulo))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Titulos> obtenerTitulosPorTipo(List<Titulos> titulosAFiltrar) {
        List<Titulos> titulosFiltrados = new ArrayList<>();
        for (Titulos titulo : titulosAFiltrar) {
            EnumEstadoTitulo estadoTituloRegistrado = titulo.getStsEstado();
            if (esEstadoTituloIgual(estadoTituloRegistrado, this.tipoTituloActual)) {
                titulosFiltrados.add(titulo);
            }
        }
        return titulosFiltrados;
    }

    public List<Titulos> filtrarPorTipo(List<Titulos> titulosAFiltrar) {
        if (hayTipos && !ComunUtil.esNulo(this.tipoTituloActual)) {
            return obtenerTitulosPorTipo(titulosAFiltrar);
        } else {
            return titulosAFiltrar;
        }
    }

    public List<Titulos> obtenerTitulosPorFechaEmision(List<Titulos> titulosAFiltrar) {
        List<Titulos> titulosFiltrados = new ArrayList<>();
        for (Titulos titulo : titulosAFiltrar) {
            Date fechaRegistrada = titulo.getFecEmision();
            if (estaDentroRangoFecha(fechaRegistrada, this.fechaMinimaEmision, this.fechaMaximaEmision)) {
                titulosFiltrados.add(titulo);
            }
        }
        return titulosFiltrados;
    }

    public Boolean sonFechasValidas(Date fechaMinima, Date fechaMaxima) {
        if (!ComunUtil.esNulo(fechaMinima) && !ComunUtil.esNulo(fechaMaxima)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Titulos> filtrarPorFechaEmision(List<Titulos> titulosAFiltrar) {
        if (hayFechaEmision && sonFechasValidas(this.fechaMinimaEmision, this.fechaMaximaEmision)) {
            return obtenerTitulosPorFechaEmision(titulosAFiltrar);
        } else {
            return titulosAFiltrar;
        }
    }

    public List<Titulos> obtenerTitulosPorFechaRecaudacion(List<Titulos> titulosAFiltrar) {
        List<Titulos> titulosFiltrados = new ArrayList<>();
        for (Titulos titulo : titulosAFiltrar) {
            Date fechaRegistrada = titulo.getFecFpago();
            if (estaDentroRangoFecha(fechaRegistrada, this.fechaMinimaRecaudacion, this.fechaMaximaRecaudacion)) {
                titulosFiltrados.add(titulo);
            }
        }
        return titulosFiltrados;
    }

    public List<Titulos> filtrarPorFechaRecaudacion(List<Titulos> titulosAFiltrar) {
        if (hayFechaEmision && sonFechasValidas(this.fechaMinimaRecaudacion, this.fechaMaximaRecaudacion)) {
            return obtenerTitulosPorFechaRecaudacion(titulosAFiltrar);
        } else {
            return titulosAFiltrar;
        }
    }

    public Boolean estaDentroRangoFecha(Date fechaRegistradaActual, Date fechaMinima, Date fechaMaxima) {
        if (!ComunUtil.esNulo(fechaRegistradaActual)
                && fechaRegistradaActual.after(fechaMinima)
                && fechaRegistradaActual.before(fechaMaxima)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Boolean estaDentroRangoNumero(BigDecimal montoRegistradoActual, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        if (!ComunUtil.esNulo(montoRegistradoActual)
                && montoRegistradoActual.subtract(valorMinimo).signum() >= 0
                && montoRegistradoActual.subtract(valorMaximo).signum() <= 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Titulos> obtenerTituloPorMonto(List<Titulos> titulosAFiltrar) {
        List<Titulos> titulosFiltrados = new ArrayList<>();
        for (Titulos titulo : titulosAFiltrar) {
            BigDecimal montoRegistrado = titulo.getValTotalapagar();
            if (estaDentroRangoNumero(montoRegistrado, this.montoMinimo, this.montoMaximo)) {
                titulosFiltrados.add(titulo);
            }
        }
        return titulosFiltrados;
    }
    public Boolean sonMontosValidos(BigDecimal valorMinimo, BigDecimal valorMaximo) {
        if (!ComunUtil.esNulo(valorMinimo) && !ComunUtil.esNulo(valorMaximo)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Titulos> filtrarPorMonto(List<Titulos> titulosAFiltrar) {
        if (hayMonto && sonMontosValidos(this.montoMinimo, this.montoMaximo)) {
            return obtenerTituloPorMonto(titulosAFiltrar);
        } else {
            return titulosAFiltrar;
        }
    }

    public List<Titulos> aplicarFiltros(List<Titulos> listaTitulos) {
        listaTitulos = filtrarPorTipo(listaTitulos);
        listaTitulos = filtrarPorMonto(listaTitulos);
        listaTitulos = filtrarPorFechaEmision(listaTitulos);
        listaTitulos = filtrarPorFechaRecaudacion(listaTitulos);
        return listaTitulos;
    }

    public void buscarTituloPorTipo() {
        this.listaTitulosRegistrados = aplicarFiltros(rentasServicio.consultarTitulos());
        this.totalCobrardoTitulos = obtenerTotalesTitulos(this.listaTitulosRegistrados);
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
    
    public void habilitarPanelFiltros(){
        this.esPanelFiltros = true;
    }
    public void desHabilitarPanelFiltros(){
        this.esPanelFiltros = Boolean.FALSE;
    }
    
    public void abrirDialogFiltros() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgFiltros').show()");
    }
}