                /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.DominioDto;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.ConstantesImpuestos;
import ec.com.newvi.sic.modelo.Dominios;
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
    
    /**
     * Generar un nuevo dominio, de acuerdo a un objeto entregado.
     * @param nuevoDominio el nuevo dominio a ser ingresado.
     * @param sesion Sesion que realiza la operación
     * @return Codigo del nuevo dominio.
     * @throws NewviExcepcion 
     */
    public String generaNuevoDominio(Dominios nuevoDominio, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Actualiza un dominio existente.
     * @param dominio el dominio a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Codigo del dominio actualizado.
     * @throws NewviExcepcion 
     */
    public String actualizarDominio (Dominios dominio, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve un dominio dado un id
     * @param idDominio Integer, codigo del dominio a obtener.
     * @return dominio
     * @throws NewviExcepcion 
     */
    public Dominios seleccionarDominio (Integer idDominio) throws NewviExcepcion;
    
    /**
     * Devuelve un listado de dominios
     * @return Listado de dominios
     */
    public List<Dominios> consultarDominios();
    
    /**
     * Elimina el dominio dado.
     * @param dominio El dominio a ser eliminado
     * @param sesion Sesion que realiza la operación
     * @return Codigo del dominio eliminado
     * @throws NewviExcepcion 
     */
    public String eliminarDominio (Dominios dominio, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Devuelve un listados de dominios filtrado por grupos
     * @return Listado de dominois filtrado por grupos
     */
    public List<Dominios> consultarGruposDominios();
    /**
     * Devuelve un listados de dominios dado un grupo
     * @param grupo grupo enviado
     * @param relacion relacion enviada
     * @return Listado de dominios filtrado por grupo
     */
    public List<Dominios> consultarDominiosPorGrupo(String grupo, String relacion);

    /**
     * Devuelve el listado de hijos por padre
     * @param dominio dominio donde se extrara los hijos
     * @return Listado de hijos
     */
    public List<Dominios> consultarHijos(Dominios dominio);
    
    /**
     * Devuelve un listado de dominios dto referente a un grupo
     * @param grupo grupo al que pertenece los dominios
     * @param relacion relacion al que pertenece los dominios
     * @return listado de dominios dto
     */
    public List<DominioDto> listarDominiosDto(String grupo, String relacion);
    
    /**
     * Obtencion del COFF
     * @param dominio dominio minimo y maximo del dominio
     * @param calculo tipo calculo
     * @return valor COFF
     */
    public BigDecimal obtenerCOFF(BigDecimal dominio, String calculo);
    /**
     * Valoracion de factores
     * @param idPredio codigo del predio a valorar
     * @param domiCalculo parametro por el cual será valorado
     * @return coeficiente del factor
     */
    
    public BigDecimal obtenerValor(Integer idPredio, String domiCalculo);
    /**
     * Calculo del Detalle de construcción
     * @param codPisos codigo del piso a valorar
     * @param domiCalculo parametro por el cual será calculado
     * @return coeficiente del detalle de contrucción
     */
    public BigDecimal obtenerDetalleContruccion(Integer codPisos, String domiCalculo);
    /**
     * Obtien los detalles del piso
     * @param codPisos codigo del piso a consultar
     * @param domiCalculo parametro por el cual será calculado
     * @return objeto de pisos
     */
    public Object[] obtenerDetallesPiso(Integer codPisos, String domiCalculo);
    /**
     * Calculo del valor de depreciación
     * @param dominio dominio minimo y maximo del dominio
     * @param domiDescripcion parametro por el cual sera calculado
     * @return  valor de VDEPRE
     */
    public BigDecimal obtenerValorDepreciacion(BigDecimal dominio, String domiDescripcion);
    /**
     * Calculo del valor del terreno
     * @param domiCodigo codigo por el cual se buscara el valor
     * @return valor de VTERRENO
     */
    public BigDecimal obtenerValorPorCodigoCalculo(String domiCodigo, String domiCalculo);
    
    /**
     * Obtiene el valor de un coeficiente por codigo
     * @param domiCodigo codigo del dominio
     * @return coefiente dominio
     */
    public BigDecimal obtenerValorPorCodigo(String domiCodigo);
    /**
     * Indica si tiene basura
     * @param codCatastral codigo del predio
     * @return true si si tiene basura
     */
    public Boolean tieneBasura(Integer codCatastral);
    
    /*-----------------------------------------------------------------------Constantes Impuestos------------------------------------------------------------------------------*/
    /**
     * Devuelve una lista de constantes de impuestos
     * @param stsTipo el tipo por el cual se buscará
     * @return lista de constantes impuestos
     */
    public List<ConstantesImpuestos> obtenerConstantesImpuestosPorTipo(String stsTipo);
    
}
