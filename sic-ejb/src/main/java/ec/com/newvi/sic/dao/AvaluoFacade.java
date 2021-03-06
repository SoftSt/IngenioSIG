/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Predios;
import java.io.Serializable;
import java.util.Date;
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
public class AvaluoFacade extends AbstractFacade<Avaluo, Integer> implements Serializable{

    public AvaluoFacade() {
        super(Avaluo.class, Integer.class);
    }
    
    public List<Avaluo> buscarAvaluos(Date fecavFechaavaluo){
        Query q = this.getEntityManager().createQuery("SELECT avaluos FROM Avaluo avaluos");
        if (fecavFechaavaluo!=null) {
        // Busca un listado de bloques
        q = this.getEntityManager().createQuery("SELECT avaluos FROM Avaluo avaluos where avaluos.fecavId.fecavFechaavaluo =:FECHA");
        q.setParameter("FECHA", fecavFechaavaluo);
        //q.setParameter("ESTADO", EnumEstadoRegistro.A);
        }
        //@return listado de bloques
        return q.getResultList();        
    }
    
    public List<Avaluo> consultarAvaluos(String fechaAvaluo){
        // Busca un listado de avaluos
        Query q = this.getEntityManager().createQuery("SELECT avaluos FROM Avaluo avaluos where trim(avaluos.fecavId.fechaDescripcion) =:FECHA and avaluos.avalEstado =:ESTADO");
        q.setParameter("FECHA", fechaAvaluo);
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de avaluos
        return q.getResultList();
    }
    public Avaluo consultarAvaluoActual(){
        // Busca un listado de avaluos
        Query q = this.getEntityManager().createQuery("SELECT avaluos FROM Avaluo avaluos where avaluos.avalEstado =:ESTADO ORDER BY avaluos.avalId DESC");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de avaluos
        return (Avaluo)q.setMaxResults(1).getSingleResult();
    }
    
    public Avaluo consultarAvaluoPorCodCatastralYFechaAvaluo(Predios codCatrastal, FechaAvaluo fecavId){
        // Busca un listado de avaluos
        Query q = this.getEntityManager().createQuery("SELECT avaluos FROM Avaluo avaluos where avaluos.codCatastral =:CODCATASTRAL AND avaluos.fecavId =:FECHAAVALUO");
        q.setParameter("CODCATASTRAL", codCatrastal);
        q.setParameter("FECHAAVALUO", fecavId);
        //@return listado de avaluos
        return (Avaluo)q.getSingleResult();
    }
    
    
    
    
    
}