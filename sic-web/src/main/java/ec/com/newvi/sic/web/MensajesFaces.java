/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author NEWVI
 */
public class MensajesFaces {

    public enum EnumMensajes {
        INFORMACION("Informacion"), ERROR("Error"), ADVERTENCIA("Advertencia");
        private String valor;

        private EnumMensajes(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }

    public static void mensajeInformacion(String mensaje) {
        mensajeInformacion(mensaje, null);
    }

    public static void mensajeInformacion(String mensaje, String elemento) {
        presentarMensajeFaces(FacesMessage.SEVERITY_INFO, EnumMensajes.INFORMACION.getValor(), mensaje, elemento);
    }
    
    public static void mensajeError(String mensaje) {
        mensajeError(mensaje, null);
    }

    public static void mensajeError(String mensaje, String elemento) {
        presentarMensajeFaces(FacesMessage.SEVERITY_ERROR, EnumMensajes.ERROR.getValor(), mensaje, elemento);
    }
    
    public static void mensajeAdvertencia(String mensaje) {
        mensajeAdvertencia(mensaje, null);
    }

    public static void mensajeAdvertencia(String mensaje, String elemento) {
        presentarMensajeFaces(FacesMessage.SEVERITY_WARN, EnumMensajes.ADVERTENCIA.getValor(), mensaje, elemento);
    }
    
    public static FacesMessage generarMensajeFaces(FacesMessage.Severity severity, String titulo, String mensaje){
        return new FacesMessage(severity, titulo, mensaje);
    }

    private static void presentarMensajeFaces(FacesMessage.Severity severity, String titulo, String mensaje, String elemento) {
        String auxiliarMensaje = mensaje;
        if (auxiliarMensaje.toUpperCase().contains("EXCEPTION")
                || auxiliarMensaje.toUpperCase().contains("EXCEPCION")) {
            Integer indice = auxiliarMensaje.toUpperCase().lastIndexOf("EXCEPTION") + 9;
            auxiliarMensaje = auxiliarMensaje.substring(indice);
        }
        FacesContext.getCurrentInstance().addMessage(elemento, generarMensajeFaces(severity, titulo, auxiliarMensaje));
    }
    
}
