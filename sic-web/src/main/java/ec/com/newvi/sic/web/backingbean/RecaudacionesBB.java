/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
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
public class RecaudacionesBB extends AdminFichaCatastralBB {

    private EnumPantallaMantenimiento pantallaActual;

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
        this.predio = new Predios();

        actualizarListadoPredios();

        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FORMULARIO_RECAUDACION_LISTA_TITULO,
                EnumEtiquetas.FORMULARIO_RECAUDACION_LISTA_ICONO,
                EnumEtiquetas.FORMULARIO_RECAUDACION_LISTA_DESCRIPCION);

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
    
    @Override
    public void seleccionarContribuyente(Integer idContribuyente) {
        //super.seleccionarContribuyente(idContribuyente);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.RECAUDACION_EDITAR_TITULO,
                EnumEtiquetas.RECAUDACION_EDITAR_ICONO,
                EnumEtiquetas.RECAUDACION_EDITAR_DESCRIPCION);
    }
}
