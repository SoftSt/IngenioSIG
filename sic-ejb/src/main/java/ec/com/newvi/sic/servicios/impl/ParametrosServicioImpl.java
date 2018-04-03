/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ConstantesImpuestosFacade;
import ec.com.newvi.sic.dao.DominiosFacade;
import ec.com.newvi.sic.dao.ParametroSistemaFacade;
import ec.com.newvi.sic.dao.ReporteFacade;
import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumParametroSistema.EnumGrupoParametroSistema;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.ParametroSistema;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.UtilArchivos;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author NEWVI
 */
@Stateless
@PermitAll
public class ParametrosServicioImpl implements ParametrosServicio {

    @EJB
    private DominiosFacade dominiosFacade;
    @EJB
    private ConstantesImpuestosFacade constantesFacade;
    @EJB
    private ReporteFacade reporteFacade;
    @EJB
    private ParametroSistemaFacade parametroSistemaFacade;

    /*---------------------------------------------------- Parámetros del Sistema ----------------------------------------------------*/
    @Override
    public String generarNuevoParametroSistema(ParametroSistema nuevoParametroSistema, SesionDto sesion) throws NewviExcepcion {

        nuevoParametroSistema.actualizarDatosPorTipoParametro();

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando parámetro del sistema...", sesion);
        if (!nuevoParametroSistema.esParametroSistemaValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR301);
        }
        // Crear el parametro
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando parámetro del sistema...", sesion);

        Date fechaIngreso = Calendar.getInstance().getTime();

