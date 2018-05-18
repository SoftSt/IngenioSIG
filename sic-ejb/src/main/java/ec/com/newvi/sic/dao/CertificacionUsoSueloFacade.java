/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.CertificacionUsoSuelo;
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
public class CertificacionUsoSueloFacade extends AbstractFacade<CertificacionUsoSuelo, Integer> {

    public CertificacionUsoSueloFacade() {
        super(CertificacionUsoSuelo.class, Integer.class);
    }   
    
    public List<CertificacionUsoSuelo> buscarCertificacion(){
        // Busca un listado de Certificados de uso de suelo
        Query q = this.getEntityManager().createQuery("SELECT c FROM CertificacionUsoSuelo c where c.cssEstado=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
