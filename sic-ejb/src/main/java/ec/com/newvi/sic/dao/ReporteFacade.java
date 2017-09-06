/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll
public class ReporteFacade extends AbstractFacade<Reporte, Integer> {

    public ReporteFacade() {
        super(Reporte.class, Integer.class);
    }

    /**
     * Obtiene un reporte dado un enumerado de nombre de reporte.
     *
     * @param nombreReporte Enumerado que corresponde al nombre del reporte a
     * generar.
     * @param sesion Sesión que genera el reporte
     * @return
     * @throws NewviExcepcion
     */
    public Reporte obtenerReportePorNombre(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
        Query q = this.getEntityManager().createQuery("SELECT reporteBuscado FROM Reporte reporteBuscado where reporteBuscado.nombreReporte = :NOMBREREPORTE");
        q.setParameter("NOMBREREPORTE", nombreReporte);
        try {
            return (Reporte) q.getSingleResult();
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
