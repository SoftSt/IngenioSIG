/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.AsignacionPermisosFacade;
import ec.com.newvi.sic.dao.FuncionalidadesFacade;
import ec.com.newvi.sic.dao.PermisosFacade;
import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.dao.UsuariosFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
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
public class SeguridadesServicioImpl implements SeguridadesServicio {

    @EJB
    private UsuariosFacade usuariosFacade;

    @EJB
    private PermisosFacade permisosFacade;
    
    @EJB
    private FuncionalidadesFacade funcionalidadesFacade;
    
    @EJB
    private AsignacionPermisosFacade asignacionPermisosFacade;
    
    
/*------------------------------------------------------------Usuarios------------------------------------------------------------*/
    @Override
    public String generarNuevoUsuario(Usuarios nuevoUsuario, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando usuario...", sesion);
        if (!nuevoUsuario.esUsuarioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR301);
        }
        // Crear el usuario
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando usuario...", sesion);
        
        Date fechaIngreso = Calendar.getInstance().getTime();
        
        //Registro de campos independientes
        nuevoUsuario.setUsuPalabraclave(nuevoUsuario.getUsuUsuario());
        nuevoUsuario.setUsuResponsable(sesion.getUsuarioRegistrado().getUsuUsuario().trim());
        nuevoUsuario.setUsuFingreso(fechaIngreso);
        
        //Registramos la auditoria de ingreso
        nuevoUsuario.setAudIngIp(sesion.getDireccionIP());
        nuevoUsuario.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());
        
        nuevoUsuario.setAudIngFec(fechaIngreso);
        
        
        usuariosFacade.create(nuevoUsuario);
        // Si todo marcha bien enviar nombre de usuario
        return nuevoUsuario.getUsuNombres();
    }

    @Override
    public String actualizarUsuario(Usuarios usuario, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando usuario...", sesion);
        if (!usuario.esUsuarioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR301);
        }
        // Acturlizar el usuario
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando usuario...", sesion);
        
        //Registro de campos independientes
        usuario.setUsuPalabraclave(usuario.getUsuUsuario());
        
        //Registramos la auditoria de modificacion
        usuario.setAudModIp(sesion.getDireccionIP());
        usuario.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        usuario.setAudModFec(fechaModificacion);
        
        usuariosFacade.edit(usuario);
        // Si todo marcha bien enviar nombre de usuario
        return usuario.getUsuNombres();
    }

    @Override
    public List<Usuarios> consultarUsuarios() {
        return usuariosFacade.buscarUsuarios();
    }

    @Override
    public Usuarios seleccionarUsuario(Integer idUsuario) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idUsuario)) {
            return usuariosFacade.find(idUsuario);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }
    
    @Override
    public String eliminarUsuario(Usuarios usuario, SesionDto sesion) throws NewviExcepcion {
        usuario.setUsuEstado(EnumEstadoRegistro.E);
        return actualizarUsuario(usuario, sesion);
    }

    @Override
    public Usuarios obtenerUsuarioPorNombreUsuario(String nombreUsuario, SesionDto sesion) throws NewviExcepcion {
        return usuariosFacade.buscarUsuariosPorNombreUsuario(nombreUsuario, sesion);
    }
    
    @Override
    public Boolean esUsuarioRepetido(String usuUsuario, Integer idUsuario){
        return usuariosFacade.existeNombreUsuarioRegistrado(usuUsuario, idUsuario);
    }
    
    @Override
    public Boolean esEmailRepetido(String usuEmail){
        
        return !usuariosFacade.buscarUsuarioRepetidoPorEmail(usuEmail);
    }
    
