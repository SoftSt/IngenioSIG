/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.UsoSuelo;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Rolando Soria by Excor
 */
@Stateless
@PermitAll
public class UsoSueloFacade extends AbstractFacade<UsoSuelo, Integer> {

    public UsoSueloFacade() {
        super(UsoSuelo.class, Integer.class);
    }   
    
    public List<UsoSuelo> buscarUsoSuelo(){
        // Busca un listado de IRM
        Query q = this.getEntityManager().createQuery("SELECT u FROM UsoSuelo u WHERE u.ussEstado=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
