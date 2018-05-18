/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.UsoSuelo;
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
public interface UsoSueloServicio {

    /*----------------------------------------------------- Uso de Suelo ----------------------------------------------------*/
    /**
     * Genera un nuevo registro de uso de suelo.
     *
     * @param nuevoUsoSuelo nuevo uso de suelo
     * @param sesion Sesion que realiza la operación
     * @return Uso de suelo generado.
     * @throws NewviExcepcion
     */
    public String generarNuevoUsoSuelo(UsoSuelo nuevoUsoSuelo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un uso de suelo existente.
     *
     * @param usoSuelo Uso de Suelo a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Uso de suelo actualizado
     * @throws NewviExcepcion
     */
    public String actualizarUsoSuelo(UsoSuelo usoSuelo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de Uso de Suelos registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de usos de suelo existentes.
     * @throws NewviExcepcion
     */
    public List<UsoSuelo> obtenerListaUsoSuelo(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporteUsoSuelo(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve un parámetro dado una id
     *
     * @param codUsoSuelo Integer, código del uso de suelo a obtener
     * @return Uso de suelo  encontrado
     * @throws NewviExcepcion
     */
    public UsoSuelo seleccionarUsoSuelo(Integer codUsoSuelo) throws NewviExcepcion;
    
     /**
     * Devuelve un listado de uso de suelos.
     *
     * @return Listado de uso de suelos
     */
    public List<UsoSuelo> cargarUsosSuelo();

}
