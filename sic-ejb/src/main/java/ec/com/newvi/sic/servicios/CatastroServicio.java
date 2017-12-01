/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios;

import ec.com.newvi.sic.dto.AvaluoDto;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.DetallesAvaluo;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.modelo.FechaAvaluo;
import ec.com.newvi.sic.modelo.Fotos;
import ec.com.newvi.sic.modelo.LogPredio;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Servicios;
import ec.com.newvi.sic.modelo.Terreno;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;

/**
 *
 * @author Andrés
 */
@Local
@PermitAll
public interface CatastroServicio {

    /*------------------------------------------------------------Predios------------------------------------------------------------*/
    /**
     * Genera un nuevo Predio, de acuerdo a un objeto entregado.
     *
     * @param nuevoPredio El nuevo Predio a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Predio.
     * @throws NewviExcepcion
     */
    public String generarNuevoPredio(Predios nuevoPredio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Predio existente.
     *
     * @param predio El Predio a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPredio(Predios predio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un predio dado un id
     *
     * @param idPredio Integer, código del predio a obtener
     * @return predio
     * @throws NewviExcepcion
     */
    public Predios seleccionarPredio(Integer idPredio) throws NewviExcepcion;

    /**
     * Devuelve un listado de Predios.
     *
     * @return Listado de Predios
     */
    public List<Predios> consultarPredios();

    /**
     * Elimina un predios dado
     *
     * @param predio El predio a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPredio(Predios predio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Genera un log con las acciones realizadas en el predio
     *
     * @param predio El predio a verificar
     * @return El log del predio
     * @throws NewviExcepcion
     */
    public String generarLogPredio(Predios predio) throws NewviExcepcion;
    /**
     * actualizará los valores de zona sector manzana y codpresio
     * @param predioActualizable predio en cual va ser actualizado
     * @param sesion sesion que realizará la accion
     * @return el predio actualizado
     * @throws NewviExcepcion 
     */
    public Predios actualizarValoresUbicacion(Predios predioActualizable, SesionDto sesion) throws NewviExcepcion;
    /**
     * Obtiene el número total de predios
     * @return número total de predios
     */
    public Integer obtenerNumeroPredios();

    /*------------------------------------------------------------Bloques------------------------------------------------------------*/
    /**
     * Genera un nuevo Bloque, de acuerdo a un objeto entregado.
     *
     * @param nuevoBloque El nuevo Bloque a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Bloque.
     * @throws NewviExcepcion
     */
    public String generarNuevoBloque(Bloques nuevoBloque, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Bloque existente.
     *
     * @param bloque El Bloque a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio actualizado
     * @throws NewviExcepcion
     */
    public String actualizarBloque(Bloques bloque, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un predio dado un id
     *
     * @param idBloque Integer, código del predio a obtener
     * @return predio
     * @throws NewviExcepcion
     */
    public Bloques seleccionarBloque(Integer idBloque) throws NewviExcepcion;

    /**
     * Devuelve un listado de Bloques. obtenerAvaluoPredio
     *
     * @return Listado de Bloques
     */
    public List<Bloques> consultarBloques();

    /**
     * Elimina un predios dado
     *
     * @param bloque El predio a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del predio eliminado
     * @throws NewviExcepcion
     */
    public String eliminarBloque(Bloques bloque, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un listado de bloques por un codigo catastral
     *
     * @param codCatastral codigo del predio por el cual vamos a buscar los
     * bloques
     * @return Listado de bos filtrados por codigo catastrak
     */
    public List<Bloques> buscarBloquesPorCodigoCatastral(Integer codCatastral);

    /**
     * Obtiene el avalúo de un bloque, dado un bloque y el promedio de factores
     * del predio al que pertenece
     *
     * @param bloque Bloque al que se va a calcular el avalúo
     * @param promedioFactores Promedio de los factores para el cálculo
     * @param sesion Usuario que realiza la consulta
     * @param formatoMonedaSistema Formato de moneda del sistema
     * @param dominios Listado de dominios
     * @return Objeto AvaluoDto que contiene el avalúo del bloque
     * @throws NewviExcepcion
     */
    public List<AvaluoDto> obtenerAvaluoBloque(Bloques bloque, BigDecimal promedioFactores, List<Dominios> dominios,String formatoMonedaSistema, SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Pisos------------------------------------------------------------*/
    /**
     * Genera un nuevo Piso, de acuerdo a un objeto entregado.
     *
     * @param nuevoPiso El nuevo Piso a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Piso.
     * @throws NewviExcepcion
     */
    public String generarNuevoPiso(Pisos nuevoPiso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Piso existente.
     *
     * @param piso El Piso a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del piso actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPiso(Pisos piso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un piso dado un id
     *
     * @param idPiso Integer, código del piso a obtener
     * @return piso
     * @throws NewviExcepcion
     */
    public Pisos seleccionarPiso(Integer idPiso) throws NewviExcepcion;

    /**
     * Devuelve un listado de Pisos.
     *
     * @return Listado de Pisos
     */
    public List<Pisos> consultarPisos();

    /**
     * Elimina un pisos dado
     *
     * @param piso El piso a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del piso eliminado
     * @throws NewviExcepcion
     */
    public String eliminarPiso(Pisos piso, SesionDto sesion) throws NewviExcepcion;

    /**
     * Obtiene los datos del piso por bloque
     *
     * @param codBloque codigo del bloque
     * @return Piso
     */
    public Object[] obtenerDatosPisoPorBloque(Integer codBloque);

    /**
     * Obtiene el listado de pisos por bloque
     *
     * @param codBloques codigo del bloque
     * @return Lista de pisos
     */
    public Pisos buscarPisosPorCodigoBloque(Integer codBloques);

    /**
     *
     * @param piso
     * @param promedioFactores
     * @param sesion
     * @param formatoMonedaSistema Formato de moneda del sistema
     * @param dominios
     * @return
     * @throws NewviExcepcion
     */
    public List<AvaluoDto> obtenerAvaluoPisos(Pisos piso, BigDecimal promedioFactores, List<Dominios> dominios,String formatoMonedaSistema, SesionDto sesion) throws NewviExcepcion;
    /**
     * Devuelve un listado de pisos filtrado por su estado
     * @return Listado de pisos filtrado por su estado
     */
    public List<Pisos> consultarStsEstadoPiso();

    /*------------------------------------------------------------PisoDetalle------------------------------------------------------------*/
    /**
     * Actualiza el detalle de piso existente.
     *
     * @param pisoDetalle El detalle del piso a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del detalle del piso actualizado
     * @throws NewviExcepcion
     */
    public String actualizarPisoDetalle(PisoDetalle pisoDetalle, SesionDto sesion) throws NewviExcepcion;

    /**
     * Generar un nuevo piso detalle
     *
     * @param nuevoPisoDetalle nuevo detalle del piso
     * @param sesion Sesion que realiza la operación
     * @return Nombre del detalle del piso actualizado
     * @throws NewviExcepcion
     */
    public String generarNuevoPisoDetalle(PisoDetalle nuevoPisoDetalle, SesionDto sesion) throws NewviExcepcion;
    /**
     * Devuelve un detalle de piso dado por id
     * @param codDetallePiso codigo del detalle de piso 
     * @return PisoDetalle
     * @throws NewviExcepcion 
     */
    public PisoDetalle seleccionarDetallePiso(Integer codDetallePiso) throws NewviExcepcion;
    /**
     * Devuelve un listado de detalles pisos filtrado por su estado
     * @return Listado de detalles pisos filtrado por su estado
     */
    public List<PisoDetalle> consultarStsEstadoDetallePiso();

    /*------------------------------------------------------------Terreno------------------------------------------------------------*/
    /**
     * Genera un nuevo Terreno, de acuerdo a un objeto entregado.
     *
     * @param nuevoTerreno El nuevo Terreno a ser ingresado
     * @param sesion Sesion que realiza la operación
     * @return Nombre del nuevo Terreno.
     * @throws NewviExcepcion
     */
    public String generarNuevoTerreno(Terreno nuevoTerreno, SesionDto sesion) throws NewviExcepcion;

    /**
     * Actualiza un Terreno existente.
     *
     * @param terreno El Terreno a actualizar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del terreno actualizado
     * @throws NewviExcepcion
     */
    public String actualizarTerreno(Terreno terreno, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un terreno dado un id
     *
     * @param idTerreno Integer, código del terreno a obtener
     * @return terreno
     * @throws NewviExcepcion
     */
    public Terreno seleccionarTerreno(Integer idTerreno) throws NewviExcepcion;

    /**
     * Devuelve un listado de Terreno.
     *
     * @return Listado de Terreno
     */
    public List<Terreno> consultarTerreno();

    /**
     * Elimina un terreno dado
     *
     * @param terreno El terreno a eliminar
     * @param sesion Sesion que realiza la operación
     * @return Nombre del terreno eliminado
     * @throws NewviExcepcion
     */
    public String eliminarTerreno(Terreno terreno, SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Fotos------------------------------------------------------------*/
    /**
     * Consulta todas las imagenes que tiene un predio
     *
     * @param codCatastral codigo del predio asignado
     * @return lista de fotos del predio
     */
    public List<Fotos> consultarFotosPorPredio(int codCatastral);

    /*------------------------------------------------------------Avalúos------------------------------------------------------------*/
    /**
     * Devuelve el avalúo del predio, dado un predio.
     *
     * @param predio Predio a calcular el avalúo
     * @param sesion Usuario que genera el avalúo
     * @param dominios lista de dominios
     * @param formatoMonedaSistema formato de moneda
     * @return Listado de tipo AvaluoDto que tiene los valores jerarquizados.
     * @throws NewviExcepcion
     */
    public List<AvaluoDto> obtenerAvaluoPredio(Predios predio, List<Dominios> dominios, String formatoMonedaSistema, SesionDto sesion) throws NewviExcepcion;

    /**
     * Genera la simulacion del calculo de los avaluos
     *
     * @param predio
     * @param sesion
     * @throws NewviExcepcion
     */
    //public void obtenerSimulacionAvaluoPredio(Predios predio, SesionDto sesion) throws NewviExcepcion;
    /**
     * Almacena el arbol en la tabla cat_cat_detallesAvaluo
     *
     * @param nodo lista de nodos a registrar
     * @param predio dato para el registro
     * @param sesion quien realiza la acción
     * @throws NewviExcepcion
     */
    public void registrarArbol(List<AvaluoDto> nodo, Predios predio, SesionDto sesion) throws NewviExcepcion;
    
    /**
     * Obtiene el valor del elemento de avaluo por descripcion
     * @param listaElementos lista de dto a ser buscado
     * @param descripcion parametro por el cual se va a buscar
     * @param formatoMonedaSistema parametro por el cual se va a buscar
     * @return el valor del elemento del avaluo
     * @throws NewviExcepcion 
     */
    public BigDecimal obtenerValorElementoAvaluoPorDescripcion(List<AvaluoDto> listaElementos, String descripcion, String formatoMonedaSistema) throws NewviExcepcion;

    /**
     * Obtiene el elemento de avaluo por descripcion
     * @param listaElementos lista de dto a ser buscado
     * @param descripcion parametro por el cual se va a buscar
     * @return el valor del elemento del avaluo
     * @throws NewviExcepcion 
     */
    public AvaluoDto obtenerElementoAvaluoPorDescripcion(List<AvaluoDto> listaElementos, String descripcion) throws NewviExcepcion;
        
    /*------------------------------------------------------------FechaAvaluo------------------------------------------------------------*/
    /**
     * Genera una nueva FechaAvaluo
     *
     * @param nuevoFechaAvaluo nueva Fecha
     * @param sesion Usuario que genera la fecha avalúo
     * @return id de fechaAvaluo
     * @throws NewviExcepcion
     */
    public FechaAvaluo generarNuevaFechaAvaluo(FechaAvaluo nuevoFechaAvaluo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Lista las fechas de los avaluos
     *
     * @return listado de fechas de avaluos
     */
    public List<FechaAvaluo> consultarFechaAvaluos();

    /*------------------------------------------------------------Avaluo------------------------------------------------------------*/
    /**
     * Genera un nuevo Avaluo
     *
     * @param nuevoAvaluo nuevo Avaluo
     * @param sesion Usuario que genera el avalúo
     * @return id de avaluo
     * @throws NewviExcepcion
     */
    public Integer generarNuevoAvaluo(Avaluo nuevoAvaluo, SesionDto sesion) throws NewviExcepcion;
    /**
     * Actualiza el avaluo entregado
     * @param avaluo objeto a ser actualizado
     * @param sesion Sesión que realiza la acción
     * @return código del avaluo actualizado
     * @throws NewviExcepcion 
     */
    public String actualizarAvaluo(Avaluo avaluo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Lista los avaluos por determinada fecha
     *
     * @param fecavFechaavaluo fecha por la que se filtraráa
     * @return lista de avaluos
     */
    public List<Avaluo> consultarAvaluos(Date fecavFechaavaluo);

    /**
     * Devuelve el avaluo dado por un id
     *
     * @param avalId id del avaluo
     * @return avaluo
     * @throws NewviExcepcion
     */
    public Avaluo seleccionarAvaluo(Integer avalId) throws NewviExcepcion;

    /**
     * Lista los avaluos actuales
     *
     * @return lista de avaluos
     */
    public List<Avaluo> consultarListaAvaluosActuales();

    /**
     * Lista los avaluos por una fecha
     *
     * @param fechaAvaluo fecha por la que se filtrará
     * @return lista de avaluos
     */
    public List<Avaluo> consultarListaAvaluosPorFecha(String fechaAvaluo);
    /**
     * Devuelve un avaluo por código catastral y fecha del avaluo
     * @param codCatrastal código catastral por el cual se buscará
     * @param fecavId id de la fecha por la cual se buscará
     * @return Avaluo
     */
    public Avaluo consultarAvaluoPorCodCatastralYFechaAvaluo(Predios codCatrastal, FechaAvaluo fecavId);

    /*------------------------------------------------------------Detalles Avaluo------------------------------------------------------------*/
    /**
     * Lista los detalles de avaluo
     *
     * @return lista de detalles de avaluo
     */
    public List<DetallesAvaluo> consultarListaDetallesAvaluo(Integer codCatastral);

    /**
     * Genera un nuevo detalle de avaluo
     *
     * @param nuevoDetalleAvaluo nuevo detalle avaluo
     * @param sesion Usuario que genera el detalle de avalúo
     * @return el codigo del detalle avaluo generado
     * @throws NewviExcepcion
     */
    public Integer generarNuevoDetalleAvaluo(DetallesAvaluo nuevoDetalleAvaluo, SesionDto sesion) throws NewviExcepcion;

    /**
     * Lista lista de hijos de detallesAvaluo
     *
     * @param detallesAvaluo objeto por el cual se buscara sus hijos
     * @return lista de detalles avaluo
     */
    public List<DetallesAvaluo> consultarHijosDetallesAvaluo(DetallesAvaluo detallesAvaluo);

    /**
     * Lista el avaluoDto
     *
     * @param relacion relacion por la cual se filtra
     * @param predio predio por el cual se filtrara
     * @return lista de AvaluoDto
     */
    public List<AvaluoDto> listarAvaluoDto(String relacion, Predios predio);

    /**
     * Consulta el padre del detalle
     *
     * @param predio predio por filtrar
     * @param relacion relacion por filtrar
     * @return detalleAvaluo padre
     */
    public DetallesAvaluo consultarPadre(Predios predio, String relacion);

    /**
     * Borra los detalles de avaluo
     *
     * @param predio parametro para borrar
     */
    public void eliminarDetallesPorPredio(Predios predio);

    /*------------------------------------------------------------LogPredio------------------------------------------------------------*/
    /**
     * Genera un nuevo Predio, de acuerdo a un objeto entregado.
     *
     * @param nuevoLogPredio El nuevo LogPredio a ser ingresado
     * @param sesion que realiza la operación
     * @return Nombre del nuevo Predio.
     * @throws NewviExcepcion
     */
    public String generarNuevoLogPredio(LogPredio nuevoLogPredio, SesionDto sesion) throws NewviExcepcion;

    /**
     *
     * @param logPredio actualza el logPredio existente
     * @param sesion que realiza la operación
     * @return Nombre del predio actualizado
     * @throws NewviExcepcion
     */
    public String actualizarLogPredio(LogPredio logPredio, SesionDto sesion) throws NewviExcepcion;

    /**
     * Devuelve un logPredio dado por un id
     *
     * @param codLogPredio, código del logPredio a obtener
     * @return logPredio
     * @throws NewviExcepcion
     */
    public LogPredio seleccionarLogPredio(Integer codLogPredio) throws NewviExcepcion;

    /**
     * Lista todos los LogPredios
     *
     * @return lista de logPredio
     */
    public List<LogPredio> consultarLogPredio();

    /**
     * Elimina el logPredio dado
     *
     * @param logPredio el logPredio a eliminar
     * @param sesion que realiza la operación
     * @return Nombre del logPredio eliminado
     * @throws NewviExcepcion
     */
    public String eliminarLogPredio(LogPredio logPredio, SesionDto sesion) throws NewviExcepcion;

    /*------------------------------------------------------------Servicios------------------------------------------------------------*/
    /**
     * Devuelve un servicio dado por un id
     *
     * @param codServicio codigo del servicio a ser buscado
     * @return servicio
     * @throws NewviExcepcion
     */
    public Servicios seleccionarServicio(Integer codServicio) throws NewviExcepcion;

    /**
     * Genera un log con las acciones realizadas en el servicio
     *
     * @param servicio El servicioa verificar
     * @return El log del predio
     * @throws NewviExcepcion
     */
    public String generarLogServicios(Servicios servicio) throws NewviExcepcion;
    /**
     * Actualiza el servicio indicado
     * @param servicio entidad a ser actualizada
     * @param sesion Sesion que realiza la operación
     * @return codigo del servicio actualizado
     * @throws NewviExcepcion 
     */
    public String actualizarServicio(Servicios servicio, SesionDto sesion) throws NewviExcepcion;
    /**
     * Se agregará un nuevo servicio
     * @param nuevoServicio servicio a ser agregado
     * @param sesion Sesion que realiza la operación
     * @return codigo del servicio agregado
     * @throws NewviExcepcion 
     */
    public String generarNuevoServicio(Servicios nuevoServicio, SesionDto sesion) throws NewviExcepcion;
}
