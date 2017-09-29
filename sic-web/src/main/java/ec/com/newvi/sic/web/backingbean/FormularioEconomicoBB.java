/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ConfiguracionReporte;
import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class FormularioEconomicoBB extends AdminFichaCatastralBB {

    private List<FichaCatastralDto> listaFichas;
    private List<FichaCatastralDto> listaFichasFiltradas;
    private Predios predio;
    private EnumPantallaMantenimiento pantallaActual;
    private ReporteGenerador.FormatoReporte mimeType;

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }

    public List<FichaCatastralDto> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaCatastralDto> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public List<FichaCatastralDto> getListaFichasFiltradas() {
        return listaFichasFiltradas;
    }

    public void setListaFichasFiltradas(List<FichaCatastralDto> listaFichasFiltradas) {
        this.listaFichasFiltradas = listaFichasFiltradas;
    }

    @PostConstruct
    public void init() {
        this.predio = new Predios();

        actualizarListadoPredios();

        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FORMULARIO_ECONOMICO_LISTA_TITULO,
                EnumEtiquetas.FORMULARIO_ECONOMICO_LISTA_ICONO,
                EnumEtiquetas.FORMULARIO_ECONOMICO_LISTA_DESCRIPCION);

    }

    private void actualizarListadoPredios() {
        List<Predios> listaPredios = catastroServicio.consultarPredios();
        listaFichas = new ArrayList<>();
        listaPredios.forEach((elementoPredio) -> {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        });
    }

    public void abrirDialogImpresionFormulario(Integer codCatastral) throws NewviExcepcion {
        this.predio = catastroServicio.seleccionarPredio(codCatastral);
        WebUtils.obtenerContextoPeticion().execute("PF('wgSeleccionFormulario').show()");
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

    public DefaultStreamedContent imprimir(int tipoReporte) {
        return generarReportes(tipoReporte);
    }

    protected DefaultStreamedContent generarReportes(int tipoReporte) {
        try {
            String archivo = "Tabla Catastral Urbana.";
            DefaultStreamedContent dscXlsPa;
            List<PresentacionFichaCatastralDto> tablita = new ArrayList<>();
            List datosImpresion;
            Class claseImpresion = TablaCatastralDto.class;
            tablita.add(new PresentacionFichaCatastralDto(this.predio));
            datosImpresion = tablita;
            Map<String, Object> parametrosReporte = new HashMap<>();
            String formatoTabla = "";
            claseImpresion = PresentacionFichaCatastralDto.class;
            if (tipoReporte == 0) {
                archivo = "Notificacion Avalúo.";
                formatoTabla = "/opt/notificacionAvaluo.jasper";
            }
            if (tipoReporte == 1) {
                archivo = "Certificación Avalúo.";
                formatoTabla = "/opt/certificacionAvaluo.jasper";

            }
            if (tipoReporte == 2) {
                archivo = "Titulo Crédito.";
                formatoTabla = "/opt/tituloCredito.jasper";
            }
            Map<String, Class> paramRepA = new HashMap<String, Class>();
            paramRepA.put("tablaCatastral", claseImpresion);
            paramRepA.put("reporTablaCatastral", List.class);
            ConfiguracionReporte reporte = new ConfiguracionReporte(ReporteGenerador.FormatoReporte.PDF   , datosImpresion, paramRepA, formatoTabla, "/reporTablaCatastral//tablaCatastral", parametrosReporte);
            if (ComunUtil.esNulo(reporte)) {
                return null;
            }
            InputStream streamPa = new ByteArrayInputStream((byte[]) reporte.getDatos());
            dscXlsPa = new DefaultStreamedContent(streamPa, reporte.getMimeType().name(), archivo + reporte.getArchivoExtension());
            streamPa.reset();
            streamPa.close();
            return dscXlsPa;
        } catch (IOException ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            return null;
        }
        return null;
    }

}
