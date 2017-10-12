/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Servicios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Andrés
 */

@Stateless
@PermitAll
public class ServicioFacade extends AbstractFacade<Servicios, Integer> implements Serializable {

    public ServicioFacade() {
        super(Servicios.class, Integer.class);
    }
    
}
