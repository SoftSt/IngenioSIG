/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.DetallesAvaluo;
import ec.com.newvi.sic.modelo.Predios;
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
    
    public List<DetallesAvaluo> buscarDetallesAvaluo(Integer codCatastral){
        // Busca un listado de detalles de avaluo
        Query q = this.getEntityManager().createQuery("SELECT detalles FROM DetallesAvaluo detalles where detalles.davalEstado =:ESTADO and detalles.codCatastral.codCatastral =:CODCATASTRAL");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        q.setParameter("CODCATASTRAL", codCatastral);
        //@return listado de detalles de avaluo
        return q.getResultList();        
    }
    
    public List<DetallesAvaluo> buscarHijosDetallesAvaluo(DetallesAvaluo detallesAvaluo){
        //Busca un listado de detalles avaluo de hijo
        Query q = this.getEntityManager().createQuery("SELECT detalles FROM DetallesAvaluo detalles where detalles.davalPadre =:PADRE");
        q.setParameter("PADRE", detallesAvaluo.getDavalId().toString());
        //@return listado de detallesAvaluo
        return q.getResultList();
    }
    
    public List<DetallesAvaluo> buscarDetallesAvaluoNodo(String relacion, Predios predio){
        // Busca un listado de detalles de avaluo
        Query q = this.getEntityManager().createQuery("SELECT detalles FROM DetallesAvaluo detalles where detalles.davalRelacion =:RELACION and detalles.codCatastral =:PREDIO");
        q.setParameter("RELACION", relacion);
        q.setParameter("PREDIO", predio);
        //@return listado de detalles de avaluo
        return q.getResultList();        
    }
    public DetallesAvaluo buscarPadre(Predios predio, String relacion){
        // Busca un detalles de avaluo
        Query q = this.getEntityManager().createQuery("SELECT detalles FROM DetallesAvaluo detalles where detalles.codCatastral =:PREDIO and detalles.davalRelacion =:RELACION");
        q.setParameter("PREDIO", predio);
        q.setParameter("RELACION", relacion);
        //@return un detalles de avaluo
        return (DetallesAvaluo)q.getSingleResult();
    }
    
    public void eliminarDetallesPorPredio(Predios predio){
        Query q = this.getEntityManager().createQuery("DELETE FROM DetallesAvaluo detalles where detalles.codCatastral =:PREDIO");
        q.setParameter("PREDIO", predio);
        q.executeUpdate();
    }
    
    
}
