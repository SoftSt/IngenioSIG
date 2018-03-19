/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class CarteraVencidaBB extends AdminTitulos {

    private EnumPantallaMantenimiento pantallaActual;
    private List<Titulos> listaTitulosRegistradosVencidos;
    private List<Titulos> listaTitulosRegistradosVencidosFiltrados;
    private BigDecimal totalTitulosVencidos;

    public BigDecimal getTotalTitulosVencidos() {
        return totalTitulosVencidos;
    }

    public void setTotalTitulosVencidos(BigDecimal totalTitulosVencidos) {
        this.totalTitulosVencidos = totalTitulosVencidos;
    }

    public List<Titulos> getListaTitulosRegistradosVencidos() {
        return listaTitulosRegistradosVencidos;
    }

    public void setListaTitulosRegistradosVencidos(List<Titulos> listaTitulosRegistradosVencidos) {
        this.listaTitulosRegistradosVencidos = listaTitulosRegistradosVencidos;
    }

    public List<Titulos> getListaTitulosRegistradosVencidosFiltrados() {
        return listaTitulosRegistradosVencidosFiltrados;
    }

    public void setListaTitulosRegistradosVencidosFiltrados(List<Titulos> listaTitulosRegistradosVencidosFiltrados) {
        this.listaTitulosRegistradosVencidosFiltrados = listaTitulosRegistradosVencidosFiltrados;
    }
    

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONSULTA_TITULOS_VENCIDOS_LISTA_TITULO,
                EnumEtiquetas.CONSULTA_TITULOS_VENCIDOS_LISTA_ICONO,
                EnumEtiquetas.CONSULTA_TITULOS_VENCIDOS_LISTA_DESCRIPCION);

        //this.hayFechaRecaudacion = Boolean.TRUE;
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

    public void buscarTitulosVencidos() {
        generarTitulosActualesYVencidos(rentasServicio.consultarTitulos());
    }

    public void generarTitulosActualesYVencidos(List<Titulos> listaTitulos) {
        this.listaTitulosRegistradosVencidos = new ArrayList<>();

        if (!ComunUtil.esNulo(listaTitulos)) {
            for (Titulos titulo : listaTitulos) {
                //if (titulo.getFecEmision().before(ComunUtil.hoy())) {
                if (!(ComunUtil.obtenerAnioDesdeFecha(titulo.getFecEmision()) >= ComunUtil.obtenerAnioDesdeFecha(ComunUtil.hoy()))) {
                    this.listaTitulosRegistradosVencidos.add(titulo);
                }
            }

            //this.listaTitulosRegistradosVencidos = obtenerTotales(this.listaTitulosRegistradosVencidos);
            //this.totalTitulosVencidos = obtenerTotalAPagarTitulos(this.listaTitulosRegistradosVencidos);
        }
    }
    
    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, String formatoReporte) throws NewviExcepcion {
        return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerDatosReporteListaTitulos(this.listaTitulosRegistradosVencidos), PresentacionFichaCatastralDto.class);
    }

}
