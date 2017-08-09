/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.backingbean;

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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
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
    private ObrasDetalle obrasDetalle;
    private List<ContribucionMejoras> listaContribucionMejoras;
    private List<ContribucionMejoras> listaContribucionMejorasFiltrado;
    private List<ObrasDetalle> listaObrasDetalleFiltrado;
    private List<ObrasDetalle> listaObrasDetalle;
    private List<Predios> listaPredios;
    private List<Predios> listaPrediosFiltrado;
    private List<Predios> listaPrediosSeleccionados;
    private Predios predioSeleccionado;
    private EnumPantallaMantenimiento pantallaActual;

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

    @PostConstruct
    public void init() {
        this.contribucionMejoras = new ContribucionMejoras();
        actualizarListadoContribucionMejoras();
        actualizarListadoPredios();
        //listaTipoPersoneria= EnumTipoPersoneria.values();
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_LISTADO);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_LISTA_DESCRIPCION);
    }

    private void actualizarListadoContribucionMejoras() {
        listaContribucionMejoras = contribucionMejorasServicio.consultarContribucionMejoras();
    }

    private void actualizarListadoObrasDetalle(Integer codObra) throws NewviExcepcion {
        listaObrasDetalle = contribucionMejorasServicio.consultarObrasDetalle(codObra);
    }

    private void actualizarListadoPredios() {
        listaPredios = catastroServicio.consultarPredios();
    }

    public void crearNuevaContribucionMejoras() {
        this.contribucionMejoras = new ContribucionMejoras();
        this.contribucionMejoras.setObrEstado(EnumEstadoRegistro.A);
        conmutarPantalla(EnumPantallaMantenimiento.PANTALLA_EDICION);
        establecerTitulo(EnumEtiquetas.CONTRIBUCION_MEJORAS_NUEVO_TITULO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_NUEVO_ICONO,
                EnumEtiquetas.CONTRIBUCION_MEJORAS_NUEVO_DESCRIPCION);
    }

    public void insertarContribucionMejoras() {
        try {
            contribucionMejorasServicio.generarNuevaContribucionMejoras(contribucionMejoras, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF333.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF333.presentarMensaje());
            actualizarListadoContribucionMejoras();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void actualizarContribucionMejoras() {
        if (!ComunUtil.esNumeroPositivo(this.contribucionMejoras.getCodObras())) {
            insertarContribucionMejoras();
        } else {
            try {
                contribucionMejorasServicio.actualizarContribucionMejoras(contribucionMejoras, sesionBean.obtenerSesionDto());
                actualizarListadoContribucionMejoras();
                LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF334.presentarMensaje(), sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF334.presentarMensaje());
            } catch (NewviExcepcion e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeError(e.getMessage());
            } catch (Exception e) {
                LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
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
                contribucionMejorasServicio.eliminarContribucionMejoras(contribucionMejoras, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF335.presentarMensaje());
                actualizarListadoContribucionMejoras();

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

    public void seleccionarContribucionMejoras(Integer idContribucionMejoras) {
        try {
            this.seleccionarContribucionMejorasPorCodigo(idContribucionMejoras);
            actualizarListadoObrasDetalle(idContribucionMejoras);
        } catch (NewviExcepcion e) {
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
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

    public void abrirDialogPredios(int codObras) throws NewviExcepcion {
        contribucionMejoras = contribucionMejorasServicio.seleccionarContribucionMejoras(codObras);
        WebUtils.obtenerContextoPeticion().execute("PF('dlgPredios').show()");
    }

    public void onRowSelect(SelectEvent event) {
        Predios predioEvento = (Predios) event.getObject();

        ObrasDetalle nuevaObraDetalle = new ObrasDetalle();
        nuevaObraDetalle.setCodObras(contribucionMejoras);
        nuevaObraDetalle.setCodCatastral(predioEvento);
        //nuevaObraDetalle.setCodPredio(predio.getCodPredio());
        nuevaObraDetalle.setNomCodigocatastral(predioEvento);
        nuevaObraDetalle.setObrValor(contribucionMejoras.getValValor());
        nuevaObraDetalle.setValAreafrente(predioEvento.getValAreaFrente());
        nuevaObraDetalle.setValPredio(predioEvento.getValPredio());
        nuevaObraDetalle.setValPredio(predioEvento.getValPredio());
        nuevaObraDetalle.setObdEstado(EnumEstadoRegistro.A);

        generarNuevoDetalleObra(nuevaObraDetalle);
    }

    public void generarNuevoDetalleObra(ObrasDetalle obraDetalle) {
        try {
            contribucionMejorasServicio.generarNuevaObrasDetalle(obraDetalle, sesionBean.obtenerSesionDto());
            LoggerNewvi.getLogNewvi(this.getClass()).info(EnumNewviExcepciones.INF368.presentarMensaje(), sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF368.presentarMensaje());
            actualizarListadoContribucionMejoras();
        } catch (NewviExcepcion e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        } catch (Exception e) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR000.presentarMensajeCodigo(), e, sesionBean.obtenerSesionDto());
            MensajesFaces.mensajeError(e.getMessage());
        }
    }

    public void onRowUnselect(UnselectEvent event) {
        //Predios dd = (Predios) event.getObject();
    }
    
    public void eliminarDetalleObra(Integer codObrasdetalle) {
        try {
            this.seleccionarDetalleObraPorCodigo(codObrasdetalle);
            if (!ComunUtil.esNulo(obrasDetalle)) {
                contribucionMejorasServicio.eliminarObrasDetalle(obrasDetalle, sesionBean.obtenerSesionDto());
                MensajesFaces.mensajeInformacion(EnumNewviExcepciones.INF369.presentarMensaje());
                actualizarListadoContribucionMejoras();

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

}
