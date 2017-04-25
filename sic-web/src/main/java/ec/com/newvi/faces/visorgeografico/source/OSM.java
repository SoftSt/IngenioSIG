/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico.source;

/**
 *
 * @author israelavila
 */
public class OSM extends Source {
    private Integer maxZoom;

    public OSM() {
        this.maxZoom = 19;
    }

    public Integer getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(Integer maxZoom) {
        this.maxZoom = maxZoom;
    }
    
}
