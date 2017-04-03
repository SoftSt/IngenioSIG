/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Permisos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author israelavila
 */
@Stateless
@PermitAll
public class PermisosFacade extends AbstractFacade<Permisos, Integer> implements Serializable {

    public PermisosFacade() {
        super(Permisos.class, Integer.class);
    }
    
    /**
     * Devuelve un listado de permisos
     * @return Listado de permisos
     */
    public List<Permisos> buscarPermisos() {
        // Busca un listado de permisos
        Query q = this.getEntityManager().createQuery("SELECT permiso FROM Permisos permiso where permiso.estadoPermiso = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de permisos
        return q.getResultList();
    }
    
}
