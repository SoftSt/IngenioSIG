/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.modelo.Titulos;
import java.io.Serializable;
import java.util.Date;
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
public class TituloFacade extends AbstractFacade<Titulos, Integer> implements Serializable {

    public TituloFacade() {
        super(Titulos.class, Integer.class);
    }

    public List<Titulos> buscarTitulos() {
        // Busca un listado de titulos
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.tituloEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de titulos
        return q.getResultList();
    }

    public List<Titulos> buscarTitulosGenerados(Date fechaEmision) {
        // Busca un listado de titulos
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.fecEmision = :FECHAEMISION AND titulo.stsEstado = :ESTADOTITULO AND titulo.tituloEstado = :ESTADO");
        q.setParameter("FECHAEMISION", fechaEmision);
        q.setParameter("ESTADOTITULO", EnumEstadoTitulo.TITULO_EMITIDO);
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de titulos
        return q.getResultList();
    }
    
    public List<Titulos> buscarTitulosGeneradosPorAnio(String anioEmision) {
        // Busca un listado de titulos
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where cast(extract(YEAR FROM titulo.fecEmision) as text)= :ANIOEMISION AND titulo.tituloEstado = :ESTADO");
        q.setParameter("ANIOEMISION", anioEmision);
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de titulos
        return q.getResultList();
    }

    public List<Titulos> buscarTitulosPorCodigoCatastral(Integer codCatastral) {
        // Busca un listado de titulos
        //Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.tituloEstado = :ESTADO AND titulo.codCatastral.codCatastral = :CODCATASTRAL AND titulo.stsEstado = :ESTADOTITULO OR titulo.stsEstado = :ESTADOTITULOAUX");
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.tituloEstado = :ESTADO AND titulo.codCatastral.codCatastral = :CODCATASTRAL AND titulo.stsEstado = :ESTADOTITULO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        q.setParameter("ESTADOTITULO", EnumEstadoTitulo.TITULO_EMITIDO);
        q.setParameter("CODCATASTRAL", codCatastral);
        //@return listado de titulos
        return q.getResultList();
    }
    /*public List<Titulos> buscarTitulosPorCodigoCatastral(Integer codCatastral) {
        // Busca un listado de titulos
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.tituloEstado = :ESTADO AND titulo.codCatastral.codCatastral = :CODCATASTRAL AND titulo.stsEstado = :ESTADOTITULO OR titulo.stsEstado = :ESTADOTITULOAUX");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        q.setParameter("ESTADOTITULO", EnumEstadoTitulo.TITULO_EMITIDO);
        q.setParameter("ESTADOTITULOAUX", EnumEstadoTitulo.TITULO_DESMARCADO);
        q.setParameter("CODCATASTRAL", codCatastral);
        //@return listado de titulos
        return q.getResultList();
    }*/

    public List<Titulos> buscarTitulosPorTipo(EnumEstadoTitulo tipoTitulo) {
        // Busca un listado de titulos
        Query q = this.getEntityManager().createQuery("SELECT titulo FROM Titulos titulo where titulo.tituloEstado = :ESTADO AND titulo.stsEstado = :ESTADOTITULO");
        q.setParameter("ESTADOTITULO", tipoTitulo);
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de titulos
        return q.getResultList();
    }
}
