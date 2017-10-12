/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Propiedad;
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
public class PropiedadFacade extends AbstractFacade<Propiedad, Integer> implements Serializable {

    public PropiedadFacade() {
        super(Propiedad.class, Integer.class);
    }

    public List<Propiedad> buscarPropiedad() {
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT propietario FROM Propiedad propietario where propietario.proEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de bloques
        return q.getResultList();
    }

}
