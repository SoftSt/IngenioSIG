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
@Table(name = "cat_cat_terreno")

public class Terreno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "TERRENO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_terreno_cod_terrenodetalle_seq", schema = "public")
    @GeneratedValue(generator = "TERRENO_CODIGO_GENERATOR")
    @Column(name = "cod_terrenodetalle")
    private Integer codTerrenodetalle;
    @Size(max = 25)
    @Column(name = "sts_codigo")
    private String stsCodigo;
    @Size(max = 100)
    @Column(name = "sts_grupo")
    private String stsGrupo;
    @Size(max = 100)
    @Column(name = "sts_subgrupo")
    private String stsSubGrupo;
    @Size(max = 100)
    @Column(name = "sts_descripcion")
    private String stsDescripcion;
    @Column(name = "cod_usuario")
    private Integer codUsuario;
    @Enumerated(EnumType.STRING)
    @Column(name = "ter_estado")
    private EnumEstadoRegistro terEstado;
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
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;

    public Terreno() {
    }

    public Terreno(Integer codTerrenodetalle) {
        this.codTerrenodetalle = codTerrenodetalle;
    }

    public Integer getCodTerrenodetalle() {
        return codTerrenodetalle;
    }

    public void setCodTerrenodetalle(Integer codTerrenodetalle) {
        this.codTerrenodetalle = codTerrenodetalle;
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

    public String getStsSubGrupo() {
        return stsSubGrupo;
    }

    public void setStsSubGrupo(String stsSubGrupo) {
        this.stsSubGrupo = stsSubGrupo;
    }

    public String getStsDescripcion() {
        return stsDescripcion;
    }

    public void setStsDescripcion(String stsDescripcion) {
        this.stsDescripcion = stsDescripcion;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public EnumEstadoRegistro getTerEstado() {
        return terEstado;
    }

    public void setTerEstado(EnumEstadoRegistro terEstado) {
        this.terEstado = terEstado;
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

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTerrenodetalle != null ? codTerrenodetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terreno)) {
            return false;
        }
        Terreno other = (Terreno) object;
        if ((this.codTerrenodetalle == null && other.codTerrenodetalle != null) || (this.codTerrenodetalle != null && !this.codTerrenodetalle.equals(other.codTerrenodetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Terreno[ codTerrenodetalle=" + codTerrenodetalle + " ]";
    }
    
    public Boolean esTerrenoValido() {
        return (!ComunUtil.esNulo(this.terEstado));
    }
    
}
