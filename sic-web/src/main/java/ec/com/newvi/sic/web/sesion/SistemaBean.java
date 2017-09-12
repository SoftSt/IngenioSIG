/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.sesion;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author israelavila
 */
@Named
@SessionScoped
public class SistemaBean implements Serializable {

    @EJB
    protected ParametrosServicio parametrosServicio;
    
    private String nombreSistema;
    private Funcionalidades funcionalidadActual;

    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    public void setFuncionalidadActual(Funcionalidades funcionalidadActual) {
        this.funcionalidadActual = funcionalidadActual;
    }
    
    @PostConstruct
    public void actualizarParametrosSistema() {
        try {
            this.nombreSistema = parametrosServicio.obtenerParametroPorNombre(EnumParametroSistema.TITULO_SISTEMA, null).getValor();
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR455.presentarMensajeCodigo(), ex, null);
            this.nombreSistema = "";
        }
    }
    
    public String getSubtitulo() {
        String subtitulo = "";
        if(!ComunUtil.esNulo(this.funcionalidadActual)){
            //TODO actualizar en el menú en el momento que se realice el cambio de pantalla
            //subtitulo = this.funcionalidadActual.getFunMenu();
        }
        return subtitulo;
    }

}
