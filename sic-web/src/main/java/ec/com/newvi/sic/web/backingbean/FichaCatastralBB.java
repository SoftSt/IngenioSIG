/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dao.PisosFacade;
import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoPisoDetalle;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.ValidacionUtils;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped

public class FichaCatastralBB extends AdminFichaCatastralBB {

    private Predios predio;
    private Propiedad propiedadActual;
    private Propiedad propiedad;
    private List<FichaCatastralDto> listaFichas;
    private List<FichaCatastralDto> listaFichasFiltradas;
    private EnumPantallaMantenimiento pantallaActual;
    private Bloques bloqueSeleccionado;
    private TreeNode listaArbolDominios;
    private TreeNode listaArbolPisosDetalle;
    private TreeNode[] listaDominiosSeleccionados;
    private TreeNode pisosDetalleSeleccionado;
    private List<Fotos> listaFotosPorPredio;
    private List<String> listaFotosJpg;
    private Pisos pisoSeleccionado;
    private EnumEstadoPisoDetalle[] listaEstadosPisoDetalle;

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }

    public List<FichaCatastralDto> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaCatastralDto> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public List<FichaCatastralDto> getListaFichasFiltradas() {
        return listaFichasFiltradas;
    }

    public void setListaFichasFiltradas(List<FichaCatastralDto> listaFichasFiltradas) {
        this.listaFichasFiltradas = listaFichasFiltradas;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public Bloques getBloqueSeleccionado() {
        return bloqueSeleccionado;
    }

    public void setBloqueSeleccionado(Bloques bloqueSeleccionado) {
        this.bloqueSeleccionado = bloqueSeleccionado;
    }

    public Propiedad getPropiedadActual() {
        return propiedadActual;
    }

    public void setPropiedadActual(Propiedad propiedadActual) {
        this.propiedadActual = propiedadActual;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public TreeNode getListaArbolDominios() {
        return listaArbolDominios;
    }

    public void setListaArbolDominios(TreeNode listaArbolDominios) {
        this.listaArbolDominios = listaArbolDominios;
    }

    public TreeNode[] getListaDominiosSeleccionados() {
        return listaDominiosSeleccionados;
    }

    public void setListaDominiosSeleccionados(TreeNode[] listaDominiosSeleccionados) {
        this.listaDominiosSeleccionados = listaDominiosSeleccionados;
    }

    public TreeNode getListaArbolPisosDetalle() {
        return listaArbolPisosDetalle;
    }

    public void setListaArbolPisosDetalle(TreeNode listaArbolPisosDetalle) {
        this.listaArbolPisosDetalle = listaArbolPisosDetalle;
    }

    public TreeNode getPisosDetalleSeleccionado() {
        return pisosDetalleSeleccionado;
    }

    public void setPisosDetalleSeleccionado(TreeNode pisosDetalleSeleccionado) {
        this.pisosDetalleSeleccionado = pisosDetalleSeleccionado;
    }

    public List<Fotos> getListaFotosPorPredio() {
        return listaFotosPorPredio;
    }

    public void setListaFotosPorPredio(List<Fotos> listaFotosPorPredio) {
        this.listaFotosPorPredio = listaFotosPorPredio;
    }

    public List<String> getListaFotosJpg() {
        return listaFotosJpg;
    }

    public void setListaFotosJpg(List<String> listaFotosJpg) {
        this.listaFotosJpg = listaFotosJpg;
    }

    public Pisos getPisoSeleccionado() {
        return pisoSeleccionado;
    }

    public void setPisoSeleccionado(Pisos pisoSeleccionado) {
        this.pisoSeleccionado = pisoSeleccionado;
    }

    public EnumEstadoPisoDetalle[] getListaEstadosPisoDetalle() {
        return listaEstadosPisoDetalle;
    }

    public void setListaEstadosPisoDetalle(EnumEstadoPisoDetalle[] listaEstadosPisoDetalle) {
        this.listaEstadosPisoDetalle = listaEstadosPisoDetalle;
    }
    
    

    @PostConstruct
    public void init() {
        this.predio = new Predios();
        listaEstadosPisoDetalle = EnumEstadoPisoDetalle.values();
        actualizarListadoPredios();
        actualizarListadoDominios();
        actualizarListadoPisosDetalle();
        this.listaFotosJpg = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);

    }

    private void actualizarListadoPredios() {
        List<Predios> listaPredios = catastroServicio.consultarPredios();
        listaFichas = new ArrayList<>();
        listaPredios.forEach((elementoPredio) -> {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        });
    }

    public void crearNuevoPredio() {
        this.predio = new Predios();
        this.predio.setCatEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_NUEVO_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_NUEVO_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_NUEVO_DESCRIPCION);
    }

    public void insertarPredio() {
        try {
            catastroServicio.generarNuevoPredio(predio, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF351.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF351.presentarMensaje());
            actualizarListadoPredios();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPredio() {
        if (!ComunUtil.esNumeroPositivo(this.predio.getCodCatastral())) {
            insertarPredio();
        } else {
            try {
                catastroServicio.actualizarPredio(this.predio, sesionBean.obtenerSesionDto());
                actualizarListadoPredios();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF352.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF352.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);
    }

    public void eliminarPredio(Integer idPredio) {
        try {
            this.seleccionarPredioPorCodigo(idPredio);
            if (!ComunUtil.esNulo(predio)) {
                catastroServicio.eliminarPredio(predio, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF353.presentarMensaje());
                actualizarListadoPredios();

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

    public void seleccionarPredio(Integer idPredio) {
        try {
            this.seleccionarPredioPorCodigo(idPredio);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_EDITAR_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_EDITAR_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_EDITAR_DESCRIPCION);
    }

    private void construirImagenes(List<Fotos> imagenes) {
        for (Fotos fotos : imagenes) {
            listaFotosJpg.add(fotos.getDirFotos().trim() + ".jpg");
        }
    }

    private void listarFotosPorPredio(Integer idPredio) {
        listaFotosPorPredio = catastroServicio.consultarFotosPorPredio(idPredio);
        if (!listaFotosPorPredio.isEmpty()) {
            construirImagenes(listaFotosPorPredio);
        } else {
            listaFotosJpg.add("vacio.jpg");
        }

    }

    private void seleccionarPredioPorCodigo(Integer idPredio) throws NewviExcepcion {
        this.predio = catastroServicio.seleccionarPredio(idPredio);
        for (FichaCatastralDto fichasCatrastrales : listaFichas) {
            if (fichasCatrastrales.getPredio().getCodCatastral().equals(idPredio)) {
                propiedad = fichasCatrastrales.getPropiedad();
            }
        }

        this.propiedadActual = contribuyentesServicio.consultarUltimoPropiedad(this.predio);

        listarFotosPorPredio(this.predio.getCodCatastral());
    }

    public Propiedad obtenerPropiedad(Predios predioConsulta) {
        try {
            return contribuyentesServicio.consultarUltimoPropiedad(predioConsulta);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
            return null;
        }
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioFichaCatastral:opDetalleFichaCatastral");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);
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

    public void validarEmail(FacesContext arg0, UIComponent arg1, Object arg2) throws NewviExcepcion {
        String usuEmail = arg2.toString();
        if (!ValidacionUtils.validarCorreoElectronico(usuEmail.trim())) {
            throw ValidacionUtils.lanzarExcepcionValidacion(EnumNewviExcepciones.ERR251);
        }
    }

    public void actualizarCodigoCatastral() {
        this.predio.actualizarCodigoPredio();
    }

    private void actualizarListadoDominios() {
        List<DominioDto> listadoDominiosDto = parametrosServicio.listarDominiosDto("INFRAESTRUCTURA DE SERVICIOS");

        try {
            listaArbolDominios = new DefaultTreeNode();
            listaArbolDominios = WebUtils.generarArbol(listadoDominiosDto, listaArbolDominios, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void actualizarListadoPisosDetalle() {
        List<DominioDto> listadoDetallesDto = parametrosServicio.listarDominiosDto("DESCRIPCION EDIFICACION");

        try {
            listaArbolPisosDetalle = new DefaultTreeNode();
            listaArbolPisosDetalle = WebUtils.generarArbol(listadoDetallesDto, listaArbolPisosDetalle, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarElementosPredio() throws NewviExcepcion {
        catastroServicio.actualizarPredio(this.predio, sesionBean.obtenerSesionDto());
        seleccionarPredio(predio.getCodCatastral());
    }

    public void agregarNuevoBloque() throws NewviExcepcion {
        Bloques bloque = new Bloques();
        bloque.setCodCatastral(this.predio);
        bloque.setNomBloque("Nuevo");
        bloque.setBloEstado(EnumEstadoRegistro.A);
        this.predio.getBloques().add(bloque);

        try {
            actualizarElementosPredio();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF354.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF354.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void agregarPisoBloqueSeleccionado(Pisos piso, Integer codBloque) throws NewviExcepcion {
        for (Bloques bloque : this.predio.getBloques()) {
            if (bloque.getCodBloques().equals(codBloque)) {
                piso.setCodBloques(bloque);
                bloque.getPisosCollection().add(piso);
                catastroServicio.generarNuevoPiso(piso, sesionBean.obtenerSesionDto());
                catastroServicio.actualizarBloque(bloque, sesionBean.obtenerSesionDto());
                break;
            }
        }
    }

    public void agregarNuevoPiso(Integer codBloque) throws NewviExcepcion {
        Pisos piso = new Pisos();
        piso.setNomPiso("Piso nuevo");
        piso.setPisEstado(EnumEstadoRegistro.A);
        agregarPisoBloqueSeleccionado(piso, codBloque);

        try {
            actualizarElementosPredio();
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF355.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF355.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void actualizarBloqueIngresado() {
        try {
            catastroServicio.actualizarPredio(this.predio, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF341.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF341.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPisoIngresado(Pisos piso) {
        try {
            catastroServicio.actualizarPiso(piso, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF345.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF345.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }
    public void actualizarDetallePisoIngresado(PisoDetalle pisoDetalle) {
        try {
            catastroServicio.actualizarPisoDetalle(pisoDetalle, sesionBean.obtenerSesionDto());//cambiar
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF357.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF357.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void agregarDetallePiso(NodeSelectEvent event) {
        PisoDetalle pisoDetalle = new PisoDetalle();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();
        Dominios padre =((DominioDto)event.getTreeNode().getParent().getData()).getDominio();
        
        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            pisoDetalle.setGrupo(hijo.getDomiGrupos());
            pisoDetalle.setSubgrupo(padre.getDomiDescripcion());
            pisoDetalle.setDescripcion(hijo.getDomiDescripcion());
            pisoDetalle.setPiso(pisoSeleccionado);
            pisoDetalle.setEstado(EnumEstadoRegistro.A);
            pisoSeleccionado.getDetalles().add(pisoDetalle);
            actualizarPisoIngresado(pisoSeleccionado);
            
            try {
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF356.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF356.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

    public void obtenerPisoSeleccionado(Integer codPisos) throws NewviExcepcion {
        pisoSeleccionado = catastroServicio.seleccionarPiso(codPisos);
        WebUtils.obtenerContextoPeticion().execute("PF('dlg2').show()");
    }

}
