/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Simbologia;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Rolando Soria by Excor
 */
@Stateless
@PermitAll
public class SimbologiaFacade extends AbstractFacade<Simbologia, Integer> {

    public SimbologiaFacade() {
        super(Simbologia.class, Integer.class);
    }   
    
    public List<Simbologia> buscarSimbologia(){
        // Busca un listado de IRM
        Query q = this.getEntityManager().createQuery("SELECT s FROM Simbologia s WHERE s.smbEstado=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
