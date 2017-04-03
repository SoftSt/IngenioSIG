/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Dominios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author NEWVI
 */
@Stateless
@PermitAll
public class DominiosFacade extends AbstractFacade<Dominios, Integer> implements Serializable{

    public DominiosFacade() {
        super(Dominios.class, Integer.class);
    }
/**
 * Devuelve un listado de dominios
 * @return Listado de dominios
 */
    public List<Dominios> buscarDominios() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio FROM Dominios dominio where dominio.domiEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de dominios
        return q.getResultList();
    }
    
    public List<Dominios> buscarDominiosGrupos(){
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT distinct dominio.domiGrupos FROM Dominios dominio");
        //@return listado de dominios
        return q.getResultList();
    }

}
