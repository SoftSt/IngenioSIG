/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
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
 * @author NEWVI
 */
@ManagedBean
@ViewScoped
public class TesoreriaBB extends AdminTesoreriaBB {
    
    private CatConConstantesdescuentos catConConstantesdescuentos;
    private List<CatConConstantesdescuentos> listacatConConstantesdescuentos;    
    private EnumPantallaMantenimiento pantallaActual;

    public CatConConstantesdescuentos getCatConConstantesdescuentos() {
        return catConConstantesdescuentos;
    }

    public void setCatConConstantesdescuentos(CatConConstantesdescuentos catConConstantesdescuentos) {
        this.catConConstantesdescuentos = catConConstantesdescuentos;
    }

    public List<CatConConstantesdescuentos> getListacatConConstantesdescuentos() {
        return listacatConConstantesdescuentos;
    }

    public void setListacatConConstantesdescuentos(List<CatConConstantesdescuentos> listacatConConstantesdescuentos) {
        this.listacatConConstantesdescuentos = listacatConConstantesdescuentos;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }
    
    @PostConstruct
    public void init() {
        this.catConConstantesdescuentos = new CatConConstantesdescuentos();
        actualizarListadoDominios();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.DESCUENTO_LISTA_TITULO, 
                EnumEtiquetas.DESCUENTO_LISTA_ICONO,
                EnumEtiquetas.DESCUENTO_LISTA_DESCRIPCION);
    }

    private void actualizarListadoDominios() {
        listacatConConstantesdescuentos = parametrosTesoreria.consultarDescuentos();
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
