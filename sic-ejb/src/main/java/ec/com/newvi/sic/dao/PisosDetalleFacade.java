/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.modelo.PisoDetalle;
import java.io.Serializable;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll
public class PisosDetalleFacade extends AbstractFacade<PisoDetalle, Integer> implements Serializable {

    public PisosDetalleFacade() {
        super(PisoDetalle.class, Integer.class);
    }
    
    
    
    
    
    
}
