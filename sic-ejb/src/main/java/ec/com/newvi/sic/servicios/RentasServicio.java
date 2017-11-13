/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author Andrés
 */
@Local
@PermitAll
public interface RentasServicio {

    /**
     * Genera un grupo de títulos a partir de un grupo de avalúos. Se envía un
     * grupo de avalúos ya registrados en el sistema para generar el listado de
     * títulos generados.
     *
     * @param listadoAvaluos Listado de avalúos a generar.
     * @param sesion Sesión que genera los nuevos títulos.
     * @return Listado de títulos generados.
     * @throws NewviExcepcion
     */
    public List<Titulos> generarTitulosDesdeAvaluos(List<Avaluo> listadoAvaluos, SesionDto sesion) throws NewviExcepcion;
    /**
     * Se genera el registro del titulo
     * @param nuevoTitulo nuevo titulo a registrar
     * @param sesion Sesión que genera el registo de los nuevos títulos.
     * @return Fecha de emisión del titulo.
     * @throws NewviExcepcion 
     */
    public Date generarNuevoTitulo(Titulos nuevoTitulo, SesionDto sesion) throws NewviExcepcion;
    /**
     * Lista todos los títulos que se han generado en una fecha determinada
     * @param fechaEmision fecha por la que se generará la consulta
     * @return lista de titulos generados en la fecha indicada
     */
    public List<Titulos> consultarTitulosGenerados(Date fechaEmision);
    /**
     * Lista los titulos que se hab generado para un predio
     * @param codCatastral codigo catastral pór el cual se generará la consulta
     * @return lista de títulos que tiene el predio
     */
    public List<Titulos> consultarTitulosPorCodigoCatastral(Integer codCatastral);
    /**
     * Selecciona un título por su código
     * @param codTitulo código del título por el que se seleccionará
     * @throws NewviExcepcion 
     * @return título
     */
    public Titulos seleccionarTitulo(Integer codTitulo)throws NewviExcepcion;
    /**
     * Se actualizar el título recibido
     * @param titulo título a actualizar
     * @param sesion Sesión que realizará la acción
     * @return el código del tpitulo actulizado
     * @throws NewviExcepcion 
     */
    public String actualizarTitulo(Titulos titulo, SesionDto sesion) throws NewviExcepcion;
}
