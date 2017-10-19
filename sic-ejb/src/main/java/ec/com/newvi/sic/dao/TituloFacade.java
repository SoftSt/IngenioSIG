/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Titulos;
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
public class TituloFacade extends AbstractFacade<Titulos, Integer> implements Serializable  {

    public TituloFacade() {
        super(Titulos.class, Integer.class);
    }
    
    public List<Titulos> buscarContribuyentes() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.tituloEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de dominios
        return q.getResultList();
    }
    
}
