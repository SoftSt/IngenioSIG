/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "cat_cat_pisos")

public class Pisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PISOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_pisos_cod_pisos_seq", schema = "public")
    @GeneratedValue(generator = "PISOS_CODIGO_GENERATOR")
    @Column(name = "cod_pisos")
    private Integer codPisos;
    @Size(max = 25)
    @Column(name = "nom_piso")
    private String nomPiso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_areapiso")
    private BigDecimal valAreapiso;
    @Column(name = "val_anioconstruccion")
    private Integer valAnioconstruccion;
    @Column(name = "val_anioreparacion")
    private Integer valAnioreparacion;
    @Column(name = "val_pisonumero")
    private Integer valPisonumero;
    @Size(max = 25)
    @Column(name = "sts_estado")
    private String stsEstado;
    @Column(name = "val_factordepreciacion")
    private BigDecimal valFactordepreciacion;
    @Column(name = "val_facestado")
    private BigDecimal valFacestado;
    @Column(name = "val_sumafactores")
    private BigDecimal valSumafactores;
    @Column(name = "val_constante")
    private BigDecimal valConstante;
    @Column(name = "val_metro2")
    private BigDecimal valMetro2;
    @Column(name = "val_piso")
    private BigDecimal valPiso;
    @Enumerated(EnumType.STRING)
    @Column(name = "pis_estado")
    private EnumEstadoRegistro pisEstado;
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
    @JoinColumn(name = "cod_bloques", referencedColumnName = "cod_bloques")
    @ManyToOne
    private Bloques codBloques;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "piso", fetch = FetchType.LAZY)
    private List<PisoDetalle> detalles;

    public Pisos() {
    }

    public Pisos(Integer codPisos) {
        this.codPisos = codPisos;
    }

    public Integer getCodPisos() {
        return codPisos;
    }

    public void setCodPisos(Integer codPisos) {
        this.codPisos = codPisos;
    }

    public String getNomPiso() {
        return nomPiso;
    }

    public void setNomPiso(String nomPiso) {
        this.nomPiso = nomPiso;
    }

    public BigDecimal getValAreapiso() {
        return valAreapiso;
    }

    public void setValAreapiso(BigDecimal valAreapiso) {
        this.valAreapiso = valAreapiso;
    }

    public Integer getValAnioconstruccion() {
        return valAnioconstruccion;
    }

    public void setValAnioconstruccion(Integer valAnioconstruccion) {
        this.valAnioconstruccion = valAnioconstruccion;
    }

    public Integer getValAnioreparacion() {
        return valAnioreparacion;
    }

    public void setValAnioreparacion(Integer valAnioreparacion) {
        this.valAnioreparacion = valAnioreparacion;
    }

    public Integer getValPisonumero() {
        return valPisonumero;
    }

    public void setValPisonumero(Integer valPisonumero) {
        this.valPisonumero = valPisonumero;
    }

    public String getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(String stsEstado) {
        this.stsEstado = stsEstado;
    }

    public BigDecimal getValFactordepreciacion() {
        return valFactordepreciacion;
    }

    public void setValFactordepreciacion(BigDecimal valFactordepreciacion) {
        this.valFactordepreciacion = valFactordepreciacion;
    }

    public BigDecimal getValFacestado() {
        return valFacestado;
    }

    public void setValFacestado(BigDecimal valFacestado) {
        this.valFacestado = valFacestado;
    }

    public BigDecimal getValSumafactores() {
        return valSumafactores;
    }

    public void setValSumafactores(BigDecimal valSumafactores) {
        this.valSumafactores = valSumafactores;
    }

    public BigDecimal getValConstante() {
        return valConstante;
    }

    public void setValConstante(BigDecimal valConstante) {
        this.valConstante = valConstante;
    }

    public BigDecimal getValMetro2() {
        return valMetro2;
    }

    public void setValMetro2(BigDecimal valMetro2) {
        this.valMetro2 = valMetro2;
    }

    public BigDecimal getValPiso() {
        return valPiso;
    }

    public void setValPiso(BigDecimal valPiso) {
        this.valPiso = valPiso;
    }

    public EnumEstadoRegistro getPisEstado() {
        return pisEstado;
    }

    public void setPisEstado(EnumEstadoRegistro pisEstado) {
        this.pisEstado = pisEstado;
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

    public Bloques getCodBloques() {
        return codBloques;
    }

    public void setCodBloques(Bloques codBloques) {
        this.codBloques = codBloques;
    }

    public List<PisoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<PisoDetalle> detalles) {
        this.detalles = detalles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPisos != null ? codPisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pisos)) {
            return false;
        }
        Pisos other = (Pisos) object;
        if ((this.codPisos == null && other.codPisos != null) || (this.codPisos != null && !this.codPisos.equals(other.codPisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Pisos[ codPisos=" + codPisos + " ]";
    }

    public Boolean esPisoValido() {
        //return (!ComunUtil.esNulo(this.pisEstado));
        boolean retorno = false;
        if (!(ComunUtil.esNulo(this.codPisos)) || !(ComunUtil.esNulo(this.pisEstado))) {
            retorno = true;
        }
        return retorno;
    }

    public Integer obtenerEdadPiso() {
        if (!ComunUtil.esNulo(this.getValAnioconstruccion())) {
            return ComunUtil.obtenerAnioDesdeFecha(null) - this.getValAnioconstruccion() + 1;
        } else {
            return 0;
        }
    }

    public List<PisoDetalle> getDetallesPisosActivos() {
        List<PisoDetalle> detallesActivos = new ArrayList<>();
        if (!ComunUtil.esNulo(detalles)) {
            for (PisoDetalle detallePiso : detalles) {
                if (!ComunUtil.esNulo(detallePiso.getEstado()) && detallePiso.getEstado().equals(EnumEstadoRegistro.A)) {
                    detallesActivos.add(detallePiso);
                }
            }
        }

        return detallesActivos;
    }
    
    public Pisos eliminarHijos(){
        if(!ComunUtil.esNulo(detalles)){
            for (PisoDetalle detalle : detalles) {
                detalle.setEstado(EnumEstadoRegistro.I);
            }
        }
        return this;
    }

}
