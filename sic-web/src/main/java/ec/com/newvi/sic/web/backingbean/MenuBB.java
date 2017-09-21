/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumTipoPermisos;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.util.ComunUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class MenuBB extends AdminSeguridadesBB {

    private List<Funcionalidades> listaFuncionalidades;
    private MenuModel menu;

    private List<AsignacionPermisos> funcionalidadesAsignadas;

    public MenuModel getMenu() {
        return menu;
    }

    @PostConstruct
    public void generarMenu() {
        // Inicializar modelo de menú
        this.menu = new DefaultMenuModel();
        // Traer las funcionalidades
        listaFuncionalidades = seguridadesServicio.consultarFuncionalidadesPadre();
        // Traer el listado de permisos activos para el usuario actual
        obtenerFuncionalidadesAsignadas();
        //por cada funcionalidad
        for (Funcionalidades funcionalidad : listaFuncionalidades) {
            // Determinar si el usuario tiene permiso de acceder al elemento
            if (esFuncionalidadHabilitada(funcionalidad)) {
                // Crear un objeto de tipo submenu y agregar al modelo
                if (tieneHijos(funcionalidad)) {
                    this.menu.addElement(generarSubmenu(funcionalidad));
                } else {
                    this.menu.addElement(generarMenuItem(funcionalidad));
                }
            }
        }
    }

    private void obtenerFuncionalidadesAsignadas() {
        funcionalidadesAsignadas = new ArrayList<AsignacionPermisos>();
        if (!ComunUtil.esNulo(sesionBean.getUsuarioRegistrado())) {
            funcionalidadesAsignadas = sesionBean.getUsuarioRegistrado().getPerId().getListaAsignacionPermisosActivas();
        }
    }

    private Boolean esFuncionalidadHabilitada(Funcionalidades funcionalidad) {
        for (AsignacionPermisos funcionalidadAsignada : funcionalidadesAsignadas) {
            if (funcionalidadAsignada.getRolId().equals(funcionalidad)
                    && !EnumTipoPermisos.N.equals(funcionalidadAsignada.getPefOperaciones())) {
                return true;
            }
        }
        return false;
    }

    private DefaultSubMenu generarSubmenu(Funcionalidades funcionalidad) {
        DefaultSubMenu submenu = new DefaultSubMenu(funcionalidad.getFunMenu());
        
        for (Funcionalidades hijo : funcionalidad.getListaSubFuncionalidadesActivas()) {
            if (esFuncionalidadHabilitada(hijo)) {
                if (tieneHijos(hijo)) {
                    submenu.addElement(generarSubmenu(hijo));
                } else {
                    submenu.addElement(generarMenuItem(hijo));
                }
            }
        }
        return submenu;
    }

    private DefaultMenuItem generarMenuItem(Funcionalidades funcionalidad) {
        DefaultMenuItem item = new DefaultMenuItem(funcionalidad.getFunMenu());
        item.setUrl(funcionalidad.getFunRuta());
        item.setIcon(funcionalidad.getFunMenuIcono());
        return item;
    }

    private Boolean tieneHijos(Funcionalidades funcionalidad) {
        List<Funcionalidades> hijos = funcionalidad.getListaSubFuncionalidadesActivas();
        return (!ComunUtil.esNulo(hijos) && hijos.size() > 0);
    }

}
