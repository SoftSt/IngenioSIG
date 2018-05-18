/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Zonificacion;
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
public interface ZonificacionServicio {

    /*----------------------------------------------------- Zonificaciòn Uso de Suelo ----------------------------------------------------*/
    /**
     * Genera una nueva Zonificacion de uso de suelo.
     *
     * @param nuevaZonificacion nueva zona a ser generada
     * @param sesion Sesion que realiza la operación
     * @return Zonificacion generada.
     * @throws NewviExcepcion
     */
    public String generarNuevaZonificacion(Zonificacion nuevaZonificacion, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una zona existente.
     *
     * @param zonificacion, zonificaciòn a actualizar
     * @param sesion Sesion que realiza la operación
     * @return zonificacion actualizada
     * @throws NewviExcepcion
     */
    public String actualizarZonificacion(Zonificacion zonificacion, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de Zonas registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de zonas existentes.
     * @throws NewviExcepcion
     */
    public List<Zonificacion> obtenerListaZonificacion(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporteZonificacion(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve un parámetro dado una id
     *
     * @param codZona Integer, código de la zona a obtener
     * @return zona  encontrado
     * @throws NewviExcepcion
     */
    public Zonificacion seleccionarZonificacion(Integer codZona) throws NewviExcepcion;
    
     /**
     * Devuelve un listado de zonificaciones.
     *
     * @return Listado de permisos
     */
    public List<Zonificacion> cargarZonificaciones();

}
