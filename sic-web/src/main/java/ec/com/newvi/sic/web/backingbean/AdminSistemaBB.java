/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.sesion.SesionBean;
import javax.inject.Inject;

/**
 *
 * @author israelavila
 */
public abstract class AdminSistemaBB {
    
    @Inject
    protected SesionBean sesionBean;
    
    protected EnumEtiquetas tituloPantalla;
    protected EnumEtiquetas iconoPantalla;
    protected EnumEtiquetas descripcionPantalla;

    public EnumEtiquetas getTituloPantalla() {
        return tituloPantalla;
    }

    public EnumEtiquetas getIconoPantalla() {
        return iconoPantalla;
    }

    public EnumEtiquetas getDescripcionPantalla() {
        return descripcionPantalla;
    }
    
    protected void establecerTitulo(EnumEtiquetas titulo, EnumEtiquetas icono, EnumEtiquetas descripcion) {
        this.tituloPantalla = titulo;
        this.iconoPantalla = icono;
        this.descripcionPantalla = descripcion;
    }

}
