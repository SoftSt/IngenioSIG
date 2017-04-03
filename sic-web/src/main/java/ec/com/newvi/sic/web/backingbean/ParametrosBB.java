/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.servicios.ParametrosServicio;
import javax.ejb.EJB;

/**
 *
 * @author NEWVI
 */
public abstract class ParametrosBB extends SistemaBB{
   
    @EJB
    protected ParametrosServicio parametrosServicio;
}
