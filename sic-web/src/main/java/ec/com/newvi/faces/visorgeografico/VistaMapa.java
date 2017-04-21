/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import java.math.BigDecimal;

/**
 *
 * @author israelavila
 */
public class VistaMapa extends BaseOpenLayers {
    
    private BigDecimal centroX;
    private BigDecimal centroY;
    private String codigoSRS;
    private BigDecimal zoom;

    public BigDecimal getCentroX() {
        return centroX;
    }

    public void setCentroX(BigDecimal centroX) {
        this.centroX = centroX;
    }

    public BigDecimal getCentroY() {
        return centroY;
    }

    public void setCentroY(BigDecimal centroY) {
        this.centroY = centroY;
    }

    public String getCodigoSRS() {
        return codigoSRS;
    }

    public void setCodigoSRS(String codigoSRS) {
        this.codigoSRS = codigoSRS;
    }

    public BigDecimal getZoom() {
        return zoom;
    }

    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

    @Override
    public String generarJSON() {
        return "{\"posx\":".concat(this.centroX.toString())
                .concat(",\"posy\":").concat(this.centroY.toString())
                .concat(",\"srs\":\"").concat(this.codigoSRS)
                .concat("\",\"zoom\":").concat(this.zoom.toString())
                .concat("}");
    }
    
}
