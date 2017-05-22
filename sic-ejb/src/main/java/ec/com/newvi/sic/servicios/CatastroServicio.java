/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Terreno;
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
        /*------------------------------------------------------------Bloques------------------------------------------------------------*/
    /**
     * Genera un nuevo Bloque, de acuerdo a un objeto entregado.
     *
     * @param nuevoBloque El nuevo Bloque a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Bloque.
     * @throws NewviExcepcion
     */
    public String generarNuevoBloque(Bloques nuevoBloque, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Bloque existente.
     *
     * @param bloque El Bloque a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio actualizado
     * @throws NewviExcepcion
     */
    public String actualizarBloque(Bloques bloque, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un predio dado un id
     *
     * @param idBloque Integer, código del predio a obtener
     * @return predio
     * @throws NewviExcepcion
     */
    public Bloques seleccionarBloque(Integer idBloque) throws NewviExcepcion;

    /**
     * Devuelve un listado de Bloques.
     *
     * @return Listado de Bloques
     */
    public List<Bloques> consultarBloques();
    
    /**
     * Elimina un predios dado
     *
     * @param bloque El predio a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio eliminado
     * @throws NewviExcepcion
     */
    public String eliminarBloque(Bloques bloque, SesionDto sesion) throws NewviExcepcion;
        /*------------------------------------------------------------Pisos------------------------------------------------------------*/
    /**
     * Genera un nuevo Piso, de acuerdo a un objeto entregado.
     *
     * @param nuevoPiso El nuevo Piso a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Piso.
     * @throws NewviExcepcion
     */
    public String generarNuevoPiso(Pisos nuevoPiso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Piso existente.
     *
     * @param piso El Piso a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del piso actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPiso(Pisos piso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un piso dado un id
     *
     * @param idPiso Integer, código del piso a obtener
     * @return piso
     * @throws NewviExcepcion
     */
    public Pisos seleccionarPiso(Integer idPiso) throws NewviExcepcion;

    /**
     * Devuelve un listado de Pisos.
     *
     * @return Listado de Pisos
     */
    public List<Pisos> consultarPisos();
    
    /**
     * Elimina un pisos dado
     *
     * @param piso El piso a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del piso eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPiso(Pisos piso, SesionDto sesion) throws NewviExcepcion;
        /*------------------------------------------------------------Terreno------------------------------------------------------------*/
    /**
     * Genera un nuevo Terreno, de acuerdo a un objeto entregado.
     *
     * @param nuevoTerreno El nuevo Terreno a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Terreno.
     * @throws NewviExcepcion
     */
    public String generarNuevoTerreno(Terreno nuevoTerreno, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Terreno existente.
     *
     * @param terreno El Terreno a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del terreno actualizado
     * @throws NewviExcepcion
     */
    public String actualizarTerreno(Terreno terreno, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un terreno dado un id
     *
     * @param idTerreno Integer, código del terreno a obtener
     * @return terreno
     * @throws NewviExcepcion
     */
    public Terreno seleccionarTerreno(Integer idTerreno) throws NewviExcepcion;

    /**
     * Devuelve un listado de Terreno.
     *
     * @return Listado de Terreno
     */
    public List<Terreno> consultarTerreno();
    
    /**
     * Elimina un terreno dado
     *
     * @param terreno El terreno a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del terreno eliminado
     * @throws NewviExcepcion
     */
    public String eliminarTerreno(Terreno terreno, SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Fotos------------------------------------------------------------*/

   /**
    * Consulta todas las imagenes que tiene un predio
    * @param codCatastral codigo del predio asignado
    * @return lista de fotos del predio
    */
    public List<Fotos> consultarFotosPorPredio(int codCatastral);
}
