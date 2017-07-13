/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class AvaluoBB extends AdminAvaluo{
    private EnumPantallaMantenimiento pantallaActual;
    
    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.BLOQUES_LISTA_TITULO,
                EnumEtiquetas.BLOQUES_LISTA_ICONO,
                EnumEtiquetas.BLOQUES_LISTA_DESCRIPCION);
    }
    
    
    public void generarSimulacion() throws NewviExcepcion {
        List<Predios> listaPredios = new ArrayList<>();
        FechaAvaluo fechaAvaluo = new FechaAvaluo();
        Avaluo avaluo = new Avaluo();
        
        Date fecha = Calendar.getInstance().getTime();
        fechaAvaluo.setFecavFechaavaluo(fecha);
        fechaAvaluo.setFecavEstado(EnumEstadoRegistro.A);
        
        FechaAvaluo fecavId = catastroServicio.generarNuevaFechaAvaluo(fechaAvaluo, sesionBean.obtenerSesionDto());
        
        listaPredios = catastroServicio.consultarPredios();
        
        for (Predios predio : listaPredios) {
            catastroServicio.obtenerAvaluoPredio(predio, sesionBean.obtenerSesionDto());
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
            avaluo.setFecavId(fecavId);
            
            catastroServicio.generarNuevoAvaluo(avaluo, sesionBean.obtenerSesionDto());
        }
        
    }
    
    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }
}
