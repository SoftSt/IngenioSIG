/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.modelo.Tenencia;
import java.io.Serializable;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll
public class TenenciaFacade extends AbstractFacade<Tenencia, Integer> implements Serializable{

    public TenenciaFacade() {
        super(Tenencia.class, Integer.class);
    }
    
    
    
}
