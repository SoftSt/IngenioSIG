/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumAplicacion;
import ec.com.newvi.sic.enums.EnumAplicacionCEM;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.ContribucionMejoras;
import ec.com.newvi.sic.modelo.ObrasDetalle;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import ec.com.newvi.sic.web.utils.ValidacionUtils;
import ec.com.newvi.sic.web.utils.WebUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class ContribucionMejorasBB extends AdminContribucionMejorasBB {

    private ContribucionMejoras contribucionMejoras;
    private ContribucionMejoras ObraActual;
    private ObrasDetalle obrasDetalle;
    private List<ContribucionMejoras> listaContribucionMejoras;
    private List<ContribucionMejoras> listaContribucionMejorasFiltrado;
    private List<ObrasDetalle> listaObrasDetalleFiltrado;
    private List<ObrasDetalle> listaObrasDetalle;
    private List<Predios> listaPredios;
    private List<Predios> listaPrediosActualizados;
    private List<Predios> listaPrediosFiltrado;
    private List<Predios> listaPrediosSeleccionados;
    private Predios predioSeleccionado;
    private EnumPantallaMantenimiento pantallaActual;
    private EnumAplicacion[] listaAplicacion;
    private Boolean aplicaOrdenanza;

    public List<Predios> getListaPrediosActualizados() {
        return listaPrediosActualizados;
    }

    public void setListaPrediosActualizados(List<Predios> listaPrediosActualizados) {
        this.listaPrediosActualizados = listaPrediosActualizados;
    }

    public ObrasDetalle getObrasDetalle() {
        return obrasDetalle;
    }

    public void setObrasDetalle(ObrasDetalle obrasDetalle) {
        this.obrasDetalle = obrasDetalle;
    }

    public Predios getPredioSeleccionado() {
        return predioSeleccionado;
    }

    public void setPredioSeleccionado(Predios predioSeleccionado) {
        this.predioSeleccionado = predioSeleccionado;
    }

    public List<Predios> getListaPrediosSeleccionados() {
        return listaPrediosSeleccionados;
    }

    public void setListaPrediosSeleccionados(List<Predios> listaPrediosSeleccionados) {
        this.listaPrediosSeleccionados = listaPrediosSeleccionados;
    }

    public ContribucionMejoras getContribucionMejoras() {
        return contribucionMejoras;
    }

    public void setContribucionMejoras(ContribucionMejoras contribucionMejoras) {
        this.contribucionMejoras = contribucionMejoras;
    }

    public List<ContribucionMejoras> getListaContribucionMejoras() {
        return listaContribucionMejoras;
    }

    public void setListaContribucionMejoras(List<ContribucionMejoras> listaContribucionMejoras) {
        this.listaContribucionMejoras = listaContribucionMejoras;
    }

    public List<ContribucionMejoras> getListaContribucionMejorasFiltrado() {
        return listaContribucionMejorasFiltrado;
    }

    public void setListaContribucionMejorasFiltrado(List<ContribucionMejoras> listaContribucionMejorasFiltrado) {
        this.listaContribucionMejorasFiltrado = listaContribucionMejorasFiltrado;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public List<ObrasDetalle> getListaObrasDetalle() {
        return listaObrasDetalle;
    }

    public void setListaObrasDetalle(List<ObrasDetalle> listaObrasDetalle) {
        this.listaObrasDetalle = listaObrasDetalle;
    }

    public List<ObrasDetalle> getListaObrasDetalleFiltrado() {
        return listaObrasDetalleFiltrado;
    }

    public void setListaObrasDetalleFiltrado(List<ObrasDetalle> listaObrasDetalleFiltrado) {
        this.listaObrasDetalleFiltrado = listaObrasDetalleFiltrado;
    }

    public List<Predios> getListaPredios() {
        return listaPredios;
    }

    public void setListaPredios(List<Predios> listaPredios) {
        this.listaPredios = listaPredios;
    }

    public List<Predios> getListaPrediosFiltrado() {
        return listaPrediosFiltrado;
    }

    public void setListaPrediosFiltrado(List<Predios> listaPrediosFiltrado) {
        this.listaPrediosFiltrado = listaPrediosFiltrado;
    }

    public EnumAplicacion[] getListaAplicacion() {
        return listaAplicacion;
    }

    public void setListaAplicacion(EnumAplicacion[] listaAplicacion) {
        this.listaAplicacion = listaAplicacion;
    }

    public Boolean getAplicaOrdenanza() {
        return aplicaOrdenanza;
    }

    public void setAplicaOrdenanza(Boolean aplicaOrdenanza) {
        this.aplicaOrdenanza = aplicaOrdenanza;
    }

    public ContribucionMejoras getObraActual() {
        return ObraActual;
    }

    public void setObraActual(ContribucionMejoras ObraActual) {
        this.ObraActual = ObraActual;
    }

    @PostConstruct
    public void init() {
        this.contribucionMejoras = new ContribucionMejoras();
        actualizarListadoContribucionMejoras();
        actualizarListadoPredios();
        listaAplicacion = EnumAplicacion.values();
        //listaTipoPersoneria= EnumTipoPersoneria.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_DESCRIPCION);
        this.aplicaOrdenanza = false;
    }

    private void actualizarListadoContribucionMejoras() {
        listaContribucionMejoras = contribucionMejorasServicio.consultarContribucionMejoras();
    }

    private void actualizarListadoObrasDetalle(Integer codObra) throws NewviExcepcion {
        listaObrasDetalle = contribucionMejorasServicio.consultarObrasDetallePorObra(codObra);
    }

    private void actualizarListadoPredios() {
        listaPredios = catastroServicio.consultarPredios();
    }

    public Boolean validacionRegistrosObrasDetalle(ObrasDetalle obraDetalleActual) throws NewviExcepcion {
        Boolean retorno = false;
        List<ObrasDetalle> listaDetalle = contribucionMejorasServicio.buscarObrasDetallePorCodigoObraCodigoCatastra(obraDetalleActual.getCodObras(), obraDetalleActual.getCodCatastral());
        if (listaDetalle.size() > 0) {
            retorno = true;
        }

        return retorno;
    }

    public void actualizarListadoPrediosParaDialog(List<ObrasDetalle> listaDetalleObrasSeleccionado) throws NewviExcepcion {
        listaPrediosActualizados = new ArrayList<>();
        Boolean esIgual = false;
        for (Predios predioNuevo : catastroServicio.consultarPredios()) {
            for (ObrasDetalle detalleObra : listaDetalleObrasSeleccionado) {
                if (predioNuevo.equals(detalleObra.getCodCatastral())) {
                    esIgual = validacionRegistrosObrasDetalle(detalleObra);
                    break;
                }
            }
            if (!esIgual) {
                listaPrediosActualizados.add(predioNuevo);
            }
            esIgual = false;
        }
    }

    public void crearNuevaContribucionMejoras() {
        this.contribucionMejoras = new ContribucionMejoras();
        this.contribucionMejoras.setObrEstado(EnumEstadoRegistro.A);
        this.listaObrasDetalle = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_NUEVO_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_NUEVO_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_NUEVO_DESCRIPCION);
    }

    public void insertarContribucionMejoras() {
        try {
            contribucionMejoras.setStsPorcentajeaplica(Boolean.TRUE);
            contribucionMejorasServicio.generarNuevaContribucionMejoras(contribucionMejoras, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF333.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF333.presentarMensaje());
            actualizarListadoContribucionMejoras();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarContribucionMejoras() {
        if (!ComunUtil.esNumeroPositivo(this.contribucionMejoras.getCodObras())) {
            insertarContribucionMejoras();
        } else {
            try {
                actualizarValorCEM(this.contribucionMejoras.getCodObras(), this.contribucionMejoras.getValAcobrar());
                contribucionMejorasServicio.actualizarContribucionMejoras(contribucionMejoras, sesionBean.getSesion());
                actualizarListadoContribucionMejoras();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF334.presentarMensaje(), sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF334.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_DESCRIPCION);
    }

    public void eliminarContribucionMejoras(Integer idContribucionMejoras) {
        try {
            this.seleccionarContribucionMejorasPorCodigo(idContribucionMejoras);
            if (!ComunUtil.esNulo(contribucionMejoras)) {
                contribucionMejorasServicio.eliminarContribucionMejoras(contribucionMejoras, sesionBean.getSesion());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF335.presentarMensaje());
                actualizarListadoContribucionMejoras();

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

    public void seleccionarContribucionMejoras(Integer idContribucionMejoras) {
        try {
            this.seleccionarContribucionMejorasPorCodigo(idContribucionMejoras);
            actualizarListadoObrasDetalle(idContribucionMejoras);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(EnumNewviExcepciones.ERR000.presentarMensajeCodigo());
        }
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_EDITAR_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_EDITAR_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_EDITAR_DESCRIPCION);
    }

    private void seleccionarContribucionMejorasPorCodigo(Integer idContribucionMejoras) throws NewviExcepcion {
        this.contribucionMejoras = contribucionMejorasServicio.seleccionarContribucionMejoras(idContribucionMejoras);
    }

    private void seleccionarDetalleObraPorCodigo(Integer codObrasdetalle) throws NewviExcepcion {
        this.obrasDetalle = contribucionMejorasServicio.seleccionarObrasDetalle(codObrasdetalle);
    }

    public void cancelarEdicion() {
        WebUtils.obtenerContextoPeticion().reset("formularioContribucionMejoras:opDetalleContribucionMejoras");
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_DESCRIPCION);
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

    public void abrirDialogPredios(int codObras) throws NewviExcepcion {
        contribucionMejoras = contribucionMejorasServicio.seleccionarContribucionMejoras(codObras);
        actualizarListadoPrediosParaDialog((List<ObrasDetalle>) contribucionMejoras.getListaBeneficiarios());
        WebUtils.obtenerContextoPeticion().execute("PF('dlgPredios').show()");
    }

    public ObrasDetalle crearObjetoObraDetalle(Predios predioObra) {
        ObrasDetalle nuevaObraDetalle = new ObrasDetalle();
        nuevaObraDetalle.setCodObras(contribucionMejoras);
        nuevaObraDetalle.setCodCatastral(predioObra);
        //nuevaObraDetalle.setCodPredio(predio.getCodPredio());
        nuevaObraDetalle.setNomCodigocatastral(predioObra);
        nuevaObraDetalle.setObrValor(BigDecimal.ZERO);
        nuevaObraDetalle.setValAreafrente(!ComunUtil.esNulo(predioObra.getValAreaFrente()) ? predioObra.getValAreaFrente() : BigDecimal.ZERO);
        nuevaObraDetalle.setValPredio(!ComunUtil.esNulo(predioObra.getValPredio()) ? predioObra.getValPredio() : BigDecimal.ZERO);
        //nuevaObraDetalle.setValPredio(predioObra.getValPredio());
        nuevaObraDetalle.setObdEstado(EnumEstadoRegistro.A);
        return nuevaObraDetalle;
    }

    public void onRowSelect(SelectEvent event) throws NewviExcepcion {
        Predios predioEvento = (Predios) event.getObject();
        generarNuevoDetalleObra(crearObjetoObraDetalle(predioEvento));
        contribucionMejoras = contribucionMejorasServicio.seleccionarContribucionMejoras(contribucionMejoras.getCodObras());
        actualizarListadoPrediosParaDialog((List<ObrasDetalle>) contribucionMejoras.getListaBeneficiarios());
    }

    public BigDecimal realizarOperacionCEM(BigDecimal cem, BigDecimal valorACobrar, String operacion) {
        if (operacion.equals("sumar")) {

            cem = cem.add(!ComunUtil.esNulo(valorACobrar) ? valorACobrar : BigDecimal.ZERO);
        } else if (operacion.equals("restar")) {
            cem = cem.subtract(!ComunUtil.esNulo(valorACobrar) ? valorACobrar : BigDecimal.ZERO);
        }
        return cem;
    }

    public void registrarCEMPredios(ObrasDetalle obraDetalle, String operacion) {
        Predios predioActual = obraDetalle.getCodCatastral();
        for (Predios predioBuscado : listaPredios) {
            if (predioBuscado.equals(predioActual)) {
                BigDecimal cem = predioActual.getValCem();
                if (ComunUtil.esNulo(cem)) {
                    cem = BigDecimal.ZERO;
                }
                predioActual.setValCem(realizarOperacionCEM(cem, obraDetalle.getCodObras().getValAcobrar(), operacion));
                try {
                    catastroServicio.actualizarPredio(predioActual, sesionBean.getSesion());
                } catch (NewviExcepcion ex) {
                    LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesionBean.getSesion());
                    MensajesFaces.mensajeError(ex.getMessage());
                }
                break;
            }
        }
    }

    public void generarNuevoDetalleObra(ObrasDetalle obraDetalle) {
        try {
            contribucionMejorasServicio.generarNuevaObrasDetalle(obraDetalle, sesionBean.getSesion());
            actualizarListadoPrediosParaDialog((List<ObrasDetalle>) contribucionMejoras.getListaBeneficiarios());
            registrarCEMPredios(obraDetalle, "sumar");
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF368.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF368.presentarMensaje());
            actualizarListadoContribucionMejoras();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void onRowUnselect(UnselectEvent event) {
        //Predios dd = (Predios) event.getObject();
    }

    public void eliminarPredioObra(Integer codObrasdetalle) {
        if (eliminarDetalleObra(codObrasdetalle)) {
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF369.presentarMensaje());
        }
    }

    public Boolean eliminarDetalleObra(Integer codObrasdetalle) {
        Boolean retorno = false;
        try {
            this.seleccionarDetalleObraPorCodigo(codObrasdetalle);
            if (!ComunUtil.esNulo(obrasDetalle)) {
                contribucionMejorasServicio.eliminarObrasDetalle(obrasDetalle, sesionBean.getSesion());
                registrarCEMPredios(obrasDetalle, "restar");
                //listaPrediosActualizados.add(obrasDetalle.getCodCatastral());
                actualizarListadoObrasDetalle(obrasDetalle.getCodObras().getCodObras());
                retorno = true;
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
        return retorno;
    }

    public void agregarTodosPrediosObra() {
        for (Predios nuevoPredio : listaPrediosActualizados) {
            generarNuevoDetalleObra(crearObjetoObraDetalle(nuevoPredio));
        }
        WebUtils.obtenerContextoPeticion().execute("PF('dlgPredios').close()");
    }

    public void eliminarTodosPredioObra() {
        List<ObrasDetalle> listaDetalleObraActual = (List<ObrasDetalle>) contribucionMejoras.getListaBeneficiarios();
        for (ObrasDetalle obrasDetalleActual : listaDetalleObraActual) {
            eliminarDetalleObra(obrasDetalleActual.getCodObrasdetalle());
        }

        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF370.presentarMensaje());
    }

    private void actualizarValorCEM(Integer codObras, BigDecimal valActual) throws NewviExcepcion {
        List<ObrasDetalle> listaDetallesObraAntiguo = (List<ObrasDetalle>) contribucionMejorasServicio.seleccionarContribucionMejoras(codObras).getListaBeneficiarios();

        for (ObrasDetalle detallesAntiguos : listaDetallesObraAntiguo) {
            if (detallesAntiguos.getObdEstado().equals(EnumEstadoRegistro.A)) {
                detallesAntiguos.getCodCatastral().setValCem(realizarOperacionCEM(detallesAntiguos.getCodCatastral().getValCem(), detallesAntiguos.getCodObras().getValAcobrar(), "restar"));
                detallesAntiguos.getCodCatastral().setValCem(realizarOperacionCEM(detallesAntiguos.getCodCatastral().getValCem(), valActual, "sumar"));
                catastroServicio.actualizarPredio(detallesAntiguos.getCodCatastral(), sesionBean.getSesion());
            }
        }
    }

    private List<ObrasDetalle> incluirValorCobradoObras(BigDecimal valorACobrar, List<ObrasDetalle> beneficiarios) {
        if (!ComunUtil.esNulo(beneficiarios)) {
            for (ObrasDetalle beneficiario : beneficiarios) {
                beneficiario.setObrValor(valorACobrar);
                actualizarValorACobrarBeneficiarios(beneficiario);
            }
        }
        return beneficiarios;
    }

    private BigDecimal obtenerValorTotalObra(BigDecimal valorObra, BigDecimal valorPorcentaje, BigDecimal aniosDepreciacion) {
        try {
            return (valorObra.divide(aniosDepreciacion, MathContext.DECIMAL32).multiply(valorPorcentaje.divide(new BigDecimal(100), MathContext.DECIMAL32)));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal obtenerValorACobrarObra(BigDecimal valorTotalObra, BigDecimal numeroBeneficiarios) {
        try {
            return (valorTotalObra).divide(numeroBeneficiarios, MathContext.DECIMAL32);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal obtenerValorACobrarObraPorOrdenanza(BigDecimal valorTotalObra, BigDecimal valorFrente, BigDecimal totalValorFrente) {
        try {
            return ((valorTotalObra).multiply(valorFrente)).divide(totalValorFrente, MathContext.DECIMAL32);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private void actualizarValorACobrar(ContribucionMejoras obraActual, BigDecimal valorACobrar) {
        obraActual.setValAcobrar(valorACobrar);
        try {
            contribucionMejorasServicio.actualizarContribucionMejoras(obraActual, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF504.presentarMensaje(), sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    private void actualizarValorACobrarBeneficiarios(ObrasDetalle beneficiario) {
        try {
            contribucionMejorasServicio.actualizarObrasDetalle(beneficiario, sesionBean.getSesion());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF505.presentarMensaje(), sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    private void actualizarValorACobrarObraTotal(ContribucionMejoras obraActual, BigDecimal valorObra, BigDecimal valorPorcentaje, BigDecimal aniosDepreciacion) {
        BigDecimal valorACobrar;
        Integer numeroBeneficiarios;
        numeroBeneficiarios = catastroServicio.obtenerNumeroPredios();
        valorACobrar = obtenerValorACobrarObra(obtenerValorTotalObra(valorObra, valorPorcentaje, aniosDepreciacion), new BigDecimal(numeroBeneficiarios));
        actualizarValorACobrar(obraActual, valorACobrar);
    }

    private void actualizarValorACobrarObraParcial(ContribucionMejoras obraActual, BigDecimal valorObra, BigDecimal valorPorcentaje, BigDecimal aniosDepreciacion) {
        BigDecimal valorACobrar;
        Integer numeroBeneficiarios;
        numeroBeneficiarios = !ComunUtil.esNulo(obraActual.getListaBeneficiarios()) ? obraActual.getListaBeneficiarios().size() : 0;
        valorACobrar = obtenerValorACobrarObra(obtenerValorTotalObra(valorObra, valorPorcentaje, aniosDepreciacion), new BigDecimal(numeroBeneficiarios));
        obraActual.setListaBeneficiarios(incluirValorCobradoObras(valorACobrar, obraActual.getListaBeneficiarios()));
        actualizarValorACobrar(obraActual, valorACobrar);
    }

    private BigDecimal sumatoriaValorFrente(List<ObrasDetalle> ListaBeneficiarios) {
        BigDecimal valorTotalFrente = BigDecimal.ZERO;
        if (!ComunUtil.esNulo(ListaBeneficiarios)) {
            for (ObrasDetalle beneficiario : ListaBeneficiarios) {
                valorTotalFrente = valorTotalFrente.add(!ComunUtil.esNulo(beneficiario.getValAreafrente()) ? beneficiario.getValAreafrente() : BigDecimal.ZERO);
            }
        }
        return valorTotalFrente;
    }

    private BigDecimal sumatoriaValorAvaluo(List<ObrasDetalle> ListaBeneficiarios) {
        BigDecimal valorTotalAvaluo = BigDecimal.ZERO;
        if (!ComunUtil.esNulo(ListaBeneficiarios)) {
            for (ObrasDetalle beneficiario : ListaBeneficiarios) {
                valorTotalAvaluo = valorTotalAvaluo.add(!ComunUtil.esNulo(beneficiario.getValPredio()) ? beneficiario.getValPredio() : BigDecimal.ZERO);
            }
        }
        return valorTotalAvaluo;
    }

    private BigDecimal obtenerValorACobrarSinRecargos(BigDecimal valorTotalObra, BigDecimal valorPorcentajeFrente) {
        try {
            return valorTotalObra.multiply(valorPorcentajeFrente.divide(new BigDecimal(100), MathContext.DECIMAL32));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private List<ObrasDetalle> incluirValorCobradoObrasPorOrdenanza(List<ObrasDetalle> listaBeneficiarios, BigDecimal valorTotalElemento, BigDecimal valorTotalObra, BigDecimal valorPorcentaje, Boolean hayValorCobrado) {
        BigDecimal valorACobrar;
        for (ObrasDetalle beneficiario : listaBeneficiarios) {
            valorACobrar = obtenerValorACobrarObraPorOrdenanza(obtenerValorACobrarSinRecargos(valorTotalObra, valorPorcentaje), beneficiario.getValAreafrente(), valorTotalElemento);

            beneficiario.setObrValor(hayValorCobrado ? beneficiario.getObrValor().add(valorACobrar) : valorACobrar);

            actualizarValorACobrarBeneficiarios(beneficiario);
        }
        return listaBeneficiarios;
    }

    private ContribucionMejoras setearListaBeneficiarios(ContribucionMejoras obraActual, BigDecimal valorTotalObra) {
        BigDecimal valorTotalFrente;
        BigDecimal valorTotalAvaluo;
        BigDecimal valorPorcentajeFrente;
        BigDecimal valorPorcentajeAvaluo;
        Boolean hayValorCobrado = Boolean.FALSE;

        valorPorcentajeAvaluo = !ComunUtil.esNulo(obraActual.getValPorcentajeavaluo()) ? obraActual.getValPorcentajeavaluo() : BigDecimal.ZERO;
        valorPorcentajeFrente = !ComunUtil.esNulo(obraActual.getValPorcentajefrentistas()) ? obraActual.getValPorcentajefrentistas() : BigDecimal.ZERO;

        if (valorPorcentajeFrente.compareTo(BigDecimal.ZERO) == 1) {
            valorTotalFrente = !ComunUtil.esNulo(obraActual.getListaBeneficiarios()) ? sumatoriaValorFrente(obraActual.getListaBeneficiarios()) : BigDecimal.ZERO;
            obraActual.setListaBeneficiarios(incluirValorCobradoObrasPorOrdenanza(obraActual.getListaBeneficiarios(), valorTotalFrente, valorTotalObra, valorPorcentajeFrente, hayValorCobrado));
            hayValorCobrado = Boolean.TRUE;
        }
        if (valorPorcentajeAvaluo.compareTo(BigDecimal.ZERO) == 1) {
            valorTotalAvaluo = !ComunUtil.esNulo(obraActual.getListaBeneficiarios()) ? sumatoriaValorAvaluo(obraActual.getListaBeneficiarios()) : BigDecimal.ZERO;
            obraActual.setListaBeneficiarios(incluirValorCobradoObrasPorOrdenanza(obraActual.getListaBeneficiarios(), valorTotalAvaluo, valorTotalObra, valorPorcentajeAvaluo, hayValorCobrado));
        }

        return obraActual;
    }

    private void actulizarValorACobrarObraPorOrdenanza(ContribucionMejoras obraActual, EnumAplicacion tipoAplicacionObra, BigDecimal valorObra, BigDecimal valorPorcentaje, BigDecimal aniosDepreciacion) {
        BigDecimal valorTotalObra;

        if (tipoAplicacionObra.equals(EnumAplicacion.Parcial)) {
            valorTotalObra = obtenerValorTotalObra(valorObra, valorPorcentaje, aniosDepreciacion);
            actualizarValorACobrar(setearListaBeneficiarios(obraActual, valorTotalObra), BigDecimal.ZERO);
        }

    }

    public void actualizarBeficiarios(ObrasDetalle Beneficiario) {
        try {
            contribucionMejorasServicio.actualizarObrasDetalle(Beneficiario, sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarObraActual(ContribucionMejoras ObraActual) {
        try {
            contribucionMejorasServicio.actualizarContribucionMejoras(ObraActual, sesionBean.getSesion());
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void obtenerValorCEM(ContribucionMejoras obraActual) {
        Integer aniosDepreciacion;
        BigDecimal valorObra;
        BigDecimal valorPorcentaje;

        EnumAplicacion tipoAplicacionObra;

        tipoAplicacionObra = !ComunUtil.esNulo(obraActual.getStsAplicacionforma()) ? obraActual.getStsAplicacionforma() : EnumAplicacion.Total;
        valorObra = !ComunUtil.esNulo(obraActual.getValValor()) ? obraActual.getValValor() : BigDecimal.ZERO;
        aniosDepreciacion = !ComunUtil.esNulo(obraActual.getValAniodeprecia()) ? obraActual.getValAniodeprecia() : 0;
        valorPorcentaje = !ComunUtil.esNulo(obraActual.getValPorcentaje()) ? obraActual.getValPorcentaje() : BigDecimal.ZERO;

        if (!obraActual.getStsPorcentajeaplica()) {
            if (tipoAplicacionObra.equals(EnumAplicacion.Parcial)) {
                actualizarValorACobrarObraParcial(obraActual, valorObra, valorPorcentaje, new BigDecimal(aniosDepreciacion));
            } else {
                actualizarValorACobrarObraTotal(obraActual, valorObra, valorPorcentaje, new BigDecimal(aniosDepreciacion));
            }
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF504.presentarMensaje());

        } else {
            //actualizarObraActual(obraActual);
            actulizarValorACobrarObraPorOrdenanza(obraActual, tipoAplicacionObra, valorObra, valorPorcentaje, new BigDecimal(aniosDepreciacion));
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF505.presentarMensaje());
        }
    }

    public void obtenerValorCEMGeneral() {
        for (ContribucionMejoras obraActual : this.listaContribucionMejoras) {
            obtenerValorCEM(obraActual);
        }
    }

    public Boolean hayBeneficiarios(List<ObrasDetalle> beneficiarios) {
        return (!ComunUtil.esNulo(beneficiarios) && beneficiarios.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean obtenerEstadoAplicacionCEM(String aplicacionPorcentajesCEM) {
        return EnumAplicacionCEM.obtenerEstadoAplicacionCEM(aplicacionPorcentajesCEM);
    }

}
