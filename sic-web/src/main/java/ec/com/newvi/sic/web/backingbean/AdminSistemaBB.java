/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ConfiguracionReporte;
import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.enums.EnumParametrosReporte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author israelavila
 */
public abstract class AdminSistemaBB extends SistemaBB {

    protected Map<String, Object> obtenerParametrosSistemaParaReporte() {
        String rutaReporte = "";
        String rutaImagenReporte = "";
        String nombreSistema = "";
        String nombreMunicipio = "";
        String imagenLogoMunicipio1 = "";
        String imagenLogoMunicipio2 = "";
        String nombreUsuario = "";
        try {
            rutaReporte = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.RUTA_UBICACION_REPORTES, sesionBean.getSesion()).getValor();
            rutaImagenReporte = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.RUTA_UBICACION_IMAGENES_REPORTE, sesionBean.getSesion()).getValor();
            nombreSistema = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.NOMBRE_SISTEMA, sesionBean.getSesion()).getValor();
            nombreMunicipio = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.NOMBRE_COMPLETO_MUNICIPIO, sesionBean.getSesion()).getValor();
            imagenLogoMunicipio1 = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.IMAGEN_LOGO_MUNICIPIO_1, sesionBean.getSesion()).getValor();
            imagenLogoMunicipio2 = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.IMAGEN_LOGO_MUNICIPIO_2, sesionBean.getSesion()).getValor();
            nombreUsuario = sesionBean.getSesion().getUsuarioRegistrado().getUsuUsuario();
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
        }

        Map<String, Object> params = new HashMap<>();
        params.put(EnumParametrosReporte.RUTA_REPORTES.getNombre(), rutaReporte);
        params.put(EnumParametrosReporte.NOMBRE_SISTEMA.getNombre(), nombreSistema);
        params.put(EnumParametrosReporte.NOMBRE_MUNICIPIO.getNombre(), nombreMunicipio);
        params.put(EnumParametrosReporte.NOMBRE_USUARIO.getNombre(), nombreUsuario);
        params.put(EnumParametrosReporte.IMAGEN_LOGO_MUNICIPIO_1.getNombre(), imagenLogoMunicipio1);
        params.put(EnumParametrosReporte.IMAGEN_LOGO_MUNICIPIO_2.getNombre(), imagenLogoMunicipio2);
        params.put(EnumParametrosReporte.RUTA_IMAGENES_REPORTES.getNombre(), rutaImagenReporte);
        return params;
    }

    protected DefaultStreamedContent generarReporte(EnumReporte tipoReporte, List datosReporte, Map<String, Class> clasesDatosReporte, String xPath, Map<String, Object> parametrosReporte, ReporteGenerador.FormatoReporte formatoReporte) throws NewviExcepcion {
        DefaultStreamedContent archivoReporteGenerado = null;
        parametrosReporte.putAll(this.obtenerParametrosSistemaParaReporte());
        Reporte reporte = parametrosServicio.obtenerReporte(tipoReporte, sesionBean.getSesion());
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
