/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dao;


import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.TituloMovimientos;
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
public class TituloMovimientosFacade extends AbstractFacade<TituloMovimientos, Integer> implements Serializable{

    public TituloMovimientosFacade() {
        super(TituloMovimientos.class, Integer.class);
    }
    
    public List<TituloMovimientos> buscarMovimientosTitulo() {
        // Busca un listado de movimientos titulos
        Query q = this.getEntityManager().createQuery("SELECT movimientos FROM TituloMovimientos movimientos where movimientos.estadoMovimiento = :ESTADO");
        q.setParameter("ESTADO", EnumEstadoRegistro.A);
        //@return listado de movimientos  titulos
        return q.getResultList();
    }
    
    
}
