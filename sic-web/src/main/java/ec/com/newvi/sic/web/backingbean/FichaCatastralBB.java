/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastral;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumAcciones;
import ec.com.newvi.sic.enums.EnumEstadoPisoDetalle;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumRelacionDominios;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.enums.EnumSiNo;
import ec.com.newvi.sic.enums.EnumSitActual;
import ec.com.newvi.sic.enums.EnumTenencia;
import ec.com.newvi.sic.enums.EnumTipoPantalla;
import ec.com.newvi.sic.enums.EnumTraslacion;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.DetallesAvaluo;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.LogPredio;
import ec.com.newvi.sic.modelo.ModeloPredioLazy;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped

public class FichaCatastralBB extends AdminFichaCatastralBB {

    private Propiedad propiedadActual;
    private Propiedad propiedad;
    private List<FichaCatastralDto> listaFichas;
    private List<FichaCatastralDto> listaFichasFiltradas;
    private AvaluoDto raiz;
    private List<AvaluoDto> nodo;
    private List<DetallesAvaluo> nodoFinal;
    private EnumPantallaMantenimiento pantallaActual;
    private Bloques bloqueSeleccionado;
    private TreeNode listaArbolServicios;
    private TreeNode listaArbolAvaluo;
    private TreeNode listaArbolDescripcionTerreno;
    private TreeNode listaArbolPisosDetalle;
    private TreeNode[] listaDominiosSeleccionados;
    private TreeNode pisosDetalleSeleccionado;
    private TreeNode servicioSeleccionado;
    private TreeNode descripcionTerrenoSeleccionado;
    private List<Fotos> listaFotosPorPredio;
    private List<String> listaFotosJpg;
    private Pisos pisoSeleccionado;
    private EnumEstadoPisoDetalle[] listaEstadosPisoDetalle;
    private EnumTenencia[] listaTenenciaDominios;
    private EnumTraslacion[] listaTraslacion;
    private EnumSitActual[] listaSituacionActual;
    private EnumSiNo[] listaEstadoEscritura;
    private LazyDataModel<FichaCatastralDto> listaFichasLazy;
    private Boolean esPantallaEdicion;
    private Boolean esPantallaEliminacion;
    private Boolean esPantallaFormularios;
    private Boolean esPantallaLista;
    private Boolean esPantallaEditable;
    private Boolean esPantallaNueva;
    private String direccionVisorPredios;

    public Boolean getEsPantallaNueva() {
        return esPantallaNueva;
    }

    public void setEsPantallaNueva(Boolean esPantallaNueva) {
        this.esPantallaNueva = esPantallaNueva;
    }

    public Boolean getEsPantallaLista() {
        return esPantallaLista;
    }

    public void setEsPantallaLista(Boolean esPantallaLista) {
        this.esPantallaLista = esPantallaLista;
    }

    public Boolean getEsPantallaEditable() {
        return esPantallaEditable;
    }

    public void setEsPantallaEditable(Boolean esPantallaEditable) {
        this.esPantallaEditable = esPantallaEditable;
    }

    public Boolean getEsPantallaFormularios() {
        return esPantallaFormularios;
    }

    public void setEsPantallaFormularios(Boolean esPantallaFormularios) {
        this.esPantallaFormularios = esPantallaFormularios;
    }

    public Boolean getEsPantallaEliminacion() {
        return esPantallaEliminacion;
    }

    public void setEsPantallaEliminacion(Boolean esPantallaEliminacion) {
        this.esPantallaEliminacion = esPantallaEliminacion;
    }

    public Boolean getEsPantallaEdicion() {
        return esPantallaEdicion;
    }

    public void setEsPantallaEdicion(Boolean esPantallaEdicion) {
        this.esPantallaEdicion = esPantallaEdicion;
    }

    public LazyDataModel<FichaCatastralDto> getListaFichasLazy() {
        return listaFichasLazy;
    }

