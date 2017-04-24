/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import ec.com.newvi.faces.visorgeografico.Base;
import java.math.BigDecimal;

/**
 *
 * @author israelavila
 */
public class View extends Base {
    
    private Coordinate center;
    private ProjectionCode projection;
    private BigDecimal zoom;

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }
    
    public BigDecimal getZoom() {
        return zoom;
    }

    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

    public ProjectionCode getProjection() {
        return projection;
    }

    public void setProjection(ProjectionCode projection) {
        this.projection = projection;
    }
    
}
