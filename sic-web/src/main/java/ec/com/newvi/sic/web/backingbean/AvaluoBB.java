/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

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
    private List<FechaAvaluo> listaFechaAvaluos;

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
    
    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.SIMULACION_LISTA_TITULO,
                EnumEtiquetas.SIMULACION_LISTA_ICONO,
                EnumEtiquetas.SIMULACION_LISTA_DESCRIPCION);
        this.skip = false;
        actualizarListadoFechaAvaluos();
    }

    public void generarSimulacion() throws NewviExcepcion {
        List<Predios> listaPredios = new ArrayList<>();
        FechaAvaluo fechaAvaluo = new FechaAvaluo();
        Avaluo avaluo;
        int cont = 0;

        Date fecha = Calendar.getInstance().getTime();
        fechaAvaluo.setFecavFechaavaluo(fecha);
        fechaAvaluo.setFecavEstado(EnumEstadoRegistro.A);

        FechaAvaluo fecavId = catastroServicio.generarNuevaFechaAvaluo(fechaAvaluo, sesionBean.obtenerSesionDto());

        listaPredios = catastroServicio.consultarPredios();

        for (Predios predio : listaPredios) {
            List<AvaluoDto> calculoAvaluo = catastroServicio.obtenerAvaluoPredio(predio, sesionBean.obtenerSesionDto());
            avaluo = new Avaluo();
            if (!(calculoAvaluo == null)) {
                avaluo.setCodCatastral(predio);
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
                avaluo.setTxtDireccion(predio.getTxtDireccion());
                avaluo.setStsBarrio(predio.getStsBarrio());
            }
            avaluo.setFecavId(fecavId);
            avaluo.setAvalEstado(EnumEstadoRegistro.A);
            catastroServicio.generarNuevoAvaluo(avaluo, sesionBean.obtenerSesionDto());

            //LoggerNewvi.getLogNewvi(this.getClass()).debug(cont++, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(cont++, sesionBean.obtenerSesionDto());

        }

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
    
    /*public void actualizarListadoAvaluo(Date fecavFechaavaluo){
        listaAvaluos = catastroServicio.consultarAvaluos(fecavFechaavaluo);
    }*/
    public void actualizarListadoFechaAvaluos(){
        listaFechaAvaluos = catastroServicio.consultarFechaAvaluos();
    }
}
