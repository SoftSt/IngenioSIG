/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

/**
 *
 * @author israelavila
 */
public enum ProjectionCode {
    EPSG_4326 ("EPSG:4326"),
    EPSG_3857 ("EPSG:3857");
    
    private final String code;
    
    private ProjectionCode(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return "'".concat(this.code).concat("'");
    }
}
