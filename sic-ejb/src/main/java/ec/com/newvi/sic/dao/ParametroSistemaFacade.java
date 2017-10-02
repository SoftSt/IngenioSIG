/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumParametroSistema.EnumGrupoParametroSistema;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.modelo.ParametroSistema;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author israelavila
 */
@Stateless
@PermitAll
public class ParametroSistemaFacade extends AbstractFacade<ParametroSistema, Integer> {

    public ParametroSistemaFacade() {
        super(ParametroSistema.class, Integer.class);
    }

    /**
     * Obtiene un parámetro dado el enumerado del nombre del parámetro. Sirve
     * para encontrar un parámetro dado en el sistema.
     *
     * @param parametro Enumerado del parámetro a entregar
     * @param sesion Sesión del usuario que realiza la consulta
     * @return Registro del parámetro encontrado
     * @throws NewviExcepcion
     */
    public ParametroSistema obtenerParametroPorNombre(EnumParametroSistema parametro, SesionDto sesion) throws NewviExcepcion {
        Query q = this.getEntityManager().createQuery("SELECT parametro FROM ParametroSistema parametro where parametro.parametro = :PARAMETRO");
        q.setParameter("PARAMETRO", parametro);
        try {
            return (ParametroSistema) q.getSingleResult();
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
    
    public List<ParametroSistema> obtenerListaParametrosPorGrupo(EnumGrupoParametroSistema grupo, SesionDto sesion) throws NewviExcepcion {
        String consultaPorGrupo = "";
        if (!ComunUtil.esNulo(grupo)) {
            consultaPorGrupo = "WHERE parametro.grupo = :GRUPO";
        }
        Query q = this.getEntityManager().createQuery("SELECT parametro FROM ParametroSistema parametro ".concat(consultaPorGrupo));
        if (!ComunUtil.esNulo(grupo)) {
            q.setParameter("GRUPO", grupo);
        }
        try {
            return q.getResultList();
        } catch (NoResultException ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR202.presentarMensajeCodigo().concat(" (").concat(ex.getMessage()).concat(")"), sesion);
            return new ArrayList<ParametroSistema>();
        } catch (Exception ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesion);
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, ex);
        }
        
    }
    
}
