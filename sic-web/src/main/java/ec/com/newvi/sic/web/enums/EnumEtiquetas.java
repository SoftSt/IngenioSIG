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
    USUARIOS_LISTA_TITULO("Usuarios"),
    USUARIOS_LISTA_ICONO("fa fa-users"),
    USUARIOS_LISTA_DESCRIPCION("Gestión de usuarios en el sistema."),
    USUARIOS_NUEVO_TITULO("Nuevo Usuario"),
    USUARIOS_NUEVO_ICONO("fa fa-user-plus"),
    USUARIOS_NUEVO_DESCRIPCION("Creación de nuevo usuario."),
    USUARIOS_EDITAR_TITULO("Editar Usuario"),
    USUARIOS_EDITAR_ICONO("fa fa-user"),
    USUARIOS_EDITAR_DESCRIPCION("Edición de usuario seleccionado."),
    DOMINIOS_LISTA_TITULO("Dominios"),
    DOMINIOS_LISTA_ICONO("fa fa-leanpub"),
    DOMINIOS_LISTA_DESCRIPCION("Gestión de dominios en el sistema."),
    DOMINIOS_NUEVO_TITULO("Nuevo Dominio"),
    DOMINIOS_NUEVO_ICONO("fa fa-plus"),
    DOMINIOS_NUEVO_DESCRIPCION("Creación de nuevo dominio."),
    DOMINIOS_EDITAR_TITULO("Editar Dominio"),
    DOMINIOS_EDITAR_ICONO("fa fa-edit"),
    DOMINIOS_EDITAR_DESCRIPCION("Edición de dominio seleccionado."),
    FUNCIONALIDADES_LISTA_TITULO("Funcionalidades"),
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
    PERMISOS_LISTA_TITULO("Roles y Permisos"),
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
    PERMISOS_ASIGNAR_DESCRIPCION("Asignación de funcionalidades permitidas al rol seleccionado."),
    CONTRIBUYENTES_LISTA_TITULO("Gestión de Contribuyentes"),
    CONTRIBUYENTES_LISTA_ICONO("fa fa-users"),
    CONTRIBUYENTES_LISTA_DESCRIPCION("Gestión de contribuyentes en el sistema."),
    CONTRIBUYENTES_NUEVO_TITULO("Nuevo Contribuyente"),
    CONTRIBUYENTES_NUEVO_ICONO("fa fa-plus"),
    CONTRIBUYENTES_NUEVO_DESCRIPCION("Creación de nuevo contribuyente."),
    CONTRIBUYENTES_EDITAR_TITULO("Editar Contribuyente"),
    CONTRIBUYENTES_EDITAR_ICONO("fa fa-edit"),
    CONTRIBUYENTES_EDITAR_DESCRIPCION("Edición de contribuyente seleccionado."),
    CONTRIBUCION_MEJORAS_LISTA_TITULO("Contribucion Especial de Mejoras"),
    CONTRIBUCION_MEJORAS_LISTA_ICONO("fa fa-thumbs-o-up"),
    CONTRIBUCION_MEJORAS_LISTA_DESCRIPCION("Gestión de Contribucion Especial de Mejoras en el sistema."),
    CONTRIBUCION_MEJORAS_NUEVO_TITULO("Nueva Contribucion Especial de Mejora"),
    CONTRIBUCION_MEJORAS_NUEVO_ICONO("fa fa-plus"),
    CONTRIBUCION_MEJORAS_NUEVO_DESCRIPCION("Creación de nueva Contribucion Especial de Mejora."),
    CONTRIBUCION_MEJORAS_EDITAR_TITULO("Editar Contribucion Especial de Mejora"),
    CONTRIBUCION_MEJORAS_EDITAR_ICONO("fa fa-edit"),
    CONTRIBUCION_MEJORAS_EDITAR_DESCRIPCION("Edición de Contribucion Especial de Mejora seleccionado."),
    FICHA_CATASTRAL_LISTA_TITULO("Ficha Predial Urbana"),
    FICHA_CATASTRAL_LISTA_ICONO("fa fa-list-alt"),
    FICHA_CATASTRAL_LISTA_DESCRIPCION("Gestión de ficha predial urbana."),
    FICHA_CATASTRAL_NUEVO_TITULO("Nueva Ficha Predial"),
    FICHA_CATASTRAL_NUEVO_ICONO("fa fa-plus"),
    FICHA_CATASTRAL_NUEVO_DESCRIPCION("Creación de nueva Ficha Predial."),
    FICHA_CATASTRAL_EDITAR_TITULO("Editar Ficha Predial"),
    FICHA_CATASTRAL_EDITAR_ICONO("fa fa-edit"),
    FICHA_CATASTRAL_EDITAR_DESCRIPCION("Edición de Ficha Predial seleccionada.");
    
    private final String textoEtiqueta;

    public String getTextoEtiqueta() {
        return textoEtiqueta;
    }
    
    private EnumEtiquetas(String textoEtiqueta) {
        this.textoEtiqueta = textoEtiqueta;
    }
}
