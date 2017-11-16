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
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
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
public class GestionarTituloBB extends AdminFichaCatastralBB {

    @EJB
    private RentasServicio rentasServicio;

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
        establecerTitulo(EnumEtiquetas.GESTION_TITULO_LISTA_TITULO,
                EnumEtiquetas.GESTION_TITULO_LISTA_ICONO,
                EnumEtiquetas.GESTION_TITULO_LISTA_DESCRIPCION);
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

}
