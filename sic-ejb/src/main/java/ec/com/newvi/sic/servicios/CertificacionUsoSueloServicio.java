/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.CertificacionUsoSuelo;
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
public interface CertificacionUsoSueloServicio {

    /*----------------------------------------------------- Certificacion Uso de Suelo ----------------------------------------------------*/
    /**
     * Genera una nueva Zonificacion de uso de suelo.
     *
     * @param nuevaCertificacion nueva certificacion a ser generada
     * @param sesion Sesion que realiza la operación
     * @return Certificacion generada.
     * @throws NewviExcepcion
     */
    public String generarNuevaCertificacion(CertificacionUsoSuelo nuevaCertificacion, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una zona existente.
     *
     * @param certficicacionUsoSuelo, certificacion a actualizar
     * @param sesion Sesion que realiza la operación
     * @return certificacion actualizada
     * @throws NewviExcepcion
     */
    public String actualizarCertificacion(CertificacionUsoSuelo certficicacionUsoSuelo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de Certificaciones registradas en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de certificaciones existentes.
     * @throws NewviExcepcion
     */
    public List<CertificacionUsoSuelo> obtenerListaCertificaciones(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporteCertificacion(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve un parámetro dado una id
     *
     * @param codCertificacion Integer, código de la certificacion a obtener
     * @return certificacion  encontrado
     * @throws NewviExcepcion
     */
    public CertificacionUsoSuelo seleccionarCertificacion(Integer codCertificacion) throws NewviExcepcion;
    
     /**
     * Devuelve un listado de certificaciones.
     *
     * @return Listado de certificaciones
     */
    public List<CertificacionUsoSuelo> cargarCertificaciones();

}
