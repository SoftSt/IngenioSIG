/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
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
import ec.com.newvi.sic.enums.EnumZonaInfluencia;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.DetallesAvaluo;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.LogPredio;
import ec.com.newvi.sic.modelo.ParametroSistema;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
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
import ec.com.newvi.sic.web.utils.ValidacionUtils;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.math.BigDecimal;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped

public class FichaCatastralBB extends AdminFichaCatastralBB {

    private Propiedad propiedadActual;
    private AvaluoDto raiz;
    private List<AvaluoDto> nodo;
    private List<DetallesAvaluo> nodoFinal;
    private EnumPantallaMantenimiento pantallaActual;
    private Bloques bloqueSeleccionado;
    private TreeNode listaArbolServicios;
    private TreeNode listaArbolAvaluo;
    private TreeNode listaArbolDescripcionTerreno;
    private TreeNode listaArbolPisosDetalle;
    private TreeNode listaArbolTenencia;
    private TreeNode[] listaDominiosSeleccionados;
    private TreeNode pisosDetalleSeleccionado;
    private TreeNode servicioSeleccionado;
    private TreeNode descripcionTerrenoSeleccionado;
    private TreeNode tenenciaSeleccionada;
    private List<Fotos> listaFotosPorPredio;
    private List<String> listaFotosJpg;
    private Pisos pisoSeleccionado;
    private List<Pisos> listaEstadosPisos;
    private List<PisoDetalle> listaEstadosDetallesPisos;
    private EnumEstadoPisoDetalle[] listaEstadosPisoDetalle;
    private EnumTenencia[] listaTenenciaDominios;
    private EnumZonaInfluencia[] listaZonaInfluencia;
    private EnumTraslacion[] listaTraslacion;
    private EnumSitActual[] listaSituacionActual;
    private EnumSiNo[] listaEstadoEscritura;
    private Boolean esPantallaEdicion;
    private Boolean esPantallaEliminacion;
    private Boolean esPantallaFormularios;
    private Boolean esPantallaLista;
    private Boolean esPantallaEditable;
    private Boolean esPantallaNueva;
    private String direccionVisorPredios;
    private UploadedFile imagenCargada;

    public UploadedFile getImagenCargada() {
        return imagenCargada;
    }

    public void setImagenCargada(UploadedFile imagenCargada) {
        this.imagenCargada = imagenCargada;
    }

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

    public TreeNode getTenenciaSeleccionada() {
        return tenenciaSeleccionada;
    }

