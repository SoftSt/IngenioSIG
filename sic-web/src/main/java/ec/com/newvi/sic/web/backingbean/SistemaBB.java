/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumFuncionalidad;
import ec.com.newvi.sic.web.sesion.SesionBean;
import ec.com.newvi.sic.web.sesion.SistemaBean;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author israelavila
 */
public abstract class SistemaBB {

    @Inject
    protected SesionBean sesionBean;

    @Inject
    protected SistemaBean sistemaBean;

    @EJB
    protected ParametrosServicio parametrosServicio;

    @EJB
    protected SeguridadesServicio seguridadesServicio;

    protected Funcionalidades funcionalidadActual;

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

    protected void obtenerFuncionalidadActual(EnumFuncionalidad funcionalidad) {
        try {
            sistemaBean.setFuncionalidadActual(seguridadesServicio.obtenerFuncionalidadPorNombre(funcionalidad.getNombreFuncionalidad(), sesionBean.getSesion()));
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR455, ex, sesionBean.getSesion());
        }
    }

}
