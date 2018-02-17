/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.geo.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author israelavila
 */
@Entity
@Table(name = "he002_lote", schema = "public")
public class GeoPredio implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    @Id
    @SequenceGenerator(name = "GEOPREDIO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "he002_lote_id_seq", schema = "public")
    @GeneratedValue(generator = "GEOPREDIO_CODIGO_GENERATOR")
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 6)
    @Column(name = "dpa_codigo")
    private String codigoDPA;
    
    /*@Column(name = "geom")
    private Polygon geom;*/

    @Size(max = 2)
    @Column(name = "zon_codigo")
    private String codigoZona;
    
    @Size(max = 2)
    @Column(name = "sec_codigo")
    private String codigoSector;
    
    @Size(max = 2)
    @Column(name = "man_codigo")
    private String codigoManzana;
    
    @Size(max = 4)
    @Column(name = "lot_numero")
    private String numeroLote;
    
    @Size(max = 24)
    @Column(name = "lot_codigo")
    private String codigoPredio;
    
    @Size(max = 10)
    @Column(name = "cod_campo")
    private String codigoCampoPredio;
    
    @Column(name = "area_total")
    private String areaPredio;
    
    public String getCodigoDPA() {
        return codigoDPA;
    }

    public void setCodigoDPA(String codigoDPA) {
        this.codigoDPA = codigoDPA;
    }

    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    public String getCodigoSector() {
        return codigoSector;
    }

    public void setCodigoSector(String codigoSector) {
        this.codigoSector = codigoSector;
    }

    public String getCodigoManzana() {
        return codigoManzana;
    }

    public void setCodigoManzana(String codigoManzana) {
        this.codigoManzana = codigoManzana;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getCodigoPredio() {
        return codigoPredio;
    }

    public void setCodigoPredio(String codigoPredio) {
        this.codigoPredio = codigoPredio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoCampoPredio() {
        return codigoCampoPredio;
    }

    public void setCodigoCampoPredio(String codigoCampoPredio) {
        this.codigoCampoPredio = codigoCampoPredio;
    }

    public String getAreaPredio() {
        return areaPredio;
    }

    public void setAreaPredio(String areaPredio) {
        this.areaPredio = areaPredio;
    }
    
    

    /*public EnumTipoPredio getTipoPredio() {
        return tipoPredio;
    }

    public void setTipoPredio(EnumTipoPredio tipoPredio) {
        this.tipoPredio = tipoPredio;
    }*/

    /*public Polygon getGeom() {
        return geom;
    }

    public void setGeom(Polygon geom) {
        this.geom = geom;
    }*/
    
    
    
    /*public BigDecimal obtenerAreaDesdeGeom(){
        return new BigDecimal(this.geom.getArea());
    }*/
}
