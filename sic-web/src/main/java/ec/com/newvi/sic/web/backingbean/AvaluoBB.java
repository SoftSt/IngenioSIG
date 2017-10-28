/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private EnumPantallaMantenimiento pantallaActual;
    private List<Avaluo> listaAvaluos;
    private List<Avaluo> listaAvaluosFiltrados;
    private List<FechaAvaluo> listaFechaAvaluos;
    private Integer progreso;
    private FechaAvaluo fechaAvaluoActual;
    private String fechaActualPrueba;
    private Boolean esActivo;
    private Boolean esProcesoIniciado;
    private Boolean esProcesoCancelado;

    private BigDecimal totalPorCobrar;

    public Boolean getEsProcesoCancelado() {
        return esProcesoCancelado;
    }

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

    public Boolean getEsProcesoIniciado() {
        return esProcesoIniciado;
    }

    public void setEsProcesoIniciado(Boolean esProcesoIniciado) {
        this.esProcesoIniciado = esProcesoIniciado;
    }

    public String getFechaActualPrueba() {
        return fechaActualPrueba;
    }

    public void setFechaActualPrueba(String fechaActualPrueba) {
        this.fechaActualPrueba = fechaActualPrueba;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }

    public List<FechaAvaluo> getListaFechaAvaluos() {
        return listaFechaAvaluos;
    }

    public void setListaFechaAvaluos(List<FechaAvaluo> listaFechaAvaluos) {
        this.listaFechaAvaluos = listaFechaAvaluos;
    }

    public List<Avaluo> getListaAvaluos() {
        return listaAvaluos;
    }

    public void setListaAvaluos(List<Avaluo> listaAvaluos) {
        this.listaAvaluos = listaAvaluos;
    }

    public FechaAvaluo getFechaAvaluoActual() {
        return fechaAvaluoActual;
    }

    public void setFechaAvaluoActual(FechaAvaluo fechaAvaluoActual) {
        this.fechaAvaluoActual = fechaAvaluoActual;
    }

    public List<Avaluo> getListaAvaluosFiltrados() {
        return listaAvaluosFiltrados;
    }

    public void setListaAvaluosFiltrados(List<Avaluo> listaAvaluosFiltrados) {
        this.listaAvaluosFiltrados = listaAvaluosFiltrados;
    }

    public BigDecimal getTotalPorCobrar() {
        return totalPorCobrar;
    }

    @PostConstruct
    public void init() {
        this.esActivo = false;
        this.esProcesoIniciado = false;
        this.esProcesoCancelado = false;
        this.progreso = 0;
        fechaAvaluoActual = new FechaAvaluo();
        listaAvaluos = new ArrayList<>();
        listaFechaAvaluos = new ArrayList<>();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.SIMULACION_LISTA_TITULO,
                EnumEtiquetas.SIMULACION_LISTA_ICONO,
                EnumEtiquetas.SIMULACION_LISTA_DESCRIPCION);
        actualizarListadoFechaAvaluos();
        //actualizarListadoAvaluos();

    }

    public FechaAvaluo generarFechaAvaluo() throws NewviExcepcion {
        FechaAvaluo fechaAvaluo = new FechaAvaluo();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = Calendar.getInstance().getTime();

        fechaAvaluo.setFecavFechaavaluo(fecha);
        fechaAvaluo.setFecavEstado(EnumEstadoRegistro.A);
        fechaAvaluo.setFechaDescripcion(formato.format(fecha));

        return catastroServicio.generarNuevaFechaAvaluo(fechaAvaluo, sesionBean.getSesion());
        //return fechaAvaluo;
    }

    public void abrirModalEspera() throws NewviExcepcion {
        //WebUtils.obtenerContextoPeticion().execute("PF('calcularSimulacion').disable()");
        //generarSimulacion();
        generarSimulacionFinal();
    }

    public void cancelarAvaluo() {
        this.esProcesoCancelado = true;
    }

    public void generarSimulacionFinal() throws NewviExcepcion {

        this.esProcesoIniciado = true;

        String formatoMonedaSistema = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.FORMATO_MONEDAS, sesionBean.getSesion()).getValor();

        List<Predios> listaPredios = catastroServicio.consultarPredios();
        List<Dominios> dominios = parametrosServicio.consultarDominios();
        List<AvaluoDto> listaAvaluosCalculados = new ArrayList<>();
        Integer numeroPrediosProcesados = 0;
        Integer numeroPrediosTotal = listaPredios.size();

        for (Predios predioACalcular : listaPredios) {
            List<AvaluoDto> calculoAvaluo = catastroServicio.obtenerAvaluoPredio(predioACalcular, dominios, formatoMonedaSistema, sesionBean.getSesion());
            listaAvaluosCalculados.add(generarNuevoNodoPredio(predioACalcular, calculoAvaluo));
            registrarLogPredioProcesadoActual(predioACalcular);
            evaluarProceso(numeroPrediosProcesados++, numeroPrediosTotal);
            if (esProcesoCancelado) {
                break;
            }
        }
        this.generarListadoAvaluos(listaAvaluosCalculados);
        obtenerTotales(listaAvaluos);
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
        if (this.progreso <= 100) {
            progreso = (numeroPrediosProcesados % (numeroPrediosTotal / 100)) == 0 ? progreso + 1 : progreso;
        } else {
            this.progreso = 100;
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

    public void actualizarListadoAvaluos() {
        listaAvaluos = catastroServicio.consultarListaAvaluosActuales();
    }

    public void actualizarListaAvaluosPorFecha(String fechaDescripcion) {
        //DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //listaAvaluos = catastroServicio.consultarListaAvaluosPorFecha(formato.parse(fechaDescripcion));
            listaAvaluos = catastroServicio.consultarListaAvaluosPorFecha(fechaDescripcion);
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR001.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }

    }

    public void finalizarProceso() {
        //this.progreso = 100;
        this.esProcesoIniciado = false;
        WebUtils.obtenerContextoPeticion().execute("PF('dlgSimulacion').hide()");
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF501.presentarMensaje());
    }

    public DefaultStreamedContent imprimir(EnumReporte tipoReporte) {
        return generarReporteCatastro(tipoReporte);
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
        EnumCaracteristicasAvaluo[] caracteristicasAvaluo = EnumCaracteristicasAvaluo.values();
        for (EnumCaracteristicasAvaluo caracteristica : caracteristicasAvaluo) {
            if (!ComunUtil.esCadenaVacia(caracteristica.getCampo()) && caracteristica.getCampo().equals(nombreMetodoBuscado)) {
                BigDecimal valorAtributo = catastroServicio.obtenerValorElementoAvaluoPorDescripcion(avaluoCalculado, caracteristica.getTitulo(), formatoMonedaSistema);
                Method metodoEjecutable = filtrarMetodos(claseAvaluo.getMethods(), renombrarMetodo(nombreMetodoBuscado));
                try {
                    metodoEjecutable.invoke(avaluo, valorAtributo);
                    break;
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Map<String, String> variables = new HashMap<>();
                    variables.put("atributo", caracteristica.getCampo());
                    throw new NewviExcepcion(EnumNewviExcepciones.ERR502, variables, ex);
                }
            } else if (caracteristica.equals(EnumCaracteristicasAvaluo.IMPUESTOS_OTROS_VALORES)) {
                AvaluoDto elemento = catastroServicio.obtenerElementoAvaluoPorDescripcion(avaluoCalculado, caracteristica.getTitulo());
                if (!ComunUtil.esNulo(elemento)
                        && !ComunUtil.esNulo(elemento.getHijos())
                        && !elemento.getHijos().isEmpty()) {
                    obtenerValorAtributo(elemento.getHijos(), avaluo, nombreMetodoBuscado, formatoMonedaSistema);
                }
            }
        }
    }

    public List<TablaCatastralDto> obtenerListadoAvaluos(List<Avaluo> listaAvaluos) {
        List<TablaCatastralDto> datosImpresion = new ArrayList<>();
        TablaCatastralDto datosAvaluo;
        for (Avaluo avaluo : listaAvaluos) {
            datosAvaluo = new TablaCatastralDto();
            datosAvaluo.setCodigoCatastral(avaluo.getCodCatastral().getCodCatastral().toString());
            datosAvaluo.setNombreCodigoCatastral(avaluo.getNomCodigocatastral());
            datosAvaluo.setPropietario(avaluo.getNomnomape());
            datosAvaluo.setCiRuc(avaluo.getCodCedularuc());
            datosAvaluo.setBarrio(avaluo.getStsBarrio());
            datosAvaluo.setDireccion(avaluo.getTxtDireccion());
            datosAvaluo.setAvaluoTerreno(avaluo.getValTerreno());
            datosAvaluo.setAreaTerreno(avaluo.getValTerreno());
            datosAvaluo.setAreaEdificacion(avaluo.getValAreaconstruccion());
            datosAvaluo.setAvaluoEdificacion(avaluo.getValEdifica());
            datosAvaluo.setAvaluoPredio(avaluo.getValPredio());
            datosAvaluo.setAreaPredio(avaluo.getValAreapredio());
            datosAvaluo.setImpuestoPredial(avaluo.getValImpuesto());
            datosAvaluo.setContribucionEspecialMejoras(avaluo.getValCem());
            datosAvaluo.setTasaNoEdificacion(avaluo.getValNoEdificacion());
            datosAvaluo.setCostoEmision(avaluo.getValEmision());
            datosAvaluo.setTasaBomberos(avaluo.getValBomberos());
            datosAvaluo.setServiciosAmbientales(avaluo.getValAmbientales());
            datosAvaluo.setTotalAPagar(avaluo.getValImppredial());
            datosAvaluo.setObservaciones(avaluo.getCatCasosespeciales());
            datosImpresion.add(datosAvaluo);
        }
        return datosImpresion;
    }

    public List<Avaluo> generarListaAvaluo() {
        return catastroServicio.consultarListaAvaluosActuales();
    }

    private void obtenerTotales(List<Avaluo> listadoAvaluo) {
        this.totalPorCobrar = BigDecimal.ZERO;
        listadoAvaluo.forEach((avaluo) -> {
            this.totalPorCobrar = this.totalPorCobrar.add(avaluo.getValImppredial());
        });
    }

    public void registrarAvaluo() throws NewviExcepcion {
        FechaAvaluo fecavId = generarFechaAvaluo();
        for (Avaluo avaluoActual : this.listaAvaluos) {
            try {
                avaluoActual.setFecavId(fecavId);
                catastroServicio.generarNuevoAvaluo(avaluoActual, sesionBean.getSesion());
            } catch (NewviExcepcion ex) {
                Map<String, String> variables = new HashMap<>();
                variables.put("avaluo", avaluoActual.getNomnomape());
                throw new NewviExcepcion(EnumNewviExcepciones.ERR362, variables, ex);
            }
        }
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF363.presentarMensaje(), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF363.presentarMensaje());
    }

}
