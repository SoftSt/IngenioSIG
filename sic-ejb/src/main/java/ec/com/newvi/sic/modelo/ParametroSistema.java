/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumParametroSistema;
import ec.com.newvi.sic.enums.EnumParametroSistema.EnumGrupoParametroSistema;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author israelavila
 */
@Entity
@Table(name = "cat_adm_parametros", schema = "public")
public class ParametroSistema implements Serializable {
    
    @Id
    @SequenceGenerator(name = "PARAMETROS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_adm_parametros_par_cod_secuencial_seq", schema = "public")
    @GeneratedValue(generator = "PARAMETROS_CODIGO_GENERATOR")
    @Column(name = "par_cod_secuencial")
    private Integer codParametro;
    
    @Column(name = "par_cod_parametro")
    @Enumerated(EnumType.STRING)
    private EnumParametroSistema parametro;

    @Column(name = "par_nom_descripcion")
    private String descripcion;
    
    @Size(max = 500)
    @Column(name = "par_nom_valor")
    private String valor;
    
    @Column(name = "par_cod_grupo")
    @Enumerated(EnumType.STRING)
    private EnumGrupoParametroSistema grupo;

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
    
    public Integer getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(Integer codParametro) {
        this.codParametro = codParametro;
    }

    public EnumParametroSistema getParametro() {
        return parametro;
    }

    public void setParametro(EnumParametroSistema parametro) {
        this.parametro = parametro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public EnumGrupoParametroSistema getGrupo() {
        return grupo;
    }

    public void setGrupo(EnumGrupoParametroSistema grupo) {
        this.grupo = grupo;
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
    
    public Boolean esParametroSistemaValido() {
        return (!ComunUtil.esNulo(this.parametro) && !ComunUtil.esNulo(this.grupo) );
    }
    
    public void actualizarDatosPorTipoParametro() {
        this.grupo = this.parametro.getGrupoParametro();
        this.descripcion = this.parametro.getDescripcionParametro();
    }
}
