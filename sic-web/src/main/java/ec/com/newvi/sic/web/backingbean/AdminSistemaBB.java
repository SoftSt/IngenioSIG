/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ConfiguracionReporte;
import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.CaracteristicasEdificacionesDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumParametrosReporte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author israelavila
 */
public abstract class AdminSistemaBB extends SistemaBB {
    
    
    @EJB
    protected GeoCatastroServicio geoCatastroServicio;
    
    protected Predios predio;

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }
    
    

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
        parametrosReporte.put("TITULO_REPORTE", reporte.getTituloReporte());

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
    
    protected DefaultStreamedContent generarReporteCatastro(EnumReporte tipoReporte, ReporteGenerador.FormatoReporte formatoReporte, List datosImpresion, Class claseImpresion) {
        try {

            CaracteristicasEdificacionesDto bloques;
            Map<String, Object> parametrosReporte = new HashMap<>();

            parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CATASTRO PREDIAL URBANO");

            String xPath = "/lista".concat(claseImpresion.getSimpleName()).concat("//").concat(claseImpresion.getSimpleName());

            if (EnumReporte.FICHA_RELEVAMIENTO_PREDIAL_URBANO.equals(tipoReporte)) {
                bloques = new CaracteristicasEdificacionesDto(this.predio);

                parametrosReporte.put(EnumParametrosReporte.DESCRIPCION_TERRENO.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaDescripcionTerreno());
                parametrosReporte.put(EnumParametrosReporte.INFRAESTRUCTURA_SERVICIOS.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaServicios());
                parametrosReporte.put(EnumParametrosReporte.CARACTERISTICAS_EDIFICACION.getNombre(), ((PresentacionFichaCatastralDto) datosImpresion.get(0)).getListaBloques());
                parametrosReporte.put(EnumParametrosReporte.PISO.getNombre(), bloques.getListadetallesPisoDtoD());

                parametrosReporte.put(EnumParametrosReporte.IMAGEN_DELIMITACION_PREDIO.getNombre(), parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.DIRECCION_SERVICIO_IMAGEN_PREDIO, sesionBean.getSesion()).getValor().concat(geoCatastroServicio.obtenerBordesPredio(predio, BigDecimal.valueOf(95), BigDecimal.valueOf(533), sesionBean.getSesion())));
            }

            Map<String, Class> paramRepA = new HashMap<String, Class>();
            paramRepA.put(claseImpresion.getSimpleName(), claseImpresion);
            paramRepA.put("lista".concat(claseImpresion.getSimpleName()), List.class);

            return generarReporte(tipoReporte, datosImpresion, paramRepA, xPath, parametrosReporte, formatoReporte);

        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            return null;
        }
        return null;
    }
    
    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteListaTitulosDesmarcados(List<Titulos> listaTitulos) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        for (Titulos titulo : listaTitulos) {
            tablita.add(new PresentacionFichaCatastralDto(titulo, Boolean.TRUE));
        }
        return tablita;
    }
    protected List<PresentacionFichaCatastralDto> obtenerDatosReporteListaTitulos(List<Titulos> listaTitulos) {
        List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
        for (Titulos titulo : listaTitulos) {
            tablita.add(new PresentacionFichaCatastralDto(titulo));
        }
        return tablita;
    }
    
    protected DefaultStreamedContent generarReporteCertificado(EnumReporte tipoReporte, ReporteGenerador.FormatoReporte formatoReporte, List datosImpresion, Class claseImpresion) {
        try {

            Map<String, Object> parametrosReporte = new HashMap<>();

            parametrosReporte.put(EnumParametrosReporte.NOMBRE_MODULO.getNombre(), "CERTIFICADO DE USO DE SUELO");

            String xPath = "/lista".concat(claseImpresion.getSimpleName()).concat("//").concat(claseImpresion.getSimpleName());

            Map<String, Class> paramRepA = new HashMap<String, Class>();
            paramRepA.put(claseImpresion.getSimpleName(), claseImpresion);
            paramRepA.put("lista".concat(claseImpresion.getSimpleName()), List.class);

            return generarReporte(tipoReporte, datosImpresion, paramRepA, xPath, parametrosReporte, formatoReporte);

        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            return null;
        }
        return null;
    }

}
