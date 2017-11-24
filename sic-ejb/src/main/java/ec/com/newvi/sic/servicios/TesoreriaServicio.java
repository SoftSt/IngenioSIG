/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
import ec.com.newvi.sic.modelo.CatConConstantesinteresmora;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author NEWVI
 */
@Local
@PermitAll
public interface TesoreriaServicio {

    /**
     * Devuelve un listado de Descuentos.
     *
     * @return Listado de Descuentos
     */
    public List<CatConConstantesdescuentos> consultarDescuentos();
    
    /**
     * Devuelve un Descuentos dado una id
     *
     * @param idDescuento Integer, código del Descuentos a obtener
     * @return CatConConstantesdescuentos
     * @throws NewviExcepcion
     */
    public CatConConstantesdescuentos seleccionarDescuento(Integer idDescuento) throws NewviExcepcion;

    /**
     * Actualiza un Descuento existente. 
     * 
     * @param catConConstantesdescuentos
     * @param sesion
     * @throws NewviExcepcion 
     */
    public void actualizarDescuento(CatConConstantesdescuentos catConConstantesdescuentos, SesionDto sesion) throws NewviExcepcion;

    /**
     * Genera un nuevo Descuento, de acuerdo a un objeto entregado.
     * 
     * @param catConConstantesdescuentos
     * @param sesion 
     */
    public void generarNuevoDescuentos(CatConConstantesdescuentos catConConstantesdescuentos, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un listado de Multa.
     * @return 
     */
    public List<CatConConstantesinteresmora> consultarMultas();

    /**
     * Devuelve un Multa dado una id
     * @param idMulta
     * @return 
     */
    public CatConConstantesinteresmora seleccionarMulta(Integer idMulta) throws NewviExcepcion;

    /**
     * Actualiza un Multa existente. 
     * @param constantesInteresMora
     * @param sesion
     * @throws NewviExcepcion 
     */
    public void actualizarDescuento(CatConConstantesinteresmora constantesInteresMora, SesionDto sesion) throws NewviExcepcion;

    /**
     * Genera un nuevo Multa, de acuerdo a un objeto entregado.
     * @param constantesInteresMora
     * @param sesion
     * @throws NewviExcepcion 
     */
    public void generarNuevaMulta(CatConConstantesinteresmora constantesInteresMora, SesionDto sesion) throws NewviExcepcion;
    
}
