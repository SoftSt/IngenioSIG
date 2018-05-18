/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.RegulacionMunicipal;
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
public interface RegulacionMunicipalServicio {

    /*----------------------------------------------------- Parametros del Sistema ----------------------------------------------------*/
    /**
     * Genera un nuevo informe de regulaciòn municipal, de acuerdo al predio entregado.
     *
     * @param nuevoIRM nuevo informe de regulaciòn municipal a ser generado
     * @param sesion Sesion que realiza la operación
     * @return IRM generado.
     * @throws NewviExcepcion
     */
    public String generarNuevoIRM(RegulacionMunicipal nuevoIRM, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un informe de regulaciòn municipal existente.
     *
     * @param irm, informe de regulaciòn municipal a actualizar
     * @param sesion Sesion que realiza la operación
     * @return IRM actualizado
     * @throws NewviExcepcion
     */
    public String actualizarIRM(RegulacionMunicipal irm, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de IRM registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de parámetros existentes.
     * @throws NewviExcepcion
     */
    public List<RegulacionMunicipal> obtenerListaIRM(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporte(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve una Simbologia dado una id
     *
     * @param codIrm Integer, código de la regulacion municipal a obtener
     * @return regulacion municipal encontrada
     * @throws NewviExcepcion
     */
    public RegulacionMunicipal seleccionarRegulacion(Integer codIrm) throws NewviExcepcion;
}
