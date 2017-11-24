/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
import ec.com.newvi.sic.modelo.CatConConstantesinteresmora;
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
public class ConstantesInteresMoraFacade extends AbstractFacade<CatConConstantesinteresmora, Integer>  implements Serializable{

    public ConstantesInteresMoraFacade() {
        super(CatConConstantesinteresmora.class, Integer.class);
    }
    
    public List<CatConConstantesinteresmora> buscarMultas(){      
        Query q = this.getEntityManager().createQuery("SELECT interesmora FROM CatConConstantesinteresmora interesmora ORDER BY interesmora.codInteresmora ASC");
        return q.getResultList();
    }
}
