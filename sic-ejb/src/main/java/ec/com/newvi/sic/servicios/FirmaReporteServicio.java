/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.FirmaReporte;
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
public interface FirmaReporteServicio {

    /*----------------------------------------------------- FirmaReporte ----------------------------------------------------*/
    /**
     * Genera un nuevo registro de FirmaReporte.
     *
     * @param nuevaFirmaReporte nueva firma reporte
     * @param sesion Sesion que realiza la operación
     * @return Firma reporte generada.
     * @throws NewviExcepcion
     */
    public String generarNuevaFirmaReporte(FirmaReporte nuevaFirmaReporte, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una firma de reporte existente.
     *
     * @param firmaReporte firma de reporte a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Simbologia actualizada
     * @throws NewviExcepcion
     */
    public String actualizarFirmaReporte(FirmaReporte firmaReporte, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de firmas registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de firmas existentes.
     * @throws NewviExcepcion
     */
    public List<FirmaReporte> obtenerListaFirmaReporte(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporteFirma(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve una Simbologia dado una id
     *
     * @param codFirmaReporte Integer, código de la firma a obtener
     * @return Firma encontrada
     * @throws NewviExcepcion
     */
    public FirmaReporte seleccionarFirmaReporte(Integer codFirmaReporte) throws NewviExcepcion;

}
