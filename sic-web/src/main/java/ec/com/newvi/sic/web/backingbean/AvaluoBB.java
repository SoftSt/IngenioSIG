/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.componente.reporte.ReporteGenerador;
import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.dto.TablaCatastralDto;
import ec.com.newvi.sic.enums.EnumCaracteristicasAvaluo;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.utils.WebUtils;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Andrés
 */
@ManagedBean
@ViewScoped
public class AvaluoBB extends AdminFichaCatastralBB {

    private final static Integer PROCESO_COMPLETO = 100;

    private EnumPantallaMantenimiento pantallaActual;
    private List<Avaluo> listaAvaluos;
    private List<Avaluo> listaAvaluosFiltrados;
    private List<Avaluo> listaAvaluosProcesados;
    private List<Avaluo> listaAvaluosProcesadosFiltrados;
    private List<FechaAvaluo> listaFechaAvaluos;
    private Integer progreso;
    private FechaAvaluo fechaAvaluoActual;
    private Boolean esProcesoIniciado;
    private Boolean esProcesoCancelado;

    private BigDecimal totalPorCobrarCalculo;
    private BigDecimal totalPorCobrarConsulta;

    public Boolean getEsProcesoCancelado() {
        return esProcesoCancelado;
    }

    public Boolean getEsProcesoIniciado() {
        return esProcesoIniciado;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public List<FechaAvaluo> getListaFechaAvaluos() {
        return listaFechaAvaluos;
    }

    public List<Avaluo> getListaAvaluos() {
        return listaAvaluos;
    }

    public List<Avaluo> getListaAvaluosFiltrados() {
        return listaAvaluosFiltrados;
    }

    public FechaAvaluo getFechaAvaluoActual() {
        return fechaAvaluoActual;
    }

    public void setListaAvaluosFiltrados(List<Avaluo> listaAvaluosFiltrados) {
        this.listaAvaluosFiltrados = listaAvaluosFiltrados;
    }

    public List<Avaluo> getListaAvaluosProcesados() {
        return listaAvaluosProcesados;
    }

    public List<Avaluo> getListaAvaluosProcesadosFiltrados() {
        return listaAvaluosProcesadosFiltrados;
    }

    public void setListaAvaluosProcesadosFiltrados(List<Avaluo> listaAvaluosProcesadosFiltrados) {
        this.listaAvaluosProcesadosFiltrados = listaAvaluosProcesadosFiltrados;
    }

    public BigDecimal getTotalPorCobrarCalculo() {
        return totalPorCobrarCalculo;
    }

    public BigDecimal getTotalPorCobrarConsulta() {
        return totalPorCobrarConsulta;
    }

    @PostConstruct
    public void init() {

        this.esProcesoIniciado = false;
        this.esProcesoCancelado = false;
        this.progreso = 0;
        fechaAvaluoActual = new FechaAvaluo();
        listaAvaluos = new ArrayList<>();
        listaAvaluosProcesados = new ArrayList<>();
        listaFechaAvaluos = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CALCULAR_AVALUO_LISTA_TITULO,
                EnumEtiquetas.CALCULAR_AVALUO_LISTA_ICONO,
                EnumEtiquetas.CALCULAR_AVALUO_LISTA_DESCRIPCION);
        actualizarListadoFechaAvaluos();

    }

    public FechaAvaluo generarFechaAvaluo() throws NewviExcepcion {
        FechaAvaluo fechaAvaluo = new FechaAvaluo();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = Calendar.getInstance().getTime();

        fechaAvaluo.setFecavFechaavaluo(fecha);
        fechaAvaluo.setFecavEstado(EnumEstadoRegistro.A);
        fechaAvaluo.setFechaDescripcion(formato.format(fecha));

        return catastroServicio.generarNuevaFechaAvaluo(fechaAvaluo, sesionBean.getSesion());
    }

