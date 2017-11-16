/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Predios;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class GenerarTituloBB extends AdminFichaCatastralBB {

    @EJB
    private RentasServicio rentasServicio;

    private EnumPantallaMantenimiento pantallaActual;

    private List<Avaluo> listaAvaluosProcesados;
    private List<Avaluo> listaAvaluosSeleccionados;
    private List<Avaluo> listaAvaluosProcesadosFiltrados;
    private List<FechaAvaluo> listaFechaAvaluos;
    private List<Titulos> listaTitulosGenerados;
    private List<Titulos> listaTitulosRegistrados;
    private List<Titulos> listaTitulosGeneradosFiltrados;
    private List<Titulos> listaTitulosRegistradosFiltrados;
    private FechaAvaluo fechaAvaluoActual;
    private String fechaActualPrueba;
    private FechaAvaluo fechaAvaluo;
    private Titulos tituloActual;

    private BigDecimal totalPorCobrarConsulta;
    private BigDecimal totalPorCobrarTitulos;
    private BigDecimal totalCobrardoTitulos;

    private Date fechaEmisionTitulo;

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

    public List<Avaluo> getListaAvaluosProcesados() {
        return listaAvaluosProcesados;
    }

    public List<Titulos> getListaTitulosGenerados() {
        return listaTitulosGenerados;
    }

    public List<Titulos> getListaTitulosGeneradosFiltrados() {
        return listaTitulosGeneradosFiltrados;
    }

    public void setListaTitulosGeneradosFiltrados(List<Titulos> listaTitulosGeneradosFiltrados) {
        this.listaTitulosGeneradosFiltrados = listaTitulosGeneradosFiltrados;
    }

    public FechaAvaluo getFechaAvaluoActual() {
        return fechaAvaluoActual;
    }

    public void setFechaAvaluoActual(FechaAvaluo fechaAvaluoActual) {
        this.fechaAvaluoActual = fechaAvaluoActual;
    }

    public List<Avaluo> getListaAvaluosProcesadosFiltrados() {
        return listaAvaluosProcesadosFiltrados;
    }

    public void setListaAvaluosprocesadosFiltrados(List<Avaluo> listaAvaluosFiltrados) {
        this.listaAvaluosProcesadosFiltrados = listaAvaluosFiltrados;
    }

    public List<Avaluo> getListaAvaluosSeleccionados() {
        return listaAvaluosSeleccionados;
    }

    public void setListaAvaluosSeleccionados(List<Avaluo> listaAvaluosSeleccionados) {
        this.listaAvaluosSeleccionados = listaAvaluosSeleccionados;
    }

    public BigDecimal getTotalPorCobrarConsulta() {
        return totalPorCobrarConsulta;
    }

    public BigDecimal getTotalPorCobrarTitulos() {
        return totalPorCobrarTitulos;
    }

    public BigDecimal getTotalCobrardoTitulos() {
        return totalCobrardoTitulos;
    }

    public Date getFechaEmisionTitulo() {
        return fechaEmisionTitulo;
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

    public FechaAvaluo getFechaAvaluo() {
        return fechaAvaluo;
    }

    public void setTituloActual(Titulos tituloActual) {
        this.tituloActual = tituloActual;
    }
    
    @PostConstruct
    public void init() {

        fechaAvaluoActual = new FechaAvaluo();
        listaAvaluosProcesados = new ArrayList<>();
        listaFechaAvaluos = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.GENERARION_TITULO_LISTA_TITULO,
                EnumEtiquetas.GENERARION_TITULO_LISTA_ICONO,
                EnumEtiquetas.GENERARION_TITULO_LISTA_DESCRIPCION);
        actualizarListadoFechaAvaluos();
        actualizarListadoTitulosRegistrados();

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
        listaAvaluosProcesados = catastroServicio.consultarListaAvaluosActuales();
    }

    public void actualizarListaAvaluosPorFecha(String fechaDescripcion) {
        try {
            listaAvaluosProcesados = catastroServicio.consultarListaAvaluosPorFecha(fechaDescripcion);
            this.totalPorCobrarConsulta = obtenerTotales(listaAvaluosProcesados);
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR001.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    private BigDecimal obtenerTotales(List<Avaluo> listadoAvaluo) {
        BigDecimal totalPorCobrar = BigDecimal.ZERO;
        for (Avaluo avaluo : listadoAvaluo) {
            totalPorCobrar = totalPorCobrar.add(avaluo.getValImppredial());
        }
        return totalPorCobrar;
    }

    private BigDecimal obtenerTotalesTitulos(List<Titulos> listadoTitulos) {
        BigDecimal totalPorCobrar = BigDecimal.ZERO;
        for (Titulos titulo : listadoTitulos) {
            totalPorCobrar = totalPorCobrar.add(titulo.getValTotalapagar());
        }
        return totalPorCobrar;
    }

    public Boolean hayDatosConsultados() {
        return !this.listaAvaluosProcesados.isEmpty();
    }

    public void reiniciarConsulta() {
        this.listaAvaluosProcesados = new ArrayList<>();
        this.listaTitulosGenerados = new ArrayList<>();
        this.totalPorCobrarConsulta = BigDecimal.ZERO;
        this.totalCobrardoTitulos = BigDecimal.ZERO;
        this.listaTitulosRegistrados = new ArrayList<>();
    }

    public void generarTitulos() {
        try {
            this.listaTitulosGenerados = rentasServicio.generarTitulosDesdeAvaluos(listaAvaluosSeleccionados, sesionBean.getSesion());
            this.totalPorCobrarTitulos = obtenerTotalesTitulos(this.listaTitulosGenerados);

            this.fechaAvaluo = listaAvaluosSeleccionados.get(0).getFecavId();

            Map<String, String> variables = new HashMap<>();
            variables.put("ntitulos", (new Integer(this.listaTitulosGenerados.size())).toString());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF601.presentarMensaje(variables), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF601.presentarMensaje(variables));

        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
        this.listaAvaluosSeleccionados = new ArrayList<>();
    }

    public void seleccionarTodosTitulos() {
        this.listaAvaluosSeleccionados = this.listaAvaluosProcesados;
    }

    public void generarTodosTitulos() {
        try {
            this.listaTitulosGenerados = rentasServicio.generarTitulosDesdeAvaluos(this.listaAvaluosProcesados, sesionBean.getSesion());
            this.totalPorCobrarTitulos = obtenerTotalesTitulos(this.listaTitulosGenerados);

            Map<String, String> variables = new HashMap<>();
            variables.put("ntitulos", (new Integer(this.listaTitulosGenerados.size())).toString());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF601.presentarMensaje(variables), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF601.presentarMensaje(variables));

        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public Boolean hayTitulosPresentados() {
        return !this.listaTitulosGenerados.isEmpty();
    }

    private String generarSerial(String codigo) {
        while (codigo.length() < 6) {
            codigo = "0".concat(codigo);
        }
        return codigo;
    }

    public void limpiarListaGenerados() {
        Iterator<Titulos> iter = this.listaTitulosGenerados.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        this.totalCobrardoTitulos = BigDecimal.ZERO;
    }

    private String generarCodSecuencial(Titulos tituloGenerado) {

        Calendar fechaEmision = Calendar.getInstance();
        fechaEmision.setTime(tituloGenerado.getFecEmision());
        return fechaEmision.get(Calendar.YEAR) + "-" + generarSerial(tituloGenerado.getCodCatastral().getCodCatastral().toString()) + "-PU";
    }

    public void actualizarListadoTitulosRegistrados() {
        this.listaTitulosRegistrados = rentasServicio.consultarTitulosGenerados(!ComunUtil.esNulo(this.fechaEmisionTitulo) ? this.fechaEmisionTitulo : ComunUtil.hoy());
        this.totalCobrardoTitulos = obtenerTotalesTitulos(this.listaTitulosRegistrados);
    }

    public void eliminarAvaluo(Predios codCatastral) {
        try {
            Avaluo avaluoEliminable = catastroServicio.consultarAvaluoPorCodCatastralYFechaAvaluo(codCatastral, this.fechaAvaluo);
            avaluoEliminable.setAvalEstado(EnumEstadoRegistro.E);
            catastroServicio.actualizarAvaluo(avaluoEliminable, sesionBean.getSesion());
            listaAvaluosProcesados.remove(avaluoEliminable);
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        }
    }

    public void registrarNuevoTitulo() throws NewviExcepcion {
        for (Titulos nuevoTitulo : this.listaTitulosGenerados) {
            try {
                nuevoTitulo.setStsEstado(EnumEstadoTitulo.TITULO_EMITIDO);
                nuevoTitulo.setCodSecuencial(generarCodSecuencial(nuevoTitulo));
                this.fechaEmisionTitulo = rentasServicio.generarNuevoTitulo(nuevoTitulo, sesionBean.getSesion());
                //this.listaTitulosGenerados.remove(nuevoTitulo);
                eliminarAvaluo(nuevoTitulo.getCodCatastral());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        actualizarListadoTitulosRegistrados();
        actualizarListadoAvaluos();
        Map<String, String> variables = new HashMap<>();
        variables.put("ntitulos", (new Integer(this.listaTitulosGenerados.size())).toString());
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF602.presentarMensaje(variables), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF602.presentarMensaje(variables));
        limpiarListaGenerados();
        this.totalPorCobrarConsulta = BigDecimal.ZERO;
    }
    
    public DefaultStreamedContent imprimir(EnumReporte tipoReporte) {
        return generarReporteCatastro(tipoReporte, ReporteGenerador.FormatoReporte.PDF, obtenerDatosReporteTitulos(this.tituloActual), PresentacionFichaCatastralDto.class);
    }

}
