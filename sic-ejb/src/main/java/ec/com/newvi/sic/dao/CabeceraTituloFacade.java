/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.CabeceraTitulo;
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
public class CabeceraTituloFacade extends AbstractFacade<CabeceraTitulo, Integer> implements Serializable{

    public CabeceraTituloFacade() {
        super(CabeceraTitulo.class, Integer.class);
    }
    
    public List<CabeceraTitulo> buscarListaCabeceraTitulos() {
        Query q = this.getEntityManager().createQuery("SELECT titulos FROM CabeceraTitulo titulos WHERE titulos.stsRegistro =:ESTADO ORDER BY titulos.secTitulo ASC");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();
    }
    
    
}