    public void setTenenciaSeleccionada(TreeNode tenenciaSeleccionada) {
        this.tenenciaSeleccionada = tenenciaSeleccionada;
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

    public EnumZonaInfluencia[] getListaZonaInfluencia() {
        return listaZonaInfluencia;
    }

    public void setListaZonaInfluencia(EnumZonaInfluencia[] listaZonaInfluencia) {
        this.listaZonaInfluencia = listaZonaInfluencia;
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

    public TreeNode getListaArbolTenencia() {
        return listaArbolTenencia;
    }

    public void setListaArbolTenencia(TreeNode listaArbolTenencia) {
        this.listaArbolTenencia = listaArbolTenencia;
    }

    public List<Pisos> getListaEstadosPisos() {
        return listaEstadosPisos;
    }

    public void setListaEstadosPisos(List<Pisos> listaEstadosPisos) {
        this.listaEstadosPisos = listaEstadosPisos;
    }

    public List<PisoDetalle> getListaEstadosDetallesPisos() {
        return listaEstadosDetallesPisos;
    }

    public void setListaEstadosDetallesPisos(List<PisoDetalle> listaEstadosDetallesPisos) {
        this.listaEstadosDetallesPisos = listaEstadosDetallesPisos;
    }

    @PostConstruct
    public void init() {

        this.predio = new Predios();
        deshabilitarPantallas();
        seleccionPantallas();
    }

    public void actulizarEstadosPisos() {
        this.listaEstadosPisos = catastroServicio.consultarStsEstadoPiso();
    }

    public void actulizarEstadosDetallesPisos() {
        this.listaEstadosDetallesPisos = catastroServicio.consultarStsEstadoDetallePiso();
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

    @Override
    public void seleccionarPredio(Integer idPredio) {
        this.seleccionarPredioPorCodigo(idPredio);
        //calcularAvaluo();
        this.listaArbolAvaluo = new DefaultTreeNode();
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
                listaFotosJpg.add(rutaFotografias.concat("vacio.jpg"));
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

    @Override
    protected void seleccionarPredioPorCodigo(Integer idPredio) {
        try {
            super.seleccionarPredioPorCodigo(idPredio);
            FichaCatastralDto fichaCatastralActual = new FichaCatastralDto(this.predio);
            propiedadActual = fichaCatastralActual.getPropiedad();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
        listarFotosPorPredio(this.predio.getCodCatastral());
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
        bloque.setValNropisos("0");
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
                    numPisos = (Integer.valueOf(txtNPisos.trim()));
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
        piso.setStsEstado("Estable");
        piso.setValAreapiso(BigDecimal.ZERO);
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

    public void actualizarTenecia() {
    }

    public void actualizarPisoIngresado(Pisos piso) {
        try {
            if(ComunUtil.esNulo(piso.getStsEstado())){
                piso.setStsEstado("Estable");
            }
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

    public void registrarDetallesPiso(Pisos piso, PisoDetalle detalle, String subGrupo) throws NewviExcepcion {
        for (Bloques bloque : this.predio.getBloques()) {
            if (bloque.equals(piso.getCodBloques())) {
                for (Pisos objetoPiso : bloque.getPisosCollection()) {
                    if (piso.equals(objetoPiso)) {
                        detalle.setPiso(piso);
                        catastroServicio.generarNuevoPisoDetalle(detalle, sesionBean.getSesion());
                        String logDetallesPiso = generarLogDetallesPiso(detalle, piso.getCodPisos().toString(), bloque.getCodBloques().toString(), predio);
                        generarLogPredio(this.predio, logDetallesPiso, sesionBean.getSesion(), EnumAcciones.Agregacion_Detalles_Pisos);
                        Collection<PisoDetalle> coleccion = objetoPiso.getDetalles();
                        //quitarPisoDetalleActual(obtenerDetallePisoActualizable(coleccion, subGrupo), coleccion);
                        //coleccion.remove(obtenerDetallePisoActualizable(coleccion, subGrupo));
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

    public PisoDetalle obtenerDetallePisoActualizable(Collection<PisoDetalle> listaPisoDetalle, String subGrupo) throws NewviExcepcion {
        String subGrupoActual;
        EnumEstadoRegistro estadoPisoDetalle;
        Integer codPisoDetalle;
        if (!ComunUtil.esNulo(listaPisoDetalle)) {
            for (PisoDetalle pisoDetalleVerificable : listaPisoDetalle) {
                subGrupoActual = pisoDetalleVerificable.getSubgrupo();
                estadoPisoDetalle = pisoDetalleVerificable.getEstado();
                codPisoDetalle = pisoDetalleVerificable.getCodPisoDetalle();
                if (!ComunUtil.esNulo(subGrupoActual) && subGrupoActual.trim().equals(subGrupo.trim()) && estadoPisoDetalle.equals(EnumEstadoRegistro.A)) {
                    PisoDetalle pisoDetalleActual = catastroServicio.seleccionarDetallePiso(codPisoDetalle);
                    pisoDetalleActual.setEstado(EnumEstadoRegistro.E);
                    return pisoDetalleActual;
                }
            }
        }
        return null;
    }

    public void quitarPisoDetalleActual(PisoDetalle pisoDetalleActual, Collection<PisoDetalle> coleccion) throws NewviExcepcion {
        if (!ComunUtil.esNulo(pisoDetalleActual) && !ComunUtil.esNulo(coleccion)) {
            catastroServicio.actualizarPisoDetalle(pisoDetalleActual, sesionBean.getSesion());
            coleccion.remove(pisoDetalleActual);
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
            pisoDetalle.setEstadoDetalle("Estable");
            registrarDetallesPiso(pisoSeleccionado, pisoDetalle, padre.getDomiDescripcion());
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

    public Terreno obtenerTerrenoActualizable(Collection<Terreno> listaTerreno, String subGrupo) throws NewviExcepcion {
        String subGrupoActual;
        EnumEstadoRegistro estadoTerreno;
        Integer codTerreno;
        for (Terreno terrenoVerificable : listaTerreno) {
            subGrupoActual = terrenoVerificable.getStsSubGrupo();
            estadoTerreno = terrenoVerificable.getTerEstado();
            codTerreno = terrenoVerificable.getCodTerrenodetalle();
            if (!ComunUtil.esNulo(subGrupoActual) && subGrupoActual.trim().equals(subGrupo.trim()) && estadoTerreno.equals(EnumEstadoRegistro.A)) {
                Terreno terrenoActual = catastroServicio.seleccionarTerreno(codTerreno);
                terrenoActual.setTerEstado(EnumEstadoRegistro.E);
                return terrenoActual;
            }
        }
        return null;
    }

    public void quitarDescripcionTerrenoActual(Terreno terrenoActual) throws NewviExcepcion {
        if (!ComunUtil.esNulo(terrenoActual)) {
            catastroServicio.actualizarTerreno(terrenoActual, sesionBean.getSesion());
            this.predio.getCaracteristicasTerreno().remove(terrenoActual);
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

            try {
                quitarDescripcionTerrenoActual(obtenerTerrenoActualizable(this.predio.getCaracteristicasTerreno(), padre.getDomiDescripcion()));
                this.predio.getCaracteristicasTerreno().add(terreno);
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

    public Propiedad obtenerPropiedad(Predios predio) {
        FichaCatastralDto ficha = new FichaCatastralDto(predio);
        return ficha.getPropiedad();
    }

    public Tenencia obtenerTenenciaActualizable(List<Tenencia> listaTenecias, String subGrupo) throws NewviExcepcion {
        for (Tenencia tenencia : listaTenecias) {
            if (tenencia.getStsSubgrupo().trim().contains(subGrupo.trim()) && tenencia.getTenEstado().equals(EnumEstadoRegistro.A)) {
                Tenencia tenenciaActual = contribuyentesServicio.seleccionarTenencia(tenencia.getCodTenencia());
                tenenciaActual.setTenEstado(EnumEstadoRegistro.E);
                return tenenciaActual;
            }
        }
        return null;
    }

    public void agregarTenenciaSeleccionada(NodeSelectEvent event) {
        Tenencia tenencia = new Tenencia();
        Tenencia tenenciaActual;
        Dominios hijo = ((DominioDto) event.getTreeNode().getData()).getDominio();
        Propiedad actual = obtenerPropiedad(this.predio);

        //generarLogPredio(this.predio, EnumAcciones.Agregacion_Descripcion_Terreno.getAccion(), sesionBean.getSesion());
        EnumRelacionDominios nodo = hijo.getDomiRelacion();
        if (!nodo.equals(EnumRelacionDominios.SubNodo) && !nodo.equals(EnumRelacionDominios.Nodo)) {
            Dominios padre = ((DominioDto) event.getTreeNode().getParent().getData()).getDominio();
            tenencia.setStsGrupo(hijo.getDomiGrupos());
            tenencia.setStsSubgrupo(padre.getDomiDescripcion());
            tenencia.setStsDescripcion(hijo.getDomiDescripcion());

            tenencia.setCodPropietarios(actual);
            tenencia.setTenEstado(EnumEstadoRegistro.A);
            tenencia.setStsCodigo(hijo.getDomiCodigo());

            try {
                tenenciaActual = obtenerTenenciaActualizable(actual.getTenenciaList(), padre.getDomiDescripcion());
                contribuyentesServicio.actualizarTenencia(tenenciaActual, sesionBean.getSesion());
                actual.getTenenciaList().remove(tenenciaActual);
                actual.getTenenciaList().add(tenencia);
                contribuyentesServicio.generarNuevaTenencia(tenencia, sesionBean.getSesion());
                //generarLogPredio(this.predio, generarLogDescripcionTerreno(terreno, this.predio), sesionBean.getSesion(), EnumAcciones.Agregacion_Descripcion_Terreno);
                actualizarElementosPredio();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF364.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF364.presentarMensaje());
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

    public void abrirDialogTenencia() throws NewviExcepcion {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgTenencia').show()");
    }

    public void actualizarPropiedad(int cod_propiedad) {
        try {
            contribuyentesServicio.actualizarPropiedad(this.propiedadActual, sesionBean.getSesion());
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
            String formatoMonedaSistema = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.FORMATO_MONEDAS, sesionBean.getSesion()).getValor();
            this.nodo = catastroServicio.obtenerAvaluoPredio(this.predio, parametrosServicio.consultarDominios(), formatoMonedaSistema, sesionBean.getSesion());
            //catastroServicio.registrarArbol(this.nodo, this.predio, sesionBean.getSesion());
            //generarArbolAvaluo(catastroServicio.listarAvaluoDto("Nodo", this.predio));
            generarArbolAvaluo(this.nodo);
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte) {
        return generarReporteCatastro(tipoReporte, ReporteGenerador.FormatoReporte.PDF, obtenerDatosReporteCatastral(this.predio), PresentacionFichaCatastralDto.class);
    }

    private ReporteGenerador.FormatoReporte obtenerFormatoReporte(String tipoReporte) {
        if (tipoReporte.equals("PDF")) {
            return ReporteGenerador.FormatoReporte.PDF;
        } else if (tipoReporte.equals("XLSX")) {
            return ReporteGenerador.FormatoReporte.XLSX;
        } else {
            return ReporteGenerador.FormatoReporte.DOCX;
        }
    }

    public DefaultStreamedContent imprimirLista(EnumReporte tipoReporte, String formatoReporte) {
        return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerListaFichas(this.listaFichas), PresentacionFichaCatastralDto.class);
        //return generarReporteCatastro(tipoReporte, obtenerFormatoReporte(formatoReporte), obtenerDatosReporteListaTitulosDesmarcados(this.listaFichas), PresentacionFichaCatastralDto.class);
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
            actulizarEstadosPisos();
            actulizarEstadosDetallesPisos();
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
            /*this.esPantallaNueva = false;
            actualizarCaracteristicasPredios();
            establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_NUEVO_TITULO,
                    EnumEtiquetas.FICHA_CATASTRAL_NUEVO_ICONO,
                    EnumEtiquetas.FICHA_CATASTRAL_NUEVO_DESCRIPCION);
            crearNuevoPredio();*/
            this.esPantallaFormularios = true;
            this.esPantallaLista = true;
            conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_GENERACION);
            establecerTitulo(EnumEtiquetas.FICHA_CATASTRAL_NUEVO_TITULO,
                    EnumEtiquetas.FICHA_CATASTRAL_NUEVO_ICONO,
                    EnumEtiquetas.FICHA_CATASTRAL_NUEVO_DESCRIPCION);

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
        actualizarListadoTenencia();
        listaEstadosPisoDetalle = EnumEstadoPisoDetalle.values();
        this.listaFotosJpg = new ArrayList<>();
        listaTenenciaDominios = EnumTenencia.values();
        listaZonaInfluencia = EnumZonaInfluencia.values();
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
                bloque.eliminarHijos();
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
                            Integer numPisos = Integer.valueOf(bloque.getValNropisos().trim());
                            bloque.setValNropisos((--numPisos).toString());
                            pisoEditable.setPisEstado(EnumEstadoRegistro.I);
                            pisoEditable.eliminarHijos();
                            catastroServicio.actualizarPiso(pisoEditable, sesionBean.getSesion());
                            catastroServicio.actualizarBloque(bloque, sesionBean.getSesion());
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

    public void eliminarDetallePiso(Integer codDetallePiso) {
        try {
            Pisos pisoBuscado = catastroServicio.seleccionarPiso(catastroServicio.seleccionarDetallePiso(codDetallePiso).getPiso().getCodPisos());
            for (Bloques bloque : this.predio.getBloques()) {
                if (bloque.getCodBloques().equals(pisoBuscado.getCodBloques().getCodBloques())) {
                    for (Pisos piso : bloque.getPisosCollection()) {
                        if (piso.getCodPisos().equals(pisoBuscado.getCodPisos())) {
                            for (PisoDetalle detalle : piso.getDetalles()) {
                                Integer codDetalleActual = detalle.getCodPisoDetalle();
                                if (codDetalleActual.equals(codDetallePiso));
                                detalle.setEstado(EnumEstadoRegistro.I);
                                catastroServicio.actualizarPisoDetalle(detalle, sesionBean.getSesion());
                                catastroServicio.actualizarPredio(this.predio, sesionBean.getSesion());
                                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF374.presentarMensaje(), sesionBean.getSesion());
                                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF374.presentarMensaje());
                                break;
                            }
                        }
                    }
                }
            }
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        }
    }

    private String registrarFotoPredio(String nombreImagen) throws NewviExcepcion {
        Fotos nuevaFoto = new Fotos();
        nuevaFoto.setCodCatastral(this.predio);
        nuevaFoto.setDirFotos(nombreImagen.substring(nombreImagen.lastIndexOf('/') + 1, nombreImagen.indexOf(".")));
        nuevaFoto.setDesFotos("Fotografía 1");
        nuevaFoto.setEstFotos(EnumEstadoRegistro.A);
        return catastroServicio.generarNuevoFoto(nuevaFoto, sesionBean.getSesion());
    }

    public void cargarImagen(FileUploadEvent event) {

        String direccionArchivo;
        this.imagenCargada = event.getFile();
        String nombreImagen = event.getFile().getFileName();

        if (!ComunUtil.esNulo(this.imagenCargada)) {
            try {
                direccionArchivo = EnumParametroSistema.DIRECCION_IMAGENES_PREDIO.getValorPorDefecto();
                String nombreArchivoGuardado = registrarFotoPredio(parametrosServicio.guardarImagenPredio(EnumParametroSistema.DIRECCION_IMAGENES_PREDIO.getValorPorDefecto(), this.imagenCargada.getContents(), nombreImagen, sesionBean.getSesion()));
                MensajesFaces.mensajeInformacion("La imagen ".concat(nombreArchivoGuardado).concat(" se ha almacenado correctamente. "));

            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError("Ocurrió un error al intentar cargar la imagen. ".concat(e.getMessage()));
            }
        } else {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR010.presentarMensajeCodigo(), sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR010.presentarMensajeCodigo());
        }
        seleccionarPredio(this.predio.getCodCatastral());
    }

    public void abrirDialogCargaImagen() {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgCargaImagen').show()");
    }

}
