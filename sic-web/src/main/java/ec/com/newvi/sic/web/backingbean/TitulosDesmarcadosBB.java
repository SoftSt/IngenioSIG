/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.TituloMovimientos;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TitulosDesmarcadosBB extends AdminTitulos {

    private EnumPantallaMantenimiento pantallaActual;

    private Boolean hayFechaRecaudacion;

    private BigDecimal totalCobrardoTitulos;
    private Titulos tituloActual;

    public Titulos getTituloActual() {
        return tituloActual;
    }

    public void setTituloActual(Titulos tituloActual) {
        this.tituloActual = tituloActual;
    }

    public BigDecimal getTotalCobrardoTitulos() {
        return totalCobrardoTitulos;
    }

    public void setTotalCobrardoTitulos(BigDecimal totalCobrardoTitulos) {
        this.totalCobrardoTitulos = totalCobrardoTitulos;
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

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, String formatoReporte) throws NewviExcepcion {
        return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerDatosReporteListaTitulosDesmarcados(this.listaTitulosRegistrados), PresentacionFichaCatastralDto.class);
    }

    public void buscarTituloPorTipo() {
        this.listaTitulosRegistrados = aplicarFiltros(rentasServicio.consultarTitulosPorTipo(EnumEstadoTitulo.TITULO_DESMARCADO), EnumEstadoTitulo.TITULO_DESMARCADO);
        this.totalCobrardoTitulos = obtenerTotalesTitulos(this.listaTitulosRegistrados);
    }

    public void seleccionarTitulo(Integer codTitulo) {

        try {
            this.tituloActual = rentasServicio.seleccionarTitulo(codTitulo);
        } catch (NewviExcepcion ex) {
            Logger.getLogger(TitulosDesmarcadosBB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarMovimiento(Titulos titulo) {
        TituloMovimientos movimiento = new TituloMovimientos();
        movimiento.setAnioEmision(ComunUtil.obtenerAnioDesdeFecha(titulo.getFecEmision()) + "");
        movimiento.setCodTitulos(titulo);
        movimiento.setEstadoMovimiento(EnumEstadoRegistro.A);
        movimiento.setEstadoTitulo(titulo.getStsEstado());
        movimiento.setFecMovimiento(ComunUtil.hoy());
        movimiento.setIpUsu(sesionBean.getSesion().getDireccionIP());
        movimiento.setNomUsu(sesionBean.getSesion().getNombreEquipo());
        movimiento.setTxtMovimiento(titulo.getStsEstado().getEstadoTitulo() + titulo.getCodTitulos());

        try {
            rentasServicio.generarNuevoMovimentosTitulo(movimiento, sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void habilitarTituloParaCobro(Integer codTitulo) {
        try {
            seleccionarTitulo(codTitulo);
            this.tituloActual.setStsEstado(EnumEstadoTitulo.TITULO_EMITIDO);
            rentasServicio.actualizarTitulo(this.tituloActual, sesionBean.getSesion());
            registrarMovimiento(this.tituloActual);
            buscarTituloPorTipo();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF606.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF606.presentarMensaje());

        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

}
