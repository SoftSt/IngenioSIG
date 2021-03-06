/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumParametroSistema.EnumGrupoParametroSistema;
import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumReporte;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.ParametroSistema;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Reporte;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author NEWVI
 */
@Local
@PermitAll
public interface ParametrosServicio {

    /*----------------------------------------------------- Parametros del Sistema ----------------------------------------------------*/
    /**
     * Genera un nuevo parámetro del sistema, de acuerdo a un objeto entregado.
     *
     * @param nuevoParametroSistema El nuevo parametro a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del parámetro generado.
     * @throws NewviExcepcion
     */
    public String generarNuevoParametroSistema(ParametroSistema nuevoParametroSistema, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un parámetro existente.
     *
     * @param parametroSistema El parámetro a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del parámetro actualizado
     * @throws NewviExcepcion
     */
    public String actualizarParametroSistema(ParametroSistema parametroSistema, SesionDto sesion) throws NewviExcepcion;

    /**
     * Guarda una imagen dado el flujo de bytes en una ubicación específica,
     * dado el parámetro creado/modificado
     *
     * @param parametroSistema El parámetro del sistema que contiene la dirección en donde se almacenará la imagen.
     * @param imagenEnBytes El stream de la imagen a guardar.
     * @param sesion Sesión que realiza la operación.
     * @return Nombre del archivo guardado.
     * @throws NewviExcepcion
     */
    public String guardarImagenParametroSistema(ParametroSistema parametroSistema, byte[] imagenEnBytes, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un parámetro dado una id
     *
     * @param codParametro Integer, código del parámetro a obtener
     * @return parámetro del sistema encontrado
     * @throws NewviExcepcion
     */
    public ParametroSistema seleccionarParametroSistema(Integer codParametro) throws NewviExcepcion;

    /**
     * Obtiene el listado de parámetros registrados en el sistema.
     *
     * @param sesion Sesión del usuario que realiza la consulta.
     * @return Listado de parámetros existentes.
     * @throws NewviExcepcion
     */
    public List<ParametroSistema> obtenerListaParametrosSistema(SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene el listado de parámetros registrados en el sistema filtrados por
     * el grupo al que pertenece el parámetro. Se puede enviar un valor de null
     * como parámetro para enviar todos los parámetros existentes.
     *
     * @param grupo Enumerado del grupo por el cual filtrar la lista de
     * parámetros
     * @param sesion Sesión del usuario que realiza la consulta
     * @return
     * @throws NewviExcepcion
     */
    public List<ParametroSistema> obtenerListaParametrosSistemaPorTipo(EnumGrupoParametroSistema grupo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene un paraámetro dado
     *
     * @param parametro Enumerado del parámetro a buscar
     * @param sesion Sesión del usuario que busca el parámetro
     * @return Parámetro encontrado en base de datos
     * @throws NewviExcepcion
     */
    public ParametroSistema obtenerParametroPorNombre(EnumParametroSistema parametro, SesionDto sesion) throws NewviExcepcion;

    /*----------------------------------------------------- Dominios ----------------------------------------------------*/
    /**
     * Generar un nuevo dominio, de acuerdo a un objeto entregado.
     *
     * @param nuevoDominio el nuevo dominio a ser ingresado.
     * @param sesion Sesion que realiza la operación
     * @return Codigo del nuevo dominio.
     * @throws NewviExcepcion
     */
    public String generaNuevoDominio(Dominios nuevoDominio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un dominio existente.
     *
     * @param dominio el dominio a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Codigo del dominio actualizado.
     * @throws NewviExcepcion
     */
    public String actualizarDominio(Dominios dominio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un dominio dado un id
     *
     * @param idDominio Integer, codigo del dominio a obtener.
     * @return dominio
     * @throws NewviExcepcion
     */
    public Dominios seleccionarDominio(Integer idDominio) throws NewviExcepcion;

    /**
     * Devuelve un listado de dominios
     *
     * @return Listado de dominios
     */
    public List<Dominios> consultarDominios();

    /**
     * Elimina el dominio dado.
     *
     * @param dominio El dominio a ser eliminado
     * @param sesion Sesion que realiza la operación
     * @return Codigo del dominio eliminado
     * @throws NewviExcepcion
     */
    public String eliminarDominio(Dominios dominio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un listados de dominios filtrado por grupos
     *
     * @return Listado de dominois filtrado por grupos
     */
    public List<Dominios> consultarGruposDominios();

    /**
     * Devuelve un listados de dominios dado un grupo
     *
     * @param grupo grupo enviado
     * @param relacion relacion enviada
     * @return Listado de dominios filtrado por grupo
     */
    public List<Dominios> consultarDominiosPorGrupo(String grupo, String relacion);

    /**
     * Devuelve el listado de hijos por padre
     *
     * @param dominio dominio donde se extrara los hijos
     * @return Listado de hijos
     */
    public List<Dominios> consultarHijos(Dominios dominio);

    /**
     * Devuelve un listado de dominios dto referente a un grupo
     *
     * @param grupo grupo al que pertenece los dominios
     * @param relacion relacion al que pertenece los dominios
     * @return listado de dominios dto
     */
    public List<DominioDto> listarDominiosDto(String grupo, String relacion);

    /**
     * Obtencion del COFF
     *
     * @param dominio dominio minimo y maximo del dominio
     * @param calculo tipo calculo
     * @return valor COFF
     */
    public BigDecimal obtenerCOFF(BigDecimal dominio, String calculo);

    /**
     * Valoracion de factores Terreno
     *
     * @param predio Predio a valorar
     * @param domiCalculo parametro por el cual será valorado
     * @return coeficiente del factor
     */
    public BigDecimal obtenerCoeficienteTerreno(Predios predio, String domiCalculo);

    /**
     * Calculo del Coeficiente de construcción
     *
     * @param piso Piso a valorar
     * @param domiCalculo parametro por el cual será calculado
     * @return coeficiente del detalle de contrucción
     */
    public BigDecimal obtenerCoeficienteConstruccion(Pisos piso, String domiCalculo);

    /**
     * Obtien los detalles del piso
     *
     * @param detalle codigo del detalle de piso a consultar
     * @param domiCalculo parametro por el cual será calculado
     * @return objeto de pisos
     */
    public BigDecimal obtenerCoeficienteDetallePiso(PisoDetalle detalle, String domiCalculo);

    /**
     * Calculo del valor de depreciación
     *
     * @param dominio dominio minimo y maximo del dominio
     * @param domiDescripcion parametro por el cual sera calculado
     * @return valor de VDEPRE
     */
    public BigDecimal obtenerValorDepreciacion(BigDecimal dominio, String domiDescripcion);

    /**
     * Calculo del valor del terreno
     *
     * @param domiCodigo codigo por el cual se buscara el valor
     * @return valor de VTERRENO
     */
    public BigDecimal obtenerValorPorCodigoCalculo(String domiCodigo, String domiCalculo);

    /**
     * Obtiene el valor de un coeficiente por codigo
     *
     * @param domiCodigo codigo del dominio
     * @return coefiente dominio
     */
    public BigDecimal obtenerTotalCoeficienteDominiosPorCodigo(String domiCodigo);

    /*-----------------------------------------------------------------------Constantes Impuestos------------------------------------------------------------------------------*/
    /**
     * Devuelve una lista de constantes de impuestos
     *
     * @param stsTipo el tipo por el cual se buscará
     * @return lista de constantes impuestos
     */
    public List<ConstantesImpuestos> obtenerConstantesImpuestosPorTipo(String stsTipo);

    /**
     * Genera una nueva constante de impuesto
     *
     * @param nuevaConstantesImpuestos nueva constante
     * @param sesion Sesion que realiza la operación
     * @return El codigo de la constante
     * @throws NewviExcepcion
     */
    public String generaNuevoConstanteImpuesto(ConstantesImpuestos nuevaConstantesImpuestos, SesionDto sesion) throws NewviExcepcion;

    /**
     * *
     * Actualiza la constante de impuesto
     *
     * @param constantesImpuestos constante a actualizar
     * @param sesion Sesion que realiza la operación
     * @return el codigo de la constante actualizada
     * @throws NewviExcepcion
     */
    public String actualizarConstanteImpuesto(ConstantesImpuestos constantesImpuestos, SesionDto sesion) throws NewviExcepcion;

    /**
     * Selecciona una constante de impuesto
     *
     * @param codConstantesimpuestos codigo de la constante de impuesto
     * @return la constante seleccionada
     * @throws NewviExcepcion
     */
    public ConstantesImpuestos seleccionarConstanteImpuestos(Integer codConstantesimpuestos) throws NewviExcepcion;

    /**
     * Devuelve el listado de constante
     *
     * @return Lista constantes
     */
    public List<ConstantesImpuestos> consultarConstantesImpuestos();

    /**
     * Elimina la constante de impuesto
     *
     * @param constantesImpuestos la constante a ser eliminada
     * @param sesion Sesion que realiza la operación
     * @return el nombre de la constante eliminada
     * @throws NewviExcepcion
     */
    public String eliminarConstanteImpuesto(ConstantesImpuestos constantesImpuestos, SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Reportes------------------------------------------------------------*/
    /**
     * Obtiene un reporte dado un enumerado del reporte a buscar.
     *
     * @param nombreReporte Enumerado del reporte a buscar
     * @param sesion Sesión que realiza la consulta
     * @return Reporte encontrado
     * @throws NewviExcepcion
     */
    public Reporte obtenerReporte(EnumReporte nombreReporte, SesionDto sesion) throws NewviExcepcion;

}
