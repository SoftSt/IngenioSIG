/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ContribucionMejorasFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.ContribucionMejoras;
import ec.com.newvi.sic.servicios.ContribucionMejorasServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll
public class ContribucionMejorasServicioImpl implements ContribucionMejorasServicio{
    @EJB
    private ContribucionMejorasFacade  contribucionMejorasFacade;
    
        /*------------------------------------------------------------Contribuyentes------------------------------------------------------------*/
    
    @Override
    public String generarNuevaContribucionMejoras(ContribucionMejoras nuevaContribucionMejoras, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando contribucionMejoras...", sesion);
        if (!nuevaContribucionMejoras.esContribucionValida()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR336);
        }
        // Crear el contribucionMejoras
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando contribucionMejoras...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevaContribucionMejoras.setAudIngIp(sesion.getDireccionIP());
        nuevaContribucionMejoras.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevaContribucionMejoras.setAudIngFec(fechaIngreso);
        
        contribucionMejorasFacade.create(nuevaContribucionMejoras);
        // Si todo marcha bien enviar nombre del contribucionMejoras
        return nuevaContribucionMejoras.getNomObras();
        
    }

    @Override
    public String actualizarContribucionMejoras(ContribucionMejoras contribucionMejoras, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando contribucionMejoras...", sesion);
        if (!contribucionMejoras.esContribucionValida()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR336);
        }
        // Editar la contribucionMejoras
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando contribucionMejoras...", sesion);
        
        //Registramos la auditoria de modificacion
        contribucionMejoras.setAudModIp(sesion.getDireccionIP());
        contribucionMejoras.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        contribucionMejoras.setAudModFec(fechaModificacion);
        
        contribucionMejorasFacade.edit(contribucionMejoras);
        // Si todo marcha bien enviar nombre de la contribucionMejoras
        return contribucionMejoras.getNomObras();
    }

    @Override
    public List<ContribucionMejoras> consultarContribucionMejoras() {
        return contribucionMejorasFacade.buscarContribucionMejoras();
    }

    @Override
    public ContribucionMejoras seleccionarContribucionMejoras(Integer idContribucionMejoras) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idContribucionMejoras)) {
            return contribucionMejorasFacade.find(idContribucionMejoras);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarContribucionMejoras(ContribucionMejoras contribucionMejoras, SesionDto sesion) throws NewviExcepcion {
        contribucionMejoras.setObrEstado(EnumEstadoRegistro.E);
        return actualizarContribucionMejoras(contribucionMejoras, sesion);
    }
    
}
