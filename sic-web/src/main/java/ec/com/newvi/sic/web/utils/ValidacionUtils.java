/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.utils;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.web.MensajesFaces;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author israelavila
 */
public class ValidacionUtils {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static Boolean validarCorreoElectronico(String correoElectronico) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correoElectronico);
        return matcher.matches();
    }

    public static ValidatorException lanzarExcepcionValidacion(EnumNewviExcepciones excepcion) {
        return new ValidatorException(MensajesFaces.generarMensajeFaces(FacesMessage.SEVERITY_ERROR, excepcion.presentarExcepcion(), excepcion.presentarMensaje()), (Throwable) new NewviExcepcion(excepcion));
    }
    
}
