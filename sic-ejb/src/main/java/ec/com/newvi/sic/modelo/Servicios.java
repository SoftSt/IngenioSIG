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
@Table(name = "cat_cat_servicios", schema = "public")

public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SERVICIOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_servicios_cod_servicios_seq", schema = "public")
    @GeneratedValue(generator = "SERVICIOS_CODIGO_GENERATOR")

    @Column(name = "cod_servicios")
    private Integer codServicios;

    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;

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
    @Size(max = 25)
    @Column(name = "sts_estado")
    private String stsEstado;
    @Size(max = 50)
    @Column(name = "nom_medidor")
    private String nomMedidor;
    @Column(name = "cod_usuario")
    private Integer codUsuario;
    @Column(name = "num_medidores")
    private Integer numMedidores;
    @Enumerated(EnumType.STRING)
    @Column(name = "ser_estado")
    private EnumEstadoRegistro serEstado;
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

    public Integer getCodServicios() {
        return codServicios;
    }

    public void setCodServicios(Integer codServicios) {
        this.codServicios = codServicios;
    }

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
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

    public String getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(String stsEstado) {
        this.stsEstado = stsEstado;
    }

    public String getNomMedidor() {
        return nomMedidor;
    }

    public void setNomMedidor(String nomMedidor) {
        this.nomMedidor = nomMedidor;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getNumMedidores() {
        return numMedidores;
    }

    public void setNumMedidores(Integer numMedidores) {
        this.numMedidores = numMedidores;
    }

    public EnumEstadoRegistro getSerEstado() {
        return serEstado;
    }

    public void setSerEstado(EnumEstadoRegistro serEstado) {
        this.serEstado = serEstado;
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
    
     @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Servicios[ cod_servicios=" + codServicios + " ]";
    }
    
    public Boolean codServicios() {
        return (!ComunUtil.esNulo(this.codServicios));
    }
    
    
    

}
