/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico.source;

/**
 *
 * @author israelavila
 */
public class BingMaps extends Source {
    public enum Style {
        ROAD ("Road"),
        AERIAL ("Aerial"),
        AERIAL_WITH_LABELS ("AerialWithLabels"),
        COLLINS_BART ("collinsBart"),
        ORDNANCE_SURVEY ("collinsBart");
        
        private final String style;
        
        private Style(String style) {
            this.style = style;
        }
        
        @Override
        public String toString() {
            return "'".concat(this.style).concat("'");
        }
    };
    
    private String key;
    private Style imagerySet;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Style getImagerySet() {
        return imagerySet;
    }

    public void setImagerySet(Style imagerySet) {
        this.imagerySet = imagerySet;
    }

}
