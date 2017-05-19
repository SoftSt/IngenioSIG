/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
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
public class DominiosFacade extends AbstractFacade<Dominios, Integer> implements Serializable {

    public DominiosFacade() {
        super(Dominios.class, Integer.class);
    }

    /**
     * Devuelve un listado de dominios
     *
     * @return Listado de dominios
     */
    public List<Dominios> buscarDominios() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio FROM Dominios dominio where dominio.estadoDominio = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de dominios
        return q.getResultList();
    }

    public List<Dominios> buscarDominiosGrupos() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT distinct dominio.domiGrupos FROM Dominios dominio");
        //@return listado de dominios
        return q.getResultList();
    }

    public List<Dominios> buscarDominiosPorGrupo(String grupo) {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio FROM Dominios dominio where dominio.domiGrupos=:GRUPOS AND TRIM(dominio.domiRelacion) =:RELACION");
        q.setParameter("GRUPOS", grupo);
        q.setParameter("RELACION", EnumRelacionDominios.Nodo.toString());
        //@return listado de dominios
        return q.getResultList();
    }

    public List<Dominios> buscarHijos(Dominios dominio) {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio from Dominios dominio where dominio.domiPadre = :DOMINIO");
        q.setParameter("DOMINIO", dominio.getDomiCodigo());
        //@return listado de dominios
        return q.getResultList();
    }

    public Dominios buscarPadre(Dominios dominio) {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio from Dominios dominio where dominio.domiCodigo=:DOMINIO");
        q.setParameter("DOMINIO", dominio.getDomiPadre());
        //@return listado de dominios
        return (Dominios) q.getSingleResult();
    }

}
