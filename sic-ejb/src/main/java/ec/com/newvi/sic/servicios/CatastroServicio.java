/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Predios;
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
public interface CatastroServicio {
        /*------------------------------------------------------------Predios------------------------------------------------------------*/
    /**
     * Genera un nuevo Predio, de acuerdo a un objeto entregado.
     *
     * @param nuevoPredio El nuevo Predio a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Predio.
     * @throws NewviExcepcion
     */
    public String generarNuevoPredio(Predios nuevoPredio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Predio existente.
     *
     * @param predio El Predio a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPredio(Predios predio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un predio dado un id
     *
     * @param idPredio Integer, código del predio a obtener
     * @return predio
     * @throws NewviExcepcion
     */
    public Predios seleccionarPredio(Integer idPredio) throws NewviExcepcion;

    /**
     * Devuelve un listado de Predios.
     *
     * @return Listado de Predios
     */
    public List<Predios> consultarPredios();
    
    /**
     * Elimina un predios dado
     *
     * @param predio El predio a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPredio(Predios predio, SesionDto sesion) throws NewviExcepcion;
}
