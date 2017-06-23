/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class DominiosFacade extends AbstractFacade<Dominios, Integer> implements Serializable {

    public DominiosFacade() {
        super(Dominios.class, Integer.class);
    }

    /**
     * Devuelve un listado de dominios
     *
     * @return Listado de dominios
     */
    public List<Dominios> buscarDominios() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio FROM Dominios dominio where dominio.estadoDominio = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de dominios
        return q.getResultList();
    }

    public List<Dominios> buscarDominiosGrupos() {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT distinct dominio.domiGrupos FROM Dominios dominio");
        //@return listado de dominios
        return q.getResultList();
    }

    public List<Dominios> buscarDominiosPorGrupo(String grupo, String relacion) {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio FROM Dominios dominio where dominio.domiGrupos=:GRUPOS AND TRIM(dominio.domiRelacion) =:RELACION");
        q.setParameter("GRUPOS", grupo);
        q.setParameter("RELACION", relacion);
        //@return listado de dominios
        return q.getResultList();
    }

    public List<Dominios> buscarHijos(Dominios dominio) {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio from Dominios dominio where dominio.domiPadre = :DOMINIO");
        q.setParameter("DOMINIO", dominio.getDomiCodigo());
        //@return listado de dominios
        return q.getResultList();
    }

    public Dominios buscarPadre(Dominios dominio) {
        // Busca un listado de dominios
        Query q = this.getEntityManager().createQuery("SELECT dominio from Dominios dominio where dominio.domiCodigo=:DOMINIO");
        q.setParameter("DOMINIO", dominio.getDomiPadre());
        //@return listado de dominios
        return (Dominios) q.getSingleResult();
    }
    //TODO Crear una variable entorno para cambiar el nombre de la tabla a consultar

    public BigDecimal obtenerCOFF(BigDecimal dominio, String domiCalculo) {

        Query q = this.getEntityManager().createNativeQuery(""
                + "select d.domi_coefic "
                + "from cat_cat_dominios d "
                + "where d.domi_minimo < ? or d.domi_minimo = ? and "
                + "d.domi_maximo > ? or d.domi_maximo = ? and "
                + "rtrim(d.domi_calculo) = ?");
        q.setParameter(1, dominio);
        q.setParameter(2, dominio);
        q.setParameter(3, dominio);
        q.setParameter(4, dominio);
        q.setParameter(5, domiCalculo);

        //return Double.parseDouble((q.getResultList().toString()).substring(1, 7));
        return new BigDecimal((q.getResultList().toString()).substring(1, 7));

    }

    public BigDecimal obtenerVDEPRE(BigDecimal dominio, String domiDescripcion) {
        String domiCalculo = "ESTADO DE REPARACION";

        Query q = this.getEntityManager().createNativeQuery(""
                + "select d.domi_coefic "
                + "from cat_cat_dominios d "
                + "where d.domi_minimo < ? or d.domi_minimo = ? and "
                + "d.domi_maximo > ? or d.domi_maximo = ? and "
                + "rtrim(d.domi_descripcion) = ? and "
                + "rtrim(d.domi_calculo) = ?");
        q.setParameter(1, dominio);
        q.setParameter(2, dominio);
        q.setParameter(3, dominio);
        q.setParameter(4, dominio);
        q.setParameter(5, domiDescripcion);
        q.setParameter(6, domiCalculo);

        //return Double.parseDouble((q.getResultList().toString()).substring(1, 7));
        return new BigDecimal((q.getResultList().toString()).substring(1, 7));

    }

    public BigDecimal obtenerValor(Integer idPredio, String domiCalculo) {

        Query q = this.getEntityManager().createNativeQuery(""
                + "select sum(B.domi_coefic)"
                + "from cat_cat_dominios B, cat_cat_terreno A "
                + "where A.sts_codigo = B.domi_codigo"
                + " and rtrim(B.domi_calculo) = ? "
                + " and cod_catastral = ?");

        q.setParameter(1, domiCalculo);
        q.setParameter(2, idPredio);

        return new BigDecimal((q.getResultList().toString()).substring(1, 7));
        //return Double.parseDouble((q.getResultList().toString()).substring(1, 7));
    }

    public BigDecimal obtenerDetalleContruccion(Integer codPisos, String domiCalculo) {
        String domiGrupo = "DESCRIPCION EDIFICACION";
        BigDecimal resultado = new BigDecimal(0.0);

        Query q = this.getEntityManager().createNativeQuery(""
                + " select sum(B.domi_coefic)"
                + " from cat_cat_dominios B, cat_cat_pisosdetalle A "
                + " where A.sts_codigo = B.domi_codigo "
                + " and rtrim(B.domi_grupos) = ? "
                + " and rtrim(B.domi_calculo) = ? "
                //+ " B.domi_coefic is not null "
                + " and cod_pisos = ?");

        q.setParameter(1, domiGrupo);
        q.setParameter(2, domiCalculo);
        q.setParameter(3, codPisos);

        try {
            resultado = new BigDecimal((q.getResultList().toString()).substring(1, 7));
        } catch (Exception e) {
        }

        return resultado;
    }

    /*Select domi_coefic as valor from cat_cat_dominios  where rtrim(domi_codigo) = '200102' and rtrim(domi_calculo) = 'ZONAS VALORADAS M2'*/
    public BigDecimal obtenerValorPorCodigoCalculo(String domiCodigo, String domiCalculo) {
        //String domiCalculo = "ZONAS VALORADAS M2";

        Query q = this.getEntityManager().createNativeQuery(""
                + "select d.domi_coefic "
                + "from cat_cat_dominios d "
                + "where rtrim(d.domi_codigo) = ? and "
                + "rtrim(d.domi_calculo) = ?");
        q.setParameter(1, domiCodigo);
        q.setParameter(2, domiCalculo);

        //return Double.parseDouble((q.getResultList().toString()).substring(1, 7));
        return new BigDecimal((q.getResultList().toString()).substring(1, 7));

    }

    public BigDecimal obtenerValorPorCodigo(String domiCodigo) {

        Query q = this.getEntityManager().createNativeQuery(""
                + "select d.domi_coefic "
                + "from cat_cat_dominios d "
                + "where rtrim(d.domi_codigo) = ? ");
        q.setParameter(1, domiCodigo);

        return new BigDecimal((q.getResultList().toString()).substring(1, 7));

    }

    public Boolean tieneBasura(Integer codCatastral) {
        Boolean resultado = false;

        Query q = this.getEntityManager().createNativeQuery(""
                + "select sum(B.domi_coefic)"
                + "from cat_cat_dominios B, cat_cat_terreno A "
                + "where A.sts_codigo = B.domi_codigo"
                + " and A.sts_codigo = '060505' "
                + " and cod_catastral = ?");

        q.setParameter(1, codCatastral);
        
        try {
            BigDecimal a = new BigDecimal((q.getResultList().toString()).substring(1, 7));
            resultado = true;
        } catch (Exception e) {
        }
        return resultado;
    }

}
