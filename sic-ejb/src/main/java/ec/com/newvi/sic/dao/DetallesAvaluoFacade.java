/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.DetallesAvaluo;
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
public class DetallesAvaluoFacade extends AbstractFacade<DetallesAvaluo, Integer> implements Serializable{

    public DetallesAvaluoFacade() {
        super(DetallesAvaluo.class, Integer.class);
    }
    
    public List<DetallesAvaluo> buscarDetallesAvaluo(){
        // Busca un listado de detalles de avaluo
        Query q = this.getEntityManager().createQuery("SELECT detalles FROM DetallesAvaluo detalles where detalles.davalEstado =:ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de detalles de avaluo
        return q.getResultList();        
    }
    
    
    
}
