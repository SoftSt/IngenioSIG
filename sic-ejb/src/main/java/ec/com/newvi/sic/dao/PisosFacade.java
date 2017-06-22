/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Pisos;
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
public class PisosFacade extends AbstractFacade<Pisos, Integer> implements Serializable {

    public PisosFacade() {
        super(Pisos.class, Integer.class);
    }

    public List<Pisos> buscarPisos() {
        // Busca un listado de bloques
        Query q = this.getEntityManager().createQuery("SELECT piso FROM Pisos piso where piso.pisEstado = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de bloques
        return q.getResultList();
    }
    
    public Object[] obtenerDatosPisoPorBloque(Integer codBloque) {
        //String domiGrupo = "DESCRIPCION EDIFICACION";
        //BigDecimal resultado = new BigDecimal(0.0);

        Query q = this.getEntityManager().createNativeQuery(""
                + " select cod_pisos, nom_piso, val_areapiso, EXTRACT(ISOYEAR FROM now())-(val_anioconstruccion)+1, val_anioreparacion, sts_estado "
                + " from cat_cat_pisos "
                + " where cod_bloques = ? ");

        q.setParameter(1, codBloque);
        
        return q.getResultList().toArray();
        //return Double.parseDouble((q.getResultList().toString()).substring(1, 7));
    }

}
