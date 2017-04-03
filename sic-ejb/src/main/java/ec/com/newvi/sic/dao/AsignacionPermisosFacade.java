/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
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

public class AsignacionPermisosFacade extends AbstractFacade<AsignacionPermisos, Integer> implements Serializable {

    private static final long serialVersionUID = -4594424897085245485L;
    
    public AsignacionPermisosFacade() {
        super(AsignacionPermisos.class, Integer.class);
    }

    /**
     * Devuelve un listado de funcionalidades
     *
     * @return Listado de funcionalidades
     */
    public List<AsignacionPermisos> buscarAsiganciones() {
        // Busca un listado de funcionalidades
        Query q = this.getEntityManager().createQuery("SELECT asigancion FROM AsignacionPermisos asigancion where asigancion.pefEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de funcionalidades
        return q.getResultList();
    }

    /**
     * Busca permisos asignados a una función y a un permiso determinado.
     * @param permiso El objeto del permiso a buscar
     * @param funcionalidad El objeto de la funcionalidad a buscar
     * @return Objeto encontrado de asignación de permisos. Si no se encuentra, devuelve NULL.
     * @throws NewviExcepcion Si se encuentra màs de un resultado, arroja un error de tipo NewviException
     */
    public AsignacionPermisos buscarAsignacionesPermisoPorPermisoFuncionalidad(Permisos permiso, Funcionalidades funcionalidad, SesionDto sesion) throws NewviExcepcion {
        if (!ComunUtil.esNulo(permiso)
                && !ComunUtil.esNulo(funcionalidad)) {
            Query q = this.getEntityManager().createQuery("SELECT permisoAsignado "
                    + " FROM AsignacionPermisos permisoAsignado "
                    + " WHERE permisoAsignado.perId = :PERMISO "
                    + " AND permisoAsignado.rolId = :FUNCIONALIDAD ");
            q.setParameter("PERMISO", permiso);
            q.setParameter("FUNCIONALIDAD", funcionalidad);
            try {
                return (AsignacionPermisos) q.getSingleResult();
            } catch (NonUniqueResultException ex) {
                throw new NewviExcepcion(EnumNewviExcepciones.ERR203, ex);
            } catch (NoResultException ex) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR203.presentarMensajeCodigo().concat(" (").concat(ex.getMessage()).concat(")"), sesion);
                return null;
            } catch (Exception ex) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesion);
                throw new NewviExcepcion(EnumNewviExcepciones.ERR000, ex);
            }
        } else {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesion);
            throw new NewviExcepcion(EnumNewviExcepciones.ERR010);
        }
    }

}
