/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.servicios.CatastroServicio;
import javax.ejb.EJB;

/**
 *
 * @author Andrés
 */
public abstract class AdminAvaluo extends AdminSistemaBB{
    @EJB
    protected CatastroServicio catastroServicio;
}
