/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.componente.reporte;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.jasperreports.engine.JRException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 *
 * @author israelavila
 */
public class Reporte {

    private String archivoNombre;
    private String archivoRuta;
    private String archivoExtension;
    private String archivoJasperNombreRuta;
    private String xpath;
    private Map<String, Object> params;
    private ReporteGenerador.FormatoReporte mimeType;
    private String titulo;
    private String subtitulo;
    private String xml;
    private Date fecha;
    protected byte[] datos;
    protected ReporteGenerador generador;

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoExtension() {
        return archivoExtension;
    }

    public void setArchivoExtension(String archivoExtension) {
        this.archivoExtension = archivoExtension;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public ReporteGenerador.FormatoReporte getMimeType() {
        return mimeType;
    }

    public void setMimeType(ReporteGenerador.FormatoReporte mimeType) {
        this.mimeType = mimeType;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public Date getFecha() {
        return (Date) fecha.clone();
    }

    public void setFecha(Date fecha) {
        this.fecha = (Date) fecha.clone();
    }

    public String getFechaString() {
        // TODO enviar fecha con formato aaaa/mm/dd
        return fecha.toString();
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public String getArchivoJasperNombreRuta() {
        return archivoJasperNombreRuta;
    }

    public void setArchivoJasperNombreRuta(String archivoJasperNombreRuta) {
        this.archivoJasperNombreRuta = archivoJasperNombreRuta;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public Reporte(ReporteGenerador.FormatoReporte formatoReporte, List informacionReporte, Map<String, Class> paramsXml, String nombreJasper, String xPath, Map<String, Object> adicionalesParams) throws NewviExcepcion {
        this.mimeType = formatoReporte;
        this.archivoJasperNombreRuta = nombreJasper;
        this.xpath = xPath;
        this.params = new HashMap<>();
        if (ReporteGenerador.FormatoReporte.XLSX.equals(this.mimeType)) {
            this.archivoExtension = "xlsx";
        } else {
            this.archivoExtension = "pdf";
        }
        this.xml = ReporteGenerador.generarXml(paramsXml, informacionReporte);
        this.procesarParametrosGenerales();
        this.procesarParametrosReporte(adicionalesParams);
        this.procesarImpresion();
    }

    private void procesarParametrosGenerales() {
        String rutaReporte = "/opt/";
        this.params.put("ARCHIVO_REPORTE", rutaReporte);
        this.params.put("DATASOURCE_EXPRESSION", this.xpath);
        Locale locale = new Locale("es", "US");
        this.params.put("LOCALE", locale);
        TimeZone timeZone = TimeZone.getTimeZone("America/Guayaquil");
        this.params.put("REPORT_TIME_ZONE", timeZone);
        this.params.put("NOMBRE_SISTEMA", "SICG");
        this.params.put("NOMBRE_USUARIO", "USUARIO");
        this.params.put("RUTA_IMAGENES", "/opt/");
    }

    private void procesarParametrosReporte(Map<String, Object> adicionalesParams) {
        this.params.putAll(adicionalesParams);
    }

    private void procesarImpresion() throws NewviExcepcion {
        try {
            if (!this.xml.startsWith("<?xml")) {
                String xmlHeader = "<?xml version=\"1.0\" encoding=\"" + "ISO-8859-1" + "\" ?>\n";
                this.xml = xmlHeader + this.xml;
            }
            this.datos = ReporteGenerador.generarReporte(new FileInputStream(this.archivoJasperNombreRuta), Reporte.doc2bytes(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(this.xml)))), ReporteGenerador.FormatoReporte.PDF, this.params);
        } catch (Exception e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000);
        }
    }
    
    public static byte[] doc2bytes(Node node) {
        try {
            Source source = new DOMSource(node);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            return out.toByteArray();
        } catch (TransformerException e) {
        }
        return null;
    }
}
