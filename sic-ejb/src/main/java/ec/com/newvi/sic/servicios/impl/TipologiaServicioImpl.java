/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dao.TipologiaFacade;
import ec.com.newvi.sic.dao.UsoSueloFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.Tipologia;
import ec.com.newvi.sic.modelo.UsoSuelo;
import ec.com.newvi.sic.modelo.Zonificacion;
import ec.com.newvi.sic.servicios.TipologiaServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Rolando Soria by Excor
 */
@Stateless
@PermitAll
public class TipologiaServicioImpl implements TipologiaServicio {

    @EJB
    private ReporteFacade reporteFacade;
    
     @EJB
    private TipologiaFacade tipologiaFacade;
     
      @EJB
    private UsoSueloFacade usoSueloFacade;

    @Override
    public String generarNuevaTipologia(Tipologia nuevaTipologia, SesionDto sesion) throws NewviExcepcion {
        Date fechaIngreso = Calendar.getInstance().getTime();
        //Registramos la auditoria de ingreso
        nuevaTipologia.setAudIngIp(sesion.getDireccionIP());
        nuevaTipologia.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevaTipologia.setAudIngFec(fechaIngreso);

        tipologiaFacade.create(nuevaTipologia);
        // Si todo marcha bien enviar nombre de usuario
        return nuevaTipologia.getTplNombre();
    }

    @Override
    public String actualizarTipologia(Tipologia tipologia, SesionDto sesion) throws NewviExcepcion {
        //Registramos la auditoria de modificacion
        tipologia.setAudModIp(sesion.getDireccionIP());
        tipologia.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        tipologia.setAudModFec(fechaModificacion);

        tipologiaFacade.edit(tipologia);
        // Si todo marcha bien enviar nombre de usuario
        return tipologia.getTplNombre();
    }

    @Override
    public List<Tipologia> obtenerListaTipologia(SesionDto sesion) throws NewviExcepcion {
       return tipologiaFacade.buscarTipologia();
    }

    @Override
    public Reporte obtenerReporteTipologia(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
         return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }
    
    @Override
    public Tipologia seleccionarTipologia(Integer codTipologia) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codTipologia)) {
            return tipologiaFacade.find(codTipologia);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
   @Override
    public List<Tipologia> cargarTipologias(){
        return tipologiaFacade.buscarTipologia();
    }
}
