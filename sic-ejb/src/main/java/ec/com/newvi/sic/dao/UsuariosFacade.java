package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import java.io.Serializable;
import javax.ejb.Stateless;

import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

@Stateless
@PermitAll
public class UsuariosFacade extends AbstractFacade<Usuarios, Integer> implements Serializable {

    private static final long serialVersionUID = -4594424897085245484L;

    public UsuariosFacade() {
        super(Usuarios.class, Integer.class);
    }

    /**
     * Devuelve un listado de usuarios
     *
     * @return Listado de usuarios
     */
    public List<Usuarios> buscarUsuarios() {
        // Busca un listado de usuarios
        Query q = this.getEntityManager().createQuery("SELECT usuario FROM Usuarios usuario where usuario.usuEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de usuarios
        return q.getResultList();
    }

    /**
     * Devuelve verdadero si existe un usuario registrado con el nombre
     * indicado, que no sea el actual.
     *
     * @param usuCodigo Nombre del usuario
     * @param idUsuario ID del usuario (secuencial)
     * @return
     */
    public Boolean existeNombreUsuarioRegistrado(String usuCodigo, Integer idUsuario) {
        String consulta = "SELECT usuario.usuUsuario FROM Usuarios usuario where usuario.usuUsuario = :USUARIO";
        if (!ComunUtil.esNulo(idUsuario)) {
            consulta = consulta.concat(" and usuario.usuId <> :ID_USUARIO");
        }
        //Buscar si el usuario se encuentra registrado
        Query q = this.getEntityManager().createQuery(consulta);
        q.setParameter("USUARIO", usuCodigo);
        if (!ComunUtil.esNulo(idUsuario)) {
            q.setParameter("ID_USUARIO", idUsuario);
        }
        //@return listado de usuarios
        return !q.getResultList().isEmpty();
    }

    public Boolean buscarUsuarioRepetidoPorEmail(String usuEmail) {
        Boolean retorno = false;
        //Buscar si el usuario se encuentra registrado
        Query q = this.getEntityManager().createQuery("SELECT usuario.usuEmai FROM Usuarios usuario where usuario.usuEmai = :EMAIL");
        q.setParameter("EMAIL", usuEmail);
        //@return listado de usuarios
        if (q.getResultList().isEmpty()) {
            retorno = true;
        }
        return retorno;
    }

    public Usuarios buscarUsuariosPorNombreUsuario(String nombreUsuario, SesionDto sesion) throws NewviExcepcion {
        if (!ComunUtil.esCadenaVacia(nombreUsuario)) {
            Query q = this.getEntityManager().createQuery("SELECT usuario FROM Usuarios usuario WHERE trim(usuario.usuPalabraclave) = :NOMBRE_USUARIO");
            q.setParameter("NOMBRE_USUARIO", nombreUsuario);
            try {
                return (Usuarios) q.getSingleResult();
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
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR012.presentarMensajeCodigo(), sesion);
            throw new NewviExcepcion(EnumNewviExcepciones.ERR012);
        }
    }

}
