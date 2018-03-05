/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumEstadoTitulo;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "cat_cat_titulo_movimientos")

public class TituloMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "TITULO_MOVIMIENTOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_titulo_movimientos_cod_movimiento_seq", schema = "public")
    @GeneratedValue(generator = "TITULO_MOVIMIENTOS_CODIGO_GENERATOR")

    @Column(name = "cod_movimiento")
    private Integer codMovimiento;
    @Size(max = 5)
    @Column(name = "anio_emision")
    private String anioEmision;
    @Column(name = "fec_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMovimiento;
    @Size(max = 50)
    @Column(name = "nom_usu")
    private String nomUsu;
    @Size(max = 30)
    @Column(name = "ip_usu")
    private String ipUsu;
    @Size(max = 2147483647)
    @Column(name = "txt_movimiento")
    private String txtMovimiento;
    @Size(max = 2147483647)
    @Column(name = "txt_razon")
    private String razonMovimiento;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_titulo")
    private EnumEstadoTitulo estadoTitulo;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_movimiento")
    private EnumEstadoRegistro estadoMovimiento;
    @JoinColumn(name = "cod_titulos", referencedColumnName = "cod_titulos")
    @ManyToOne
    private Titulos codTitulos;

    public TituloMovimientos() {
    }

    public TituloMovimientos(Integer codMovimiento) {
        this.codMovimiento = codMovimiento;
    }

    public Integer getCodMovimiento() {
        return codMovimiento;
    }

    public void setCodMovimiento(Integer codMovimiento) {
        this.codMovimiento = codMovimiento;
    }

    public String getAnioEmision() {
        return anioEmision;
    }

    public void setAnioEmision(String anioEmision) {
        this.anioEmision = anioEmision;
    }

    public Date getFecMovimiento() {
        return fecMovimiento;
    }

    public void setFecMovimiento(Date fecMovimiento) {
        this.fecMovimiento = fecMovimiento;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getIpUsu() {
        return ipUsu;
    }

    public void setIpUsu(String ipUsu) {
        this.ipUsu = ipUsu;
    }

    public String getTxtMovimiento() {
        return txtMovimiento;
    }

    public void setTxtMovimiento(String txtMovimiento) {
        this.txtMovimiento = txtMovimiento;
    }

    public EnumEstadoTitulo getEstadoTitulo() {
        return estadoTitulo;
    }

    public void setEstadoTitulo(EnumEstadoTitulo estadoTitulo) {
        this.estadoTitulo = estadoTitulo;
    }

    public EnumEstadoRegistro getEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(EnumEstadoRegistro estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public Titulos getCodTitulos() {
        return codTitulos;
    }

    public void setCodTitulos(Titulos codTitulos) {
        this.codTitulos = codTitulos;
    }

    public String getRazonMovimiento() {
        return razonMovimiento;
    }

    public void setRazonMovimiento(String razonMovimiento) {
        this.razonMovimiento = razonMovimiento;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMovimiento != null ? codMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TituloMovimientos)) {
            return false;
        }
        TituloMovimientos other = (TituloMovimientos) object;
        if ((this.codMovimiento == null && other.codMovimiento != null) || (this.codMovimiento != null && !this.codMovimiento.equals(other.codMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.TituloMovimientos[ codMovimiento=" + codMovimiento + " ]";
    }
    
    public Boolean esTituloMovimientosValido() {
        return (!ComunUtil.esNulo(this.estadoMovimiento));
    }

}
