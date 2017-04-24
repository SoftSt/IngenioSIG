/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico.layer;

import ec.com.newvi.faces.visorgeografico.Base;
import java.math.BigDecimal;

/**
 *
 * @author israelavila
 */
public class Layer extends Base {
    private BigDecimal opacity;
    private Boolean visible;
    private Integer zIndex;
    private BigDecimal minResolution;    
    private BigDecimal maxResolution;

    public BigDecimal getOpacity() {
        return opacity;
    }

    public void setOpacity(BigDecimal opacity) {
        this.opacity = opacity;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public BigDecimal getMinResolution() {
        return minResolution;
    }

    public void setMinResolution(BigDecimal minResolution) {
        this.minResolution = minResolution;
    }

    public BigDecimal getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(BigDecimal maxResolution) {
        this.maxResolution = maxResolution;
    }
    
}
