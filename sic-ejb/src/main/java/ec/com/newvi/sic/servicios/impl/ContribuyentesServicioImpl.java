/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ContribuyentesFacade;
import ec.com.newvi.sic.dao.PropietarioFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Propietario;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.com.newvi.sic.servicios.ContribuyentesServicio;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll

public class ContribuyentesServicioImpl implements ContribuyentesServicio{
    @EJB
    private ContribuyentesFacade contribuyentesFacade;
    @EJB
    private PropietarioFacade propietarioFacade;
    
       
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
    /*------------------------------------------------------------Propietario------------------------------------------------------------*/
    
    @Override
    public String generarNuevoPropietario(Propietario nuevoPropietario, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando propietario...", sesion);
        if (!nuevoPropietario.esPropietarioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR329);
        }
        // Crear el propietario
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando propietario...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevoPropietario.setAudIngIp(sesion.getDireccionIP());
        nuevoPropietario.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoPropietario.setAudIngFec(fechaIngreso);
        
        propietarioFacade.create(nuevoPropietario);
        // Si todo marcha bien enviar nombre del propietario
        return nuevoPropietario.getPropietario().toString();
        
    }

    @Override
    public String actualizarPropietario(Propietario propietario, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando propietario...", sesion);
        if (!propietario.esPropietarioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR329);
        }
        // Editar la propietario
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando propietario...", sesion);
        
        //Registramos la auditoria de modificacion
        propietario.setAudModIp(sesion.getDireccionIP());
        propietario.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        propietario.setAudModFec(fechaModificacion);
        
        propietarioFacade.edit(propietario);
        // Si todo marcha bien enviar nombre del Propietario
        return propietario.getPropietario().toString();
    }

    @Override
    public List<Propietario> consultarPropietario() {
        return propietarioFacade.buscarPropietarios();
    }

    @Override
    public Propietario seleccionarPropietario(Integer idPropietario) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idPropietario)) {
            return propietarioFacade.find(idPropietario);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarPropietario(Propietario propietario, SesionDto sesion) throws NewviExcepcion {
        propietario.setProEstado(EnumEstadoRegistro.E);
        return actualizarPropietario(propietario, sesion);
    }
}
