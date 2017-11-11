/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ConstantesDescuentosFacade;
import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
import ec.com.newvi.sic.servicios.TesoreriaServicio;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author NEWVI
 */
@Stateless
@PermitAll
public class TesoreriaServicioImpl implements TesoreriaServicio {

    @EJB
    private ConstantesDescuentosFacade constantesDescuentosFacade;

    @Override
    public List<CatConConstantesdescuentos> consultarDescuentos() {
        return constantesDescuentosFacade.buscarDescuentos();
    }
}
