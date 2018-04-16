/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.geo.modelo.GeoPredio;
import ec.com.newvi.sic.geo.servicios.GeoCatastroServicio;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.web.utils.WebUtils;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.MensajesFaces;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumPantallaMantenimiento;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author israelavila
 */
@ManagedBean
@ViewScoped
public class NuevoPredioBB extends AdminFichaCatastralBB {

    private EnumPantallaMantenimiento pantallaActual;
    private List<GeoPredio> listaPrediosHuerfanos;
    private List<GeoPredio> listaPrediosHuerfanosFiltrados;
    private List<GeoPredio> listaPrediosHuerfanosSeleccionados;
    private List<Predios> listaPrediosGenerados;
    private List<Predios> listaPrediosGeneradosFiltrados;
    private List<FichaCatastralDto> listaPrediosHuerfanosRegistrados;
    private List<FichaCatastralDto> listaPrediosHuerfanosRegistradosFiltrados;
    private List<Contribuyentes> listaContribuyentesFiltrado;
    private Predios predioActual;

    public Predios getPredioActual() {
        return predioActual;
    }

    public void setPredioActual(Predios predioActual) {
        this.predioActual = predioActual;
    }

    public List<Contribuyentes> getListaContribuyentesFiltrado() {
        return listaContribuyentesFiltrado;
    }

    public void setListaContribuyentesFiltrado(List<Contribuyentes> listaContribuyentesFiltrado) {
        this.listaContribuyentesFiltrado = listaContribuyentesFiltrado;
    }

    public EnumPantallaMantenimiento getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(EnumPantallaMantenimiento pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public List<GeoPredio> getListaPrediosHuerfanos() {
        return listaPrediosHuerfanos;
    }

    public void setListaPrediosHuerfanos(List<GeoPredio> listaPrediosHuerfanos) {
        this.listaPrediosHuerfanos = listaPrediosHuerfanos;
    }

    public List<GeoPredio> getListaPrediosHuerfanosFiltrados() {
        return listaPrediosHuerfanosFiltrados;
    }

    public void setListaPrediosHuerfanosFiltrados(List<GeoPredio> listaPrediosHuerfanosFiltrados) {
        this.listaPrediosHuerfanosFiltrados = listaPrediosHuerfanosFiltrados;
    }

    public List<GeoPredio> getListaPrediosHuerfanosSeleccionados() {
        return listaPrediosHuerfanosSeleccionados;
    }

    public void setListaPrediosHuerfanosSeleccionados(List<GeoPredio> listaPrediosHuerfanosSeleccionados) {
        this.listaPrediosHuerfanosSeleccionados = listaPrediosHuerfanosSeleccionados;
    }

    public List<Predios> getListaPrediosGenerados() {
        return listaPrediosGenerados;
    }

    public void setListaPrediosGenerados(List<Predios> listaPrediosGenerados) {
        this.listaPrediosGenerados = listaPrediosGenerados;
    }

    public List<Predios> getListaPrediosGeneradosFiltrados() {
        return listaPrediosGeneradosFiltrados;
    }

    public void setListaPrediosGeneradosFiltrados(List<Predios> listaPrediosGeneradosFiltrados) {
        this.listaPrediosGeneradosFiltrados = listaPrediosGeneradosFiltrados;
    }

    public List<FichaCatastralDto> getListaPrediosHuerfanosRegistrados() {
        return listaPrediosHuerfanosRegistrados;
    }

    public void setListaPrediosHuerfanosRegistrados(List<FichaCatastralDto> listaPrediosHuerfanosRegistrados) {
        this.listaPrediosHuerfanosRegistrados = listaPrediosHuerfanosRegistrados;
    }

    public List<FichaCatastralDto> getListaPrediosHuerfanosRegistradosFiltrados() {
        return listaPrediosHuerfanosRegistradosFiltrados;
    }

    public void setListaPrediosHuerfanosRegistradosFiltrados(List<FichaCatastralDto> listaPrediosHuerfanosRegistradosFiltrados) {
        this.listaPrediosHuerfanosRegistradosFiltrados = listaPrediosHuerfanosRegistradosFiltrados;
    }

    @PostConstruct
    public void init() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_BUSQUEDA);
        this.establecerTitulo(tituloPantalla, iconoPantalla, descripcionPantalla);
        establecerTitulo(EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_TITULO,
                EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_ICONO,
                EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_DESCRIPCION);
        this.listaPrediosHuerfanosRegistrados = new ArrayList<>();
        actualizarListadoContribuyentes();
        try {
            obtenerListaPrediosHuerfanos();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    private void obtenerListaPrediosHuerfanos() throws NewviExcepcion {
        List<Predios> listaPrediosActual = catastroServicio.consultarPredios();
        this.listaPrediosHuerfanos = geoCatastroServicio.obtenerListadoGeoPrediosHuerfanos(listaPrediosActual, sesionBean.getSesion());
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

    public void seleccionarTodosPrediosHuerfanos() {
        this.listaPrediosHuerfanosSeleccionados = this.listaPrediosHuerfanos;
    }

    public void generarPrediosHuerfanos() {

        this.listaPrediosGenerados = geoCatastroServicio.obtenerListaPrediosDesdeGeoPredio(this.listaPrediosHuerfanosSeleccionados, sesionBean.getSesion());
        Map<String, String> variables = new HashMap<>();
        variables.put("npredios", (new Integer(this.listaPrediosGenerados.size())).toString());
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF506.presentarMensaje(variables), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF506.presentarMensaje(variables));
    }

    private Predios obtenerPredio(Predios predio) {
        Predios nuevoPredio = new Predios();
        //nuevoPredio.setCodCatastral(geoPredio.getId());
        nuevoPredio.setCodDpa(predio.getCodDpa());
        nuevoPredio.setCodZona(predio.getCodZona());
        nuevoPredio.setCodSector(predio.getCodSector());
        nuevoPredio.setCodManzana(predio.getCodManzana());
        nuevoPredio.setCodPredio(predio.getCodPredio());
        nuevoPredio.setNomCodigocatastral(predio.getNomCodigocatastral());
        nuevoPredio.setValAreaPredio(predio.getValAreaPredio());
        nuevoPredio.setCodCampo(predio.getCodCampo());
        nuevoPredio.setCatEstado(EnumEstadoRegistro.A);
        nuevoPredio.setHistoricoPropiedad(predio.getHistoricoPropiedad());
        return nuevoPredio;
    }

    public void registrarPredios() {
        for (Predios nuevoPredio : this.listaPrediosGenerados) {
            try {
                //Predios predioRegistrado = catastroServicio.seleccionarPredio(catastroServicio.actualizarPredio(obtenerPredio(nuevoPredio), sesionBean.getSesion()));
                //Predios predioRegistrado = catastroServicio.seleccionarPredio(catastroServicio.generarNuevoPredio(obtenerPredio(nuevoPredio), sesionBean.getSesion()));
                /*predioRegistrado.setHistoricoPropiedad(nuevoPredio.getHistoricoPropiedad());
                predioRegistrado = catastroServicio.seleccionarPredio(catastroServicio.actualizarPredio(predioRegistrado, sesionBean.getSesion()));*/
                //this.listaPrediosHuerfanosRegistrados.add(new FichaCatastralDto(predioRegistrado));
                //this.listaPrediosHuerfanosRegistrados.add(new FichaCatastralDto(obtenerPredio(predioRegistrado)));
                nuevoPredio.setCatEstado(EnumEstadoRegistro.A);

            } /*catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } */ catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }

        Map<String, String> variables = new HashMap<>();
        variables.put("npredios", (new Integer(this.listaPrediosHuerfanosRegistrados.size())).toString());
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF507.presentarMensaje(variables), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF507.presentarMensaje(variables));

    }

