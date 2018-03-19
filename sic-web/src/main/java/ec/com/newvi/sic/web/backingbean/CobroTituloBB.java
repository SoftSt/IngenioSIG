/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.PresentacionFichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.ConstantesDescuentos;
import ec.com.newvi.sic.modelo.ConstantesInteresMora;
import ec.com.newvi.sic.modelo.TituloMovimientos;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.servicios.TesoreriaServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class CobroTituloBB extends AdminFichaCatastralBB {

    @EJB
    private RentasServicio rentasServicio;
    @EJB
    private TesoreriaServicio tesoreriaServicio;

    private EnumPantallaMantenimiento pantallaActual;
    private List<Titulos> listaTitulosActualesSeleccionados;
    private List<Titulos> listaTitulosVencidosSeleccionados;
    private List<Titulos> listaTitulosPorPagar;
    private List<Titulos> listaTitulosCobrados;
    private List<Titulos> listaTitulosCobradosFiltrados;
    private List<Titulos> listaTitulosPorPagarFiltrados;
    private List<Titulos> listaTitulosRegistradosActuales;
    private List<Titulos> listaTitulosRegistradosActualesFiltrados;
    private List<Titulos> listaTitulosRegistradosVencidos;
    private List<Titulos> listaTitulosRegistradosVencidosFiltrados;
    private FichaCatastralDto fichaCatastral;
    private Titulos tituloActual;

    private BigDecimal totalTitulosActulales;
    private BigDecimal totalTitulosVencidos;
    private BigDecimal totalPorPagarTitulos;

    private String razonTituloDesmarcado;

    private Boolean esCobrado;

    private Titulos tituloDesmarcadoActual;

    public Titulos getTituloDesmarcadoActual() {
        return tituloDesmarcadoActual;
    }

    public void setTituloDesmarcadoActual(Titulos tituloDesmarcadoActual) {
        this.tituloDesmarcadoActual = tituloDesmarcadoActual;
    }

    public String getRazonTituloDesmarcado() {
        return razonTituloDesmarcado;
    }

    public void setRazonTituloDesmarcado(String razonTituloDesmarcado) {
        this.razonTituloDesmarcado = razonTituloDesmarcado;
    }

    public List<Titulos> getListaTitulosCobrados() {
        return listaTitulosCobrados;
    }

    public void setListaTitulosCobrados(List<Titulos> listaTitulosCobrados) {
        this.listaTitulosCobrados = listaTitulosCobrados;
    }

    public List<Titulos> getListaTitulosCobradosFiltrados() {
        return listaTitulosCobradosFiltrados;
    }

    public void setListaTitulosCobradosFiltrados(List<Titulos> listaTitulosCobradosFiltrados) {
        this.listaTitulosCobradosFiltrados = listaTitulosCobradosFiltrados;
    }

    public Boolean getEsCobrado() {
        return esCobrado;
    }

    public void setEsCobrado(Boolean esCobrado) {
        this.esCobrado = esCobrado;
    }

    public List<Titulos> getListaTitulosActualesSeleccionados() {
        return listaTitulosActualesSeleccionados;
    }

    public void setListaTitulosActualesSeleccionados(List<Titulos> listaTitulosActualesSeleccionados) {
        this.listaTitulosActualesSeleccionados = listaTitulosActualesSeleccionados;
    }

    public List<Titulos> getListaTitulosVencidosSeleccionados() {
        return listaTitulosVencidosSeleccionados;
    }

    public void setListaTitulosVencidosSeleccionados(List<Titulos> listaTitulosVencidosSeleccionados) {
        this.listaTitulosVencidosSeleccionados = listaTitulosVencidosSeleccionados;
    }

    public BigDecimal getTotalPorPagarTitulos() {
        return totalPorPagarTitulos;
    }

    public void setTotalPorPagarTitulos(BigDecimal totalPorPagarTitulos) {
        this.totalPorPagarTitulos = totalPorPagarTitulos;
    }

    public BigDecimal getTotalTitulosActulales() {
        return totalTitulosActulales;
    }

    public void setTotalTitulosActulales(BigDecimal totalTitulosActulales) {
        this.totalTitulosActulales = totalTitulosActulales;
    }

    public BigDecimal getTotalTitulosVencidos() {
        return totalTitulosVencidos;
    }

    public void setTotalTitulosVencidos(BigDecimal totalTitulosVencidos) {
        this.totalTitulosVencidos = totalTitulosVencidos;
    }

    public List<Titulos> getListaTitulosPorPagarFiltrados() {
        return listaTitulosPorPagarFiltrados;
    }

    public void setListaTitulosPorPagarFiltrados(List<Titulos> listaTitulosPorPagarFiltrados) {
        this.listaTitulosPorPagarFiltrados = listaTitulosPorPagarFiltrados;
    }

    public List<Titulos> getListaTitulosPorPagar() {
        return listaTitulosPorPagar;
    }

    public void setListaTitulosPorPagar(List<Titulos> listaTitulosPorPagar) {
        this.listaTitulosPorPagar = listaTitulosPorPagar;
    }

    public List<Titulos> getListaTitulosRegistradosActuales() {
        return listaTitulosRegistradosActuales;
    }

    public void setListaTitulosRegistradosActuales(List<Titulos> listaTitulosRegistradosActuales) {
        this.listaTitulosRegistradosActuales = listaTitulosRegistradosActuales;
    }

    public List<Titulos> getListaTitulosRegistradosActualesFiltrados() {
        return listaTitulosRegistradosActualesFiltrados;
    }

    public void setListaTitulosRegistradosActualesFiltrados(List<Titulos> listaTitulosRegistradosActualesFiltrados) {
        this.listaTitulosRegistradosActualesFiltrados = listaTitulosRegistradosActualesFiltrados;
    }

    public List<Titulos> getListaTitulosRegistradosVencidos() {
        return listaTitulosRegistradosVencidos;
    }

    public void setListaTitulosRegistradosVencidos(List<Titulos> listaTitulosRegistradosVencidos) {
        this.listaTitulosRegistradosVencidos = listaTitulosRegistradosVencidos;
    }

    public List<Titulos> getListaTitulosRegistradosVencidosFiltrados() {
        return listaTitulosRegistradosVencidosFiltrados;
    }

    public void setListaTitulosRegistradosVencidosFiltrados(List<Titulos> listaTitulosRegistradosVencidosFiltrados) {
        this.listaTitulosRegistradosVencidosFiltrados = listaTitulosRegistradosVencidosFiltrados;
    }

    public FichaCatastralDto getFichaCatastral() {
        return fichaCatastral;
    }

    public void setFichaCatastral(FichaCatastralDto fichaCatastral) {
        this.fichaCatastral = fichaCatastral;
    }

    public Titulos getTituloActual() {
        return tituloActual;
    }

    @PostConstruct
    public void init() {
        this.listaTitulosPorPagar = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_LISTA_TITULO,
                EnumEtiquetas.COBRO_TITULO_LISTA_ICONO,
                EnumEtiquetas.COBRO_TITULO_LISTA_DESCRIPCION);
        actualizarListadoPredios();
        this.esCobrado = Boolean.FALSE;
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

    public BigDecimal obtenerTotalAPagarTitulos(List<Titulos> listaTitulos) {
        BigDecimal valAPagar = BigDecimal.ZERO;
        for (Titulos tituloTotal : listaTitulos) {
            valAPagar = valAPagar.add(tituloTotal.getValPagado());
        }
        return valAPagar;
    }

    public List<Titulos> obtenerTotales(List<Titulos> listaTitulosRecargos) {
        if (!ComunUtil.esNulo(listaTitulosRecargos)) {
            List<Titulos> listaAux = new ArrayList<>();
            for (Titulos tituloRecargo : listaTitulosRecargos) {
                listaAux.add(calcularDescuentosIntereses(tituloRecargo.getCodTitulos()));
            }
            listaTitulosRecargos = new ArrayList<>();
            listaTitulosRecargos.addAll(listaAux);
        }

        return listaTitulosRecargos;
    }

    public void generarTitulosActualesYVencidos(List<Titulos> listaTitulos) {
        this.listaTitulosRegistradosActuales = new ArrayList<>();
        this.listaTitulosRegistradosVencidos = new ArrayList<>();

        if (!ComunUtil.esNulo(listaTitulos)) {
            for (Titulos titulo : listaTitulos) {
                //if (titulo.getFecEmision().before(ComunUtil.hoy())) {
                if (ComunUtil.obtenerAnioDesdeFecha(titulo.getFecEmision()) >= ComunUtil.obtenerAnioDesdeFecha(ComunUtil.hoy())) {
                    this.listaTitulosRegistradosActuales.add(titulo);
                } else {
                    this.listaTitulosRegistradosVencidos.add(titulo);
                }
            }
            this.listaTitulosRegistradosActuales = obtenerTotales(this.listaTitulosRegistradosActuales);
            this.totalTitulosActulales = obtenerTotalAPagarTitulos(this.listaTitulosRegistradosActuales);
            this.listaTitulosRegistradosVencidos = obtenerTotales(this.listaTitulosRegistradosVencidos);
            this.totalTitulosVencidos = obtenerTotalAPagarTitulos(this.listaTitulosRegistradosVencidos);
        }
    }

    public void actualizarListaTitulosRegistrados(Integer codCatastral) {
        //this.listaTitulosRegistrados = new ArrayList<>();
        List<Titulos> listaTitulosRegistrados = rentasServicio.consultarTitulosPorCodigoCatastral(codCatastral);
        generarTitulosActualesYVencidos(listaTitulosRegistrados);
    }

    public void seleccionarTituloDePredio(Integer codCatastral) throws NewviExcepcion {
        actualizarListaTitulosRegistrados(codCatastral);
        this.predio = catastroServicio.seleccionarPredio(codCatastral);
        this.fichaCatastral = new FichaCatastralDto(this.predio);
        WebUtils.obtenerContextoPeticion().execute("PF('dlgTitulos').show()");
    }

    public Titulos seleccionarTitulo(Integer codTitulo) {
        Titulos tituloEliminable = null;
        try {
            tituloEliminable = rentasServicio.seleccionarTitulo(codTitulo);
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR603.presentarMensajeCodigo(), ex, sesionBean.getSesion());
            MensajesFaces.mensajeError(ex.getMessage());
        }

        return tituloEliminable;
    }

    private String obtenerNumeroQuincena(Integer dia) {
        return dia <= 15 ? "1" : "2";
    }

    private BigDecimal calcularInteresMora(Titulos titulo) {
        Integer aniosMora = obtenerDiferenciaAniosActualEmision(titulo);
        ConstantesInteresMora interesMora = tesoreriaServicio.buscarInteresPorNumeroAnios(aniosMora);
        return interesMora.getValMonto();
    }

    private BigDecimal calcularDescuentoRecargo(Titulos titulo) {
        String mesEmision;
        Integer diaEmision;
        BigDecimal valorBase;
        BigDecimal tasaDescuento;
        ConstantesDescuentos objetoDescuentos;

        valorBase = titulo.getValTotalapagar();
        mesEmision = ComunUtil.obtenerMesDesdeFecha(titulo.getFecEmision());
        diaEmision = ComunUtil.obtenerDiaDesdeFecha(titulo.getFecEmision());
        objetoDescuentos = tesoreriaServicio.buscarDescuentoRecargoPorMesYQuincena(mesEmision, obtenerNumeroQuincena(diaEmision));
        tasaDescuento = objetoDescuentos.getValValor().divide(new BigDecimal(100));

        return objetoDescuentos.getStsEstado().trim().equals("r") ? valorBase.multiply(tasaDescuento) : valorBase.multiply(tasaDescuento).negate();
    }

    private Integer obtenerDiferenciaAniosActualEmision(Titulos titulo) {
        Integer anioActual = ComunUtil.obtenerAnioDesdeFecha(ComunUtil.hoy());
        Integer anioEmision = ComunUtil.obtenerAnioDesdeFecha(titulo.getFecEmision());
        return anioActual - anioEmision;

    }

    private Boolean esDescuento(Titulos titulo) {
        return obtenerDiferenciaAniosActualEmision(titulo) >= 1 ? Boolean.FALSE : Boolean.TRUE;
    }

    public void abrirDialogoComprobante(Integer codTitulo) {
        //calcularDescuentosIntereses(codTitulo);
        this.tituloActual = calcularDescuentosIntereses(codTitulo);
        //this.tituloActual = seleccionarTitulo(codTitulo);
        WebUtils.obtenerContextoPeticion().execute("PF('dlgResumenTitulos').show()");
    }

    public Titulos calcularDescuentosIntereses(Integer codTitulo) {
        //this.predio = this.tituloActual.getCodCatastral();
        //this.fichaCatastral = new FichaCatastralDto(this.predio);
        Titulos tituloRecargo = seleccionarTitulo(codTitulo);

        if (esDescuento(tituloRecargo)) {
            tituloRecargo.setValDescuentoaplicado(calcularDescuentoRecargo(tituloRecargo));
            tituloRecargo.setValInteresaplicado(BigDecimal.ZERO);
        } else {
            tituloRecargo.setValInteresaplicado(calcularInteresMora(tituloRecargo));
            tituloRecargo.setValDescuentoaplicado(BigDecimal.ZERO);
        }
        tituloRecargo.setValPagado(tituloRecargo.getValTotalapagar().add(tituloRecargo.getValDescuentoaplicado()).add(tituloRecargo.getValInteresaplicado()));

        return tituloRecargo;
        //WebUtils.obtenerContextoPeticion().execute("PF('dlgResumenTitulos').show()");

    }

    public void eliminarTitulo(Integer codTitulo) {
        Titulos tituloEliminable = seleccionarTitulo(codTitulo);
        tituloEliminable.setStsEstado(EnumEstadoTitulo.TITULO_ANULADO);

        try {
            rentasServicio.actualizarTitulo(tituloEliminable, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF603.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF603.presentarMensaje());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
        registrarMovimiento(tituloEliminable);
        actualizarListaTitulosRegistrados(tituloEliminable.getCodCatastral().getCodCatastral());

    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, Integer codTitulos) throws NewviExcepcion {
        return generarReporteCatastro(tipoReporte, ReporteGenerador.FormatoReporte.PDF, obtenerDatosReporteTitulos(rentasServicio.seleccionarTitulo(codTitulos)), PresentacionFichaCatastralDto.class);
    }

    public void seleccionarTitulosPredio(Integer codCatastral) throws NewviExcepcion {
        actualizarListaTitulosRegistrados(codCatastral);
        this.predio = catastroServicio.seleccionarPredio(codCatastral);
        this.fichaCatastral = new FichaCatastralDto(this.predio);

        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_EDITAR_TITULO,
                EnumEtiquetas.COBRO_TITULO_EDITAR_ICONO,
                EnumEtiquetas.COBRO_TITULO_EDITAR_DESCRIPCION);
    }

    public void regresarPaginaAnterior() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_LISTA_TITULO,
                EnumEtiquetas.COBRO_TITULO_LISTA_ICONO,
                EnumEtiquetas.COBRO_TITULO_LISTA_DESCRIPCION);
    }

    public void avanzarPaginaVerificacion() {
        seleccionarTodosTitulosAPagar();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_ASIGNACION);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_VERIFICAR_TITULO,
                EnumEtiquetas.COBRO_TITULO_VERIFICAR_ICONO,
                EnumEtiquetas.COBRO_TITULO_VERIFICAR_DESCRIPCION);
    }

    public void regresarInicio() {

        this.listaTitulosPorPagar = new ArrayList<>();
        this.listaTitulosRegistradosActuales = new ArrayList<>();
        this.listaTitulosRegistradosVencidos = new ArrayList<>();

        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_LISTA_TITULO,
                EnumEtiquetas.COBRO_TITULO_LISTA_ICONO,
                EnumEtiquetas.COBRO_TITULO_LISTA_DESCRIPCION);
    }

    public void regresarSeleccionDeTitulosACobrar() {
        this.listaTitulosPorPagar = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_EDITAR_TITULO,
                EnumEtiquetas.COBRO_TITULO_EDITAR_ICONO,
                EnumEtiquetas.COBRO_TITULO_EDITAR_DESCRIPCION);
    }

    public void registrarMovimiento(Titulos titulo) {
        TituloMovimientos movimiento = new TituloMovimientos();
        movimiento.setAnioEmision(ComunUtil.obtenerAnioDesdeFecha(titulo.getFecEmision()) + "");
        movimiento.setCodTitulos(titulo);
        movimiento.setEstadoMovimiento(EnumEstadoRegistro.A);
        movimiento.setEstadoTitulo(titulo.getStsEstado());
        movimiento.setFecMovimiento(ComunUtil.hoy());
        movimiento.setIpUsu(sesionBean.getSesion().getDireccionIP());
        movimiento.setNomUsu(sesionBean.getSesion().getNombreEquipo());
        movimiento.setTxtMovimiento(titulo.getStsEstado().getEstadoTitulo() + titulo.getCodTitulos());
        movimiento.setRazonMovimiento(!ComunUtil.esNulo(this.razonTituloDesmarcado) ? this.razonTituloDesmarcado : "");

        try {
            rentasServicio.generarNuevoMovimentosTitulo(movimiento, sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void cobrarTitulo() {

        this.listaTitulosCobrados = new ArrayList<>();

        for (Titulos tituloAPagar : this.listaTitulosPorPagar) {
            tituloAPagar.setStsEstado(EnumEstadoTitulo.TITULO_COBRADO);
            tituloAPagar.setFecFpago(ComunUtil.hoy());
            try {
                rentasServicio.actualizarTitulo(tituloAPagar, sesionBean.getSesion());
                registrarMovimiento(tituloAPagar);
                this.listaTitulosCobrados.add(seleccionarTitulo(tituloAPagar.getCodTitulos()));
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        WebUtils.obtenerContextoPeticion().execute("PF('dlgCobroTitulos').hide()");
        this.esCobrado = Boolean.TRUE;
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF604.presentarMensaje(), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF604.presentarMensaje());
    }

    public void seleccionarTodosTitulosActuales() {
        this.listaTitulosActualesSeleccionados = new ArrayList<>();
        for (Titulos titulosAValorar : listaTitulosRegistradosActuales) {
            this.listaTitulosActualesSeleccionados.add(titulosAValorar);
        }
    }

    public void seleccionarTodosTitulosVencidos() {
        this.listaTitulosVencidosSeleccionados = new ArrayList<>();
        for (Titulos titulosAValorar : listaTitulosRegistradosVencidos) {
            this.listaTitulosVencidosSeleccionados.add(titulosAValorar);
        }
    }

    public List<Titulos> adjuntarTitulosALista(List<Titulos> listaTitulosBase, List<Titulos> listaTitulos) {
        for (Titulos titulo : listaTitulos) {
            listaTitulosBase.add(titulo);
        }
        return listaTitulosBase;
    }

    public void seleccionarTodosTitulosAPagar() {
        this.listaTitulosPorPagar = new ArrayList<>();
        List<Titulos> listaGeneral = new ArrayList<>();

        listaGeneral.addAll(this.listaTitulosVencidosSeleccionados);
        listaGeneral.addAll(this.listaTitulosActualesSeleccionados);
        this.listaTitulosPorPagar = adjuntarTitulosALista(this.listaTitulosPorPagar, listaGeneral);
        //this.listaTitulosPorPagar = adjuntarTitulosALista(this.listaTitulosPorPagar, listaTitulosActualesSeleccionados);
        this.totalPorPagarTitulos = obtenerTotalAPagarTitulos(this.listaTitulosPorPagar);
    }

    public void cobrarTituloPrueba() {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgCobroTitulos').close()");
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF604.presentarMensaje(), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF604.presentarMensaje());

    }

    public void avanzarFinal() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_BUSQUEDA);
        establecerTitulo(EnumEtiquetas.COBRO_TITULO_RESUMEN_TITULO,
                EnumEtiquetas.COBRO_TITULO_RESUMEN_ICONO,
                EnumEtiquetas.COBRO_TITULO_RESUMEN_DESCRIPCION);

    }

    public void abrirDialogRazonDesmarque(Integer codTitulo) {
        this.tituloDesmarcadoActual = seleccionarTitulo(codTitulo);
        WebUtils.obtenerContextoPeticion().execute("PF('dlgRazonDesmarque').show()");
    }

    public void desmarcarTitulo() {
        if (!ComunUtil.esNulo(this.razonTituloDesmarcado)) {
            this.tituloDesmarcadoActual.setStsEstado(EnumEstadoTitulo.TITULO_DESMARCADO);

            try {
                rentasServicio.actualizarTitulo(this.tituloDesmarcadoActual, sesionBean.getSesion());
                registrarMovimiento(this.tituloDesmarcadoActual);
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF605.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF605.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
    }

}
