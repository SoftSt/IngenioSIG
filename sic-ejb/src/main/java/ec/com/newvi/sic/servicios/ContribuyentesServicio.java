/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
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
/*------------------------------------------------------------Propiedad------------------------------------------------------------*/
    /**
     * Genera una nueva Propiedad, de acuerdo a un objeto entregado.
     *
     * @param nuevoPropiedad El nuevo Propiedad a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Propiedad.
     * @throws NewviExcepcion
     */
    public String generarNuevoPropiedad(Propiedad nuevoPropiedad, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una Propiedad existente.
     *
     * @param propiedad El Propiedad a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la propiedad actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPropiedad(Propiedad propiedad, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una propiedad dado un id
     *
     * @param idPropiedad Integer, código del propiedad a obtener
     * @return propiedad
     * @throws NewviExcepcion
     */
    public Propiedad seleccionarPropiedad(Integer idPropiedad) throws NewviExcepcion;

    /**
     * Devuelve un listado de Propiedad.
     *
     * @return Listado de Propiedad
     */
    public List<Propiedad> consultarPropiedad();
    
    /**
     * Elimina un propiedad dado
     *
     * @param propiedad El propiedad a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del propiedad eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPropiedad(Propiedad propiedad, SesionDto sesion) throws NewviExcepcion;
    
    public Propiedad consultarUltimoPropiedad(Predios predio) throws NewviExcepcion;
}
