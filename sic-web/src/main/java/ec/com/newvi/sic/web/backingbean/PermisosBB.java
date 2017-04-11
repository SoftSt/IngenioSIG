/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.AsignacionPermisosDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumTipoPermisos;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.backingbean.utils.SeguridadesUtil;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class PermisosBB extends AdminSeguridadesBB {

    private Permisos permiso;
    private List<Permisos> listaPermisos;

    private Funcionalidades funcionalidad;
    private List<AsignacionPermisosDto> listaFuncionalidadesDto;

    private TreeNode listaArbolFuncionalidadesDto;

    private AsignacionPermisos asignacionPermiso;
    private EnumTipoPermisos[] listaTipoPermisos;
    private EnumPantallaMantenimiento pantallaActual;

    public List<AsignacionPermisosDto> getListaFuncionalidadesDto() {
        return listaFuncionalidadesDto;
    }

    public void setListaFuncionalidadesDto(List<AsignacionPermisosDto> listaFuncionalidadesDto) {
        this.listaFuncionalidadesDto = listaFuncionalidadesDto;
    }

    public TreeNode getListaArbolFuncionalidadesDto() {
        return listaArbolFuncionalidadesDto;
    }

    public void setListaArbolFuncionalidadesDto(TreeNode listaArbolFuncionalidadesDto) {
        this.listaArbolFuncionalidadesDto = listaArbolFuncionalidadesDto;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }

    public List<Permisos> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permisos> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public EnumTipoPermisos[] getListaTipoPermisos() {
        return (EnumTipoPermisos[]) listaTipoPermisos.clone();
    }

    public void setListaTipoPermisos(EnumTipoPermisos[] listaTipoPermisos) {
        this.listaTipoPermisos = (EnumTipoPermisos[]) listaTipoPermisos.clone();
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @PostConstruct
    public void init() {
        this.permiso = new Permisos();
        actualizarListadoPermisos();
        listaFuncionalidadesDto = new ArrayList<AsignacionPermisosDto>();
        listaTipoPermisos = EnumTipoPermisos.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PERMISOS_LISTA_TITULO,
                EnumEtiquetas.PERMISOS_LISTA_ICONO,
                EnumEtiquetas.PERMISOS_LISTA_DESCRIPCION);
    }

    /**
     * Actualiza el listado de permisos.
     */
    private void actualizarListadoPermisos() {
        listaPermisos = seguridadesServicio.consultarPermisos();
    }

    /**
     * Actualiza el listado de funcionalidades asignadas al permiso
     *
     * @param idPermiso Integer que contiene el ID del permiso seleccionado.
     */
    public void actulizarListadoFuncionalidades(Integer idPermiso) {
        // Seleccionar el permiso para edición
        this.seleccionarPermiso(idPermiso);
        // Obtener las funcionalidades raíz, y convertirlas en DTOs de asignación permisos
        listaFuncionalidadesDto = new ArrayList<AsignacionPermisosDto>();
        List<Funcionalidades> listaFuncionalidades = seguridadesServicio.consultarFuncionalidadesPadre();
        listaFuncionalidades.stream().map((elementoFuncionalidad) -> new AsignacionPermisosDto(permiso, elementoFuncionalidad, seguridadesServicio, sesionBean.obtenerSesionDto())).forEachOrdered((nuevaFuncionalidad) -> {
            listaFuncionalidadesDto.add(nuevaFuncionalidad);
        });
        // Crear la raíz de asignación funcionalidades
        listaArbolFuncionalidadesDto = new DefaultTreeNode();
        // Generar el árbol a partir del listado de funcionalidades raíz.
        try {
            listaArbolFuncionalidadesDto = WebUtils.generarArbol(listaFuncionalidadesDto, listaArbolFuncionalidadesDto, "getListaPermisosSubfuncionalidades");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
        // Actualizar la pantalla para Asignación
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_ASIGNACION);
        establecerTitulo(EnumEtiquetas.PERMISOS_ASIGNAR_TITULO,
                EnumEtiquetas.PERMISOS_ASIGNAR_ICONO,
                EnumEtiquetas.PERMISOS_ASIGNAR_DESCRIPCION);
    }

    /**
     * Crea un nuevo permiso y actualiza la pantalla para su edición.
     */
    public void crearNuevoPermiso() {
        // Crear un nuevo Permiso
        this.permiso = new Permisos();
        this.permiso.setEstadoPermiso(EnumEstadoRegistro.A);
        // Actualizar la pantalla para Edición
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PERMISOS_NUEVO_TITULO,
                EnumEtiquetas.PERMISOS_NUEVO_ICONO,
                EnumEtiquetas.PERMISOS_NUEVO_DESCRIPCION);
    }

    /**
     * Almacena el nuevo permiso en la base de datos, y refresca el listado con
     * el nuevo elemento.
     */
    public void insertarPermiso() {
        try {
            seguridadesServicio.generarNuevoPermiso(permiso, sesionBean.obtenerSesionDto());
            actualizarListadoPermisos();
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF307.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
    }

    /**
     * Guarda los cambios generados en el permiso, ya sea nuevo o seleccionado
     * para edición.
     */
    public void actualizarPermiso() {
        // Si el permiso es nuevo
        if (!ComunUtil.esNumeroPositivo(this.permiso.getIdPermiso())) {
            insertarPermiso();
        } else {
            try {
                seguridadesServicio.actualizarPermiso(permiso, sesionBean.obtenerSesionDto());
                actualizarListadoPermisos();
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF309.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        // Actualizar la pantalla para Listado
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PERMISOS_LISTA_TITULO,
                EnumEtiquetas.PERMISOS_LISTA_ICONO,
                EnumEtiquetas.PERMISOS_LISTA_DESCRIPCION);
    }

    /**
     * Eliminar un permiso seleccionado.
     * @param idPermiso Integer que contiene la Id del permiso seleccionado.
     */
    public void eliminarPermiso(Integer idPermiso) {
        try {
            this.seleccionarPermisoPorCodigo(idPermiso);
            if (!ComunUtil.esNulo(permiso)) {
                seguridadesServicio.eliminarPermiso(permiso, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF308.presentarMensaje());
                actualizarListadoPermisos();
            } else {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
            }
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void seleccionarPermiso(Integer idPermiso) {
        try {
            this.seleccionarPermisoPorCodigo(idPermiso);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.PERMISOS_EDITAR_TITULO,
                EnumEtiquetas.PERMISOS_EDITAR_ICONO,
                EnumEtiquetas.PERMISOS_EDITAR_DESCRIPCION);
    }

    private void seleccionarPermisoPorCodigo(Integer idPermiso) throws NewviExcepcion {
        this.permiso = seguridadesServicio.seleccionarPermiso(idPermiso);
    }

    public void actualizarPermisoAsignado(AsignacionPermisos asignacionPermisoSeleccionado, Integer idFuncionalidad, EnumTipoPermisos permisoAsignado) {
        try {
            this.funcionalidad = SeguridadesUtil.seleccionarFuncionalidad(idFuncionalidad, seguridadesServicio);
            if (ComunUtil.esNulo(asignacionPermisoSeleccionado)) {
                this.asignacionPermiso = new AsignacionPermisos();
                asignacionPermiso.setPerId(permiso);
                asignacionPermiso.setRolId(funcionalidad);
                asignacionPermiso.setPefEstado(EnumEstadoRegistro.A);
                asignacionPermiso.setPefOperaciones(permisoAsignado);
                seguridadesServicio.generarNuevaAsignacion(asignacionPermiso, sesionBean.obtenerSesionDto());
            } else {
                this.asignacionPermiso = asignacionPermisoSeleccionado;
                asignacionPermiso.setPefOperaciones(permisoAsignado);
                seguridadesServicio.actualizarAsignacion(asignacionPermiso, sesionBean.obtenerSesionDto());
            }
            MensajesFaces.mensajeInformacion("Operación actualizada.");
        } catch (NewviExcepcion ex) {
            MensajesFaces.mensajeError(ex.getMessage());
        }
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioPermiso:opDetallePermisos");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.PERMISOS_LISTA_TITULO,
                EnumEtiquetas.PERMISOS_LISTA_ICONO,
                EnumEtiquetas.PERMISOS_LISTA_DESCRIPCION);
    }

    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }

    public Boolean esPantallaActual(String pantallaEsperada) {
        try {
            return this.pantallaActual.equals(EnumPantallaMantenimiento.obtenerPantallaPorNombre(pantallaEsperada));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }
}
