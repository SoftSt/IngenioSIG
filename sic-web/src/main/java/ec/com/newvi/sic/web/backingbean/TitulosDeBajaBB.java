/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
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
public class TitulosDeBajaBB extends AdminTitulos {

    private EnumPantallaMantenimiento pantallaActual;

    private Boolean hayFechaRecaudacion;

    private BigDecimal totalCobrardoTitulos;

    

    public Boolean getHayFechaRecaudacion() {
        return hayFechaRecaudacion;
    }

    public void setHayFechaRecaudacion(Boolean hayFechaRecaudacion) {
        this.hayFechaRecaudacion = hayFechaRecaudacion;
    }

    public BigDecimal getTotalCobrardoTitulos() {
        return totalCobrardoTitulos;
    }

    public void setTotalCobrardoTitulos(BigDecimal totalCobrardoTitulos) {
        this.totalCobrardoTitulos = totalCobrardoTitulos;
    }

    

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONSULTA_TITULOS_DE_BAJA_LISTA_TITULO,
                EnumEtiquetas.CONSULTA_TITULOS_DE_BAJA_LISTA_ICONO,
                EnumEtiquetas.CONSULTA_TITULOS_DE_BAJA_LISTA_DESCRIPCION);

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


    public void buscarTituloPorTipo() {
        this.listaTitulosRegistrados = aplicarFiltros(rentasServicio.consultarTitulosPorTipo(EnumEstadoTitulo.TITULO_ANULADO), EnumEstadoTitulo.TITULO_ANULADO );
        this.totalCobrardoTitulos = obtenerTotalesTitulos(this.listaTitulosRegistrados);
    }

    

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, String formatoReporte) throws NewviExcepcion {
        return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerDatosReporteListaTitulos(this.listaTitulosRegistrados), PresentacionFichaCatastralDto.class);
    }
}
