/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dao.ZonificacionFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.Zonificacion;
import ec.com.newvi.sic.servicios.ZonificacionServicio;
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
public class ZonificacionServicioImpl implements ZonificacionServicio {

    @EJB
    private ReporteFacade reporteFacade;
    
     @EJB
    private ZonificacionFacade zonificacionFacade;

    @Override
    public String generarNuevaZonificacion(Zonificacion nuevaZonificacion, SesionDto sesion) throws NewviExcepcion {
        Date fechaIngreso = Calendar.getInstance().getTime();
        //Registramos la auditoria de ingreso
        nuevaZonificacion.setAudIngIp(sesion.getDireccionIP());
        nuevaZonificacion.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevaZonificacion.setAudIngFec(fechaIngreso);

        zonificacionFacade.create(nuevaZonificacion);
        // Si todo marcha bien enviar nombre de usuario
        return nuevaZonificacion.getZnfNombre();
    }

    @Override
    public String actualizarZonificacion(Zonificacion zonificacion, SesionDto sesion) throws NewviExcepcion {
        //Registramos la auditoria de modificacion
        zonificacion.setAudModIp(sesion.getDireccionIP());
        zonificacion.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        zonificacion.setAudModFec(fechaModificacion);

        zonificacionFacade.edit(zonificacion);
        // Si todo marcha bien enviar nombre de usuario
        return zonificacion.getZnfNombre();
    }

    @Override
    public List<Zonificacion> obtenerListaZonificacion(SesionDto sesion) throws NewviExcepcion {
       return zonificacionFacade.buscarZonificacion();
    }

    @Override
    public Reporte obtenerReporteZonificacion(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
         return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }
    
    @Override
    public Zonificacion seleccionarZonificacion(Integer codZona) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codZona)) {
            return zonificacionFacade.find(codZona);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
    @Override
    public List<Zonificacion> cargarZonificaciones(){
        return zonificacionFacade.buscarZonificacion();
    }
}
