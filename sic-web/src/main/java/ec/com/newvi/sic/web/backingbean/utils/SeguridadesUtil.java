/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean.utils;

import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;

/**
 *
 * @author NEWVI
 */
public class SeguridadesUtil {

    public static Funcionalidades seleccionarFuncionalidad(Integer idFuncionalidad, SeguridadesServicio seguridadesServicio) throws NewviExcepcion {
        return seleccionarFuncionalidadPorCodigo(idFuncionalidad, seguridadesServicio);
    }

    public static Funcionalidades seleccionarFuncionalidadPorCodigo(Integer idFuncionalidad, SeguridadesServicio seguridadesServicio) throws NewviExcepcion {
        return seguridadesServicio.seleccionarFuncionalidad(idFuncionalidad);
    }

}