        //Registramos la auditoria de ingreso
        nuevoParametroSistema.setAudIngIp(sesion.getDireccionIP());
        nuevoParametroSistema.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuUsuario().trim());

        nuevoParametroSistema.setAudIngFec(fechaIngreso);

        parametroSistemaFacade.create(nuevoParametroSistema);
        // Si todo marcha bien enviar nombre de usuario
        return nuevoParametroSistema.getParametro().name();
    }

    @Override
    public String actualizarParametroSistema(ParametroSistema parametroSistema, SesionDto sesion) throws NewviExcepcion {

        parametroSistema.actualizarDatosPorTipoParametro();

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando parámetro del sistema...", sesion);
        if (!parametroSistema.esParametroSistemaValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR451);
        }
        // Acturlizar el parámetro
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando parámetro del sistema...", sesion);

        //Registramos la auditoria de modificacion
        parametroSistema.setAudModIp(sesion.getDireccionIP());
        parametroSistema.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        parametroSistema.setAudModFec(fechaModificacion);

        parametroSistemaFacade.edit(parametroSistema);
        // Si todo marcha bien enviar nombre de usuario
        return parametroSistema.getParametro().name();
    }

    @Override
    public String guardarImagenPredio(String direccionArchivo, byte[] imagenEnBytes, String nombreArchivo, SesionDto sesion) throws NewviExcepcion {
        return UtilArchivos.almacenarArchivoEnServidor(nombreArchivo, direccionArchivo, imagenEnBytes);
    }

    @Override
    public String guardarImagenParametroSistema(ParametroSistema parametroSistema, byte[] imagenEnBytes, SesionDto sesion) throws NewviExcepcion {
        String nombreArchivo = reemplazarValoresParametros(parametroSistema, sesion);
        String direccionArchivo = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("/"));
        nombreArchivo = nombreArchivo.substring(nombreArchivo.lastIndexOf("/") + 1, nombreArchivo.length());
        return UtilArchivos.almacenarArchivoEnServidor(nombreArchivo, direccionArchivo, imagenEnBytes);
    }

    @Override
    public ParametroSistema seleccionarParametroSistema(Integer codParametro) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codParametro)) {
            return parametroSistemaFacade.find(codParametro);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public List<ParametroSistema> obtenerListaParametrosSistema(SesionDto sesion) throws NewviExcepcion {
        return obtenerListaParametrosSistemaPorTipo(null, sesion);
    }

    @Override
    public List<ParametroSistema> obtenerListaParametrosSistemaPorTipo(EnumGrupoParametroSistema grupo, SesionDto sesion) throws NewviExcepcion {
        return parametroSistemaFacade.obtenerListaParametrosPorGrupo(grupo, sesion);
    }

    @Override
    public ParametroSistema obtenerParametroPorNombre(EnumParametroSistema parametro, SesionDto sesion) throws NewviExcepcion {
        try {
            return parametroSistemaFacade.obtenerParametroPorNombre(parametro, sesion);
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(ex, sesion);
            Map<String, String> paramError = new HashMap<>();
            paramError.put("parametro", parametro.getNombreParametro());
            throw new NewviExcepcion(EnumNewviExcepciones.ERR455, paramError, ex);
        }
    }

    private String reemplazarValoresParametros(ParametroSistema parametro, SesionDto sesion) throws NewviExcepcion {
        String valorParametro = parametro.getValor();
        String valorReemplazado = valorParametro;
        try {
            String parametroAReemplazar = valorParametro.substring(valorParametro.indexOf("[") + 1, valorParametro.indexOf("]"));
            if (!ComunUtil.esCadenaVacia(parametroAReemplazar)) {
                EnumParametroSistema parametroSistemaReemplazo = EnumParametroSistema.valueOf(parametroAReemplazar);
                String valorParametroAReemplazar = obtenerParametroPorNombre(parametroSistemaReemplazo, sesion).getValor();
                valorReemplazado = valorParametro.replace("[".concat(parametroAReemplazar).concat("]"), valorParametroAReemplazar);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).warn("No se pudo encontrar el parámetro a ser reemplazado. Omitiendo. :".concat(ex.getMessage()), sesion);
        }
        return valorReemplazado;
    }

    /*------------------------------------------------------------Dominios------------------------------------------------------------*/
    @Override
    public String generaNuevoDominio(Dominios nuevoDominio, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando dominio...", sesion);
        if (!nuevoDominio.esDominioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR322);
        }
        // Crear el dominio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando dominio...", sesion);

        //Registramos la auditoria de ingreso
        nuevoDominio.setAudIngIp("");
        nuevoDominio.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoDominio.setAudIngFec(fechaIngreso);

        dominiosFacade.create(nuevoDominio);
        // Si todo marcha bien enviar codigo del dominio
        return nuevoDominio.getDomiCodigo();
    }

    @Override
    public String actualizarDominio(Dominios dominio, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando dominio...", sesion);
        if (!dominio.esDominioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR322);
        }
        // Acturlizar el dominio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando dominio...", sesion);

        //Registramos la auditoria de modificacion
        dominio.setAudModIp("");
        dominio.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        dominio.setAudModFec(fechaModificacion);

        dominiosFacade.edit(dominio);
        // Si todo marcha bien enviar codigo del dominio
        return dominio.getDomiCodigo();
    }

    @Override
    public List<Dominios> consultarDominios() {
        return dominiosFacade.buscarDominios();
    }

    @Override
    public Dominios seleccionarDominio(Integer idDominio) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idDominio)) {
            return dominiosFacade.find(idDominio);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarDominio(Dominios dominio, SesionDto sesion) throws NewviExcepcion {
        dominio.setEstadoDominio(EnumEstadoRegistro.E);
        return actualizarDominio(dominio, sesion);
    }

    @Override
    public List<Dominios> consultarGruposDominios() {
        return dominiosFacade.buscarDominiosGrupos();
    }

    @Override
    public List<Dominios> consultarDominiosPorGrupo(String grupo, String relacion) {
        return dominiosFacade.buscarDominiosPorGrupo(grupo, relacion);
    }

    @Override
    public List<Dominios> consultarHijos(Dominios dominio) {
        return dominiosFacade.buscarHijos(dominio);
    }

    @Override
    public List<DominioDto> listarDominiosDto(String grupo, String relacion) {
        List<DominioDto> listadoDominiosDto = new ArrayList<>();

        for (Dominios dominio : dominiosFacade.buscarDominiosPorGrupo(grupo, relacion)) {
            listadoDominiosDto.add(new DominioDto(dominio, this));
            //listadoDominiosDto.add(new DominioDto(dominio, dominiosFacade));
        }

        return listadoDominiosDto;
    }

    @Override
    public BigDecimal obtenerCOFF(BigDecimal dominio, String calculo) {
        return dominiosFacade.obtenerCOFF(dominio, calculo);
    }

    @Override
    public BigDecimal obtenerCoeficienteTerreno(Predios predio, String domiCalculo) {
        List<Dominios> listaDominiosTerreno;
        BigDecimal totalCoeficienteCalculo = BigDecimal.ZERO;
        for (Terreno terreno : predio.getCaracteristicasTerreno()) {
            listaDominiosTerreno = dominiosFacade.buscarDominiosPorCodigoYCalculo(terreno.getStsCodigo(), domiCalculo);
            for (Dominios dominio : listaDominiosTerreno) {
                totalCoeficienteCalculo = totalCoeficienteCalculo.add(BigDecimal.valueOf(dominio.getDomiCoefic()));
            }
        }
        return totalCoeficienteCalculo;
    }

    @Override
    public BigDecimal obtenerCoeficienteConstruccion(Pisos piso, String domiCalculo) {
        BigDecimal totalDetalleConstruccion = BigDecimal.ZERO;
        for (PisoDetalle detalle : piso.getDetalles()) {
            totalDetalleConstruccion = totalDetalleConstruccion.add(obtenerCoeficienteDetallePiso(detalle, domiCalculo));
        }
        return totalDetalleConstruccion;
    }

    @Override
    public BigDecimal obtenerCoeficienteDetallePiso(PisoDetalle detalle, String domiCalculo) {
        List<Dominios> listaDominios;
        BigDecimal totalDetalle = BigDecimal.ZERO;
        listaDominios = dominiosFacade.buscarDominiosPorGrupoCodigoYCalculo(detalle.getCodigo(), "DESCRIPCION EDIFICACION", domiCalculo);
        for (Dominios dominio : listaDominios) {
            totalDetalle = totalDetalle.add(BigDecimal.valueOf(dominio.getDomiCoefic()));
        }
        return totalDetalle;
    }

    @Override
    public BigDecimal obtenerValorDepreciacion(BigDecimal dominio, String domiDescripcion) {
        for (Dominios objetoDominio : dominiosFacade.buscarDominiosPorEstadoReparacion(domiDescripcion)) {
            if ((BigDecimal.valueOf(objetoDominio.getDomiMinimo()).compareTo(dominio) <= 0)
                    && (BigDecimal.valueOf(objetoDominio.getDomiMaximo()).compareTo(dominio) >= 0)) {
                return BigDecimal.valueOf(objetoDominio.getDomiCoefic());
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal obtenerValorPorCodigoCalculo(String domiCodigo, String domiCalculo) {
        return dominiosFacade.obtenerValorPorCodigoCalculo(domiCodigo, domiCalculo);
    }

    @Override
    public BigDecimal obtenerTotalCoeficienteDominiosPorCodigo(String domiCodigo) {
        BigDecimal totalCoeficiente = BigDecimal.ZERO;
        for (Dominios dominio : dominiosFacade.buscarDominiosPorCodigo(domiCodigo)) {
            totalCoeficiente = totalCoeficiente.add(BigDecimal.valueOf(dominio.getDomiCoefic()));
        }
        return totalCoeficiente;
    }

    @Override
    public List<ConstantesImpuestos> obtenerConstantesImpuestosPorTipo(String stsTipo) {
        return constantesFacade.obtenerConstantesImpuestosPorTipo(stsTipo);
    }

    @Override
    public String generaNuevoConstanteImpuesto(ConstantesImpuestos nuevaConstantesImpuestos, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando constante impuesto...", sesion);
        if (!nuevaConstantesImpuestos.esConstanteImpuestosValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR364);
        }
        // Crear el dominio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando constante impuesto...", sesion);

        //Registramos la auditoria de ingreso
        nuevaConstantesImpuestos.setAudIngIp("");
        nuevaConstantesImpuestos.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevaConstantesImpuestos.setAudIngFec(fechaIngreso);

        constantesFacade.create(nuevaConstantesImpuestos);
        // Si todo marcha bien enviar codigo del constante
        return nuevaConstantesImpuestos.getCodConstantesimpuestos() + "";
    }

    @Override
    public String actualizarConstanteImpuesto(ConstantesImpuestos constantesImpuestos, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando constante impuesto...", sesion);
        if (!constantesImpuestos.esConstanteImpuestosValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR364);
        }
        // Acturlizar el constante impuesto
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando constante impuesto...", sesion);

        //Registramos la auditoria de modificacion
        constantesImpuestos.setAudModIp("");
        constantesImpuestos.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        constantesImpuestos.setAudModFec(fechaModificacion);

        constantesFacade.edit(constantesImpuestos);
        // Si todo marcha bien enviar codigo de la constante impuesto
        return constantesImpuestos.getCodConstantesimpuestos() + "";
    }

    @Override
    public ConstantesImpuestos seleccionarConstanteImpuestos(Integer codConstantesimpuestos) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(codConstantesimpuestos)) {
            return constantesFacade.find(codConstantesimpuestos);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public List<ConstantesImpuestos> consultarConstantesImpuestos() {
        return constantesFacade.buscarConstantesImpuestos();
    }

    @Override
    public String eliminarConstanteImpuesto(ConstantesImpuestos constantesImpuestos, SesionDto sesion) throws NewviExcepcion {
        constantesImpuestos.setconImpuestoEstado(EnumEstadoRegistro.E);
        return actualizarConstanteImpuesto(constantesImpuestos, sesion);
    }

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    @Override
    public Reporte obtenerReporte(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion {
        return reporteFacade.obtenerReportePorNombre(nombreReporte, sesion);
    }

}
