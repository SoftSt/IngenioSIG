/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.util.logs;

import ec.com.newvi.sic.dto.SesionDto;
import org.apache.log4j.Logger;

/**
 *
 * @author NEWVI
 */
public class LoggerNewvi {

    private Class clase;

    protected LoggerNewvi(Class clase) {
        this.clase = clase;
    }

    public static LoggerNewvi getLogNewvi(Class clase) {
        return new LoggerNewvi(clase);
    }

    private String getInfo(SesionDto sesion) {
        String ipCliente = "N/D";
        String nomServidor = "N/D";
        String nomUsuario = "N/D";
        String nomSistema = "N/D";

        // TODO tomar los datos desde la sesion
        if (sesion != null) {
            nomServidor = sesion.getNombreServidor();
            nomUsuario = sesion.getUsuarioRegistrado().getUsuPalabraclave().trim();
            //todo colocar variable del nombre del sistema
            nomSistema = "SIC";
            ipCliente = sesion.getDireccionIP();
        }

        return "cliente: " + ipCliente + " - server: " + nomServidor + " - usuario: " + nomUsuario + " - sistema: " + nomSistema + " - ";
    }

    private Logger getLOGGER() {
        return Logger.getLogger(clase);
    }

    public void info(Object message, SesionDto sesion) {
        getLOGGER().info(getInfo(sesion) + message);
    }

    public void warn(Object message, SesionDto sesion) {
        getLOGGER().warn(getInfo(sesion) + message);
    }

    public void debug(Object message, SesionDto sesion) {
        getLOGGER().debug(getInfo(sesion) + message);
    }

    public void error(Throwable t, SesionDto sesion) {
        getLOGGER().error(getInfo(sesion) + t.getMessage(), t);
    }

    public void error(String message, SesionDto sesion) {
        getLOGGER().error(getInfo(sesion) + message);
    }

    public void error(Object message, Throwable t, SesionDto sesion) {
        getLOGGER().error(getInfo(sesion) + message, t);
    }

    public void fatal(Object message, Throwable t, SesionDto sesion) {
        getLOGGER().error(getInfo(sesion) + message, t);
    }

    public void fatal(Object message, SesionDto sesion) {
        getLOGGER().error(getInfo(sesion) + message);
    }
}
