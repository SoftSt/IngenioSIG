/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import javax.ejb.EJB;
import ec.com.newvi.sic.servicios.ContribuyentesServicio;

/**
 *
 * @author Andrés
 */
public abstract class AdminContribuyentesBB extends AdminSistemaBB {
    
    @EJB
    protected ContribuyentesServicio contribuyentesServicio;
    
}
