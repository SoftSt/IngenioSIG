/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.PersoneriaFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.com.newvi.sic.servicios.PersoneriaServicio;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll

public class PersoneriaServicioImpl implements PersoneriaServicio{
    @EJB
    private PersoneriaFacade contribuyentesFacade;
    
       
    /*------------------------------------------------------------Contribuyentes------------------------------------------------------------*/
    
    @Override
    public String generarNuevoContribuyente(Contribuyentes nuevoContribuyente, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando contribuyente...", sesion);
        if (!nuevoContribuyente.esContribuyenteValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR329);
        }
        // Crear el contribuyente
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando contribuyente...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevoContribuyente.setAudIngIp(sesion.getDireccionIP());
        nuevoContribuyente.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoContribuyente.setAudIngFec(fechaIngreso);
        
        contribuyentesFacade.create(nuevoContribuyente);
        // Si todo marcha bien enviar nombre del contribuyente
        return nuevoContribuyente.getNomNombres();
        
    }

    @Override
    public String actualizarContribuyente(Contribuyentes contribuyente, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando contribuyente...", sesion);
        if (!contribuyente.esContribuyenteValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR329);
        }
        // Editar la contribuyente
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando contribuyente...", sesion);
        
        //Registramos la auditoria de modificacion
        contribuyente.setAudModIp(sesion.getDireccionIP());
        contribuyente.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        contribuyente.setAudModFec(fechaModificacion);
        
        contribuyentesFacade.edit(contribuyente);
        // Si todo marcha bien enviar nombre del Contribuyente
        return contribuyente.getNomNombres();
    }

    @Override
    public List<Contribuyentes> consultarContribuyentes() {
        return contribuyentesFacade.buscarContribuyentes();
    }

    @Override
    public Contribuyentes seleccionarContribuyente(Integer idContribuyente) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idContribuyente)) {
            return contribuyentesFacade.find(idContribuyente);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarContribuyente(Contribuyentes contribuyente, SesionDto sesion) throws NewviExcepcion {
        contribuyente.setStsPersoneria(EnumEstadoRegistro.E);
        return actualizarContribuyente(contribuyente, sesion);
    }
}
