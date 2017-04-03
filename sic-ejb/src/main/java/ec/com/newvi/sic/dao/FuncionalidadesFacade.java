/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author NEWVI
 */
@Stateless
@PermitAll

public class FuncionalidadesFacade extends AbstractFacade<Funcionalidades, Integer> implements Serializable {

    public FuncionalidadesFacade() {
        super(Funcionalidades.class, Integer.class);
    }

    /**
     * Devuelve un listado de funcionalidades
     *
     * @return Listado de funcionalidades
     */
    public List<Funcionalidades> buscarFuncionalidades() {
        Query q = this.getEntityManager().createQuery("SELECT funcionalidad FROM Funcionalidades funcionalidad where funcionalidad.funEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        return q.getResultList();
    }

    /**
     * Devuelve un listado de subfuncionalidades dado una funcionalidad dada
     *
     * @param padre Funcionalidad al que pertenece las subfuncionalidades
     * @return
     */
    public List<Funcionalidades> buscarFuncionalidadesPorPadre(Funcionalidades padre) {
        String cadenaPadre;
        if (ComunUtil.esNulo(padre)) {
            cadenaPadre = " AND funcionalidad.funIdPadre IS NULL ";
        } else {
            cadenaPadre = " AND funcionalidad.funIdPadre = :PADRE ";
        }
        Query q = this.getEntityManager().createQuery("SELECT funcionalidad "
                + " FROM Funcionalidades funcionalidad "
                + " WHERE funcionalidad.funEstado = :ESTADO "
                + cadenaPadre 
                + " ORDER BY funcionalidad.funOrden ");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        if (!ComunUtil.esNulo(padre)) {
            q.setParameter("PADRE", padre);
            
        }
        return q.getResultList();
    }

}
