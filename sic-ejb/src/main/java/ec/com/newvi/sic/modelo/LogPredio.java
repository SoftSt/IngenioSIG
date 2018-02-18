/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.dto.FichaCatastralDto;
import ec.com.newvi.sic.enums.EnumAcciones;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "cat_cat_log_predio", schema = "public")

public class LogPredio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "LOG_PREDIO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_log_predio_cod_log_predio_seq", schema = "public")
    @GeneratedValue(generator = "LOG_PREDIO_CODIGO_GENERATOR")
    @Column(name = "cod_log_predio")
    private Integer codLogPredio;
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;
    @Size(max = 50)
    @Column(name = "cod_usu")
    private String codUsu;
    @Column(name = "fec_log")
    @Temporal(TemporalType.DATE)
    private Date fecLog;
    @Size(max = 30)
    @Column(name = "nom_ip")
    private String nomIp;
    @Size(max=10485760)
    @Column(name = "txt_log")
    private String txtLog;
    @Enumerated(EnumType.STRING)
    @Column(name = "log_accion")
    private EnumAcciones logAccion;
    @Enumerated(EnumType.STRING)
    @Column(name = "log_estado")
    private EnumEstadoRegistro logEstado;
    
    public LogPredio() {
    }

    public LogPredio(Integer codLogPredio) {
        this.codLogPredio = codLogPredio;
    }

    public Integer getCodLogPredio() {
        return codLogPredio;
    }

    public void setCodLogPredio(Integer codLogPredio) {
        this.codLogPredio = codLogPredio;
    }

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public Date getFecLog() {
        return fecLog;
    }

    public void setFecLog(Date fecLog) {
        this.fecLog = fecLog;
    }

    public String getNomIp() {
        return nomIp;
    }

    public void setNomIp(String nomIp) {
        this.nomIp = nomIp;
    }

    public String getTxtLog() {
        return txtLog;
    }

    public void setTxtLog(String txtLog) {
        this.txtLog = txtLog;
    }

    public EnumEstadoRegistro getLogEstado() {
        return logEstado;
    }

    public void setLogEstado(EnumEstadoRegistro logEstado) {
        this.logEstado = logEstado;
    }

    public EnumAcciones getLogAccion() {
        return logAccion;
    }

    public void setLogAccion(EnumAcciones logAccion) {
        this.logAccion = logAccion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLogPredio != null ? codLogPredio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogPredio)) {
            return false;
        }
        LogPredio other = (LogPredio) object;
        if ((this.codLogPredio == null && other.codLogPredio != null) || (this.codLogPredio != null && !this.codLogPredio.equals(other.codLogPredio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.LogPredio[ codLogPredio=" + codLogPredio + " ]";
    }
    
    public Boolean esLogPredioValido() {
        return (!ComunUtil.esNulo(this.logEstado));
    }
    
    public Contribuyentes obtenerContribuyente(){
        FichaCatastralDto ficha = new FichaCatastralDto(this.codCatastral);
        return ficha.getContribuyentePropiedad();
        //return contribuyente.getNomApellidos().concat(" ").concat(contribuyente.getNomApellidos());
    }
    
}
