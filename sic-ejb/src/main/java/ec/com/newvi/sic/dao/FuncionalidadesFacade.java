/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
    
    public Funcionalidades obtenerFuncionalidadPorNombre(String nombreFuncionalidad, SesionDto sesion) throws NewviExcepcion {
        Query q = this.getEntityManager().createQuery("SELECT funcionalidad FROM Funcionalidades funcionalidad WHERE funcionalidad.funNombre = :NOMBREFUNCIONALIDAD ");
        q.setParameter("NOMBREFUNCIONALIDAD", nombreFuncionalidad);
        try {
            return (Funcionalidades) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR203.presentarMensajeCodigo().concat(" (").concat(ex.getMessage()).concat(")"), sesion);
            throw new NewviExcepcion(EnumNewviExcepciones.ERR203, ex);
        } catch (NoResultException ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR202.presentarMensajeCodigo().concat(" (").concat(ex.getMessage()).concat(")"), sesion);
            throw new NewviExcepcion(EnumNewviExcepciones.ERR202, ex);
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesion);
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, ex);
        }
    }

}
