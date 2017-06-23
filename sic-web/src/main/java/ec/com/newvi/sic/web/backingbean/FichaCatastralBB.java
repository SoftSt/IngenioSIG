/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoPisoDetalle;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
import ec.com.newvi.sic.enums.EnumSiNo;
import ec.com.newvi.sic.enums.EnumSitActual;
import ec.com.newvi.sic.enums.EnumTenencia;
import ec.com.newvi.sic.enums.EnumTraslacion;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.modelo.Servicios;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.ValidacionUtils;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.websocket.OnOpen;
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
    private Bloques bloqueAvaluo;
    private TreeNode listaArbolServicios;
    private TreeNode listaArbolDescripcionTerreno;
    private TreeNode listaArbolPisosDetalle;
    private TreeNode[] listaDominiosSeleccionados;
    private TreeNode pisosDetalleSeleccionado;
    private TreeNode servicioSeleccionado;
    private TreeNode descripcionTerrenoSeleccionado;
    private List<Fotos> listaFotosPorPredio;
    private List<String> listaFotosJpg;
    private Pisos pisoSeleccionado;
    private Pisos pisoAvaluo;
    private EnumEstadoPisoDetalle[] listaEstadosPisoDetalle;
    private EnumTenencia[] listaTenenciaDominios;
    private EnumTraslacion[] listaTraslacion;
    private EnumSitActual[] listaSituacionActual;
    private EnumSiNo[] listaEstadoEscritura;

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

    public TreeNode getListaArbolServicios() {
        return listaArbolServicios;
    }

    public void setListaArbolServicios(TreeNode listaArbolServicios) {
        this.listaArbolServicios = listaArbolServicios;
    }

    public TreeNode getListaArbolDescripcionTerreno() {
        return listaArbolDescripcionTerreno;
    }

    public void setListaArbolDescripcionTerreno(TreeNode listaArbolDescripcionTerreno) {
        this.listaArbolDescripcionTerreno = listaArbolDescripcionTerreno;
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

    public EnumTenencia[] getListaTenenciaDominios() {
        return listaTenenciaDominios;
    }

    public void setListaTenenciaDominios(EnumTenencia[] listaTenenciaDominios) {
        this.listaTenenciaDominios = listaTenenciaDominios;
    }

    public EnumTraslacion[] getListaTraslacion() {
        return listaTraslacion;
    }

    public void setListaTraslacion(EnumTraslacion[] listaTraslacion) {
        this.listaTraslacion = listaTraslacion;
    }

    public EnumSitActual[] getListaSituacionActual() {
        return listaSituacionActual;
    }

    public void setListaSituacionActual(EnumSitActual[] listaSituacionActual) {
        this.listaSituacionActual = listaSituacionActual;
    }

    public EnumSiNo[] getListaEstadoEscritura() {
        return listaEstadoEscritura;
    }

    public void setListaEstadoEscritura(EnumSiNo[] listaEstadoEscritura) {
        this.listaEstadoEscritura = listaEstadoEscritura;
    }

    @PostConstruct
    public void init() {
        this.predio = new Predios();
        listaEstadosPisoDetalle = EnumEstadoPisoDetalle.values();
        actualizarListadoPredios();
        actualizarListadoServicios();
        actualizarListadoDescripcionTerreno();
        actualizarListadoPisosDetalle();
        this.listaFotosJpg = new ArrayList<>();
        listaTenenciaDominios = EnumTenencia.values();
        listaTraslacion = EnumTraslacion.values();
        listaSituacionActual = EnumSitActual.values();
        listaEstadoEscritura = EnumSiNo.values();
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
            calcularAvaluo();
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
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void actualizarListadoDescripcionTerreno() {
        List<DominioDto> listadoDominiosDto = parametrosServicio.listarDominiosDto("DESCRIPCION DEL TERRENO", "Nodo");

        try {
            listaArbolDescripcionTerreno = new DefaultTreeNode();
            listaArbolDescripcionTerreno = WebUtils.generarArbol(listadoDominiosDto, listaArbolDescripcionTerreno, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void actualizarListadoPisosDetalle() {
        List<DominioDto> listadoDetallesDto = parametrosServicio.listarDominiosDto("DESCRIPCION EDIFICACION", "Nodo");

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
        seleccionarPredio(this.predio.getCodCatastral());
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

    public void actualizarServicioIngresado() {
        try {
            catastroServicio.actualizarPredio(this.predio, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF361.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF361.presentarMensaje());
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

        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            pisoDetalle.setGrupo(hijo.getDomiGrupos());
            pisoDetalle.setSubgrupo(padre.getDomiDescripcion());
            pisoDetalle.setDescripcion(hijo.getDomiDescripcion());
            pisoDetalle.setPiso(pisoSeleccionado);
            pisoDetalle.setCodigo(hijo.getDomiCodigo());
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
        WebUtils.obtenerContextoPeticion().execute("PF('dlgDetallePiso').show()");
    }

    public void agregarServicio(NodeSelectEvent event) {
        Servicios servicio = new Servicios();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            servicio.setStsGrupo(hijo.getDomiGrupos());
            servicio.setStsSubGrupo(padre.getDomiDescripcion());
            servicio.setStsDescripcion(hijo.getDomiDescripcion());
            servicio.setCodCatastral(this.predio);
            servicio.setStsCodigo(hijo.getDomiCodigo());
            servicio.setSerEstado(EnumEstadoRegistro.A);
            this.predio.getServicios().add(servicio);

            try {
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF358.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF358.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

    public void agregarDescripcionTerreno(NodeSelectEvent event) {
        Terreno terreno = new Terreno();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            terreno.setStsGrupo(hijo.getDomiGrupos());
            terreno.setStsSubgrupo(padre.getDomiDescripcion());
            terreno.setStsDescripcion(hijo.getDomiDescripcion());
            terreno.setCodCatastral(this.predio);
            terreno.setTerEstado(EnumEstadoRegistro.A);
            terreno.setStsCodigo(hijo.getDomiCodigo());
            this.predio.getCaracteristicasTerreno().add(terreno);

            try {
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF360.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF360.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

    public void abrirDialogServicios() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgServicios').show()");
    }

    public void abrirDialogDescripcionTerreno() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgDescripcionTerreno').show()");
    }

    public void actualizarPropiedad(int cod_propiedad) {
        try {
            contribuyentesServicio.actualizarPropiedad(this.propiedad, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF359.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF359.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void cancelarEdicionPropiedad() {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgPropiedad').close()");
    }

    public BigDecimal obtenerValoracionFondoRelativo(BigDecimal area, BigDecimal frente) {
        BigDecimal v1, v2;

        v1 = new BigDecimal(0);
        v2 = new BigDecimal(0);

        v1 = area.divide(frente, 4, RoundingMode.CEILING);
        v2 = frente.divide(v1, 4, RoundingMode.CEILING);

        return parametrosServicio.obtenerCOFF(v2, "FACTOR FRENTE FONDO");

    }

    public void calcularAvaluo() throws NewviExcepcion {
        BigDecimal coff, cot, cofo, cubi, cero, fa1, div, vestruc, vcabado, vextra, areapiso, edad, vdepre, vterreno, area, frente, v1, v2, v3, valor_terreno, cosB, areaB, cost, valPredio, c1, c2, c3, c4, c5, c6, basura, aPagar;
        Object[] objetoPiso;
        Integer codigo, codigo_piso, reposicion, codigo_bloque;
        String piso, estado, zona, sector, consulta;
        Boolean ba;

        codigo = this.predio.getCodCatastral();
        fa1 = new BigDecimal(0);
        div = new BigDecimal(5);
        frente = this.predio.getValAreaFrente();
        area = this.predio.getValAreaFondo();
        cosB = new BigDecimal(0);
        areaB = new BigDecimal(0);
        cost = new BigDecimal(0);

        c1 = new BigDecimal(0);
        c2 = new BigDecimal(0);
        c3 = new BigDecimal(0);
        c4 = new BigDecimal(0);
        c5 = new BigDecimal(0);
        c6 = new BigDecimal(0);

        aPagar = new BigDecimal(0);

        piso = "";
        estado = "";
        zona = this.predio.getCodZona();
        sector = this.predio.getCodSector();

        edad = new BigDecimal(0);

        // Calculo del fondo relativo COFF
        coff = obtenerValoracionFondoRelativo(area, frente);
        // Coeficiente de Topografía COT
        cot = parametrosServicio.obtenerValor(codigo, "TOPOGRAFIA");
        // Coeficinte de Erosion
        cero = parametrosServicio.obtenerValor(codigo, "LOCALIZACION");
        // Coeficinte de forma COFO
        cofo = parametrosServicio.obtenerValor(codigo, "FORMA");
        // Coeficinte de Ubicacion
        cubi = parametrosServicio.obtenerValor(codigo, "OCUPACION");

        fa1 = (fa1.add(coff).add(cot).add(cofo).add(cero).add(cubi)).divide(div, 4, RoundingMode.CEILING);

        // CALCULO DEL PRECIO BASE PARA EL TERRENO
        // SE TOMA EN CUENTA UNA VALORACION POR LAS ZONAS y SECTORES DEL MUNICIPIO.
        consulta = "20" + zona + sector;

        vterreno = parametrosServicio.obtenerValorPorCodigoCalculo(consulta, "ZONAS VALORADAS M2");

        valor_terreno = (fa1.multiply(area)).multiply(vterreno);

        //this.predio.setValTerreno(valor_terreno);
        //actualizarPredio();
        List<Bloques> bloques = catastroServicio.buscarBloquesPorCodigoCatastral(codigo);

        for (Bloques bloque : bloques) {

            codigo_bloque = bloque.getCodBloques();

            for (Object pisos : catastroServicio.obtenerDatosPisoPorBloque(codigo_bloque)) {
                objetoPiso = (Object[]) pisos;
                codigo_piso = (Integer) objetoPiso[0];
                piso = (String) objetoPiso[1];
                areapiso = (BigDecimal) objetoPiso[2];
                edad = new BigDecimal((Double) objetoPiso[3]);
                reposicion = (Integer) objetoPiso[4];
                estado = (String) objetoPiso[5];

                //Factor de depreciación de inmueble por piso y depreciacion
                vdepre = parametrosServicio.obtenerVDEPRE(edad, estado);

                //Detalle de construccion Estructura
                vestruc = parametrosServicio.obtenerDetalleContruccion(codigo_piso, "ESTRUCTURA");
                v1 = parametrosServicio.obtenerValorPorCodigo("210101");
                //Destalle de construccion Acabados
                vcabado = parametrosServicio.obtenerDetalleContruccion(codigo_piso, "ACABADOS");
                v2 = parametrosServicio.obtenerValorPorCodigo("210102");
                //Detalle de construccion Extras
                vextra = parametrosServicio.obtenerDetalleContruccion(codigo_piso, "EXTRAS");
                v3 = parametrosServicio.obtenerValorPorCodigo("210103");

                // Factos de costos del pios es igual a la suma de los factores por el área menos la depreciación del bien por edad y estado
                cosB = (vestruc.multiply(v1)).add(vcabado.multiply(v2)).add(vextra.multiply(v3)).subtract(((vestruc.multiply(v1)).add(vcabado.multiply(v2)).add(vextra.multiply(v3))).multiply(vdepre));
                areaB = areaB.add(areapiso);
                cost = cost.add(cosB);

                // Ubica valor de calculos en la tabla de pisos
                pisoAvaluo = catastroServicio.seleccionarPiso(codigo_piso);

                pisoAvaluo.setValFactordepreciacion(vdepre);
                pisoAvaluo.setValSumafactores(vestruc.add(vcabado).add(vextra));
                pisoAvaluo.setValConstante(fa1);
                pisoAvaluo.setValMetro2(((vestruc.multiply(v1)).add(vcabado.multiply(v2)).add(vextra.multiply(v3))).multiply(vdepre));
                pisoAvaluo.setValPiso(cosB);

                //catastroServicio.actualizarPiso(pisoAvaluo, sesionBean.obtenerSesionDto());
            }

            // Actualiza Valores por Bloque
            bloqueAvaluo = catastroServicio.seleccionarBloque(codigo_bloque);

            bloqueAvaluo.setValAreabloque(areaB);
            bloqueAvaluo.setValBloque(cosB);

            //catastroServicio.actualizarBloque(bloqueAvaluo, sesionBean.obtenerSesionDto());
            // Actualiza Valoración de Terreno y Contrucción
            this.predio.setValEdifica(cost);
            this.predio.setValAreaConstruccion(areaB);

            valPredio = valor_terreno.add(cost);

            this.predio.setValPredio(valPredio);

            //catastroServicio.actualizarPredio(this.predio, sesionBean.obtenerSesionDto());
            // Constantes catastro urbano
            List<ConstantesImpuestos> constantesImpuestos = parametrosServicio.obtenerConstantesImpuestosPorTipo("URBANO");

            for (ConstantesImpuestos constantesImpuesto : constantesImpuestos) {
                c1 = constantesImpuesto.getValBomberos();
                c2 = constantesImpuesto.getValServiciosadministrativos();
                c3 = constantesImpuesto.getValCem();
                c4 = constantesImpuesto.getValBasura();
                c5 = constantesImpuesto.getValTasaaplicada();
                c6 = constantesImpuesto.getValAmbientales();
            }

            // Ubica Valor recoleccion de basura segun Zona mirar domi_calculo = TASA RECOLECCIÓN DE BASURA            
            consulta = "60" + zona;
            basura = parametrosServicio.obtenerValorPorCodigoCalculo(consulta, "TASA RECOLECCIÓN DE BASURA");
            ba = parametrosServicio.tieneBasura(codigo);

            if (ba) {
                aPagar = ((valPredio.multiply(c5)).add(c2)).add(c3).add(c6).add((valPredio.multiply(c1)).multiply(c5)).add(basura);
                // Actualiza otros valores calculados
                this.predio.setValCem(c3);
                this.predio.setValBomberos((valPredio.multiply(c1)).multiply(c5));
                this.predio.setValEmision(c2);
                this.predio.setValBasura(basura);
                this.predio.setValAmbientales(c6);
                this.predio.setValImpuesto(valPredio.add(c5));
                this.predio.setValImppredial(aPagar);
                //catastroServicio.actualizarPredio(this.predio, sesionBean.obtenerSesionDto());

            }

        }

    }

}
