/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Tipologia;
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
public class TipologiaFacade extends AbstractFacade<Tipologia, Integer> {

    public TipologiaFacade() {
        super(Tipologia.class, Integer.class);
    }   
    
    public List<Tipologia> buscarTipologia(){
        // Busca un listado de IRM
        Query q = this.getEntityManager().createQuery("SELECT t FROM Tipologia t WHERE t.tplEstado=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
