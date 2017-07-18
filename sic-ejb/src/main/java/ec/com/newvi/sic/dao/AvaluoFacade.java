/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Avaluo;
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
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT avaluos FROM Avaluo avaluos where avaluos.fecavId.fecavFechaavaluo =:FECHA");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        q.setParameter("FECHA", fecavFechaavaluo);
        //@return listado de bloques
        return q.getResultList();        
    } 
    
}