    public void reiniciarVariables() {
        this.listaAvaluos = new ArrayList<>();
        this.listaAvaluosProcesados = new ArrayList<>();
        this.totalPorCobrarCalculo = BigDecimal.ZERO;
        this.esProcesoIniciado = false;
        WebUtils.obtenerContextoPeticion().execute("PF('dlgAvaluo').hide()");
        this.progreso = 0;
    }

    public void cancelarAvaluo() {
        this.esProcesoCancelado = true;
    }

    public void iniciarProcesoCalculo() {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgAvaluo').show()");
        WebUtils.obtenerContextoPeticion().execute("rc()");
        WebUtils.obtenerContextoPeticion().execute("PF('calcularAvaluo').disable()");
        WebUtils.obtenerContextoPeticion().execute("PF('pbAjax').start()");
    }

    public void generarAvaluo() throws NewviExcepcion {

        this.esProcesoIniciado = true;

        String formatoMonedaSistema = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.FORMATO_MONEDAS, sesionBean.getSesion()).getValor();

        List<Predios> listaPredios = catastroServicio.consultarPredios();
        List<Dominios> dominios = parametrosServicio.consultarDominios();
        List<AvaluoDto> listaAvaluosCalculados = new ArrayList<>();
        Integer numeroPrediosProcesados = 0;
        Integer numeroPrediosTotal = listaPredios.size();

