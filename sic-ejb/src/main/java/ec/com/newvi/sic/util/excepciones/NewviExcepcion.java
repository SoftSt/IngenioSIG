/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.util.excepciones;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;

/**
 *
 * @author NEWVI
 */
public class NewviExcepcion extends Exception {
    public NewviExcepcion() {
    }
    
    public NewviExcepcion(String mensaje) {
        super(mensaje);
    }
    
    public NewviExcepcion(Throwable causa) {
        super(causa);
    }
    
    public NewviExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public NewviExcepcion(EnumNewviExcepciones excepcion) {
        super(excepcion.presentarMensajeCodigo());
    }
    
    public NewviExcepcion(EnumNewviExcepciones excepcion, Throwable causa) {
        super(excepcion.presentarMensajeCodigo(), causa);
    }
    
}
