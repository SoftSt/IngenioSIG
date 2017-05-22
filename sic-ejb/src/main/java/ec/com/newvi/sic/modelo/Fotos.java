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
@Table(name = "cat_cat_fotos", schema = "public")
public class Fotos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "FOTOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_fotos_cod_fotos_seq", schema = "public")
    @GeneratedValue(generator = "FOTOS_CODIGO_GENERATOR")
    
    @Column(name = "cod_fotos")
    private Integer codFotos;
    
    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne
    private Predios codCatastral;
    
    @Size(max = 100)
    @Column(name = "txt_descripcion")
    private String desFotos;
    
    @Size(max = 150)
    @Column(name = "txt_archivo")
    private String dirFotos;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "foto_estado")
    private EnumEstadoRegistro estFotos;
    
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

    public Integer getCodFotos() {
        return codFotos;
    }

    public void setCodFotos(Integer codFotos) {
        this.codFotos = codFotos;
    }

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    public String getDesFotos() {
        return desFotos;
    }

    public void setDesFotos(String desFotos) {
        this.desFotos = desFotos;
    }

    public String getDirFotos() {
        return dirFotos;
    }

    public void setDirFotos(String dirFotos) {
        this.dirFotos = dirFotos;
    }

    public EnumEstadoRegistro getEstFotos() {
        return estFotos;
    }

    public void setEstFotos(EnumEstadoRegistro estFotos) {
        this.estFotos = estFotos;
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
        return "ec.com.newvi.sic.modelo.Fotos[ cod_fotos=" + codFotos + " ]";
    }
    
    public Boolean esFotoValida() {
        return (!ComunUtil.esNulo(this.estFotos));
    }
    
    
    
    
}
