/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Predios;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class PrediosFacade extends AbstractFacade<Predios, Integer> implements Serializable {

    public PrediosFacade() {
        super(Predios.class, Integer.class);
    }

    public List<Predios> buscarPredio() {
        // Busca un listado de Predios
        //Query q = this.getEntityManager().createQuery("SELECT predio FROM Predios predio where predio.catEstado = :ESTADO");
        Query q = this.getEntityManager().createQuery("SELECT predio FROM Predios predio where PREDIO.catEstado =:ESTADO ORDER BY predio.codCatastral ASC");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de Predios
        return q.getResultList();
    }

    public Integer obtenerNumeroPredios() {
        Integer resultado;
        Query q = this.getEntityManager().createNativeQuery(""
                + "select sum(1)"
                + "from cat_cat_predios");

        try {
            //Integer dd = new Integer(q.getResultList().toString());
            resultado = new Integer(q.getResultList().toString());
        } catch (Exception e) {
            resultado = 0;
        }

        return resultado;
    }
}
