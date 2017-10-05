/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.LogPredio;
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
public class LogPredioFacade extends AbstractFacade<LogPredio, Integer> implements Serializable{

    public LogPredioFacade() {
        super(LogPredio.class, Integer.class);
    }
    
    public List<LogPredio> buscarLogPredio(){
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT logPredio FROM LogPredio logPredio where logPredio.logEstado =:ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de bloques
        return q.getResultList();        
    } 
    
}
