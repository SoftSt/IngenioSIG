/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
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
     * Devuelve un listado de usuarios.
     *
     * @return Listado de usuarios
     */
    public List<CatConConstantesdescuentos> consultarDescuentos();

}
