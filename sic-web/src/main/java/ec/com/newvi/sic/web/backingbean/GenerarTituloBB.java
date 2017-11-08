/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    private List<Titulos> listaTitulosGeneradosFiltrados;
    private FechaAvaluo fechaAvaluoActual;
    private String fechaActualPrueba;

    private BigDecimal totalPorCobrarConsulta;
    private BigDecimal totalPorCobrarTitulos;

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
        this.totalPorCobrarConsulta = BigDecimal.ZERO;
    }

    public void generarTitulos() {
        try {
            this.listaTitulosGenerados = rentasServicio.generarTitulosDesdeAvaluos(listaAvaluosSeleccionados, sesionBean.getSesion());
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

    public void registrarNuevoTitulo() throws NewviExcepcion {
        for (Titulos nuevoTitulo : this.listaTitulosGenerados) {
            try {
                rentasServicio.generarNuevoTitulo(nuevoTitulo, sesionBean.getSesion());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        Map<String, String> variables = new HashMap<>();
        variables.put("ntitulos", (new Integer(this.listaTitulosGenerados.size())).toString());
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF602.presentarMensaje(variables), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF602.presentarMensaje(variables));
    }

}
