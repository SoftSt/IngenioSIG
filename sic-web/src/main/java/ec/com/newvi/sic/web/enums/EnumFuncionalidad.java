/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.enums;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;

/**
 *
 * @author israelavila
 */
public enum EnumFuncionalidad {
    CATASTRO_URBANO("CATASTRO_URBANO"),
    FORMULARIOS_ECONOMICOS("FORMULARIOS_ECONOMICOS"),
    ADMINISTRACION("ADMINISTRACION"),
    ADMIN_USUARIOS("ADMIN_USUARIOS"),
    ADMIN_FUNCIONALIDADES("ADMIN_FUNCIONALIDADES"),
    ADMIN_ROLES_Y_PERMISOS("ADMIN_ROLES_Y_PERMISOS"),
    ADMIN_DOMINIOS("ADMIN_DOMINIOS"),
    PARAMETROS_PARA_CALCULOS("PARAMETROS_PARA_CALCULOS"),
    GESTION_CONTRIBUYENTES("GESTION_CONTRIBUYENTES"),
    URBANO_FICHA_PREDIAL("URBANO_FICHA_PREDIAL"),
    URBANO_CONTRIBUCION_MEJORAS("URBANO_CONTRIBUCION_MEJORAS"),
    URBANO_SIMULACION_FICHA_CATASTRAL("URBANO_SIMULACION_FICHA_CATASTRAL"),
    FORMULARIO_GESTION_FORMULARIOS_ECONOMICOS("FORMULARIO_GESTION_FORMULARIOS_ECONOMICOS"),
    SISTEMA_GEOGRAFICO("SISTEMA_GEOGRAFICO"),
    VISOR_PREDIAL("VISOR_PREDIAL"),
    GESTION_PREDIAL("GESTION_PREDIAL"),
    NUEVOS_PREDIOS("NUEVOS_PREDIOS"),
    EDICION_PREDIOS("EDICION_PREDIOS"),
    ELIMINACION_PREDIOS("ELIMINACION_PREDIOS"),
    PARAMETROS_DEL_SISTEMA("PARAMETROS_DEL_SISTEMA"), 
    DESMEMBRACION_PREDIOS("DESMEMBRACION_PREDIOS"),
    RECAUDACIONES("RECAUDACIONES")
    ;
    
    private final String nombreFuncionalidad;

    public String getNombreFuncionalidad() {
        return nombreFuncionalidad;
    }
    
    private EnumFuncionalidad(String nombrePantalla) {
        this.nombreFuncionalidad = nombrePantalla;
    }
    
    public static EnumFuncionalidad obtenerFuncionalidadPorNombre(String nombreFuncionalidad) throws NewviExcepcion {
        for (EnumFuncionalidad funcionalidad : EnumFuncionalidad.values()) {
            if (funcionalidad.getNombreFuncionalidad().contentEquals(nombreFuncionalidad)) {
                return funcionalidad;
            }
        }
        throw new NewviExcepcion(EnumNewviExcepciones.ERR202);
    }
}
