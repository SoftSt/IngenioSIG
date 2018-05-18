/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.RegulacionMunicipal;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.ArrayList;
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
public class RegulacionMunicipalFacade extends AbstractFacade<RegulacionMunicipal, Integer> {

    public RegulacionMunicipalFacade() {
        super(RegulacionMunicipal.class, Integer.class);
    }   
    
    public List<RegulacionMunicipal> buscarIRM(){
        // Busca un listado de IRM
        Query q = this.getEntityManager().createQuery("SELECT irm FROM RegulacionMunicipal irm WHERE irm.estadoInforme=:ESTADO ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();        
    }
}
