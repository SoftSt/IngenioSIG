/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.ConstantesInteresMora;
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
public class ConstantesInteresMoraFacade extends AbstractFacade<ConstantesInteresMora, Integer>  implements Serializable{

    public ConstantesInteresMoraFacade() {
        super(ConstantesInteresMora.class, Integer.class);
    }
    
    public List<ConstantesInteresMora> buscarMultas(){      
        Query q = this.getEntityManager().createQuery("SELECT interesmora FROM ConstantesInteresMora interesmora where interesmora.estadointeresmora =:ESTADO ORDER BY interesmora.codInteresmora ASC ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();
    }
}
