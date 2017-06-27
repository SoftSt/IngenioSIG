/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Bloques;
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
public class BloquesFacade extends AbstractFacade<Bloques, Integer> implements Serializable {

    public BloquesFacade() {
        super(Bloques.class, Integer.class);
    }
    
    public List<Bloques> buscarBloques(){
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT bloque FROM Bloques bloque where bloque.bloEstado =:ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de bloques
        return q.getResultList();        
    } 
    
    public List<Bloques> buscarBloquesPorCodigoCatastral(Integer codCatastral){
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT bloque FROM Bloques bloque where bloque.codCatastral.codCatastral =:CODCATASTRAL");
        q.setParameter("CODCATASTRAL", codCatastral);
        //@return listado de bloques
        return q.getResultList();
    } 

}
