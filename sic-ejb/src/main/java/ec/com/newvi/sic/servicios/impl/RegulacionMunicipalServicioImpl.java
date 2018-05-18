/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.RegulacionMunicipalFacade;
import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.RegulacionMunicipal;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.servicios.RegulacionMunicipalServicio;
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
public class RegulacionMunicipalServicioImpl implements RegulacionMunicipalServicio {

    @EJB
    private ReporteFacade reporteFacade;
    @EJB
    private RegulacionMunicipalFacade irmFacade;

    @Override
    public String generarNuevoIRM(RegulacionMunicipal nuevoIRM, SesionDto sesion) throws NewviExcepcion {

        Date fechaIngreso = Calendar.getInstance().getTime();

        //Registramos la auditoria de ingreso
        nuevoIRM.setAudIngIp(sesion.getDireccionIP());
        nuevoIRM.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevoIRM.setAudIngFec(fechaIngreso);

        irmFacade.create(nuevoIRM);
        // Si todo marcha bien enviar nombre de usuario
        return nuevoIRM.getCodigoInforme();
    }

    @Override
    public String actualizarIRM(RegulacionMunicipal irm, SesionDto sesion) throws NewviExcepcion {

        //Registramos la auditoria de modificacion
        irm.setAudModIp(sesion.getDireccionIP());
        irm.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        irm.setAudModFec(fechaModificacion);

        irmFacade.edit(irm);
        // Si todo marcha bien enviar nombre de usuario
        return irm.getCodigoInforme();
    }

    @Override
    public List<RegulacionMunicipal> obtenerListaIRM(SesionDto sesion) throws NewviExcepcion {
        return irmFacade.buscarIRM();
    }


    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    @Override
    public Reporte obtenerReporte(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
        return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }

    @Override
    public RegulacionMunicipal seleccionarRegulacion(Integer codIrm) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codIrm)) {
            return irmFacade.find(codIrm);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
}
