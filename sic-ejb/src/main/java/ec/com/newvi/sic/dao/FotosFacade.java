/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.modelo.Fotos;
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
public class FotosFacade extends AbstractFacade<Fotos, Integer> implements Serializable {

    public FotosFacade() {
        super(Fotos.class, Integer.class);
    }
    
    public List<Fotos> buscarFotosPorPredio(int codCatastral){
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT foto FROM Fotos foto where foto.codCatastral = :CODCATASTRAL");
        q.setParameter("CODCATASTRAL", codCatastral);
        //@return listado de bloques
        return q.getResultList();        
    }
    
}
