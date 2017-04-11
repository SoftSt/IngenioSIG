/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.ContribucionMejoras;
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
public interface ContribucionMejorasServicio {
    /*------------------------------------------------------------ContribucionMejoras------------------------------------------------------------*/
    /**
     * Genera una nueva ContribucionMejora, de acuerdo a un objeto entregado.
     *
     * @param nuevaContribucionMejoras La nueva ContribucionMejoras a ser ingresada
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nueva ContribucionMejoras.
     * @throws NewviExcepcion
     */
    public String generarNuevaContribucionMejoras(ContribucionMejoras nuevaContribucionMejoras, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una ContribucionMejoras existente.
     *
     * @param contribucionMejoras La ContribucionMejoras a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la contribucionMejoras actualizada
     * @throws NewviExcepcion
     */
    public String actualizarContribucionMejoras(ContribucionMejoras contribucionMejoras, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una contribucionMejoras dado un id
     *
     * @param idContribucionMejoras Integer, código del contribucionMejoras a obtener
     * @return contribucionMejoras
     * @throws NewviExcepcion
     */
    public ContribucionMejoras seleccionarContribucionMejoras(Integer idContribucionMejoras) throws NewviExcepcion;

    /**
     * Devuelve un listado de ContribucionMejoras.
     *
     * @return Listado de ContribucionMejoras
     */
    public List<ContribucionMejoras> consultarContribucionMejoras();
    
    /**
     * Elimina un contribucionMejoras dado
     *
     * @param contribucionMejoras La contribucionMejoras a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre de la contribucionMejoras eliminada
     * @throws NewviExcepcion
     */
    public String eliminarContribucionMejoras(ContribucionMejoras contribucionMejoras, SesionDto sesion) throws NewviExcepcion;
}
