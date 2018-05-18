/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dao.UsoSueloFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.UsoSuelo;
import ec.com.newvi.sic.servicios.UsoSueloServicio;
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
public class UsoSueloServicioImpl implements UsoSueloServicio {

    @EJB
    private ReporteFacade reporteFacade;
    
     @EJB
    private UsoSueloFacade usoSueloFacade;

    @Override
    public String generarNuevoUsoSuelo(UsoSuelo nuevoUsoSuelo, SesionDto sesion) throws NewviExcepcion {
        Date fechaIngreso = Calendar.getInstance().getTime();
        //Registramos la auditoria de ingreso
        nuevoUsoSuelo.setAudIngIp(sesion.getDireccionIP());
        nuevoUsoSuelo.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevoUsoSuelo.setAudIngFec(fechaIngreso);

        usoSueloFacade.create(nuevoUsoSuelo);
        // Si todo marcha bien enviar nombre de usuario
        return nuevoUsoSuelo.getUssNombre();
    }

    @Override
    public String actualizarUsoSuelo(UsoSuelo usoSuelo, SesionDto sesion) throws NewviExcepcion {
        //Registramos la auditoria de modificacion
        usoSuelo.setAudModIp(sesion.getDireccionIP());
        usoSuelo.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        usoSuelo.setAudModFec(fechaModificacion);

        usoSueloFacade.edit(usoSuelo);
        // Si todo marcha bien enviar nombre de usuario
        return usoSuelo.getUssNombre();
    }

    @Override
    public List<UsoSuelo> obtenerListaUsoSuelo(SesionDto sesion) throws NewviExcepcion {
       return usoSueloFacade.buscarUsoSuelo();
    }

    @Override
    public Reporte obtenerReporteUsoSuelo(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
         return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }
    
    @Override
    public UsoSuelo seleccionarUsoSuelo(Integer codUsoSUelo) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codUsoSUelo)) {
            return usoSueloFacade.find(codUsoSUelo);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
   @Override
    public List<UsoSuelo> cargarUsosSuelo(){
        return usoSueloFacade.buscarUsoSuelo();
    }
}
