/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author NEWVI
 */
public enum EnumTipoPermisos {
    
    L ("L", "Listar"),
    C ("C", "Crear"),
    E ("E", "Editar"),
    S ("S", "Suprimir"),
    A ("A", "Administrar"),
    N ("N", "Ninguno");
    
    private final String codTipoPermiso;
    private final String descripcionTipoPermiso;
    
    private EnumTipoPermisos(String codTipoPermiso, String descripcionTipoPermiso) {
        this.codTipoPermiso = codTipoPermiso;
        this.descripcionTipoPermiso = descripcionTipoPermiso;
    }

    public String getCodTipoPermiso() {
        return codTipoPermiso;
    }

    public String getDescripcionTipoPermiso() {
        return descripcionTipoPermiso;
    }
        
}
