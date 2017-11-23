/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.modelo.PisoDetalle;
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
public class PisosDetalleFacade extends AbstractFacade<PisoDetalle, Integer> implements Serializable {

    public PisosDetalleFacade() {
        super(PisoDetalle.class, Integer.class);
    }
    
    public List<PisoDetalle> buscarStsEstadoDetallePisos() {
        // Busca un listado de Detalle Pisos
        Query q = this.getEntityManager().createQuery("SELECT distinct detalle.estadoDetalle FROM PisoDetalle detalle where detalle.estadoDetalle IS NOT NULL");
        //@return listado de Detalle Pisos
        return q.getResultList();
    } 
    
}