/*------------------------------------------------------------Permisos------------------------------------------------------------*/
    @Override
    public String generarNuevoPermiso(Permisos nuevoPermiso, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando rol...", sesion);
        if (!nuevoPermiso.esPermisoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR306);
        }
        // Crear el rol
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando rol...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevoPermiso.setAudIngresoIp(sesion.getDireccionIP());
        nuevoPermiso.setAudIngresoUsuario(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoPermiso.setAudIngresoFecha(fechaIngreso);
        
        permisosFacade.create(nuevoPermiso);
        // Si todo marcha bien enviar nombre del rol
        return nuevoPermiso.getGrupoPermiso();
        
    }

    @Override
    public String actualizarPermiso(Permisos permiso, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando rol...", sesion);
        if (!permiso.esPermisoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR306);
        }
        // Crear el usuario
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando rol...", sesion);
        
        //Registramos la auditoria de modificacion
        permiso.setAudModificacionIp(sesion.getDireccionIP());
        permiso.setAudModificacionUsuario(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        permiso.setAudModificacionFecha(fechaModificacion);
        
        permisosFacade.edit(permiso);
        // Si todo marcha bien enviar nombre del rol
        return permiso.getGrupoPermiso();
        
    }

    @Override
    public List<Permisos> consultarPermisos() {
        return permisosFacade.buscarPermisos();
    }

    @Override
    public Permisos seleccionarPermiso(Integer idPermiso) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idPermiso)) {
            return permisosFacade.find(idPermiso);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarPermiso(Permisos permiso, SesionDto sesion) throws NewviExcepcion {
        permiso.setEstadoPermiso(EnumEstadoRegistro.E);
        return actualizarPermiso(permiso, sesion);
    }

    
/*------------------------------------------------------------Funcionalidades------------------------------------------------------------*/
    
    @Override
    public String generarNuevaFuncionalidad(Funcionalidades nuevaFuncionalidad, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando funcionalidad...", sesion);
        if (!nuevaFuncionalidad.esFuncionalidadValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR310);
        }
        // Crear el funcionalidad
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando funcionalidad...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevaFuncionalidad.setAudIngIp(sesion.getDireccionIP());
        nuevaFuncionalidad.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevaFuncionalidad.setAudIngFec(fechaIngreso);
        
        funcionalidadesFacade.create(nuevaFuncionalidad);
        // Si todo marcha bien enviar nombre de la funcionalidad
        return nuevaFuncionalidad.getFunNombre();
        
    }

    @Override
    public String actualizarFuncionalidad(Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando funcionalidad...", sesion);
        if (!funcionalidad.esFuncionalidadValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR310);
        }
        // Editar la funcionalidad
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando funcionalidad...", sesion);
        
        //Registramos la auditoria de modificacion
        funcionalidad.setAudModIp(sesion.getDireccionIP());
        funcionalidad.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        funcionalidad.setAudModFec(fechaModificacion);
        
        funcionalidadesFacade.edit(funcionalidad);
        // Si todo marcha bien enviar nombre de la funcionalidad
        return funcionalidad.getFunNombre();
    }

    @Override
    public List<Funcionalidades> consultarFuncionalidades() {
        return funcionalidadesFacade.buscarFuncionalidades();
    }

    @Override
    public List<Funcionalidades> consultarFuncionalidadesPadre() {
        return funcionalidadesFacade.buscarFuncionalidadesPorPadre(null);
    }

    @Override
    public Funcionalidades seleccionarFuncionalidad(Integer idfuncionalidad) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idfuncionalidad)) {
            return funcionalidadesFacade.find(idfuncionalidad);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarFuncionalidad(Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion {
        funcionalidad.setFunEstado(EnumEstadoRegistro.E);
        return actualizarFuncionalidad(funcionalidad, sesion);
    }

    @Override
    public Funcionalidades obtenerFuncionalidadPorNombre(String nombreFuncionalidad, SesionDto sesion) throws NewviExcepcion {
        return funcionalidadesFacade.obtenerFuncionalidadPorNombre(nombreFuncionalidad, sesion);
    }
    
/*------------------------------------------------------------asignaciones------------------------------------------------------------*/
    
    @Override
    public String generarNuevaAsignacion(AsignacionPermisos nuevaAsignacion, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando asignacion...", sesion);
        if (!nuevaAsignacion.esAsignacionValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR310);
        }
        // Crear el asignacion
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando asignacion...", sesion);
        
        //Registramos la auditoria de ingreso
        nuevaAsignacion.setAudIngIp(sesion.getDireccionIP());
        nuevaAsignacion.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevaAsignacion.setAudIngFec(fechaIngreso);
        
        asignacionPermisosFacade.create(nuevaAsignacion);
        // Si todo marcha bien enviar nombre de la asignacion
        return nuevaAsignacion.getPefId().toString();
        
    }

    @Override
    public String actualizarAsignacion(AsignacionPermisos asignacion, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando asignacion...", sesion);
        if (!asignacion.esAsignacionValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR310);
        }
        // Editar la asignacion
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando asignacion...", sesion);
        
        //Registramos la auditoria de modificacion
        asignacion.setAudModIp(sesion.getDireccionIP());
        asignacion.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        asignacion.setAudModFec(fechaModificacion);
        
        asignacionPermisosFacade.edit(asignacion);
        // Si todo marcha bien enviar nombre de la asignacion
        return asignacion.getPefId().toString();
    }

    @Override
    public List<AsignacionPermisos> consultarAsignaciones() {
        return asignacionPermisosFacade.buscarAsiganciones();
    }

    @Override
    public AsignacionPermisos seleccionarAsignacion(Integer idasignacion) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idasignacion)) {
            return asignacionPermisosFacade.find(idasignacion);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarAsignacion(AsignacionPermisos asignacion, SesionDto sesion) throws NewviExcepcion {
        asignacion.setPefEstado(EnumEstadoRegistro.E);
        return actualizarAsignacion(asignacion, sesion);
    }
    
    @Override
    public AsignacionPermisos buscarAsignacionPermisos(Permisos permiso, Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion {
        return asignacionPermisosFacade.buscarAsignacionesPermisoPorPermisoFuncionalidad(permiso, funcionalidad, sesion);
    }
    
}
