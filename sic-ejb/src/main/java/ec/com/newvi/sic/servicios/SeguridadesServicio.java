/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author NEWVI
 */
@Local
@PermitAll
public interface SeguridadesServicio {

    /*------------------------------------------------------------Usuarios------------------------------------------------------------*/
    /**
     * Genera un nuevo usuario, de acuerdo a un objeto entregado.
     *
     * @param nuevoUsuario El nuevo usuario a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo usuario.
     * @throws NewviExcepcion
     */
    public String generarNuevoUsuario(Usuarios nuevoUsuario, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un usuario existente.
     *
     * @param usuario El usuario a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del usuario actualizado
     * @throws NewviExcepcion
     */
    public String actualizarUsuario(Usuarios usuario, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un usuario dado una id
     *
     * @param idUsuario Integer, código del usuario a obtener
     * @return usuario
     * @throws NewviExcepcion
     */
    public Usuarios seleccionarUsuario(Integer idUsuario) throws NewviExcepcion;

    /**
     * Devuelve un listado de usuarios.
     *
     * @return Listado de usuarios
     */
    public List<Usuarios> consultarUsuarios();

    /**
     * Elimina un usuario dado
     *
     * @param usuario El usuario a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del usuario eliminado
     * @throws NewviExcepcion
     */
    public String eliminarUsuario(Usuarios usuario, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Obtiene un usuario dado el nombre de usuario
     * @param nombreUsuario El nombre del usuario en String
     * @param sesion Sesion que realiza la operación
     * @return Usuario encontrado. Null si no se encontró usuario
     * @throws NewviExcepcion 
     */
    public Usuarios obtenerUsuarioPorNombreUsuario(String nombreUsuario, SesionDto sesion) throws NewviExcepcion;
    /**
     * Verifica si el usuario no se encuentra registrado en el sistema
     * @param usuUsuario El usuario a ser verificado
     * @return False si el usuario no se encuentra registrado
     * @throws NewviExcepcion 
     */
    public Boolean esUsuarioRepetido(String usuUsuario, Integer idUsuario) throws NewviExcepcion;
    /**
     * Verifica si el email no se encuentra registrado en el sistema
     * @param usuEmail El usuario será verificado por el email
     * @return False si el usuario no se encuentra registrado
     * @throws NewviExcepcion 
     */
    public Boolean esEmailRepetido(String usuEmail) throws NewviExcepcion;

    /*------------------------------------------------------------Permisos------------------------------------------------------------*/
    /**
     * Genera un nuevo permiso, de acuerdo a un objeto entregado.
     *
     * @param nuevoPermiso El nuevo permiso a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo permiso.
     * @throws NewviExcepcion
     */
    public String generarNuevoPermiso(Permisos nuevoPermiso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un permiso existente.
     *
     * @param permiso El permiso a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del permiso actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPermiso(Permisos permiso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un permiso dado una id
     *
     * @param idPermiso Integer, código del permiso a obtener
     * @return permiso
     * @throws NewviExcepcion
     */
    public Permisos seleccionarPermiso(Integer idPermiso) throws NewviExcepcion;

    /**
     * Devuelve un listado de permisos.
     *
     * @return Listado de permisos
     */
    public List<Permisos> consultarPermisos();

    /**
     * Elimina un permiso dado
     *
     * @param permiso El permiso a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del permiso eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPermiso(Permisos permiso, SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Funcionalidades------------------------------------------------------------*/
    /**
     * Genera una nueva funcionalidad, de acuerdo a un objeto entregado.
     *
     * @param nuevaFuncionalidad La nueva funcionalidad a ser ingresada
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la nueva funcionalidad.
     * @throws NewviExcepcion
     */
    public String generarNuevaFuncionalidad(Funcionalidades nuevaFuncionalidad, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una funcionalidad existente.
     *
     * @param funcionalidad La funcionalidad a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la funcionalidad actualizada
     * @throws NewviExcepcion
     */
    public String actualizarFuncionalidad(Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una funcionalidad dado un id
     *
     * @param idfuncionalidad Integer, código de la funcionalidad a obtener
     * @return funcionalidad
     * @throws NewviExcepcion
     */
    public Funcionalidades seleccionarFuncionalidad(Integer idfuncionalidad) throws NewviExcepcion;

    /**
     * Devuelve un listado de funcionalidades.
     *
     * @return Listado de funcionalidades
     */
    public List<Funcionalidades> consultarFuncionalidades();
    
    /**
     * Devuelve un listado de funcionalidades de primer nivel
     * @return 
     */
    public List<Funcionalidades> consultarFuncionalidadesPadre();

    /**
     * Elimina una funcionalidad dada
     *
     * @param funcionalidad La funcionalidad a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la funcionalidad eliminada
     * @throws NewviExcepcion
     */
    public String eliminarFuncionalidad(Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion;
            
    /*------------------------------------------------------------ASignaciones------------------------------------------------------------*/
    /**
     * Genera una nueva asignacion de funcionalidad, de acuerdo a un objeto entregado.
     *
     * @param nuevaAsignacion La nueva asignacion a ser ingresada
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la nueva asignacion.
     * @throws NewviExcepcion
     */
    public String generarNuevaAsignacion(AsignacionPermisos nuevaAsignacion, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una asignacion existente.
     *
     * @param asignacion La asignacion a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la asignacion actualizada
     * @throws NewviExcepcion
     */
    public String actualizarAsignacion(AsignacionPermisos asignacion, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una asignacion dado un id
     *
     * @param idasignacion Integer, código de la asignacion a obtener
     * @return asignacion
     * @throws NewviExcepcion
     */
    public AsignacionPermisos seleccionarAsignacion(Integer idasignacion) throws NewviExcepcion;

    /**
     * Devuelve un listado de asignaciones.
     *
     * @return Listado de asignaciones
     */
    public List<AsignacionPermisos> consultarAsignaciones();

    /**
     * Elimina una asignacion dada
     *
     * @param asignacion La asignacion a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la asignacion eliminada
     * @throws NewviExcepcion
     */
    public String eliminarAsignacion(AsignacionPermisos asignacion, SesionDto sesion) throws NewviExcepcion;

    /**
     * Busca un permiso asignado por permiso y funcionalidad
     * @param permiso Objeto con el permiso a buscar
     * @param funcionalidad Objeto con la funcionalidad
     * @param sesion Sesion que realiza la operación
     * @return Permiso asignado
     * @throws NewviExcepcion 
     */
    public AsignacionPermisos buscarAsignacionPermisos(Permisos permiso, Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion;
}
