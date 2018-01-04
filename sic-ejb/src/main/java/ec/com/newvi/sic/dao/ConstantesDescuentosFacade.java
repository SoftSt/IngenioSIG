/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.ConstantesDescuentos;
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
public class ConstantesDescuentosFacade extends AbstractFacade<ConstantesDescuentos, Integer> implements Serializable {

    public ConstantesDescuentosFacade() {
        super(ConstantesDescuentos.class, Integer.class);
    }

    public List<ConstantesDescuentos> buscarDescuentos() {
        Query q = this.getEntityManager().createQuery("SELECT descuentos FROM ConstantesDescuentos descuentos WHERE descuentos.estadoDescuento =:ESTADO ORDER BY descuentos.codConstantesdescuentos ASC");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();
    }

    public ConstantesDescuentos buscarDescuentoRecargoPorMesYQuincena(String mes, String quincena) {
        Query q = this.getEntityManager().createQuery("SELECT descuentos FROM ConstantesDescuentos descuentos WHERE descuentos.stsMes =:MES AND descuentos.stsQuincena =:QUINCENA AND descuentos.estadoDescuento =:ESTADO");
        q.setParameter("MES", mes);
        q.setParameter("QUINCENA", quincena);
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return (ConstantesDescuentos) q.getSingleResult();
    }
}
