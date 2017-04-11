/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.ContribucionMejoras;
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
public class ContribucionMejorasFacade extends AbstractFacade<ContribucionMejoras, Integer> implements Serializable {

    public ContribucionMejorasFacade() {
        super(ContribucionMejoras.class, Integer.class);
    }
    
    public List<ContribucionMejoras> buscarContribucionMejoras() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT contribuciones FROM ContribucionMejoras contribuciones where contribuciones.obrEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de dominios
        return q.getResultList();
    }
    
}