    public void setListaFichasLazy(LazyDataModel<FichaCatastralDto> listaFichasLazy) {
        this.listaFichasLazy = listaFichasLazy;
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

    public TreeNode getListaArbolAvaluo() {
        return listaArbolAvaluo;
    }

    public void setListaArbolAvaluo(TreeNode listaArbolAvaluo) {
        this.listaArbolAvaluo = listaArbolAvaluo;
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

    public AvaluoDto getRaiz() {
        return raiz;
    }

    public void setRaiz(AvaluoDto raiz) {
        this.raiz = raiz;
    }

    public String getDireccionVisorPredios() {
        return direccionVisorPredios;
    }

    @PostConstruct
    public void init() {
        this.predio = new Predios();
        deshabilitarPantallas();
        seleccionPantallas();
    }

    private void actualizarListadoPredios() {
        List<Predios> listaPredios = catastroServicio.consultarPredios();
        listaFichas = new ArrayList<>();
        listaPredios.forEach((elementoPredio) -> {
            listaFichas.add(new FichaCatastralDto(elementoPredio));
        });
        listaFichasLazy = new ModeloPredioLazy(listaFichas);
    }

    public void crearNuevoPredio() {

        this.predio = new Predios();
        this.predio.setCatEstado(EnumEstadoRegistro.A);
        insertarPredio();
//        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_ASIGNACION);

    }

    public void insertarPredio() {
        try {
            catastroServicio.generarNuevoPredio(predio, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF351.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF351.presentarMensaje());
            actualizarListadoPredios();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void generarLogPredio(Predios predio, String log, SesionDto sesion, EnumAcciones accion) {
        LogPredio logPredio = new LogPredio();
        Date fecha = Calendar.getInstance().getTime();

        logPredio.setCodCatastral(predio);
        logPredio.setTxtLog(log);
        logPredio.setCodUsu(sesion.getNombreEquipo());
        logPredio.setNomIp(sesion.getDireccionIP());
        logPredio.setFecLog(fecha);
        logPredio.setLogEstado(EnumEstadoRegistro.A);
        logPredio.setLogAccion(accion);

        try {
            catastroServicio.generarNuevoLogPredio(logPredio, sesion);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPredio() {
        if (!ComunUtil.esNumeroPositivo(this.predio.getCodCatastral())) {
            insertarPredio();
        } else {
            try {

                generarLogPredio(predio, catastroServicio.generarLogPredio(this.predio), sesionBean.getSesion(), EnumAcciones.Edicion_General);
                catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
                actualizarListadoPredios();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF352.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF352.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
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
                catastroServicio.eliminarPredio(predio, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF353.presentarMensaje());
                actualizarListadoPredios();

            } else {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.getSesion());
                MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
            }
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void seleccionarPredio(Integer idPredio) {
        this.seleccionarPredioPorCodigo(idPredio);
        calcularAvaluo();
        try {
            this.direccionVisorPredios = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.DIRECCION_VISOR_PREDIOS, sesionBean.getSesion()).getValor();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_EDITAR_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_EDITAR_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_EDITAR_DESCRIPCION);
    }

    private void listarFotosPorPredio(Integer idPredio) {
        listaFotosJpg = new ArrayList<>();
        List<Fotos> imagenes = catastroServicio.consultarFotosPorPredio(idPredio);
        try {
            String rutaFotografias = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.DIRECCION_IMAGENES_PREDIO, sesionBean.getSesion()).getValor().concat("/");
            if (imagenes.isEmpty()) {
                listaFotosJpg.add("vacio.jpg");
            } else {
                imagenes.forEach((imagen) -> {
                    listaFotosJpg.add(rutaFotografias.concat(imagen.getDirFotos().trim()).concat(".JPG"));
                });
            }
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
            listaFotosJpg.add("vacio.jpg");
        }
    }

    private void seleccionarPredioPorCodigo(Integer idPredio) {
        try {
            this.predio = catastroServicio.seleccionarPredio(idPredio);
            //this.predio.setCodManzana(this.predio.getCodManzana().trim());
            for (FichaCatastralDto fichasCatrastrales : listaFichas) {
                if (fichasCatrastrales.getPredio().getCodCatastral().equals(idPredio)) {
                    propiedad = fichasCatrastrales.getPropiedad();
                }
            }
            this.propiedadActual = contribuyentesServicio.consultarUltimoPropiedad(this.predio);

        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
            this.propiedadActual = new Propiedad();
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
            this.propiedadActual = new Propiedad();
        }

        listarFotosPorPredio(this.predio.getCodCatastral());
    }

    public Propiedad obtenerPropiedad(Predios predioConsulta) {
        try {
            return contribuyentesServicio.consultarUltimoPropiedad(predioConsulta);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
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
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
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
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void generarArbolAvaluo(List<AvaluoDto> listaArbol) {

        try {
            listaArbolAvaluo = new DefaultTreeNode();
            listaArbolAvaluo = WebUtils.generarArbol(listaArbol, listaArbolAvaluo, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
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

    private void actualizarListadoPisosDetalle() {
        List<DominioDto> listadoDetallesDto = parametrosServicio.listarDominiosDto("DESCRIPCION EDIFICACION", "Nodo");

        try {
            listaArbolPisosDetalle = new DefaultTreeNode();
            listaArbolPisosDetalle = WebUtils.generarArbol(listadoDetallesDto, listaArbolPisosDetalle, "getHijos");
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarElementosPredio() throws NewviExcepcion {
        catastroServicio.seleccionarPredio(this.predio.getCodCatastral());
        catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
    }

    public String generarLogAdicionBloques(String codBloque, Predios predio) {
        return "Se agregó el bloque '" + codBloque + "' del predio '" + predio.getCodCatastral().toString();
    }

    public void agregarNuevoBloque() throws NewviExcepcion {
        Bloques bloque = new Bloques();
        bloque.setCodCatastral(this.predio);
        bloque.setNomBloque("Nuevo");
        bloque.setBloEstado(EnumEstadoRegistro.A);
        String logBloque = generarLogAdicionBloques(catastroServicio.generarNuevoBloque(bloque, sesionBean.getSesion()), this.predio);
        this.predio.getBloques().add(bloque);

        try {
            generarLogPredio(this.predio, logBloque, sesionBean.getSesion(), EnumAcciones.Agregacion_Bloques);
            actualizarElementosPredio();
            WebUtils.obtenerContextoPeticion().reset("formularioFichaCatastral:opDetalleFichaCatastral");
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF354.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF354.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public String generarLogAdicionPiso(String codPiso, String codBloque, Predios predio) {
        return "Se agregó el piso '" + codPiso + "'"
                + "/n que pertenece al bloque '" + codBloque + "'"
                + "/n del predio '" + predio.getCodCatastral().toString() + "'";
    }

    public void agregarPisoBloqueSeleccionado(Pisos piso, Integer codBloque) throws NewviExcepcion {
        Integer numPisos;
        for (Bloques bloque : this.predio.getBloques()) {
            if (bloque.getCodBloques().equals(codBloque)) {
                piso.setCodBloques(bloque);
                String txtNPisos = bloque.getValNropisos();
                if (!ComunUtil.esNulo(txtNPisos)) {
                    numPisos = (Integer.valueOf(txtNPisos));
                } else {
                    numPisos = 0;
                }
                bloque.setValNropisos((++(numPisos)).toString());
                String codPiso = catastroServicio.generarNuevoPiso(piso, sesionBean.getSesion());
                String logPiso = generarLogAdicionPiso(codPiso, bloque.getCodBloques().toString(), this.predio);
                generarLogPredio(this.predio, logPiso, sesionBean.getSesion(), EnumAcciones.Agregacion_Pisos);
                catastroServicio.actualizarBloque(bloque, sesionBean.getSesion());
                //catastroServicio.actualizarBloque(bloque, sesionBean.getSesion());
                Collection<Pisos> coleccion = bloque.getPisosCollection();
                if (!ComunUtil.esNulo(coleccion)) {
                    bloque.getPisosCollection().add(piso);
                } else {
                    //coleccion.add(piso);
                    List<Pisos> listaPisosColeccion = new ArrayList<>();
                    listaPisosColeccion.add(piso);
                    bloque.setPisosCollection(listaPisosColeccion);
                }
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
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF355.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF355.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void actualizarBloqueIngresado() {
        try {
            catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF341.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF341.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void generarLogServicio(Predios predio) throws NewviExcepcion {
        for (Servicios servicio : predio.getServicios()) {
            String logServicio = catastroServicio.generarLogServicios(servicio);
            if (!ComunUtil.esCadenaVacia(logServicio)) {
                generarLogPredio(predio, logServicio, sesionBean.getSesion(), EnumAcciones.Edicion_Servicio);
            }
        }
    }

    public void actualizarServicioIngresado() {
        try {
            //generarLogServicio(this.predio);
            catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF361.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF361.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarPisoIngresado(Pisos piso) {
        try {
            catastroServicio.actualizarPiso(piso, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF345.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF345.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarDetallePisoIngresado(PisoDetalle pisoDetalle) {
        try {
            catastroServicio.actualizarPisoDetalle(pisoDetalle, sesionBean.getSesion());//cambiar
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF357.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF357.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public String generarLogAdicionDetallePiso(String codPiso, String codBloque, Predios predio) {
        return "Se agregó el piso '" + codPiso + "'"
                + "/n que pertenece al bloque '" + codBloque + "'"
                + "/n del predio '" + predio.getCodCatastral().toString() + "'";
    }

    public void registrarDetallesPiso(Pisos piso, PisoDetalle detalle) throws NewviExcepcion {
        for (Bloques bloque : this.predio.getBloques()) {
            if (bloque.equals(piso.getCodBloques())) {
                for (Pisos objetoPiso : bloque.getPisosCollection()) {
                    if (piso.equals(objetoPiso)) {
                        detalle.setPiso(piso);
                        catastroServicio.generarNuevoPisoDetalle(detalle, sesionBean.getSesion());
                        String logDetallesPiso = generarLogDetallesPiso(detalle, piso.getCodPisos().toString(), bloque.getCodBloques().toString(), predio);
                        generarLogPredio(this.predio, logDetallesPiso, sesionBean.getSesion(), EnumAcciones.Agregacion_Detalles_Pisos);
                        Collection<PisoDetalle> coleccion = objetoPiso.getDetalles();
                        if (!ComunUtil.esNulo(coleccion)) {
                            objetoPiso.getDetalles().add(detalle);
                        } else {
                            List<PisoDetalle> listaDetalles = new ArrayList<>();
                            listaDetalles.add(detalle);
                            objetoPiso.setDetalles(listaDetalles);
                        }
                        break;

                    }
                }
            }
        }

    }

    public void agregarDetallePiso(NodeSelectEvent event) throws NewviExcepcion {
        PisoDetalle pisoDetalle = new PisoDetalle();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            pisoDetalle.setGrupo(hijo.getDomiGrupos());
            pisoDetalle.setSubgrupo(padre.getDomiDescripcion());
            pisoDetalle.setDescripcion(hijo.getDomiDescripcion());
            pisoDetalle.setCodigo(hijo.getDomiCodigo());
            pisoDetalle.setEstado(EnumEstadoRegistro.A);
            registrarDetallesPiso(pisoSeleccionado, pisoDetalle);
            try {
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF356.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF356.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

    public void obtenerPisoSeleccionado(Integer codPisos) throws NewviExcepcion {
        pisoSeleccionado = catastroServicio.seleccionarPiso(codPisos);
        WebUtils.obtenerContextoPeticion().execute("PF('dlgDetallePiso').show()");
    }

    public String generarLogServicio(Servicios servicio, Predios predio) {
        return "Se agregó el servicio perteneciente a '" + servicio.getStsGrupo().trim() + "' "
                + "\ndel subgrupo '" + servicio.getStsSubGrupo() + "' "
                + "\ncon su descripción '" + servicio.getStsDescripcion() + "'"
                + "\ndel predio '" + predio.getCodCatastral() + "' ";
    }

    public String generarLogDescripcionTerreno(Terreno terreno, Predios predio) {
        return "Se agregó la descripción perteneciente a '" + terreno.getStsGrupo().trim() + "' "
                + "\ndel subgrupo '" + terreno.getStsSubGrupo() + "' "
                + "\ncon su descripción '" + terreno.getStsDescripcion() + "' "
                + "\ndel predio '" + predio.getCodCatastral() + "' ";
    }

    public String generarLogDetallesPiso(PisoDetalle detalles, String codPiso, String codBloque, Predios predio) {
        return "Se agregó el detalle perteneciente a '" + detalles.getGrupo().trim() + "' "
                + "\ndel subgrupo '" + detalles.getSubgrupo() + "' "
                + "\ncon su descripción '" + detalles.getDescripcion() + "' "
                + "\ndel piso '" + codPiso + "' y del bloque '" + codBloque + "' "
                + "\nperteneciente al predio '" + predio.getCodCatastral() + "' ";
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
                catastroServicio.generarNuevoServicio(servicio, sesionBean.getSesion());
                generarLogPredio(this.predio, generarLogServicio(servicio, this.predio), sesionBean.getSesion(), EnumAcciones.Agregacion_Servicio);
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF358.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF358.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

    public void agregarDescripcionTerreno(NodeSelectEvent event) {
        Terreno terreno = new Terreno();
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();

        //generarLogPredio(this.predio, EnumAcciones.Agregacion_Descripcion_Terreno.getAccion(), sesionBean.getSesion());
        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            terreno.setStsGrupo(hijo.getDomiGrupos());
            terreno.setStsSubGrupo(padre.getDomiDescripcion());
            terreno.setStsDescripcion(hijo.getDomiDescripcion());
            terreno.setCodCatastral(this.predio);
            terreno.setTerEstado(EnumEstadoRegistro.A);
            terreno.setStsCodigo(hijo.getDomiCodigo());
            this.predio.getCaracteristicasTerreno().add(terreno);

            try {
                catastroServicio.generarNuevoTerreno(terreno, sesionBean.getSesion());
                generarLogPredio(this.predio, generarLogDescripcionTerreno(terreno, this.predio), sesionBean.getSesion(), EnumAcciones.Agregacion_Descripcion_Terreno);
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF360.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF360.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
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
            contribuyentesServicio.actualizarPropiedad(this.propiedad, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF359.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF359.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void cancelarEdicionPropiedad() {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgPropiedad').close()");
    }

    public void calcularAvaluo() {
        try {
            this.nodo = catastroServicio.obtenerAvaluoPredio(this.predio, parametrosServicio.consultarDominios(), sesionBean.getSesion());
            catastroServicio.registrarArbol(this.nodo, this.predio, sesionBean.getSesion());
            generarArbolAvaluo(catastroServicio.listarAvaluoDto("Nodo", this.predio));
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte) {
        return generarReporteCatastro(tipoReporte);
    }

    public void seleccionPantallas() {
        String cadenaAccion = "";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.getExternalContext().getRequestParameterMap().isEmpty()) {
            cadenaAccion = (String) facesContext.getExternalContext().getRequestParameterMap().get("accion");
        }

        ejecutarAccion(cadenaAccion);
    }

    private void ejecutarAccion(String cadenaAccion) {
        if (cadenaAccion.equals(EnumTipoPantalla.edicionFicha.getTipoPantalla())) {
            this.esPantallaLista = true;
            this.esPantallaEdicion = true;
            establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_EDITAR_TITULO,
                    EnumEtiquetas.FICHA_CATASTRAL_LISTA_EDITAR_ICONO,
                    EnumEtiquetas.FICHA_CATASTRAL_LISTA_EDITAR_DESCRIPCION);
            actualizarListadoPredios();
            actualizarCaracteristicasPredios();
        } else if (cadenaAccion.equals(EnumTipoPantalla.eliminacionFicha.getTipoPantalla())) {
            this.esPantallaEliminacion = true;
            this.esPantallaLista = true;
            establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_ELIMINAR_TITULO,
                    EnumEtiquetas.FICHA_CATASTRAL_LISTA_ELIMINAR_ICONO,
                    EnumEtiquetas.FICHA_CATASTRAL_LISTA_ELIMINAR_DESCRIPCION);
            actualizarListadoPredios();
        } else if (cadenaAccion.equals(EnumTipoPantalla.formulariosEconomicos.getTipoPantalla())) {
            this.esPantallaFormularios = true;
            this.esPantallaLista = true;
            establecerTitulo(EnumEtiquetas.FORMULARIO_ECONOMICO_LISTA_TITULO,
                    EnumEtiquetas.FORMULARIO_ECONOMICO_LISTA_ICONO,
                    EnumEtiquetas.FORMULARIO_ECONOMICO_LISTA_DESCRIPCION);
            actualizarListadoPredios();

        } else if (cadenaAccion.equals(EnumTipoPantalla.nuevaFicha.getTipoPantalla())) {
            this.esPantallaNueva = false;
            actualizarCaracteristicasPredios();
            establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_NUEVO_TITULO,
                    EnumEtiquetas.FICHA_CATASTRAL_NUEVO_ICONO,
                    EnumEtiquetas.FICHA_CATASTRAL_NUEVO_DESCRIPCION);
            crearNuevoPredio();

        }
    }

    public void abrirDialogImpresionFormulario(Integer codCatastral) throws NewviExcepcion {
        this.predio = catastroServicio.seleccionarPredio(codCatastral);
        WebUtils.obtenerContextoPeticion().execute("PF('wgSeleccionFormulario').show()");
    }

    private void deshabilitarPantallas() {
        this.esPantallaEdicion = false;
        this.esPantallaEliminacion = false;
        this.esPantallaFormularios = false;
        this.esPantallaLista = false;
        this.esPantallaEditable = false;
        this.esPantallaNueva = true;
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_LISTA_TITULO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_ICONO,
                EnumEtiquetas.FICHA_CATASTRAL_LISTA_DESCRIPCION);
    }

    private void actualizarCaracteristicasPredios() {
        actualizarListadoServicios();
        actualizarListadoDescripcionTerreno();
        actualizarListadoPisosDetalle();
        listaEstadosPisoDetalle = EnumEstadoPisoDetalle.values();
        this.listaFotosJpg = new ArrayList<>();
        listaTenenciaDominios = EnumTenencia.values();
        listaTraslacion = EnumTraslacion.values();
        listaSituacionActual = EnumSitActual.values();
        listaEstadoEscritura = EnumSiNo.values();
    }

    public void eliminarTerreno(Integer codTerreno) {
        for (Terreno terreno : this.predio.getCaracteristicasTerreno()) {
            if (terreno.getCodTerrenodetalle().equals(codTerreno)) {
                terreno.setTerEstado(EnumEstadoRegistro.I);
                try {
                    catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
                    LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF362.presentarMensaje(), sesionBean.getSesion());
                    MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF362.presentarMensaje());
                } catch (NewviExcepcion ex) {
                    LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
                    MensajesFaces.mensajeError(ex.getMessage());
                }
            }
        }

    }

    public void eliminarServicio(Integer codServicio) {
        for (Servicios servicio : this.predio.getServicios()) {
            if (servicio.getCodServicios().equals(codServicio)) {
                servicio.setSerEstado(EnumEstadoRegistro.I);
                try {
                    LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF371.presentarMensaje(), sesionBean.getSesion());
                    MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF371.presentarMensaje());
                    catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
                } catch (NewviExcepcion ex) {
                    LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
                    MensajesFaces.mensajeError(ex.getMessage());
                }
            }
        }
    }

    public void eliminarBloque(Integer codBloque) {
        for (Bloques bloque : this.predio.getBloques()) {
            if (bloque.getCodBloques().equals(codBloque)) {
                bloque.setBloEstado(EnumEstadoRegistro.I);
                try {
                    catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
                    LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF372.presentarMensaje(), sesionBean.getSesion());
                    MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF372.presentarMensaje());
                } catch (NewviExcepcion ex) {
                    LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
                    MensajesFaces.mensajeError(ex.getMessage());
                }
            }
        }
    }

    public String obtenerCodigoLote() {
        return predio.getNomCodigocatastral().trim().substring(0, 15);
    }

    public void eliminarPiso(Integer codPiso) {
        try {
            Pisos piso = catastroServicio.seleccionarPiso(codPiso);
            for (Bloques bloque : this.predio.getBloques()) {
                if (bloque.getCodBloques().equals(piso.getCodBloques().getCodBloques())) {
                    for (Pisos pisoEditable : bloque.getPisosCollection()) {
                        if (pisoEditable.getCodPisos().equals(codPiso)) {
                            pisoEditable.setPisEstado(EnumEstadoRegistro.I);
                            catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
                            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF373.presentarMensaje(), sesionBean.getSesion());
                            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF373.presentarMensaje());
                            break;
                        }
                    }
                }
            }
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        }
    }

}
