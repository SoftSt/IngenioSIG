/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dao.SimbologiaFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.Simbologia;
import ec.com.newvi.sic.servicios.SimbologiaServicio;
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
public class SimbologiaServicioImpl implements SimbologiaServicio {

    @EJB
    private ReporteFacade reporteFacade;
    
     @EJB
    private SimbologiaFacade simbologiaFacade;

    @Override
    public String generarNuevaSimbologia(Simbologia nuevoSimbologia, SesionDto sesion) throws NewviExcepcion {
        Date fechaIngreso = Calendar.getInstance().getTime();
        //Registramos la auditoria de ingreso
        nuevoSimbologia.setAudIngIp(sesion.getDireccionIP());
        nuevoSimbologia.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevoSimbologia.setAudIngFec(fechaIngreso);

        simbologiaFacade.create(nuevoSimbologia);
        // Si todo marcha bien enviar nombre de usuario
        return nuevoSimbologia.getSmbNombre();
    }

    @Override
    public String actualizarSimbologia(Simbologia simbologia, SesionDto sesion) throws NewviExcepcion {
        //Registramos la auditoria de modificacion
        simbologia.setAudModIp(sesion.getDireccionIP());
        simbologia.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        simbologia.setAudModFec(fechaModificacion);

        simbologiaFacade.edit(simbologia);
        // Si todo marcha bien enviar nombre de usuario
        return simbologia.getSmbNombre();
    }

    @Override
    public List<Simbologia> obtenerListaSimbologia(SesionDto sesion) throws NewviExcepcion {
       return simbologiaFacade.buscarSimbologia();
    }

    @Override
    public Reporte obtenerReporteSimbologia(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
         return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }
    
    @Override
    public Simbologia seleccionarSimbologia(Integer codSimbologia) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codSimbologia)) {
            return simbologiaFacade.find(codSimbologia);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
   
}