    public void avanzarPaginaSiguiente() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_EDITAR_TITULO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_EDITAR_ICONO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_EDITAR_DESCRIPCION);
    }

    public void regresarPaginaAnterior() {
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_BUSQUEDA);
        establecerTitulo(EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_TITULO,
                EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_ICONO,
                EnumEtiquetas.NUEVO_PREDIO_BUSQUEDA_DESCRIPCION);
    }

    public void avanzarPaginaVerificacion() {;
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_ASIGNACION);
        establecerTitulo(EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_VERIFICAR_TITULO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_VERIFICAR_ICONO,
                EnumEtiquetas.NUEVOS_ELEMENTOS_PREDIO_VERIFICAR_DESCRIPCION);
    }

    private Predios obtenerPredioSeleccionado(String codCampo) {
        for (Predios predioNuevo : this.listaPrediosGenerados) {
            if (predioNuevo.getCodCampo().trim().equals(codCampo)) {
                return predioNuevo;
            }
        }
        return null;
    }

    private void incluirContribuyenteAlPredio(Predios predioConContribuyente) {
        for (Predios predioNuevo : this.listaPrediosGenerados) {
            if (predioNuevo.getNomCodigocatastral().trim().equals(predioConContribuyente.getNomCodigocatastral().trim())) {
                if (this.listaPrediosGenerados.remove(predioNuevo)) {
                    this.listaPrediosGenerados.add(predioConContribuyente);
                }
            }
        }
    }

    public void abrirListadoContribuyentes(String codCampo) {
        WebUtils.obtenerContextoPeticion().execute("PF('dlgContribuyentePredio').show()");
        this.predioActual = obtenerPredioSeleccionado(codCampo);

    }

    public void agregarContribuyente(Integer codPersoneria) {
        Propiedad nuevaPropiedad = new Propiedad();
        Contribuyentes contribuyenteActual = new Contribuyentes();

        try {
            contribuyenteActual = contribuyentesServicio.seleccionarContribuyente(codPersoneria);
            nuevaPropiedad.setContribuyente(contribuyenteActual);
            this.predioActual.setCatEstado(EnumEstadoRegistro.A);
            nuevaPropiedad.setCodCatastral(this.predioActual);
            nuevaPropiedad.setProEstado(EnumEstadoRegistro.A);
            List<Propiedad> listaPropiedadesHistoricas = new ArrayList();
            listaPropiedadesHistoricas.add(nuevaPropiedad);
            this.predioActual.setHistoricoPropiedad(listaPropiedadesHistoricas);
            contribuyentesServicio.generarNuevoPropiedad(nuevaPropiedad, sesionBean.getSesion());

            this.listaPrediosGenerados.remove(obtenerPredioSeleccionado(this.predioActual.getCodCampo()));
            this.listaPrediosGenerados.add(this.predioActual);

            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF375.presentarMensaje(), sesionBean.getSesion());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF375.presentarMensaje());

        } catch (NewviExcepcion ex) {
            Logger.getLogger(NuevoPredioBB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void registrarListaPrediosNuevos() {
        Integer contador = 0;
        for (Predios nuevoPredio : this.listaPrediosGenerados) {
            try {
                nuevoPredio.setCatEstado(EnumEstadoRegistro.A);
                catastroServicio.generarNuevoPredio(nuevoPredio, sesionBean.getSesion());
                contador++;
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.getSesion());
                MensajesFaces.mensajeError(e.getMessage());
            }
        }

        Map<String, String> variables = new HashMap<>();
        variables.put("npredios", (new Integer(contador)).toString());
        LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF507.presentarMensaje(variables), sesionBean.getSesion());
        MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF507.presentarMensaje(variables));

    }

}
