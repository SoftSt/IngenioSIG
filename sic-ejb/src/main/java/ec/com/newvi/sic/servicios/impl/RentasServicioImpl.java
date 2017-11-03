/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 *
 * @author israelavila
 */
@Stateless
@PermitAll
public class RentasServicioImpl implements RentasServicio {

    @Override
    public List<Titulos> generarTitulosDeAvaluos(List<Avaluo> listadoAvaluos, SesionDto sesion) throws NewviExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
