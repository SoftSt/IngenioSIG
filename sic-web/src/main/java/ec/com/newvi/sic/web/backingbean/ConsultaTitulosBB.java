/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
public class ConsultaTitulosBB extends AdminFichaCatastralBB {
    
    @EJB
    private RentasServicio rentasServicio;
    private EnumPantallaMantenimiento pantallaActual;
    private String tipoTituloActual;
    private List<Titulos> listaTitulosRegistrados;
    private List<Titulos> listaTitulosRegistradosFiltrados;
    private Date fechaMinima;
    private Date fechaMaxima;
           
    
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

    public Date getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
    
    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONSULTA_TITULO_CARACTERISITCAS_LISTA_TITULO,
                EnumEtiquetas.CONSULTA_TITULO_CARACTERISITCAS_LISTA_ICONO,
                EnumEtiquetas.CONSULTA_TITULO_CARACTERISITCAS_LISTA_DESCRIPCION);
        
        this.listaEstadosTitulo = EnumEstadoTitulo.values();
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
    
    public void buscarTituloPorTipo(){
        this.listaTitulosRegistrados = rentasServicio.consultarTitulosPorTipo(EnumEstadoTitulo.obtenerEstadoTitulo(this.tipoTituloActual));
        this.totalCobrardoTitulos = obtenerTotalesTitulos(this.listaTitulosRegistrados);
    }
    
    
        
    
}
