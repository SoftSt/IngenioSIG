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
}
