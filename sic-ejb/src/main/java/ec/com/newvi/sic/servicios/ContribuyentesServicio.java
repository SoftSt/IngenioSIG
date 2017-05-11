/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Propietario;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author Andrés
 */
@Local
@PermitAll
public interface ContribuyentesServicio {
/*------------------------------------------------------------Contribuyentes------------------------------------------------------------*/
    /**
     * Genera una nueva Contribuyente, de acuerdo a un objeto entregado.
     *
     * @param nuevoContribuyente El nuevo Contribuyente a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Contribuyente.
     * @throws NewviExcepcion
     */
    public String generarNuevoContribuyente(Contribuyentes nuevoContribuyente, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una Contribuyente existente.
     *
     * @param contribuyente El Contribuyente a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la contribuyente actualizado
     * @throws NewviExcepcion
     */
    public String actualizarContribuyente(Contribuyentes contribuyente, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una contribuyente dado un id
     *
     * @param idContribuyente Integer, código del contribuyente a obtener
     * @return contribuyente
     * @throws NewviExcepcion
     */
    public Contribuyentes seleccionarContribuyente(Integer idContribuyente) throws NewviExcepcion;

    /**
     * Devuelve un listado de Contribuyentes.
     *
     * @return Listado de Contribuyentes
     */
    public List<Contribuyentes> consultarContribuyentes();
    
    /**
     * Elimina un contribuyente dado
     *
     * @param contribuyente El contribuyente a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del contribuyente eliminado
     * @throws NewviExcepcion
     */
    public String eliminarContribuyente(Contribuyentes contribuyente, SesionDto sesion) throws NewviExcepcion;
/*------------------------------------------------------------Propietario------------------------------------------------------------*/
    /**
     * Genera una nueva Propietario, de acuerdo a un objeto entregado.
     *
     * @param nuevoPropietario El nuevo Propietario a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Propietario.
     * @throws NewviExcepcion
     */
    public String generarNuevoPropietario(Propietario nuevoPropietario, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una Propietario existente.
     *
     * @param propietario El Propietario a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la propietario actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPropietario(Propietario propietario, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una propietario dado un id
     *
     * @param idPropietario Integer, código del propietario a obtener
     * @return propietario
     * @throws NewviExcepcion
     */
    public Propietario seleccionarPropietario(Integer idPropietario) throws NewviExcepcion;

    /**
     * Devuelve un listado de Propietario.
     *
     * @return Listado de Propietario
     */
    public List<Propietario> consultarPropietario();
    
    /**
     * Elimina un propietario dado
     *
     * @param propietario El propietario a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del propietario eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPropietario(Propietario propietario, SesionDto sesion) throws NewviExcepcion;
}
