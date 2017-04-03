/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.enums;

/**
 *
 * @author israelavila
 */
public enum EnumEtiquetas {
    
    GENERICO_LISTA_TITULO("Administración"),
    GENERICO_LISTA_ICONO("fa fa-table"),
    GENERICO_LISTA_DESCRIPCION("Gestión de elementos."),
    GENERICO_NUEVO_TITULO("Nuevo"),
    GENERICO_NUEVO_ICONO("fa-plus"),
    GENERICO_NUEVO_DESCRIPCION("Creación de nuevos elementos."),
    GENERICO_EDITAR_TITULO("Editar"),
    GENERICO_EDITAR_ICONO("fa fa-edit"),
    GENERICO_EDITAR_DESCRIPCION("Edición de elementos."),
    USUARIOS_LISTA_TITULO("Administración de Usuarios"),
    USUARIOS_LISTA_ICONO("fa fa-users"),
    USUARIOS_LISTA_DESCRIPCION("Gestión de usuarios en el sistema."),
    USUARIOS_NUEVO_TITULO("Nuevo Usuario"),
    USUARIOS_NUEVO_ICONO("fa fa-user-plus"),
    USUARIOS_NUEVO_DESCRIPCION("Creación de nuevo usuario."),
    USUARIOS_EDITAR_TITULO("Editar Usuario"),
    USUARIOS_EDITAR_ICONO("fa fa-user"),
    USUARIOS_EDITAR_DESCRIPCION("Edición de usuario seleccionado."),
    DOMINIOS_LISTA_TITULO("Administración de Dominios"),
    DOMINIOS_LISTA_ICONO("fa fa-leanpub"),
    DOMINIOS_LISTA_DESCRIPCION("Gestión de dominios en el sistema."),
    DOMINIOS_NUEVO_TITULO("Nuevo Dominio"),
    DOMINIOS_NUEVO_ICONO("fa fa-plus"),
    DOMINIOS_NUEVO_DESCRIPCION("Creación de nuevo dominio."),
    DOMINIOS_EDITAR_TITULO("Editar Dominio"),
    DOMINIOS_EDITAR_ICONO("fa fa-edit"),
    DOMINIOS_EDITAR_DESCRIPCION("Edición de dominio seleccionado."),
    FUNCIONALIDADES_LISTA_TITULO("Administración de Funcionalidades"),
    FUNCIONALIDADES_LISTA_ICONO("fa fa-gears"),
    FUNCIONALIDADES_LISTA_DESCRIPCION("Gestión de funcionalidades en el sistema."),
    FUNCIONALIDADES_NUEVO_TITULO("Nueva Funcionalidad"),
    FUNCIONALIDADES_NUEVO_ICONO("fa fa-plus"),
    FUNCIONALIDADES_NUEVO_DESCRIPCION("Creación de nueva funcionalidad."),
    FUNCIONALIDADES_SUB_NUEVO_TITULO("Nueva Subfuncionalidad"),
    FUNCIONALIDADES_SUB_NUEVO_ICONO("fa fa-plus"),
    FUNCIONALIDADES_SUB_NUEVO_DESCRIPCION("Creación de nueva subfuncionalidad."),
    FUNCIONALIDADES_EDITAR_TITULO("Editar Funcionalidad"),
    FUNCIONALIDADES_EDITAR_ICONO("fa fa-edit"),
    FUNCIONALIDADES_EDITAR_DESCRIPCION("Edición de funcionalidad seleccionado."),
    PERMISOS_LISTA_TITULO("Administración de Permisos"),
    PERMISOS_LISTA_ICONO("fa fa-user-secret"),
    PERMISOS_LISTA_DESCRIPCION("Gestión de permisos en el sistema."),
    PERMISOS_NUEVO_TITULO("Nuevo Permiso"),
    PERMISOS_NUEVO_ICONO("fa fa-plus"),
    PERMISOS_NUEVO_DESCRIPCION("Creación de nueva permiso."),
    PERMISOS_EDITAR_TITULO("Editar Permiso"),
    PERMISOS_EDITAR_ICONO("fa fa-edit"),
    PERMISOS_EDITAR_DESCRIPCION("Edición de permiso seleccionado."),
    PERMISOS_ASIGNAR_TITULO("Asignar funcionalidades al rol"),
    PERMISOS_ASIGNAR_ICONO("fa fa-check-square-o"),
    PERMISOS_ASIGNAR_DESCRIPCION("Asignación de funcionalidades permitidas al rol seleccionado.");
    
    private final String textoEtiqueta;

    public String getTextoEtiqueta() {
        return textoEtiqueta;
    }
    
    private EnumEtiquetas(String textoEtiqueta) {
        this.textoEtiqueta = textoEtiqueta;
    }
}
