/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.BloquesFacade;
import ec.com.newvi.sic.dao.FotosFacade;
import ec.com.newvi.sic.dao.PisosDetalleFacade;
import ec.com.newvi.sic.dao.PisosFacade;
import ec.com.newvi.sic.dao.PrediosFacade;
import ec.com.newvi.sic.dao.TerrenoFacade;
import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.servicios.CatastroServicio;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Andrés
 */
@Stateless
@PermitAll
public class CatastroServicioImpl implements CatastroServicio {

    @EJB
    PrediosFacade prediosFacade;
    @EJB
    BloquesFacade bloquesFacade;
    @EJB
    PisosFacade pisosFacade;
    @EJB
    TerrenoFacade terrenoFacade;
    @EJB
    FotosFacade fotosFacade;
    @EJB
    PisosDetalleFacade pisosDetalleFacade;
    @EJB
    ParametrosServicio parametrosServicio;

    /*------------------------------------------------------------Predios------------------------------------------------------------*/
    @Override
    public String generarNuevoPredio(Predios nuevoPredio, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando predio...", sesion);
        if (!nuevoPredio.esPredioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR338);
        }
        // Crear el predio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando predio...", sesion);

        //Registramos la auditoria de ingreso
        nuevoPredio.setAudIngIp(sesion.getDireccionIP());
        nuevoPredio.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoPredio.setAudIngFec(fechaIngreso);

