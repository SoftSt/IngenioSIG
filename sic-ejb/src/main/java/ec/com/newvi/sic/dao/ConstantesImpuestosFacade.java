/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
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
public class ConstantesImpuestosFacade extends AbstractFacade<ConstantesImpuestos, Integer> implements Serializable {

    public ConstantesImpuestosFacade() {
        super(ConstantesImpuestos.class, Integer.class);
    }
    
    public List<ConstantesImpuestos> obtenerConstantesImpuestosPorTipo(String stsTipo){
        Query q = this.getEntityManager().createQuery("SELECT constantes FROM ConstantesImpuestos constantes where constantes.stsTipo =:TIPO");
        q.setParameter("TIPO", stsTipo);
        //@return listado de Constantes Impuestos
        return q.getResultList();
    }
    
    public List<ConstantesImpuestos> buscarConstantesImpuestos() {
        Query q = this.getEntityManager().createQuery("SELECT constantes FROM ConstantesImpuestos constantes where constantes.conImpuestoEstado =:ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();
        
    }
    
}
