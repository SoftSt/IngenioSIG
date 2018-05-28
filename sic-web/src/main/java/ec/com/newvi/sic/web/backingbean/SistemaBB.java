/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.backingbean;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import ec.com.newvi.sic.servicios.SeguridadesServicio;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import ec.com.newvi.sic.web.enums.EnumEtiquetas;
import ec.com.newvi.sic.web.enums.EnumFuncionalidad;
import ec.com.newvi.sic.web.sesion.SesionBean;
import ec.com.newvi.sic.web.sesion.SistemaBean;
import javax.ejb.EJB;
import javax.inject.Inject;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author israelavila
 */
public abstract class SistemaBB {

    @Inject
    protected SesionBean sesionBean;

    @Inject
    protected SistemaBean sistemaBean;

    @EJB
    protected ParametrosServicio parametrosServicio;

    @EJB
    protected SeguridadesServicio seguridadesServicio;

    protected Funcionalidades funcionalidadActual;

    protected EnumEtiquetas tituloPantalla;
    protected String tituloPantallaXML;
    protected EnumEtiquetas iconoPantalla;
    protected String iconoPantallaXML;
    protected EnumEtiquetas descripcionPantalla;
    protected String descripcionPantallaXML;

    public EnumEtiquetas getTituloPantalla() {
        return tituloPantalla;
    }

    public EnumEtiquetas getIconoPantalla() {
        return iconoPantalla;
    }

    public EnumEtiquetas getDescripcionPantalla() {
        return descripcionPantalla;
    }

    public String getTituloPantallaXML() {
        return tituloPantallaXML;
    }

    public String getIconoPantallaXML() {
        return iconoPantallaXML;
    }

    public String getDescripcionPantallaXML() {
        return descripcionPantallaXML;
    }

    protected void establecerTitulo(EnumEtiquetas titulo, EnumEtiquetas icono, EnumEtiquetas descripcion) {
        this.tituloPantalla = titulo;
        this.iconoPantalla = icono;
        this.descripcionPantalla = descripcion;
    }

    protected void establecerTituloXML() {

        try {

            File archivo = new File("/opt/sigc/config/pantallas/prueba.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            //NodeList nList = doc.getElementsByTagName("pantalla");
            NodeList listaEmpleados = document.getElementsByTagName("pantalla");
            NodeList listaEmpleados2 = document.getChildNodes();
            //NodeList listaEmpleados2 = document.get
            NodeList node = document.getChildNodes();
             
            for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
                Node node2 = listaEmpleados.item(temp);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) node2;

                    this.tituloPantallaXML = eElement.getAttribute("titulo");
                    this.iconoPantallaXML = eElement.getAttribute("icono");
                    this.descripcionPantallaXML = eElement.getAttribute("descripcion");
                }
            }
        } catch (Exception e) {
        }

    }

    protected void obtenerFuncionalidadActual(EnumFuncionalidad funcionalidad) {
        try {
            sistemaBean.setFuncionalidadActual(seguridadesServicio.obtenerFuncionalidadPorNombre(funcionalidad.getNombreFuncionalidad(), sesionBean.getSesion()));
        } catch (NewviExcepcion ex) {
            LoggerNewvi.getLogNewvi(this.getClass()).error(EnumNewviExcepciones.ERR455, ex, sesionBean.getSesion());
        }
    }

}
