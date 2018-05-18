/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Tipologia;
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
public interface TipologiaServicio {

    /*----------------------------------------------------- Tipologia ----------------------------------------------------*/
    /**
     * Genera un nuevo registro de tipologia.
     *
     * @param nuevaTipologia nuevo uso de suelo
     * @param sesion Sesion que realiza la operación
     * @return Tipologia generado.
     * @throws NewviExcepcion
     */
    public String generarNuevaTipologia(Tipologia nuevaTipologia, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza una tipologia existente.
     *
     * @param tipologia Tipologia a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Tipologia actualizada
     * @throws NewviExcepcion
     */
    public String actualizarTipologia(Tipologia tipologia, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de Tipologias registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de tipologias existentes.
     * @throws NewviExcepcion
     */
    public List<Tipologia> obtenerListaTipologia(SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporteTipologia(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve una tipologia dado una id
     *
     * @param codTipologia Integer, código de la tipologia a obtener
     * @return Tipologia encontrado
     * @throws NewviExcepcion
     */
    public Tipologia seleccionarTipologia(Integer codTipologia) throws NewviExcepcion;
    
     /**
     * Devuelve un listado de tipologias.
     *
     * @return Listado de tipologias
     */
    public List<Tipologia> cargarTipologias();
   
}
