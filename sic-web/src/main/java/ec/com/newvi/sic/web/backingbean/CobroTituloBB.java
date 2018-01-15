/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.ConstantesDescuentos;
import ec.com.newvi.sic.modelo.ConstantesInteresMora;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.servicios.TesoreriaServicio;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CobroTituloBB extends AdminFichaCatastralBB {

    @EJB
    private RentasServicio rentasServicio;
    @EJB
    private TesoreriaServicio tesoreriaServicio;

    private EnumPantallaMantenimiento pantallaActual;
    private List<Titulos> listaTitulosRegistrados;
    private List<Titulos> listaTitulosRegistradosFiltrados;
    private FichaCatastralDto fichaCatastral;
    private Titulos tituloActual;

    public List<Titulos> getListaTitulosRegistrados() {
        return listaTitulosRegistrados;
    }

    public List<Titulos> getListaTitulosRegistradosFiltrados() {
        return listaTitulosRegistradosFiltrados;
    }

    public void setListaTitulosRegistradosFiltrados(List<Titulos> listaTitulosRegistradosFiltrados) {
        this.listaTitulosRegistradosFiltrados = listaTitulosRegistradosFiltrados;
    }

    public FichaCatastralDto getFichaCatastral() {
        return fichaCatastral;
    }

    public void setFichaCatastral(FichaCatastralDto fichaCatastral) {
        this.fichaCatastral = fichaCatastral;
    }

    public Titulos getTituloActual() {
        return tituloActual;
    }

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_LISTA_TITULO,
                EnumEtiquetas.COBRO_TITULO_LISTA_ICONO,
                EnumEtiquetas.COBRO_TITULO_LISTA_DESCRIPCION);
        actualizarListadoPredios();
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

    public void actualizarListaTitulosRegistrados(Integer codCatastral) {
        this.listaTitulosRegistrados = new ArrayList<>();
        this.listaTitulosRegistrados = rentasServicio.consultarTitulosPorCodigoCatastral(codCatastral);
    }

    public void seleccionarTituloDePredio(Integer codCatastral) throws NewviExcepcion {
        actualizarListaTitulosRegistrados(codCatastral);
        this.predio = catastroServicio.seleccionarPredio(codCatastral);
        this.fichaCatastral = new FichaCatastralDto(this.predio);
        WebUtils.obtenerContextoPeticion().execute("PF('dlgTitulos').show()");
    }

    public Titulos seleccionarTitulo(Integer codTitulo) {
        Titulos tituloEliminable = null;
        try {
            tituloEliminable = rentasServicio.seleccionarTitulo(codTitulo);
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR603.presentarMensajeCodigo(), ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        }

        return tituloEliminable;
    }

    private String obtenerNumeroQuincena(Integer dia) {
        return dia <= 15 ? "1" : "2";
    }

    private BigDecimal calcularInteresMora(Titulos titulo) {
        Integer aniosMora = obtenerDiferenciaAniosActualEmision(titulo);
        ConstantesInteresMora interesMora = tesoreriaServicio.buscarInteresPorNumeroAnios(aniosMora);
        return interesMora.getValMonto();
    }

    private BigDecimal calcularDescuentoRecargo(Titulos titulo) {
        String mesEmision;
        Integer diaEmision;
        BigDecimal valorBase;
        BigDecimal tasaDescuento;
        ConstantesDescuentos objetoDescuentos;

        valorBase = titulo.getValTotalapagar();
        mesEmision = ComunUtil.obtenerMesDesdeFecha(titulo.getFecEmision());
        diaEmision = ComunUtil.obtenerDiaDesdeFecha(titulo.getFecEmision());
        objetoDescuentos = tesoreriaServicio.buscarDescuentoRecargoPorMesYQuincena(mesEmision, obtenerNumeroQuincena(diaEmision));
        tasaDescuento = objetoDescuentos.getValValor().divide(new BigDecimal(100));

        return objetoDescuentos.getStsEstado().trim().equals("r") ? valorBase.multiply(tasaDescuento) : valorBase.multiply(tasaDescuento).negate();
    }

    private Integer obtenerDiferenciaAniosActualEmision(Titulos titulo) {
        Integer anioActual = ComunUtil.obtenerAnioDesdeFecha(ComunUtil.hoy());
        Integer anioEmision = ComunUtil.obtenerAnioDesdeFecha(titulo.getFecEmision());
        return anioActual - anioEmision;

    }

    private Boolean esDescuento(Titulos titulo) {
        return obtenerDiferenciaAniosActualEmision(titulo) > 1 ? Boolean.FALSE : Boolean.TRUE;
    }

    public void calcularDescuentosIntereses(Integer codTitulo) {
        this.tituloActual = seleccionarTitulo(codTitulo);
        this.predio = this.tituloActual.getCodCatastral();
        this.fichaCatastral = new FichaCatastralDto(this.predio);
        if (esDescuento(this.tituloActual)) {
            this.tituloActual.setValDescuentoaplicado(calcularDescuentoRecargo(this.tituloActual));
            this.tituloActual.setValInteresaplicado(BigDecimal.ZERO);
        } else {
            this.tituloActual.setValInteresaplicado(calcularInteresMora(this.tituloActual));
            this.tituloActual.setValDescuentoaplicado(BigDecimal.ZERO);
        }
        this.tituloActual.setValPagado(this.tituloActual.getValTotalapagar().add(this.tituloActual.getValDescuentoaplicado()).add(this.tituloActual.getValInteresaplicado()));

        WebUtils.obtenerContextoPeticion().execute("PF('dlgResumenTitulos').show()");

    }

    public void eliminarTitulo(Integer codTitulo) {
        Titulos tituloEliminable = seleccionarTitulo(codTitulo);
        tituloEliminable.setStsEstado(EnumEstadoTitulo.TITULO_ANULADO);

        try {
            rentasServicio.actualizarTitulo(tituloEliminable, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF603.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF603.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
        this.listaTitulosRegistrados.remove(tituloEliminable);

    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, Integer codTitulos) throws NewviExcepcion {
        return generarReporteCatastro(tipoReporte, ReporteGenerador.FormatoReporte.PDF, obtenerDatosReporteTitulos(rentasServicio.seleccionarTitulo(codTitulos)), PresentacionFichaCatastralDto.class);
    }

    public void seleccionarTitulosPredio(Integer codCatastral) throws NewviExcepcion {
        actualizarListaTitulosRegistrados(codCatastral);
        this.predio = catastroServicio.seleccionarPredio(codCatastral);
        this.fichaCatastral = new FichaCatastralDto(this.predio);

        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_EDITAR_TITULO,
                EnumEtiquetas.COBRO_TITULO_EDITAR_ICONO,
                EnumEtiquetas.COBRO_TITULO_EDITAR_DESCRIPCION);
    }

    public void regresarPaginaAnterior() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_LISTA_TITULO,
                EnumEtiquetas.COBRO_TITULO_LISTA_ICONO,
                EnumEtiquetas.COBRO_TITULO_LISTA_DESCRIPCION);
    }

    public void cobrarTitulo() {
        this.tituloActual.setStsEstado(EnumEstadoTitulo.TITULO_COBRADO);
        this.tituloActual.setFecFpago(ComunUtil.hoy());

        try {
            rentasServicio.actualizarTitulo(this.tituloActual, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF604.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF604.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
}
