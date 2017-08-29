/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.Reporte;
import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.FechaAvaluo;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class AvaluoBB extends AdminAvaluo {

    private EnumPantallaMantenimiento pantallaActual;
    private Boolean skip;
    private List<Avaluo> listaAvaluos;
    private List<Avaluo> listaAvaluosFiltrados;
    private List<FechaAvaluo> listaFechaAvaluos;
    private Integer progreso;
    private FechaAvaluo fechaAvaluoActual;
    private String fechaActualPrueba;

    public String getFechaActualPrueba() {
        return fechaActualPrueba;
    }

    public void setFechaActualPrueba(String fechaActualPrueba) {
        this.fechaActualPrueba = fechaActualPrueba;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }

    public List<FechaAvaluo> getListaFechaAvaluos() {
        return listaFechaAvaluos;
    }

    public void setListaFechaAvaluos(List<FechaAvaluo> listaFechaAvaluos) {
        this.listaFechaAvaluos = listaFechaAvaluos;
    }

    public List<Avaluo> getListaAvaluos() {
        return listaAvaluos;
    }

    public void setListaAvaluos(List<Avaluo> listaAvaluos) {
        this.listaAvaluos = listaAvaluos;
    }

    public FechaAvaluo getFechaAvaluoActual() {
        return fechaAvaluoActual;
    }

    public void setFechaAvaluoActual(FechaAvaluo fechaAvaluoActual) {
        this.fechaAvaluoActual = fechaAvaluoActual;
    }

    public List<Avaluo> getListaAvaluosFiltrados() {
        return listaAvaluosFiltrados;
    }

    public void setListaAvaluosFiltrados(List<Avaluo> listaAvaluosFiltrados) {
        this.listaAvaluosFiltrados = listaAvaluosFiltrados;
    }

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    @PostConstruct
    public void init() {
        this.progreso = 0;
        fechaAvaluoActual = new FechaAvaluo();
        listaAvaluos = new ArrayList<>();
        listaFechaAvaluos = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.SIMULACION_LISTA_TITULO,
                EnumEtiquetas.SIMULACION_LISTA_ICONO,
                EnumEtiquetas.SIMULACION_LISTA_DESCRIPCION);
        this.skip = false;
        actualizarListadoFechaAvaluos();
        //actualizarListadoAvaluos();
    }

    public FechaAvaluo generarFechaAvaluo() throws NewviExcepcion {
        FechaAvaluo fechaAvaluo = new FechaAvaluo();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = Calendar.getInstance().getTime();

        fechaAvaluo.setFecavFechaavaluo(fecha);
        fechaAvaluo.setFecavEstado(EnumEstadoRegistro.A);
        fechaAvaluo.setFechaDescripcion(formato.format(fecha));

        return catastroServicio.generarNuevaFechaAvaluo(fechaAvaluo, sesionBean.obtenerSesionDto());
    }

    public List<FichaCatastralDto> generarListaFichaCatastral() {
        List<Predios> listaPredios;
        List<FichaCatastralDto> listaFichas = new ArrayList<>();

        listaPredios = catastroServicio.consultarPredios();

        for (Predios elementoPredio : listaPredios) {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        }

        return listaFichas;
    }

    public void abrirModalEspera() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgSimulacion').show()");
        //generarSimulacion();
    }

    public void generarSimulacion() throws NewviExcepcion {
        //WebUtils.obtenerContextoPeticion().execute("PF('dlgSimulacion').show()");
        //PF('pbAjax').start();PF('calcularSimulacion').disable();

        Avaluo avaluo;
        int cont = 0;

        FechaAvaluo fecavId = generarFechaAvaluo();
        List<FichaCatastralDto> listaFichas = generarListaFichaCatastral();

        for (FichaCatastralDto fichaDto : listaFichas) {
            Predios predio = fichaDto.getPredio();
            Contribuyentes contribuyente = fichaDto.getContribuyentePropiedad();
            List<AvaluoDto> calculoAvaluo = catastroServicio.obtenerAvaluoPredio(predio, sesionBean.obtenerSesionDto());
            avaluo = new Avaluo();
            if (!(calculoAvaluo == null)) {
                avaluo.setValTerreno(predio.getValTerreno());
                avaluo.setValPredio(predio.getValPredio());
                avaluo.setValImppredial(predio.getValImppredial());
                avaluo.setValEmision(predio.getValEmision());
                avaluo.setValEdifica(predio.getValEdifica());
                avaluo.setValCem(predio.getValCem());
                avaluo.setValBomberos(predio.getValBomberos());
                avaluo.setValBasura(predio.getValBasura());
                avaluo.setValAreapredio(predio.getValAreaPredio());
                avaluo.setValAreaconstruccion(predio.getValAreaConstruccion());
                avaluo.setValAmbientales(predio.getValAmbientales());
                avaluo.setValImpuesto(predio.getValImpuesto());
            }
            avaluo.setCodCatastral(predio);
            avaluo.setNomCodigocatastral(predio.getNomCodigocatastral());
            avaluo.setTxtDireccion(predio.getTxtDireccion());
            avaluo.setStsBarrio(predio.getStsBarrio());
            avaluo.setCodCedularuc(contribuyente.getCodCedularuc());
            avaluo.setNomnomape(contribuyente.getNomNombres().trim() + " " + contribuyente.getNomApellidos().trim());
            avaluo.setFecavId(fecavId);
            avaluo.setAvalEstado(EnumEstadoRegistro.A);
            catastroServicio.generarNuevoAvaluo(avaluo, sesionBean.obtenerSesionDto());

            //LoggerNewvi.getLogNewvi(this.getClass()).debug(cont++, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(cont++, sesionBean.obtenerSesionDto());
            /*if (this.progreso <= 100) {
                if (cont++ == listaFichas.size() / 100) {
                    progreso++;
                }
            } else {
                this.progreso = 100;
            }*/

        }

        actualizarListadoAvaluos();

    }

    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }

    public Boolean esPantallaActual(String pantallaEsperada) {
        try {
            return this.pantallaActual.equals(EnumPantallaMantenimiento.obtenerPantallaPorNombre(pantallaEsperada));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void actualizarListadoFechaAvaluos() {
        listaFechaAvaluos = catastroServicio.consultarFechaAvaluos();
    }

    public void actualizarListadoAvaluos() {
        listaAvaluos = catastroServicio.consultarListaAvaluosActuales();
    }

    public void actualizarListaAvaluosPorFecha(String fechaDescripcion) {
        //DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //listaAvaluos = catastroServicio.consultarListaAvaluosPorFecha(formato.parse(fechaDescripcion));
            listaAvaluos = catastroServicio.consultarListaAvaluosPorFecha(fechaDescripcion);
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR001.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
    }

        public DefaultStreamedContent imprimir(int tipoReporte) {
        return generarReportes(tipoReporte);
    }

    protected DefaultStreamedContent generarReportes(int tipoReporte) {
        try {
            String archivo = "Tabla Catastral Urbana.";
            DefaultStreamedContent dscXlsPa;
            List datosImpresion;
            Class claseImpresion = TablaCatastralDto.class;
            Map<String, Object> parametrosReporte = new HashMap<>();
            datosImpresion = obtenerListadoAvaluos(listaAvaluos);
            String formatoTabla = "/opt/tablaCatastralUrbana.jasper";
            if (tipoReporte == 0) {
                archivo = "Tabla Catastral Urbana Condensada.";
                formatoTabla = "/opt/newReport.jasper";
                parametrosReporte.put("TITULO_REPORTE", "REPORTECITO");
            }
            Map<String, Class> paramRepA = new HashMap<String, Class>();
            paramRepA.put("tablaCatastral", claseImpresion);
            paramRepA.put("reporTablaCatastral", List.class);
            Reporte reporte = new Reporte(ReporteGenerador.FormatoReporte.PDF, datosImpresion, paramRepA, formatoTabla, "/reporTablaCatastral//tablaCatastral", parametrosReporte);
            if (ComunUtil.esNulo(reporte)) {
                return null;
            }
            InputStream streamPa = new ByteArrayInputStream((byte[]) reporte.getDatos());
            dscXlsPa = new DefaultStreamedContent(streamPa, reporte.getMimeType().name(), archivo + reporte.getArchivoExtension());
            streamPa.reset();
            streamPa.close();
            return dscXlsPa;
        } catch (IOException ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            return null;
        }
        return null;
    }


    public List<TablaCatastralDto> obtenerListadoAvaluos(List<Avaluo> listaAvaluos) {
        List<TablaCatastralDto> datosImpresion = new ArrayList<>();
        TablaCatastralDto datosAvaluo;
        for (Avaluo avaluo : listaAvaluos) {
            datosAvaluo = new TablaCatastralDto();
            datosAvaluo.setCodigoCatastral(avaluo.getCodCatastral().getCodCatastral().toString());
            datosAvaluo.setNombreCodigoCatastral(avaluo.getNomCodigocatastral());
            datosAvaluo.setPropietario(avaluo.getNomnomape());
            datosAvaluo.setCiRuc(avaluo.getCodCedularuc());
            datosAvaluo.setBarrio(avaluo.getStsBarrio());
            datosAvaluo.setDireccion(avaluo.getTxtDireccion());
            datosAvaluo.setAvaluoTerreno(avaluo.getValTerreno());
            datosAvaluo.setAreaTerreno(avaluo.getValTerreno());
            datosAvaluo.setAreaEdificacion(avaluo.getValAreaconstruccion());
            datosAvaluo.setAvaluoEdificacion(avaluo.getValEdifica());
            datosAvaluo.setAvaluoPredio(avaluo.getValPredio());
            datosAvaluo.setAreaPredio(avaluo.getValAreapredio());
            datosAvaluo.setImpuestoPredial(avaluo.getValImpuesto());
            datosAvaluo.setContribucionEspecialMejoras(avaluo.getValCem());
            datosAvaluo.setTasaRecoleccionBasura(avaluo.getValBasura());
            datosAvaluo.setCostoEmision(avaluo.getValEmision());
            datosAvaluo.setTasaBomberos(avaluo.getValBomberos());
            datosAvaluo.setServiciosAmbientales(avaluo.getValAmbientales());
            datosAvaluo.setTotalAPagar(avaluo.getValImppredial());
            datosAvaluo.setObservaciones(avaluo.getCatCasosespeciales());
            datosImpresion.add(datosAvaluo);
        }
        return datosImpresion;
    }


    
    
    
    public List<Avaluo> generarListaAvaluo() {
        return catastroServicio.consultarListaAvaluosActuales();
    }

}
