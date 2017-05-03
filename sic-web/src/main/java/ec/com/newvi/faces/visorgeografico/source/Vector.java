/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico.source;

/**
 *
 * @author israelavila
 */
public class Vector extends Source {
    public enum Format {
        GEOJSON("geojson");

        private final String format;

        private Format(String format) {
            this.format = format;
        }

        @Override
        public String toString() {
            return "'".concat(this.format).concat("'");
        }
    }
    
    private String url;
    private Format format;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
    
    
}
