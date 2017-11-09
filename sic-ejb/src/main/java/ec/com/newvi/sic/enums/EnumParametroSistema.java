/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author israelavila
 */
public enum EnumParametroSistema {

    // SISTEMA
    TITULO_SISTEMA("TITULO_SISTEMA", "Título del sistema", "SIGC", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    NOMBRE_SISTEMA("NOMBRE_SISTEMA", "Nombre del sistema", "Sistema de Información Geográfico Catastral", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    DIRECCION_VISOR_PREDIOS("DIRECCION_VISOR_PREDIOS", "Dirección del visor de predios", "http://localhost/catvisor/?predio=", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    DIRECCION_IMAGENES_SISTEMA("DIRECCION_IMAGENES_SISTEMA", "Dirección de imágenes del sistema", "/opt/sigc/config/imagenes", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    DIRECCION_IMAGENES_PREDIO("DIRECCION_IMAGENES_PREDIO", "Dirección de imágenes de los predios dentro del sistema operativo", "/opt/sigc/imagenes/", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    DIRECCION_SERVICIO_IMAGEN_PREDIO("DIRECCION_SERVICIO_IMAGEN_PREDIO", "Dirección del servicio para las imágenes del predio", "http://localhost:8080/geoserver/wms?LAYERS=nwi_catastro%3Abase_predios,nwi_catastro%3Aap031_via,nwi_catastro%3Ahe002_lote&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&STYLES=&FORMAT=image%2Fjpeg&SRS=EPSG%3A900913&WIDTH=533&HEIGHT=195&BBOX=", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    RUTA_UBICACION_REPORTES("RUTA_UBICACION_REPORTES", "Ruta de ubicación de los reportes del sistema", "/opt/sigc/reportes/", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    RUTA_UBICACION_IMAGENES_REPORTE("RUTA_UBICACION_IMAGENES_REPORTE", "Ruta de ubicación de imágenes de los reportes del sistema", "/opt/sigc/reportes/", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    IMAGEN_LOGO_MUNICIPIO_1("IMAGEN_LOGO_MUNICIPIO_1", "Ubicación de la imagen del primer logo del municipio", "[DIRECCION_IMAGENES_SISTEMA]/logo_1.png", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.IMAGEN, null),
    IMAGEN_LOGO_MUNICIPIO_2("IMAGEN_LOGO_MUNICIPIO_2", "Ubicación de la imagen del segundo logo del municipio", "[DIRECCION_IMAGENES_SISTEMA]/logo_2.png", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.IMAGEN, null),
    FORMATO_MONEDAS("FORMATO_MONEDAS", "Formato para la representación de las monedas en el sistema.", "$ ###,###.00", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.VALOR, null),
    NIVEL_ZONAS_HOMOGENEAS("NIVEL_ZONAS_HOMOGENEAS", "Nivel zonas homogeneas", "[Zonas Homogeneas]", EnumGrupoParametroSistema.DATOS_SISTEMA, EnumTipoParametro.LISTA, EnumZonasHomeneas.values()),
    // MUNICIPIO
    NOMBRE_COMPLETO_MUNICIPIO("NOMBRE_COMPLETO_MUNICIPIO", "Nombre completo del municipio", "[Municipio]", EnumGrupoParametroSistema.DATOS_MUNICIPIO, EnumTipoParametro.VALOR, null),
    NOMBRE_CORTO_MUNICIPIO("NOMBRE_CORTO_MUNICIPIO", "Nombre corto del municipio", "[Municipio]", EnumGrupoParametroSistema.DATOS_MUNICIPIO, EnumTipoParametro.VALOR, null);

    public enum EnumTipoParametro {
        VALOR,
        RUTA,
        DIRECCION_WEB,
        IMAGEN,
        LISTA
    }

    public enum EnumGrupoParametroSistema {
        DATOS_SISTEMA,
        DATOS_MUNICIPIO
    }

    private final String nombreParametro;
    private final String descripcionParametro;
    private final EnumGrupoParametroSistema grupoParametro;
    private final EnumTipoParametro tipoParametro;
    private final String valorPorDefecto;
    private final EnumZonasHomeneas[] zonasHomogeneas;

    public String getNombreParametro() {
        return nombreParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public EnumGrupoParametroSistema getGrupoParametro() {
        return grupoParametro;
    }

    public EnumTipoParametro getTipoParametro() {
        return tipoParametro;
    }

    public String getValorPorDefecto() {
        return valorPorDefecto;
    }

    public EnumZonasHomeneas[] getZonasHomogeneas() {
        return zonasHomogeneas;
    }
    
    private EnumParametroSistema(String nombreParametro, String descripcionParametro, String valorPorDefecto, EnumGrupoParametroSistema grupoParametro, EnumTipoParametro tipoParametro, EnumZonasHomeneas[] zonasHomogeneas) {
        this.nombreParametro = nombreParametro;
        this.descripcionParametro = descripcionParametro;
        this.valorPorDefecto = valorPorDefecto;
        this.grupoParametro = grupoParametro;
        this.tipoParametro = tipoParametro;
        this.zonasHomogeneas = zonasHomogeneas;
    }

}
