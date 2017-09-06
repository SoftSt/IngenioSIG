/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.modelo;

import ec.com.newvi.sic.geo.enums.EnumTipoPredio;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.postgresql.geometric.PGpolygon;

/**
 *
 * @author israelavila
 */
@Entity
@Table(name = "he002_lote", schema = "public")
public class GeoPredio implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    @Id
    @SequenceGenerator(name = "GEOPREDIO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "he007_predio_gid_seq", schema = "public")
    @GeneratedValue(generator = "GEOPREDIO_CODIGO_GENERATOR")
    @Column(name = "gid")
    private Integer gId;
    
    @Size(max = 24)
    @Column(name = "lot_codigo")
    private String codigoPredio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "lot_regim")
    private EnumTipoPredio tipoPredio;
    
    @Column(name = "geom")
    private PGpolygon geom;

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getCodigoPredio() {
        return codigoPredio;
    }

    public void setCodigoPredio(String codigoPredio) {
        this.codigoPredio = codigoPredio;
    }

    public EnumTipoPredio getTipoPredio() {
        return tipoPredio;
    }

    public void setTipoPredio(EnumTipoPredio tipoPredio) {
        this.tipoPredio = tipoPredio;
    }

    public PGpolygon getGeom() {
        return geom;
    }

    public void setGeom(PGpolygon geom) {
        this.geom = geom;
    }
    
}
