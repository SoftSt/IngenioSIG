/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.geo.modelo.GeoPredio;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class NuevoPredioBB extends AdminFichaCatastralBB {

    private EnumPantallaMantenimiento pantallaActual;
    private List<GeoPredio> listaPrediosHuerfanos;
    private List<GeoPredio> listaPrediosHuerfanosFiltrados;

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public List<GeoPredio> getListaPrediosHuerfanos() {
        return listaPrediosHuerfanos;
    }

    public void setListaPrediosHuerfanos(List<GeoPredio> listaPrediosHuerfanos) {
        this.listaPrediosHuerfanos = listaPrediosHuerfanos;
    }

    public List<GeoPredio> getListaPrediosHuerfanosFiltrados() {
        return listaPrediosHuerfanosFiltrados;
    }

    public void setListaPrediosHuerfanosFiltrados(List<GeoPredio> listaPrediosHuerfanosFiltrados) {
        this.listaPrediosHuerfanosFiltrados = listaPrediosHuerfanosFiltrados;
    }
    
    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_BUSQUEDA);
        this.establecerTitulo(tituloPantalla, iconoPantalla, descripcionPantalla);
        establecerTitulo(EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_TITULO,
                EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_ICONO,
                EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_DESCRIPCION);
        try {
            obtenerListaPrediosHuerfanos();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void obtenerListaPrediosHuerfanos() throws NewviExcepcion {
        List<Predios> listaPrediosActual = catastroServicio.consultarPredios();
        listaPrediosHuerfanos = geoCatastroServicio.obtenerListadoGeoPrediosHuerfanos(listaPrediosActual, sesionBean.getSesion());
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

}
