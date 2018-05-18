/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dao.FirmaReporteFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.FirmaReporte;
import ec.com.newvi.sic.servicios.FirmaReporteServicio;
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
public class FirmaReporteServicioImpl implements FirmaReporteServicio {

    @EJB
    private ReporteFacade reporteFacade;
    
     @EJB
    private FirmaReporteFacade firmaReporteFacade;

    @Override
    public String generarNuevaFirmaReporte(FirmaReporte nuevaFirmaReporte, SesionDto sesion) throws NewviExcepcion {
        Date fechaIngreso = Calendar.getInstance().getTime();
        //Registramos la auditoria de ingreso
        nuevaFirmaReporte.setAudIngIp(sesion.getDireccionIP());
        nuevaFirmaReporte.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevaFirmaReporte.setAudIngFec(fechaIngreso);

        firmaReporteFacade.create(nuevaFirmaReporte);
        // Si todo marcha bien enviar nombre de usuario
        return nuevaFirmaReporte.getFrrNombre();
    }

    @Override
    public String actualizarFirmaReporte(FirmaReporte firmaReporte, SesionDto sesion) throws NewviExcepcion {
        //Registramos la auditoria de modificacion
        firmaReporte.setAudModIp(sesion.getDireccionIP());
        firmaReporte.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        firmaReporte.setAudModFec(fechaModificacion);

        firmaReporteFacade.edit(firmaReporte);
        // Si todo marcha bien enviar nombre de usuario
        return firmaReporte.getFrrNombre();
    }

    @Override
    public List<FirmaReporte> obtenerListaFirmaReporte(SesionDto sesion) throws NewviExcepcion {
       return firmaReporteFacade.buscarFirmaReporte();
    }

    @Override
    public Reporte obtenerReporteFirma(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
         return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }
    
    @Override
    public FirmaReporte seleccionarFirmaReporte(Integer codFirmaReporte) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codFirmaReporte)) {
            return firmaReporteFacade.find(codFirmaReporte);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
   
}
