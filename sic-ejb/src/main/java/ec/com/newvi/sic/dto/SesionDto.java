/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Usuarios;

/**
 *
 * @author israelavila
 */
public class SesionDto {

    private Usuarios usuarioRegistrado;
    private String direccionIP;
    private String nombreServidor;

    public SesionDto() {
    }

    public SesionDto(Usuarios usuarioRegistrado, String direccionIP, String nombreServidor) {
        this.usuarioRegistrado = usuarioRegistrado;
        this.direccionIP = direccionIP;
        this.nombreServidor = nombreServidor;
    }
    
    public Usuarios getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(Usuarios usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }
    
}
