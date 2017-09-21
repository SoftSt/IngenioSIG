/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.enums.EnumTipoPermisos;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NEWVI
 */
public class AsignacionPermisosDto {

    private AsignacionPermisos asignacionPermiso;
    private Funcionalidades funcionalidad;
    private Permisos permiso;
    private EnumTipoPermisos operacion;
    private List<AsignacionPermisosDto> listaPermisosSubfuncionalidades;

    public AsignacionPermisos getAsignacionPermiso() {
        return asignacionPermiso;
    }

    public void setAsignacionPermiso(AsignacionPermisos asignacionPermiso) {
        this.asignacionPermiso = asignacionPermiso;
    }

    public Funcionalidades getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Funcionalidades funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }

    public EnumTipoPermisos getOperacion() {
        return operacion;
    }

    public void setOperacion(EnumTipoPermisos Operacion) {
        this.operacion = Operacion;
    }

    public List<AsignacionPermisosDto> getListaPermisosSubfuncionalidades() {
        return listaPermisosSubfuncionalidades;
    }

    public void setListaPermisosSubfuncionalidades(List<AsignacionPermisosDto> listaPermisosSubfuncionalidades) {
        this.listaPermisosSubfuncionalidades = listaPermisosSubfuncionalidades;
    }
    
    public final void generarListaPermisosSubfuncionalidades(SeguridadesServicio seguridadesServicio, SesionDto sesion) {
        this.listaPermisosSubfuncionalidades = new ArrayList<>();
        for (Funcionalidades subfuncionalidad : funcionalidad.getListaSubFuncionalidadesActivas()) {
            this.listaPermisosSubfuncionalidades.add(new AsignacionPermisosDto(permiso, subfuncionalidad, seguridadesServicio, sesion));
        }
    }
    
    public AsignacionPermisosDto(Permisos permisoAsignado, Funcionalidades funcionalidadAsignada, SeguridadesServicio seguridadesServicio, SesionDto sesion) {
            AsignacionPermisos permisoActual = null;
            try {
                permisoActual = seguridadesServicio.buscarAsignacionPermisos(permisoAsignado, funcionalidadAsignada, sesion);
            } catch (NewviExcepcion ex) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesion);
            }
            if (!ComunUtil.esNulo(permisoActual)
                    && !ComunUtil.esNulo(permisoActual.getPefOperaciones())) {
                this.asignacionPermiso = permisoActual;
                this.operacion = permisoActual.getPefOperaciones();
            } else {
                this.asignacionPermiso = null;
                this.operacion = EnumTipoPermisos.N;
            }
            this.funcionalidad = funcionalidadAsignada;
            this.permiso = permisoAsignado;
            generarListaPermisosSubfuncionalidades(seguridadesServicio, sesion);
    }
}
