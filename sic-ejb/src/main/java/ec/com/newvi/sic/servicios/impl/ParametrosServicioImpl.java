/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ConstantesImpuestosFacade;
import ec.com.newvi.sic.dao.DominiosFacade;
import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        //dominio.setDomiEstado(EnumEstadoRegistro.E);
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
    public Boolean tieneBasura(Integer codCatastral) {
        return dominiosFacade.tieneBasura(codCatastral);
    }
}