        prediosFacade.create(nuevoPredio);
        // Si todo marcha bien enviar nombre del predio
        return nuevoPredio.getNomCodigocatastral();

    }

    @Override
    public String actualizarPredio(Predios predio, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando predio...", sesion);
        if (!predio.esPredioValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR338);
        }
        // Editar la predio
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando predio...", sesion);

        //Registramos la auditoria de modificacion
        predio.setAudModIp(sesion.getDireccionIP());
        predio.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        predio.setAudModFec(fechaModificacion);
        
        predio.setCodManzana(predio.getCodManzana().trim()); 

        prediosFacade.edit(predio);
        // Si todo marcha bien enviar nombre de la predio
        return predio.getNomCodigocatastral();
    }

    @Override
    public List<Predios> consultarPredios() {
        return prediosFacade.buscarPredio();
    }

    @Override
    public Predios seleccionarPredio(Integer idPredio) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idPredio)) {
            return prediosFacade.find(idPredio);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarPredio(Predios predio, SesionDto sesion) throws NewviExcepcion {
        predio.setCatEstado(EnumEstadoRegistro.E);
        return actualizarPredio(predio, sesion);
    }

    /*------------------------------------------------------------Bloques------------------------------------------------------------*/
    @Override
    public String generarNuevoBloque(Bloques nuevoBloque, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando bloque...", sesion);
        if (!nuevoBloque.esBloqueValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR339);
        }
        // Crear el bloque
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando bloque...", sesion);

        //Registramos la auditoria de ingreso
        nuevoBloque.setAudIngIp(sesion.getDireccionIP());
        nuevoBloque.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoBloque.setAudIngFec(fechaIngreso);

        bloquesFacade.create(nuevoBloque);
        // Si todo marcha bien enviar nombre del bloque
        return nuevoBloque.getNomBloque();

    }

    @Override
    public String actualizarBloque(Bloques bloque, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando bloque...", sesion);
        if (!bloque.esBloqueValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR339);
        }
        // Editar la bloque
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando bloque...", sesion);

        //Registramos la auditoria de modificacion
        bloque.setAudModIp(sesion.getDireccionIP());
        bloque.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        bloque.setAudModFec(fechaModificacion);

        bloquesFacade.edit(bloque);
        // Si todo marcha bien enviar nombre de la bloque
        return bloque.getNomBloque();
    }

    @Override
    public List<Bloques> consultarBloques() {
        return bloquesFacade.buscarBloques();
    }

    @Override
    public Bloques seleccionarBloque(Integer idBloque) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idBloque)) {
            return bloquesFacade.find(idBloque);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarBloque(Bloques bloque, SesionDto sesion) throws NewviExcepcion {
        bloque.setBloEstado(EnumEstadoRegistro.E);
        return actualizarBloque(bloque, sesion);
    }

    @Override
    public List<Bloques> buscarBloquesPorCodigoCatastral(Integer codCatastral) {
        return bloquesFacade.buscarBloquesPorCodigoCatastral(codCatastral);
    }

    /*------------------------------------------------------------Pisos------------------------------------------------------------*/
    @Override
    public String generarNuevoPiso(Pisos nuevoPiso, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando piso...", sesion);
        if (!nuevoPiso.esPisoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR343);
        }
        // Crear el piso
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando piso...", sesion);

        //Registramos la auditoria de ingreso
        nuevoPiso.setAudIngIp(sesion.getDireccionIP());
        nuevoPiso.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoPiso.setAudIngFec(fechaIngreso);

        pisosFacade.create(nuevoPiso);
        // Si todo marcha bien enviar nombre del piso
        return nuevoPiso.getNomPiso();

    }

    @Override
    public String actualizarPiso(Pisos piso, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando piso...", sesion);
        if (!piso.esPisoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR343);
        }
        // Editar la piso
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando piso...", sesion);

        //Registramos la auditoria de modificacion
        piso.setAudModIp(sesion.getDireccionIP());
        piso.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        piso.setAudModFec(fechaModificacion);

        pisosFacade.edit(piso);
        // Si todo marcha bien enviar nombre de la piso
        return piso.getNomPiso();
    }

    @Override
    public List<Pisos> consultarPisos() {
        return pisosFacade.buscarPisos();
    }

    @Override
    public Pisos seleccionarPiso(Integer idPiso) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idPiso)) {
            return pisosFacade.find(idPiso);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarPiso(Pisos piso, SesionDto sesion) throws NewviExcepcion {
        piso.setPisEstado(EnumEstadoRegistro.E);
        return actualizarPiso(piso, sesion);
    }

    @Override
    public Object[] obtenerDatosPisoPorBloque(Integer codBloque) {
        return pisosFacade.obtenerDatosPisoPorBloque(codBloque);
    }
    @Override
    public Pisos buscarPisosPorCodigoBloque(Integer codBloques){
        return pisosFacade.buscarPisosPorCodigoBloque(codBloques);
    }

    /*------------------------------------------------------------PisosDetalle------------------------------------------------------------*/
    @Override
    public String actualizarPisoDetalle(PisoDetalle pisoDetalle, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando detalle piso...", sesion);
        if (!pisoDetalle.esDetallePisoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR343);
        }
        // Editar la piso
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando detalle piso...", sesion);

        //Registramos la auditoria de modificacion
        pisoDetalle.setAudModIp(sesion.getDireccionIP());
        pisoDetalle.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        pisoDetalle.setAudModFec(fechaModificacion);

        pisosDetalleFacade.edit(pisoDetalle);
        // Si todo marcha bien enviar nombre de la piso
        return pisoDetalle.getCodigo();
    }

    /*------------------------------------------------------------Terreno------------------------------------------------------------*/
    @Override
    public String generarNuevoTerreno(Terreno nuevoTerreno, SesionDto sesion) throws NewviExcepcion {

        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando terreno...", sesion);
        if (!nuevoTerreno.esTerrenoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR343);
        }
        // Crear el terreno
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando terreno...", sesion);

        //Registramos la auditoria de ingreso
        nuevoTerreno.setAudIngIp(sesion.getDireccionIP());
        nuevoTerreno.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoTerreno.setAudIngFec(fechaIngreso);

        terrenoFacade.create(nuevoTerreno);
        // Si todo marcha bien enviar nombre del terreno
        return nuevoTerreno.getCodTerrenodetalle().toString();

    }

    @Override
    public String actualizarTerreno(Terreno terreno, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando terreno...", sesion);
        if (!terreno.esTerrenoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR343);
        }
        // Editar la terreno
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando terreno...", sesion);

        //Registramos la auditoria de modificacion
        terreno.setAudModIp(sesion.getDireccionIP());
        terreno.setAudModUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
        Date fechaModificacion = Calendar.getInstance().getTime();
        terreno.setAudModFec(fechaModificacion);

        terrenoFacade.edit(terreno);
        // Si todo marcha bien enviar nombre de la terreno
        return terreno.getCodTerrenodetalle().toString();
    }

    @Override
    public List<Terreno> consultarTerreno() {
        return terrenoFacade.buscarTerreno();
    }

    @Override
    public Terreno seleccionarTerreno(Integer idTerreno) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idTerreno)) {
            return terrenoFacade.find(idTerreno);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public String eliminarTerreno(Terreno terreno, SesionDto sesion) throws NewviExcepcion {
        terreno.setTerEstado(EnumEstadoRegistro.E);
        return actualizarTerreno(terreno, sesion);
    }

    /*------------------------------------------------------------Fotos------------------------------------------------------------*/
    @Override
    public List<Fotos> consultarFotosPorPredio(int codCatastral) {
        return fotosFacade.buscarFotosPorPredio(codCatastral);
    }
    
    @Override
    public List<AvaluoDto> obtenerAvaluoPredio(Predios predio, SesionDto sesion) throws NewviExcepcion {
        BigDecimal coff, cot, cofo, cubi, cero, fa1, div, vestruc, vcabado, vextra, areapiso, edad, vdepre, vterreno, area, frente, v1, v2, v3, valor_terreno, cosB, areaB, cost, valPredio, c1, c2, c3, c4, c5, c6, basura, aPagar;
        Object[] objetoPiso;
        Integer codigo, codigo_piso, reposicion, codigo_bloque;
        String nombrePiso, estado, zona, sector, consulta;
        Boolean ba;

        codigo = predio.getCodCatastral();
        fa1 = BigDecimal.ZERO;
        div = new BigDecimal(5);
        frente = predio.getValAreaFrente();
        area = predio.getValAreaFondo();
        cosB = BigDecimal.ZERO;
        areaB = BigDecimal.ZERO;
        cost = BigDecimal.ZERO;

        List<AvaluoDto> nodo = new ArrayList<>();
        List<AvaluoDto> listaCaracteristicasPisosDto;
        List<AvaluoDto> listaPisosDto;

        c1 = BigDecimal.ZERO;
        c2 = BigDecimal.ZERO;
        c3 = BigDecimal.ZERO;
        c4 = BigDecimal.ZERO;
        c5 = BigDecimal.ZERO;
        c6 = BigDecimal.ZERO;

        aPagar = BigDecimal.ZERO;

        nombrePiso = "";
        estado = "";
        zona = predio.getCodZona();
        sector = predio.getCodSector();

        edad = BigDecimal.ZERO;

        // Calculo del fondo relativo COFF
        coff = obtenerValoracionFondoRelativo(area, frente);
        // Coeficiente de Topografía COT
        cot = parametrosServicio.obtenerValor(codigo, "TOPOGRAFIA");
        insertarElementosArbolAvaluo("Topografía", cot, null, nodo);
        // Coeficinte de Erosion
        cero = parametrosServicio.obtenerValor(codigo, "LOCALIZACION");
        insertarElementosArbolAvaluo("Erosión", cero, null, nodo);
        // Coeficinte de forma COFO
        cofo = parametrosServicio.obtenerValor(codigo, "FORMA");
        insertarElementosArbolAvaluo("Forma", cofo, null, nodo);
        // Coeficinte de Ubicacion
        cubi = parametrosServicio.obtenerValor(codigo, "OCUPACION");
        insertarElementosArbolAvaluo("Ubicación", cubi, null, nodo);

        fa1 = (fa1.add(coff).add(cot).add(cofo).add(cero).add(cubi)).divide(div, 4, RoundingMode.CEILING);
        insertarElementosArbolAvaluo("Promedio de factores", fa1, null, nodo);

        // CALCULO DEL PRECIO BASE PARA EL TERRENO
        // SE TOMA EN CUENTA UNA VALORACION POR LAS ZONAS y SECTORES DEL MUNICIPIO.
        consulta = "20" + zona + sector;

        vterreno = parametrosServicio.obtenerValorPorCodigoCalculo(consulta, "ZONAS VALORADAS M2");
        insertarElementosArbolAvaluo("Precio base en M2 en la zona " + zona + " sector " + sector, vterreno, null, nodo);

        valor_terreno = (fa1.multiply(area)).multiply(vterreno);

        predio.setValTerreno(valor_terreno);
        actualizarPredio(predio, sesion);

        for (Bloques bloque : predio.getBloques()) {

            listaCaracteristicasPisosDto = new ArrayList<>();
            listaPisosDto = new ArrayList<>();

            codigo_bloque = bloque.getCodBloques();

            for (Pisos piso : bloque.getPisosCollection()) {

                codigo_piso = piso.getCodPisos();
                nombrePiso = piso.getNomPiso();
                
                edad = new BigDecimal(piso.obtenerEdadPiso());
                insertarElementosArbolAvaluo("Edad", edad, null, listaCaracteristicasPisosDto);
                
                estado = piso.getStsEstado();
                insertarElementosArbolAvaluo("Estado", estado, null, listaCaracteristicasPisosDto);
                
                areapiso = piso.getValAreapiso();
                insertarElementosArbolAvaluo("Area", areapiso, null, listaCaracteristicasPisosDto);

                //reposicion = piso.getValAnioreparacion();

                //Factor de depreciación de inmueble por piso y depreciacion
                vdepre = parametrosServicio.obtenerVDEPRE(edad, estado);
                insertarElementosArbolAvaluo("Factor Depresiación", vdepre, null, listaCaracteristicasPisosDto);

                //Detalle de construccion Estructura
                vestruc = parametrosServicio.obtenerDetalleContruccion(codigo_piso, "ESTRUCTURA");
                v1 = parametrosServicio.obtenerValorPorCodigo("210101");
                //Destalle de construccion Acabados
                vcabado = parametrosServicio.obtenerDetalleContruccion(codigo_piso, "ACABADOS");
                v2 = parametrosServicio.obtenerValorPorCodigo("210102");
                //Detalle de construccion Extras
                vextra = parametrosServicio.obtenerDetalleContruccion(codigo_piso, "EXTRAS");
                v3 = parametrosServicio.obtenerValorPorCodigo("210103");

                // Factos de costos del pios es igual a la suma de los factores por el área menos la depreciación del bien por edad y estado
                cosB = (vestruc.multiply(v1)).add(vcabado.multiply(v2)).add(vextra.multiply(v3)).subtract(((vestruc.multiply(v1)).add(vcabado.multiply(v2)).add(vextra.multiply(v3))).multiply(vdepre));
                areaB = areaB.add(areapiso);
                cost = cost.add(cosB);

                // Ubica valor de calculos en la tabla de pisos
                piso = seleccionarPiso(codigo_piso);

                piso.setValFactordepreciacion(vdepre);
                piso.setValSumafactores(vestruc.add(vcabado).add(vextra));
                piso.setValConstante(fa1);
                piso.setValMetro2(((vestruc.multiply(v1)).add(vcabado.multiply(v2)).add(vextra.multiply(v3))).multiply(vdepre));
                piso.setValPiso(cosB);

                actualizarPiso(piso, sesion);

                insertarElementosArbolAvaluo("Piso: " + nombrePiso, null, listaCaracteristicasPisosDto, listaPisosDto);
            }

            // Actualiza Valores por Bloque
            bloque = seleccionarBloque(codigo_bloque);

            bloque.setValAreabloque(areaB);
            bloque.setValBloque(cosB);

            actualizarBloque(bloque, sesion);

            insertarElementosArbolAvaluo("Bloque: " + bloque.getNomBloque(), null, listaPisosDto, nodo);
        }
        //Actualiza Valoración de Terreno y Contrucción
        predio.setValEdifica(cost);
        predio.setValAreaConstruccion(areaB);

        valPredio = valor_terreno.add(cost);

        predio.setValPredio(valPredio);
        actualizarPredio(predio, sesion);

        // Constantes catastro urbano
        List<ConstantesImpuestos> constantesImpuestos = parametrosServicio.obtenerConstantesImpuestosPorTipo("URBANO");

        for (ConstantesImpuestos constantesImpuesto : constantesImpuestos) {
            c1 = constantesImpuesto.getValBomberos();
            c2 = constantesImpuesto.getValServiciosadministrativos();
            c3 = constantesImpuesto.getValCem();
            c4 = constantesImpuesto.getValBasura();
            c5 = constantesImpuesto.getValTasaaplicada();
            c6 = constantesImpuesto.getValAmbientales();
        }

        // Ubica Valor recoleccion de basura segun Zona mirar domi_calculo = TASA RECOLECCIÓN DE BASURA            
        consulta = "60" + zona;
        basura = parametrosServicio.obtenerValorPorCodigoCalculo(consulta, "TASA RECOLECCIÓN DE BASURA");
        ba = parametrosServicio.tieneBasura(codigo);

        if (ba) {
            aPagar = ((valPredio.multiply(c5)).add(c2)).add(c3).add(c6).add((valPredio.multiply(c1)).multiply(c5)).add(basura);
            // Actualiza otros valores calculados
            predio.setValCem(c3);
            predio.setValBomberos((valPredio.multiply(c1)).multiply(c5));
            predio.setValEmision(c2);
            predio.setValBasura(basura);
            predio.setValAmbientales(c6);
            predio.setValImpuesto(valPredio.add(c5));
            predio.setValImppredial(aPagar);
            actualizarPredio(predio, sesion);

        }

        //insertarElementosArbolAvaluo("Raiz", null, nodo, this.raiz, null);
        return nodo;
    }
    
    private BigDecimal obtenerValoracionFondoRelativo(BigDecimal area, BigDecimal frente) {
        
        BigDecimal v1, v2;
        v1 = area.divide(frente, 4, RoundingMode.CEILING);
        v2 = frente.divide(v1, 4, RoundingMode.CEILING);

        return parametrosServicio.obtenerCOFF(v2, "FACTOR FRENTE FONDO");

    }

    private void insertarElementosArbolAvaluo(String descripcion, Object valor, List<AvaluoDto> hijos, List<AvaluoDto> raiz) {
        AvaluoDto nodoRaiz = new AvaluoDto();
        
        nodoRaiz.setDescripcion(descripcion.trim());
        nodoRaiz.setValor(valor);
        nodoRaiz.setHijos(hijos);
        //nodoRaiz.setValor2("prueba");
        raiz.add(nodoRaiz);
    }
    
}
