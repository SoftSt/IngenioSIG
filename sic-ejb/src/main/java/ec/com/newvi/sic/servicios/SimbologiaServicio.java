/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Simbologia;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author Rolando Soria by Excor
 */
@Local
@PermitAll
public interface SimbologiaServicio {

    /*----------------------------------------------------- Simbologia ----------------------------------------------------*/
    /**
     * Genera un nuevo registro de simbologia.
     *
     * @param nuevaSimbologia nueva simbologia
     * @param sesion Sesion que realiza la operación
     * @return Simbologia generada.
     * @throws NewviExcepcion
     */
    public String generarNuevaSimbologia(Simbologia nuevaSimbologia, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una simbologia existente.
     *
     * @param simbologia Simbologia a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Simbologia actualizada
     * @throws NewviExcepcion
     */
    public String actualizarSimbologia(Simbologia simbologia, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de Simbologias registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de simbologias existentes.
     * @throws NewviExcepcion
     */
    public List<Simbologia> obtenerListaSimbologia(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporteSimbologia(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve una Simbologia dado una id
     *
     * @param codSimbologia Integer, código de la Simbologia a obtener
     * @return Simbologia encontrado
     * @throws NewviExcepcion
     */
    public Simbologia seleccionarSimbologia(Integer codSimbologia) throws NewviExcepcion;

}
