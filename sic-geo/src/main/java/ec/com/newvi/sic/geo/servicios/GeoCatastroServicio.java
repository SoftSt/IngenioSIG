/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.geo.modelo.GeoPredio;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author israelavila
 */
@Local
@PermitAll
public interface GeoCatastroServicio {

    /**
     * Obtiene los valores de borde en la forma de una cadena
     * xmin,ymin,xmax,ymax
     *
     * @param predio El predio a buscar
     * @param alto El alto del recuadro, en pixeles
     * @param ancho El ancho del recuadro, en pixeles
     * @param sesion Sesion del usuario que realiza la consulta
     * @return cadena del borde
     * @throws NewviExcepcion
     */
    public String obtenerBordesPredio(Predios predio, BigDecimal alto, BigDecimal ancho, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de predios en la cobertura de predios que no contienen
     * ficha catastral.
     *
     * @param prediosRegistrados Lista de predios registrados en el sistema actual.
     * @param sesion Sesion del usuario que realiza la consulta
     * @return listado de predios huérfanos
     * @throws NewviExcepcion
     */
    public List<GeoPredio> obtenerListadoGeoPrediosHuerfanos(List<Predios> prediosRegistrados, SesionDto sesion) throws NewviExcepcion;
    /**
     * Genera un listado de predios a partir de un GeoPredio
     * @param geoPredios lista de GeoPredios a ser generados
     * @param sesion usuario que realiza la acción
     * @return lista de predios generados
     */
    public List<Predios> obtenerListaPrediosDesdeGeoPredio(List<GeoPredio> geoPredios, SesionDto sesion);

}
