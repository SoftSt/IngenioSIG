/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.FechaAvaluo;
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
public class FechaAvaluoFacade extends AbstractFacade<FechaAvaluo, Integer>{

    public FechaAvaluoFacade() {
        super(FechaAvaluo.class, Integer.class);
    }
    
    public List<FechaAvaluo> buscarFechaAvaluos(){
    // Busca un listado de FechasAvaluo
        Query q = this.getEntityManager().createQuery("SELECT distinct fecha.fecavFechaavaluo FROM FechaAvaluo fecha");
        //q.setParameter("ESTADO", EnumEstadoRegistro.A);
//where fecha.fecavEstado =:ESTADO        
//@return listado de fechaAvaluo
        return q.getResultList();   
    }
    
}
