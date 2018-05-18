/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.FirmaReporte;
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
public class FirmaReporteFacade extends AbstractFacade<FirmaReporte, Integer> {

    public FirmaReporteFacade() {
        super(FirmaReporte.class, Integer.class);
    }   
    
    public List<FirmaReporte> buscarFirmaReporte(){
        // Busca un listado de IRM
        Query q = this.getEntityManager().createQuery("SELECT f FROM FirmaReporte f WHERE f.frrEstado=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
