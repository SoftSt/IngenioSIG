/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.modelo.Servicios;
import ec.com.newvi.sic.modelo.Tenencia;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class NuevosElementosPredioBB extends AdminFichaCatastralBB {

    private EnumPantallaMantenimiento pantallaActual;

    private TreeNode listaArbolDescripcionTerreno;
    private TreeNode listaArbolServicios;
    private TreeNode listaArbolTenencia;

    private TreeNode servicioSeleccionado;
    private TreeNode descripcionTerrenoSeleccionado;
    private TreeNode tenenciaSeleccionada;

    private List<Terreno> listaTerrenoSeleccionados;
    private List<Terreno> listaTerrenoProcesados;
    private List<Servicios> listaServiciosSeleccionados;
    private List<Servicios> listaServiciosProcesados;
    private List<Tenencia> listaTenenciaSeleccionados;
    private List<Tenencia> listaTenenciaProcesados;

    private List<FichaCatastralDto> listaFichasSeleccionadas;
    private List<FichaCatastralDto> listaFichasProcesados;
    private List<FichaCatastralDto> listaFichaFiltradas;
    private List<FichaCatastralDto> listaFichaProcesadosFiltradas;

    public TreeNode getListaArbolDescripcionTerreno() {
        return listaArbolDescripcionTerreno;
    }

    public TreeNode getListaArbolServicios() {
        return listaArbolServicios;
    }

    public TreeNode getListaArbolTenencia() {
        return listaArbolTenencia;
    }

    public List<FichaCatastralDto> getListaFichasSeleccionadas() {
        return listaFichasSeleccionadas;
    }

    public void setListaFichasSeleccionadas(List<FichaCatastralDto> listaFichasSeleccionadas) {
        this.listaFichasSeleccionadas = listaFichasSeleccionadas;
    }

    public List<FichaCatastralDto> getListaFichaFiltradas() {
        return listaFichaFiltradas;
    }

    public void setListaFichaFiltradas(List<FichaCatastralDto> listaFichaFiltradas) {
        this.listaFichaFiltradas = listaFichaFiltradas;
    }

    public TreeNode getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(TreeNode servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public TreeNode getDescripcionTerrenoSeleccionado() {
        return descripcionTerrenoSeleccionado;
    }

    public void setDescripcionTerrenoSeleccionado(TreeNode descripcionTerrenoSeleccionado) {
        this.descripcionTerrenoSeleccionado = descripcionTerrenoSeleccionado;
    }

    public TreeNode getTenenciaSeleccionada() {
        return tenenciaSeleccionada;
    }

    public void setTenenciaSeleccionada(TreeNode tenenciaSeleccionada) {
        this.tenenciaSeleccionada = tenenciaSeleccionada;
    }

    public List<Terreno> getListaTerrenoSeleccionados() {
        return listaTerrenoSeleccionados;
    }

    public void setListaTerrenoSeleccionados(List<Terreno> listaTerrenoSeleccionados) {
        this.listaTerrenoSeleccionados = listaTerrenoSeleccionados;
    }

    public List<Servicios> getListaServiciosSeleccionados() {
        return listaServiciosSeleccionados;
    }

    public void setListaServiciosSeleccionados(List<Servicios> listaServiciosSeleccionados) {
        this.listaServiciosSeleccionados = listaServiciosSeleccionados;
    }

    public List<Tenencia> getListaTenenciaSeleccionados() {
        return listaTenenciaSeleccionados;
    }

    public void setListaTenenciaSeleccionados(List<Tenencia> listaTenenciaSeleccionados) {
        this.listaTenenciaSeleccionados = listaTenenciaSeleccionados;
    }

    public List<Terreno> getListaTerrenoProcesados() {
        return listaTerrenoProcesados;
    }

    public void setListaTerrenoProcesados(List<Terreno> listaTerrenoProcesados) {
        this.listaTerrenoProcesados = listaTerrenoProcesados;
    }

    public List<Servicios> getListaServiciosProcesados() {
        return listaServiciosProcesados;
    }

    public void setListaServiciosProcesados(List<Servicios> listaServiciosProcesados) {
        this.listaServiciosProcesados = listaServiciosProcesados;
    }

    public List<Tenencia> getListaTenenciaProcesados() {
        return listaTenenciaProcesados;
    }

    public void setListaTenenciaProcesados(List<Tenencia> listaTenenciaProcesados) {
        this.listaTenenciaProcesados = listaTenenciaProcesados;
    }

    public List<FichaCatastralDto> getListaFichasProcesados() {
        return listaFichasProcesados;
    }

    public void setListaFichasProcesados(List<FichaCatastralDto> listaFichasProcesados) {
        this.listaFichasProcesados = listaFichasProcesados;
    }

    public List<FichaCatastralDto> getListaFichaProcesadosFiltradas() {
        return listaFichaProcesadosFiltradas;
    }

    public void setListaFichaProcesadosFiltradas(List<FichaCatastralDto> listaFichaProcesadosFiltradas) {
        this.listaFichaProcesadosFiltradas = listaFichaProcesadosFiltradas;
    }

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_LISTA_TITULO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_LISTA_ICONO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_LISTA_DESCRIPCION);
        actualizarListadoPredios();
        actualizarListadoDescripcionTerreno();
        actualizarListadoServicios();
        actualizarListadoTenencia();
        inicializarListasSeleccionadas();
        inicializarListasProcesadas();
    }

    private void inicializarListasSeleccionadas() {
        this.listaServiciosSeleccionados = new ArrayList<>();
        this.listaTerrenoSeleccionados = new ArrayList<>();
        this.listaTenenciaSeleccionados = new ArrayList<>();
    }

    private void inicializarListasProcesadas() {
        this.listaServiciosProcesados = new ArrayList<>();
        this.listaTerrenoProcesados = new ArrayList<>();
        this.listaTenenciaProcesados = new ArrayList<>();
        this.listaFichasProcesados = new ArrayList<>();
    }

    private void conmutarPantalla(EnumPantallaMantenimiento nuevaPantalla) {
        this.pantallaActual = nuevaPantalla;
    }

    public Boolean esPantallaActual(String pantallaEsperada) {
        try {
            return this.pantallaActual.equals(EnumPantallaMantenimiento.obtenerPantallaPorNombre(pantallaEsperada));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
            return false;
        }
    }

    private void actualizarListadoDescripcionTerreno() {
        List<DominioDto> listadoDominiosDto = parametrosServicio.listarDominiosDto("DESCRIPCION DEL TERRENO", "Nodo");

        try {
            listaArbolDescripcionTerreno = new DefaultTreeNode();
            listaArbolDescripcionTerreno = WebUtils.generarArbol(listadoDominiosDto, listaArbolDescripcionTerreno, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void actualizarListadoServicios() {
        List<DominioDto> listadoDominiosDto = parametrosServicio.listarDominiosDto("INFRAESTRUCTURA DE SERVICIOS", "Nodo");
        List<DominioDto> listadoDominiosDtoCerramiento = parametrosServicio.listarDominiosDto("CERRAMIENTO", "SubNodo");

        for (DominioDto dominioDto : listadoDominiosDtoCerramiento) {
            listadoDominiosDto.add(dominioDto);
        }

        try {
            listaArbolServicios = new DefaultTreeNode();
            listaArbolServicios = WebUtils.generarArbol(listadoDominiosDto, listaArbolServicios, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void actualizarListadoTenencia() {
        List<DominioDto> listadoDominiosDto = parametrosServicio.listarDominiosDto("TENENCIA", "Nodo");

        try {
            listaArbolTenencia = new DefaultTreeNode();
            listaArbolTenencia = WebUtils.generarArbol(listadoDominiosDto, listaArbolTenencia, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void abrirDialogServicios() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgServicios').show()");
    }

    public void abrirDialogDescripcionTerreno() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgDescripcionTerreno').show()");
    }

    public void abrirDialogTenencia() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgTenencia').show()");
    }

    public void eliminarTerreno(Terreno terrenoEliminable) {
        this.listaTerrenoSeleccionados.remove(terrenoEliminable);
    }

    public void eliminarServicio(Servicios servicioEliminable) {
        this.listaServiciosSeleccionados.remove(servicioEliminable);
    }

    public void eliminarTenencia(Tenencia tenenciaEliminable) {
        this.listaTenenciaSeleccionados.remove(tenenciaEliminable);
    }

    public void agregarServicioSeleccionado(NodeSelectEvent event) {
        Servicios nuevoServicio = new Servicios();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            nuevoServicio.setStsGrupo(hijo.getDomiGrupos());
            nuevoServicio.setStsSubGrupo(padre.getDomiDescripcion());
            nuevoServicio.setStsDescripcion(hijo.getDomiDescripcion());
            //servicio.setCodCatastral(this.predio);
            nuevoServicio.setStsCodigo(hijo.getDomiCodigo());
            nuevoServicio.setSerEstado(EnumEstadoRegistro.A);

            //this.predio.getServicios().add(servicio);
            this.listaServiciosSeleccionados.add(nuevoServicio);
        }
    }

    public void agregarDescripcionTerrenoSeleccionada(NodeSelectEvent event) {
        Terreno nuevaDescripcionTerreno = new Terreno();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        //generarLogPredio(this.predio, EnumAcciones.Agregacion_Descripcion_Terreno.getAccion(), sesionBean.getSesion());
        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            nuevaDescripcionTerreno.setStsGrupo(hijo.getDomiGrupos());
            nuevaDescripcionTerreno.setStsSubGrupo(padre.getDomiDescripcion());
            nuevaDescripcionTerreno.setStsDescripcion(hijo.getDomiDescripcion());
            //terreno.setCodCatastral(this.predio);
            nuevaDescripcionTerreno.setTerEstado(EnumEstadoRegistro.A);
            nuevaDescripcionTerreno.setStsCodigo(hijo.getDomiCodigo());

            this.listaTerrenoSeleccionados.add(nuevaDescripcionTerreno);
        }
    }

    public void agregarTenenciaSeleccionada(NodeSelectEvent event) {
        Tenencia nuevaTenencia = new Tenencia();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            nuevaTenencia.setStsGrupo(hijo.getDomiGrupos());
            nuevaTenencia.setStsSubgrupo(padre.getDomiDescripcion());
            nuevaTenencia.setStsDescripcion(hijo.getDomiDescripcion());

            //nuevaTenencia.setCodPropietarios(actual);
            nuevaTenencia.setTenEstado(EnumEstadoRegistro.A);
            nuevaTenencia.setStsCodigo(hijo.getDomiCodigo());

            this.listaTenenciaSeleccionados.add(nuevaTenencia);
        }
    }

    public void avanzarPaginaSiguiente() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_EDITAR_TITULO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_EDITAR_ICONO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_EDITAR_DESCRIPCION);
    }

    private void procesarListasServiciosSeleccionadas() {
        if (!ComunUtil.esNulo(this.listaServiciosSeleccionados)) {
            for (Servicios servicioAProcesar : this.listaServiciosSeleccionados) {
                this.listaServiciosProcesados.add(servicioAProcesar);
            }
        }
    }

    private void procesarListasDescripcionTerrenoSeleccionadas() {
        if (!ComunUtil.esNulo(this.listaTerrenoSeleccionados)) {
            for (Terreno terrenoAProcesar : this.listaTerrenoSeleccionados) {
                this.listaTerrenoProcesados.add(terrenoAProcesar);
            }
        }
    }

    private void procesarListasTenenciaSeleccionadas() {
        if (!ComunUtil.esNulo(this.listaTenenciaSeleccionados)) {
            for (Tenencia tenenciaAProcesar : this.listaTenenciaSeleccionados) {
                this.listaTenenciaProcesados.add(tenenciaAProcesar);
            }
        }
    }

    private void procesarListasFichasSeleccionadas() {
        if (!ComunUtil.esNulo(this.listaFichasSeleccionadas)) {
            for (FichaCatastralDto fichaAProcesar : this.listaFichasSeleccionadas) {
                this.listaFichasProcesados.add(fichaAProcesar);
            }
        }
    }

    public void avanzarPaginaVerificacion() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_ASIGNACION);
        establecerTitulo(EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_VERIFICAR_TITULO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_VERIFICAR_ICONO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_VERIFICAR_DESCRIPCION);
        inicializarListasProcesadas();
        procesarListasServiciosSeleccionadas();
        procesarListasDescripcionTerrenoSeleccionadas();
        procesarListasTenenciaSeleccionadas();
        procesarListasFichasSeleccionadas();
    }

    public void regresarPaginaAnterior() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_LISTA_TITULO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_LISTA_ICONO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_LISTA_DESCRIPCION);
    }

    private void registrarServicios(Predios predioActual) {
        if (!ComunUtil.esNulo(this.listaServiciosSeleccionados)) {
            for (Servicios servicioAProcesar : this.listaServiciosProcesados) {
                servicioAProcesar.setCodCatastral(predioActual);
                //predioActual.getServicios().add(servicioAProcesar);
                try {
                    catastroServicio.generarNuevoServicio(servicioAProcesar, sesionBean.getSesion());
                } catch (NewviExcepcion ex) {
                    Logger.getLogger(NuevosElementosPredioBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void registrarTerreno(Predios predioActual) {
        if (!ComunUtil.esNulo(this.listaTerrenoSeleccionados)) {
            for (Terreno terrenoAProcesar : this.listaTerrenoSeleccionados) {
                terrenoAProcesar.setCodCatastral(predioActual);
                //predioActual.getCaracteristicasTerreno().add(terrenoAProcesar);
                try {
                    catastroServicio.generarNuevoTerreno(terrenoAProcesar, sesionBean.getSesion());
                } catch (NewviExcepcion ex) {
                    Logger.getLogger(NuevosElementosPredioBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void registrarTenencia(Propiedad propiedadActual) {
        if (!ComunUtil.esNulo(this.listaTenenciaSeleccionados)) {
            for (Tenencia tenenciaAProcesar : this.listaTenenciaSeleccionados) {
                tenenciaAProcesar.setCodPropietarios(propiedadActual);
                //propiedadActual.getTenenciaList().add(tenenciaAProcesar);
                try {
                    contribuyentesServicio.generarNuevaTenencia(tenenciaAProcesar, sesionBean.getSesion());
                } catch (NewviExcepcion ex) {
                    Logger.getLogger(NuevosElementosPredioBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void ejecutarRegistro() {
        for (FichaCatastralDto ficha : this.listaFichasProcesados) {
            Predios predioActual = ficha.getPredio();
            Propiedad propiedadActual = ficha.getPropiedad();
            registrarServicios(predioActual);
            registrarTerreno(predioActual);
            registrarTenencia(propiedadActual);
        }
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF364.presentarMensaje(), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF364.presentarMensaje());
    }

}
