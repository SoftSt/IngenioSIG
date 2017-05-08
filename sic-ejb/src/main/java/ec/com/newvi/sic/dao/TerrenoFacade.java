/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Terreno;
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
public class TerrenoFacade extends AbstractFacade<Terreno, Integer> implements Serializable {

    public TerrenoFacade() {
        super(Terreno.class, Integer.class);
    }
    
    public List<Terreno> buscarTerreno(){
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT terreno FROM Terreno terreno where terreno.codTerrenodetalle = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de bloques
        return q.getResultList();        
    }
    
}
