/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.ObrasDetalle;
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
public class ObrasDetalleFacade extends AbstractFacade<ObrasDetalle, Integer> implements Serializable{

    public ObrasDetalleFacade() {
        super(ObrasDetalle.class, Integer.class);
    }
    
    public List<ObrasDetalle> buscarObrasDetalle(Integer codObra) {
        // Busca un listado de ObrasDetalle
        Query q = this.getEntityManager().createQuery("SELECT obras FROM ObrasDetalle obras where obras.obdEstado = :ESTADO and obras.codObras.codObras =:CODOBRA");
        q.setParameter("CODOBRA", codObra);
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        // and obras.codObras = :CODOBRA
        //@return listado de ObrasDetalle
        return q.getResultList();
    }
    
}