        for (Predios predioACalcular : listaPredios) {
            //Predios predioACalcular= listaPredios.get(612);
            List<AvaluoDto> calculoAvaluo = catastroServicio.obtenerAvaluoPredio(predioACalcular, dominios, formatoMonedaSistema, sesionBean.getSesion());
            listaAvaluosCalculados.add(generarNuevoNodoPredio(predioACalcular, calculoAvaluo));
            registrarLogPredioProcesadoActual(predioACalcular);
            numeroPrediosProcesados++;
            evaluarProceso(numeroPrediosProcesados, numeroPrediosTotal);
            if (esProcesoCancelado) {
                break;
            }
        }
        this.generarListadoAvaluos(listaAvaluosCalculados);
        this.totalPorCobrarCalculo = obtenerTotales(listaAvaluos);
        this.progreso = PROCESO_COMPLETO;
        //catastroServicio.registrarArbol(calculoAvaluo, predioACalcular, sesionBean.getSesion());
    }

    private void registrarLogPredioProcesadoActual(Predios predioACalcular) {
        LoggerNewvi.getLogNewvi(this.getClass()).info(predioACalcular.getCodCatastral(), sesionBean.getSesion());
    }

    private void generarListadoAvaluos(List<AvaluoDto> listaAvaluosCalculados) throws NewviExcepcion {
        Predios predioActual;
        FichaCatastralDto fichaActual;
        this.listaAvaluos = new ArrayList<>();
        String formatoMonedaSistema = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.FORMATO_MONEDAS, sesionBean.getSesion()).getValor();
        for (AvaluoDto avaluoCalculado : listaAvaluosCalculados) {
            predioActual = catastroServicio.seleccionarPredio(new Integer(avaluoCalculado.getValor()));
            fichaActual = new FichaCatastralDto(predioActual);
            this.listaAvaluos.add(this.generarElementoAvaluo(fichaActual, avaluoCalculado.getHijos(), formatoMonedaSistema));
        }
    }

    private AvaluoDto generarNuevoNodoPredio(Predios predio, List<AvaluoDto> hijos) {
        return new AvaluoDto("Predio: ".concat(predio.getNomCodigocatastral()), predio.getCodCatastral().toString(), predio.getNomCodigocatastral(), hijos);
    }

    private void evaluarProceso(Integer numeroPrediosProcesados, Integer numeroPrediosTotal) {
        if (this.progreso <= PROCESO_COMPLETO - 1) {
            
            Integer  porcentajeAvance = (numeroPrediosProcesados * 100)/numeroPrediosTotal;
            progreso = porcentajeAvance > 0 ? porcentajeAvance : progreso;
            //progreso = (numeroPrediosProcesados % (numeroPrediosTotal / 100)) == 0 ? progreso + 1 : progreso;
        } else {
            this.progreso = PROCESO_COMPLETO - 1;
        }
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

    public void actualizarListadoFechaAvaluos() {
        listaFechaAvaluos = catastroServicio.consultarFechaAvaluos();
    }

    public void actualizarListaAvaluosPorFecha(String fechaDescripcion) {
        try {
            listaAvaluosProcesados = catastroServicio.consultarListaAvaluosPorFecha(fechaDescripcion);
            this.totalPorCobrarConsulta = obtenerTotales(listaAvaluosProcesados);
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR001.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void finalizarProceso() {
        this.esProcesoIniciado = false;
        WebUtils.obtenerContextoPeticion().execute("PF('dlgAvaluo').hide()");
        this.progreso = 0;
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF501.presentarMensaje());
    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte, ReporteGenerador.FormatoReporte formatoReporte) {
        return generarReporteCatastro(tipoReporte, formatoReporte, obtenerListadoAvaluos(this.listaAvaluos), TablaCatastralDto.class);
    }

    public DefaultStreamedContent imprimirConsultado(EnumReporte tipoReporte, ReporteGenerador.FormatoReporte formatoReporte) {
        return generarReporteCatastro(tipoReporte, formatoReporte, obtenerListadoAvaluos(this.listaAvaluosProcesados), TablaCatastralDto.class);
    }

    public List<Field> filtrarFieldDecimales(Field[] metodosClase) {
        List<Field> metodosBuscado = new ArrayList<>();
        for (Field metodo : metodosClase) {
            if (metodo.getType().getName().contains("BigDecimal")) {
                metodosBuscado.add(metodo);
            }
        }
        return metodosBuscado;
    }

    public List<Method> obtenerSetters(Method[] listaMetodos) {
        List<Method> listaMetodosFiltrada = new ArrayList<>();
        for (Method metodo : listaMetodos) {
            if (metodo.getName().startsWith("set")) {
                listaMetodosFiltrada.add(metodo);
            }
        }
        return listaMetodosFiltrada;
    }

    public Method filtrarMetodos(Method[] listaMetodos, String nombreMetodo) {
        for (Method metodo : listaMetodos) {
            if (metodo.getName().contains(nombreMetodo)) {
                return metodo;
            }
        }
        return null;
    }

    public String renombrarMetodo(String metodo) {
        return "set".concat(metodo.substring(0, 1).toUpperCase().concat(metodo.substring(1, metodo.length())));
    }

    public Avaluo generarElementoAvaluo(FichaCatastralDto ficha, List<AvaluoDto> avaluoCalculado, String formatoMonedaSistema) throws NewviExcepcion {
        Avaluo avaluo = new Avaluo();
        obtenerDatosAvaluo(avaluo, ficha);
        Class claseAvaluo = avaluo.getClass();
        String nombreMetodoBuscado = "";
        //Field[] metodosClase = claseAvaluo.getDeclaredFields();
        List<Field> metodosClase = filtrarFieldDecimales(claseAvaluo.getDeclaredFields());
        for (Field metodo : metodosClase) {
            nombreMetodoBuscado = metodo.getName();
            obtenerValorAtributo(avaluoCalculado, avaluo, nombreMetodoBuscado, formatoMonedaSistema);
        }
        return avaluo;
    }

    private void obtenerDatosAvaluo(Avaluo avaluo, FichaCatastralDto ficha) throws NewviExcepcion {
        //FechaAvaluo fecavId = generarFechaAvaluo();
        avaluo.setCodCatastral(ficha.getPredio());
        avaluo.setNomCodigocatastral(ficha.getPredio().getNomCodigocatastral());
        avaluo.setTxtDireccion(ficha.getPredio().getTxtDireccion());
        avaluo.setStsBarrio(ficha.getPredio().getStsBarrio());
        avaluo.setCodCedularuc(ficha.getContribuyentePropiedad().getCodCedularuc());
        avaluo.setNomnomape(ficha.getContribuyentePropiedad().getNomApellidos().trim() + " " + ficha.getContribuyentePropiedad().getNomNombres().trim());
        //avaluo.setFecavId(fecavId);
        avaluo.setAvalEstado(EnumEstadoRegistro.A);
    }

    private void obtenerValorAtributo(List<AvaluoDto> avaluoCalculado, Avaluo avaluo, String nombreMetodoBuscado, String formatoMonedaSistema) throws NewviExcepcion {
        Class claseAvaluo = avaluo.getClass();
        for (EnumCaracteristicasAvaluo caracteristica : EnumCaracteristicasAvaluo.obtenerCaracteristicasAvaluoConCampo()) {
            AvaluoDto elemento = catastroServicio.obtenerElementoAvaluoPorDescripcion(avaluoCalculado, caracteristica.getTitulo());
            if (!ComunUtil.esCadenaVacia(caracteristica.getCampo())
                    && caracteristica.getCampo().equals(nombreMetodoBuscado)
                    && !ComunUtil.esNulo(elemento)) {
                BigDecimal valorAtributo = ComunUtil.obtenerNumeroDecimalDesdeTexto(elemento.getValor(), formatoMonedaSistema);
                Method metodoEjecutable = filtrarMetodos(claseAvaluo.getMethods(), renombrarMetodo(nombreMetodoBuscado));
                try {
                    metodoEjecutable.invoke(avaluo, valorAtributo);
                    break;
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Map<String, String> variables = new HashMap<>();
                    variables.put("atributo", caracteristica.getCampo());
                    throw new NewviExcepcion(EnumNewviExcepciones.ERR502, variables, ex);
                }
            } else if (caracteristica.getCampo().contains("tieneHijos")
                    && !ComunUtil.esNulo(elemento)
                    && !ComunUtil.esNulo(elemento.getHijos())
                    && !elemento.getHijos().isEmpty()) {
                obtenerValorAtributo(elemento.getHijos(), avaluo, nombreMetodoBuscado, formatoMonedaSistema);
            }
        }
    }

    private BigDecimal obtenerTotales(List<Avaluo> listadoAvaluo) {
        BigDecimal totalPorCobrar = BigDecimal.ZERO;
        for (Avaluo avaluo : listadoAvaluo) {
            totalPorCobrar = totalPorCobrar.add(avaluo.getValImppredial());
        }
        return totalPorCobrar;
    }

    public void registrarAvaluo() throws NewviExcepcion {
        FechaAvaluo fechaAvaluoId = generarFechaAvaluo();
        for (Avaluo avaluoActual : this.listaAvaluos) {
            try {
                avaluoActual.setFecavId(fechaAvaluoId);
                catastroServicio.generarNuevoAvaluo(avaluoActual, sesionBean.getSesion());
            } catch (NewviExcepcion ex) {
                Map<String, String> variables = new HashMap<>();
                variables.put("avaluo", avaluoActual.getNomnomape());
                throw new NewviExcepcion(EnumNewviExcepciones.ERR362, variables, ex);
            }
        }
        actualizarListadoFechaAvaluos();
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF363.presentarMensaje(), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF363.presentarMensaje());
    }

    public Boolean hayDatosCalculados() {
        return !ComunUtil.esNulo(this.listaAvaluos) && !this.listaAvaluos.isEmpty();
    }

    public Boolean hayDatosConsultados() {
        return !this.listaAvaluosProcesados.isEmpty();
    }

    public void reiniciarCalculo() {
        this.listaAvaluos = new ArrayList<>();
        this.totalPorCobrarCalculo = BigDecimal.ZERO;
    }

    public void reiniciarConsulta() {
        this.listaAvaluosProcesados = new ArrayList<>();
        this.totalPorCobrarConsulta = BigDecimal.ZERO;
    }
}
