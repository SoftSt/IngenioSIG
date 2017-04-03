/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.DominiosFacade;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.servicios.ParametrosServicio;
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
 * @author NEWVI
 */
@Stateless
@PermitAll
public class ParametrosServicioImpl implements ParametrosServicio{
    @EJB
    private DominiosFacade dominiosFacade;
    
    /*------------------------------------------------------------Dominios------------------------------------------------------------*/
    @Override
    public String generaNuevoDominio(Dominios nuevoDominio, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando dominio...", sesion);
        if (!nuevoDominio.esDominioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR322);
        }
        // Crear el dominio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando dominio...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevoDominio.setAudIngIp("");
        nuevoDominio.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoDominio.setAudIngFec(fechaIngreso);
        
        dominiosFacade.create(nuevoDominio);
        // Si todo marcha bien enviar codigo del dominio
        return nuevoDominio.getDomiCodigo();
    }

    @Override
    public String actualizarDominio (Dominios dominio, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando dominio...", sesion);
        if (!dominio.esDominioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR322);
        }
        // Acturlizar el dominio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando dominio...", sesion);
        
        //Registramos la auditoria de modificacion
        dominio.setAudModIp("");
        dominio.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        dominio.setAudModFec(fechaModificacion);
        
        dominiosFacade.edit(dominio);
        // Si todo marcha bien enviar codigo del dominio
        return dominio.getDomiCodigo();
    }

    @Override
    public List<Dominios> consultarDominios(){
        return dominiosFacade.buscarDominios();
    }

    @Override
    public Dominios seleccionarDominio (Integer idDominio) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idDominio)) {
            return dominiosFacade.find(idDominio);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
    
    @Override
    public String eliminarDominio (Dominios dominio, SesionDto sesion) throws NewviExcepcion {
        dominio.setDomiEstado(EnumEstadoRegistro.E);
        return actualizarDominio(dominio, sesion);
    }
    
    @Override
    public List<Dominios> consultarGruposDominios(){
        return dominiosFacade.buscarDominiosGrupos();
    }
}
