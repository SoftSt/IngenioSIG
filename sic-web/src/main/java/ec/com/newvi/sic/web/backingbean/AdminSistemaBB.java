/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ConfiguracionReporte;
import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.sesion.SesionBean;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author israelavila
 */
public abstract class AdminSistemaBB {
    
    @Inject
    protected SesionBean sesionBean;
    
    @EJB
    protected ParametrosServicio parametrosServicio;
    
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

    protected DefaultStreamedContent generarReporte(EnumReporte tipoReporte, List datosReporte, Map<String, Class> clasesDatosReporte, String xPath, Map<String, Object> parametrosReporte, ReporteGenerador.FormatoReporte formatoReporte) throws NewviExcepcion {
        DefaultStreamedContent archivoReporteGenerado = null;
        Reporte reporte = parametrosServicio.obtenerReporte(tipoReporte, sesionBean.obtenerSesionDto());
        ConfiguracionReporte configReporte = new ConfiguracionReporte(formatoReporte, datosReporte, clasesDatosReporte, reporte.getArchivoReporte(), xPath, parametrosReporte);
        if (ComunUtil.esNulo(configReporte)) {
            return null;
        }
        try {
            InputStream flujoDatosIngreso = new ByteArrayInputStream((byte[]) configReporte.getDatos());
            archivoReporteGenerado = new DefaultStreamedContent(flujoDatosIngreso, configReporte.getMimeType().name(), reporte.getArchivoDescargaReporte() + configReporte.getArchivoExtension());
            flujoDatosIngreso.reset();
            flujoDatosIngreso.close();
        } catch (IOException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR404, ex);
        }
        return archivoReporteGenerado;
    }

}
