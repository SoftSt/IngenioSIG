/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
import ec.com.newvi.sic.modelo.Predios;
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
public class ConstantesDescuentosFacade extends AbstractFacade<CatConConstantesdescuentos, Integer>  implements Serializable{

    public ConstantesDescuentosFacade() {
        super(CatConConstantesdescuentos.class, Integer.class);
    }
    
    public List<CatConConstantesdescuentos> buscarDescuentos(){
    // Busca un listado de Predios       
        Query q = this.getEntityManager().createQuery("SELECT descuentos FROM CatConConstantesdescuentos descuentos WHERE descuentos.stsEstado =:ESTADO ORDER BY descuentos.codConstantesdescuentos ASC");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de Predios
        return q.getResultList();
    }
}
