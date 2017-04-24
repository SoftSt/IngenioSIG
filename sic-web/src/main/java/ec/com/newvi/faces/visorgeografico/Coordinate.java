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
public class Coordinate {
    private BigDecimal x;
    private BigDecimal y;

    public Coordinate() {
        this.x = BigDecimal.ZERO;
        this.y = BigDecimal.ZERO;
    }

    public Coordinate(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "[".concat(this.x.toString()).concat(",").concat(this.y.toString()).concat("]");
    }

}
