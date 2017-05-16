/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

import ec.com.newvi.sic.util.ComunUtil;

/**
 *
 * @author Andrés
 */
public enum EnumDescripcionServicios {
    Red_Provisional("Red Provisional"),
    Medidor_a("Medidor (a)"),
    Hormigon("Hormigón"),
    No_tiene_alcantarillado("No tiene alcantarillado"),
    Ladrillo("Ladrillo"),
    Red_Separada("Red Separada"),
    Aceras("Aceras"),
    Red_Telefonica("Red Telefónica"),
    Bordillos("Bordillos"),
    Medidor("Medidor"),
    Recoleccion_de_Basura("Recolección de Basura"),
    Bloque("Bloque"),
    Piedra("Piedra"),
    No_tiene_otros_servicios("No tiene otro servicios"),
    Cemento("Cemento"),
    Cesped("Césped"),
    Red_Publica("Red Pública"),
    Lastre("Lastre"),
    Asfalto("Asfalto"),
    Conexion_Domiciliaria("Conexión Domiciliaria"),
    No_tiene_Energia("No tiene Energía"),
    adoquin("adoquín"),
    Pozo_ciego("Pozo ciego"),
    No_Tiene("No Tiene"),
    Alumbrado("Alumbrado"),
    Peatonal("Peatonal"),
    Tierra("Tierra"),
    Tiene_Red_Publica("Tiene Red Pública"),
    Red_Combinada("Red Combinada"),
    Conexion_Domiciliaria_a("Conexión Domiciliaria (a)"),
    Adoquin("Adoquín"),
    Red_Definitiva("Red Definitiva"),
    No_tiene("No tiene"),
    Vehicular("Vehicular");

    private final String stsDescripcion;

    private EnumDescripcionServicios(String stsDescripcion) {
        this.stsDescripcion = stsDescripcion;
    }

    public String getStsDescripcion() {
        return stsDescripcion;
    }

    @Override
    public String toString() {
        return this.stsDescripcion;
    }

    public static EnumDescripcionServicios obtenerDescripcion(String nombre) {
        if (!ComunUtil.esCadenaVacia(nombre)) {
            for (EnumDescripcionServicios descripcion : EnumDescripcionServicios.values()) {
                if (descripcion.toString().contentEquals(nombre.trim())) {
                    return descripcion;
                }
            }
            return EnumDescripcionServicios.Bloque;
        } else {
            return EnumDescripcionServicios.Bloque;
        }
    }
}
