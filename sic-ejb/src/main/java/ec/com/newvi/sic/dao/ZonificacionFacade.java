/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.modelo.Zonificacion;
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
public class ZonificacionFacade extends AbstractFacade<Zonificacion, Integer> {

    public ZonificacionFacade() {
        super(Zonificacion.class, Integer.class);
    }   
    
    public List<Zonificacion> buscarZonificacion(){
        // Busca un listado de Zonificaciones
        Query q = this.getEntityManager().createQuery("SELECT z FROM Zonificacion z where z.znfEstado=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
