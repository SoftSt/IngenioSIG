/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dao.CertificacionUsoSueloFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.CertificacionUsoSuelo;
import ec.com.newvi.sic.servicios.CertificacionUsoSueloServicio;
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
public class CertificacionUsoSueloServicioImpl implements CertificacionUsoSueloServicio {

    @EJB
    private ReporteFacade reporteFacade;
    
     @EJB
    private CertificacionUsoSueloFacade certificacionFacade;

    @Override
    public String generarNuevaCertificacion(CertificacionUsoSuelo nuevaCertificacion, SesionDto sesion) throws NewviExcepcion {
        Date fechaIngreso = Calendar.getInstance().getTime();
        //Registramos la auditoria de ingreso
        nuevaCertificacion.setAudIngIp(sesion.getDireccionIP());
        nuevaCertificacion.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevaCertificacion.setAudIngFec(fechaIngreso);

        certificacionFacade.create(nuevaCertificacion);
        // Si todo marcha bien enviar nombre de usuario
        return nuevaCertificacion.getCssNombreLocal();
    }

    @Override
    public String actualizarCertificacion(CertificacionUsoSuelo certificacionUsoSuelo, SesionDto sesion) throws NewviExcepcion {
        //Registramos la auditoria de modificacion
        certificacionUsoSuelo.setAudModIp(sesion.getDireccionIP());
        certificacionUsoSuelo.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        certificacionUsoSuelo.setAudModFec(fechaModificacion);

        certificacionFacade.edit(certificacionUsoSuelo);
        // Si todo marcha bien enviar nombre de usuario
        return certificacionUsoSuelo.getCssNombreLocal();
    }

    @Override
    public List<CertificacionUsoSuelo> obtenerListaCertificaciones(SesionDto sesion) throws NewviExcepcion {
       return certificacionFacade.buscarCertificacion();
    }

    @Override
    public Reporte obtenerReporteCertificacion(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
         return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }
    
    @Override
    public CertificacionUsoSuelo seleccionarCertificacion(Integer codCertificacion) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codCertificacion)) {
            return certificacionFacade.find(codCertificacion);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
    @Override
    public List<CertificacionUsoSuelo> cargarCertificaciones(){
        return certificacionFacade.buscarCertificacion();
    }
}
