/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ContribuyentesFacade;
import ec.com.newvi.sic.dao.PropiedadFacade;
import ec.com.newvi.sic.dao.TenenciaFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.modelo.Tenencia;
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
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll

public class ContribuyentesServicioImpl implements ContribuyentesServicio {

    @EJB
    private ContribuyentesFacade contribuyentesFacade;
    @EJB
    private PropiedadFacade propiedadFacade;
    @EJB
    private TenenciaFacade tenenciaFacade;

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

    /*------------------------------------------------------------Propiedad------------------------------------------------------------*/
    @Override
    public String generarNuevoPropiedad(Propiedad nuevoPropiedad, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando propiedad...", sesion);
        if (!nuevoPropiedad.esPropiedadValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR347);
        }
        // Crear el propiedad
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando propiedad...", sesion);

        //Registramos la auditoria de ingreso
        nuevoPropiedad.setAudIngIp(sesion.getDireccionIP());
        nuevoPropiedad.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoPropiedad.setAudIngFec(fechaIngreso);

        propiedadFacade.create(nuevoPropiedad);
        // Si todo marcha bien enviar nombre del propiedad
        return nuevoPropiedad.getCodPropiedad().toString();

    }

    @Override
    public String actualizarPropiedad(Propiedad propiedad, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando propiedad...", sesion);
        if (!propiedad.esPropiedadValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR347);
        }
        // Editar la propiedad
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando propiedad...", sesion);

        //Registramos la auditoria de modificacion
        propiedad.setAudModIp(sesion.getDireccionIP());
        propiedad.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        propiedad.setAudModFec(fechaModificacion);

        propiedadFacade.edit(propiedad);
        // Si todo marcha bien enviar nombre del Propiedad
        return propiedad.getCodPropiedad().toString();
    }

    @Override
    public List<Propiedad> consultarPropiedad() {
        return propiedadFacade.buscarPropiedad();
    }

    @Override
    public Propiedad seleccionarPropiedad(Integer idPropiedad) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idPropiedad)) {
            return propiedadFacade.find(idPropiedad);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarPropiedad(Propiedad propiedad, SesionDto sesion) throws NewviExcepcion {
        propiedad.setProEstado(EnumEstadoRegistro.E);
        return actualizarPropiedad(propiedad, sesion);
    }

    @Override
    public Propiedad consultarUltimoPropiedad(Predios predio) throws NewviExcepcion {
        try {
            for (Propiedad propiedad : predio.getHistoricoPropiedad()) {
                if (EnumEstadoRegistro.A.equals(propiedad.getProEstado())
                        && !ComunUtil.esNulo(propiedad.getPropiedad())) {
                    return propiedad;
                }
            }
            throw new NewviExcepcion(EnumNewviExcepciones.ERR501);
        } catch (EntityNotFoundException e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR501, e);
        }
    }

    @Override
    public String generarNuevaTenencia(Tenencia nuevaTenencia, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando tenencia...", sesion);
        if (!nuevaTenencia.esTenenciaValida()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR348);
        }
        // Crear el tenencia
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando tenencia...", sesion);

        //Registramos la auditoria de ingreso
        nuevaTenencia.setAudIngIp(sesion.getDireccionIP());
        nuevaTenencia.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevaTenencia.setAudIngFec(fechaIngreso);

        tenenciaFacade.create(nuevaTenencia);
        // Si todo marcha bien enviar nombre del tenencia
        return nuevaTenencia.getStsDescripcion();
    }

    @Override
    public String actualizarTenencia(Tenencia tenencia, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando tenencia...", sesion);
        if (!tenencia.esTenenciaValida()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR347);
        }
        // Editar la tenencia
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando tenencia...", sesion);

        //Registramos la auditoria de modificacion
        tenencia.setAudModIp(sesion.getDireccionIP());
        tenencia.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        tenencia.setAudModFec(fechaModificacion);

        tenenciaFacade.edit(tenencia);
        // Si todo marcha bien enviar nombre del tenencia
        return tenencia.getStsDescripcion();
    }
    
    @Override
    public Tenencia seleccionarTenencia(Integer codTenencia) throws NewviExcepcion{
        if (ComunUtil.esNumeroPositivo(codTenencia)) {
            return tenenciaFacade.find(codTenencia);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
}
