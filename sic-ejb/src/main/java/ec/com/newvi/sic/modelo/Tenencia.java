/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

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
@Table(name = "cat_ciu_tenencia")
public class Tenencia implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @SequenceGenerator(name = "TENENCIA_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_tenencia_cod_tenencia_seq", schema = "public")
    @GeneratedValue(generator = "TENENCIA_CODIGO_GENERATOR")
    @Column(name = "cod_tenencia")
    private Integer codTenencia;
    @Size(max = 25)
    @Column(name = "sts_codigo")
    private String stsCodigo;
    @Size(max = 100)
    @Column(name = "sts_grupo")
    private String stsGrupo;
    @Size(max = 100)
    @Column(name = "sts_subgrupo")
    private String stsSubgrupo;
    @Size(max = 100)
    @Column(name = "sts_descripcion")
    private String stsDescripcion;
    @Enumerated(EnumType.STRING)
    @Column(name = "ten_estado")
    private EnumEstadoRegistro tenEstado;
    @Size(max = 50)
    @Column(name = "aud_ing_usu")
    private String audIngUsu;
    @Column(name = "aud_ing_fec")
    @Temporal(TemporalType.DATE)
    private Date audIngFec;
    @Size(max = 30)
    @Column(name = "aud_ing_ip")
    private String audIngIp;
    @Size(max = 50)
    @Column(name = "aud_mod_usu")
    private String audModUsu;
    @Column(name = "aud_mod_fec")
    @Temporal(TemporalType.DATE)
    private Date audModFec;
    @Size(max = 30)
    @Column(name = "aud_mod_ip")
    private String audModIp;
    @JoinColumn(name = "cod_propietarios", referencedColumnName = "cod_propietarios")
    @ManyToOne
    private Propiedad codPropietarios;

    public Tenencia() {
    }

    public Tenencia(Integer codTenencia) {
        this.codTenencia = codTenencia;
    }

    public Integer getCodTenencia() {
        return codTenencia;
    }

    public void setCodTenencia(Integer codTenencia) {
        this.codTenencia = codTenencia;
    }

    public String getStsCodigo() {
        return stsCodigo;
    }

    public void setStsCodigo(String stsCodigo) {
        this.stsCodigo = stsCodigo;
    }

    public String getStsGrupo() {
        return stsGrupo;
    }

    public void setStsGrupo(String stsGrupo) {
        this.stsGrupo = stsGrupo;
    }

    public String getStsSubgrupo() {
        return stsSubgrupo;
    }

    public void setStsSubgrupo(String stsSubgrupo) {
        this.stsSubgrupo = stsSubgrupo;
    }

    public String getStsDescripcion() {
        return stsDescripcion;
    }

    public void setStsDescripcion(String stsDescripcion) {
        this.stsDescripcion = stsDescripcion;
    }

    public EnumEstadoRegistro getTenEstado() {
        return tenEstado;
    }

    public void setTenEstado(EnumEstadoRegistro tenEstado) {
        this.tenEstado = tenEstado;
    }

    public String getAudIngUsu() {
        return audIngUsu;
    }

    public void setAudIngUsu(String audIngUsu) {
        this.audIngUsu = audIngUsu;
    }

    public Date getAudIngFec() {
        return audIngFec;
    }

    public void setAudIngFec(Date audIngFec) {
        this.audIngFec = audIngFec;
    }

    public String getAudIngIp() {
        return audIngIp;
    }

    public void setAudIngIp(String audIngIp) {
        this.audIngIp = audIngIp;
    }

    public String getAudModUsu() {
        return audModUsu;
    }

    public void setAudModUsu(String audModUsu) {
        this.audModUsu = audModUsu;
    }

    public Date getAudModFec() {
        return audModFec;
    }

    public void setAudModFec(Date audModFec) {
        this.audModFec = audModFec;
    }

    public String getAudModIp() {
        return audModIp;
    }

    public void setAudModIp(String audModIp) {
        this.audModIp = audModIp;
    }

    public Propiedad getCodPropietarios() {
        return codPropietarios;
    }

    public void setCodPropietarios(Propiedad codPropietarios) {
        this.codPropietarios = codPropietarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTenencia != null ? codTenencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tenencia)) {
            return false;
        }
        Tenencia other = (Tenencia) object;
        if ((this.codTenencia == null && other.codTenencia != null) || (this.codTenencia != null && !this.codTenencia.equals(other.codTenencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Tenencia[ codTenencia=" + codTenencia + " ]";
    }
    
    public Boolean esTenenciaValida() {
        return (!ComunUtil.esNulo(this.tenEstado));
    }
    
}
