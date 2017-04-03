/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumTipoPermisos;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author NEWVI
 */
@Entity
@Table(name = "cat_seg_fun_permisos")

public class AsignacionPermisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ASIGNACION_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_seg_fun_permisos_pef_id_seq", schema = "public")
    @GeneratedValue(generator = "ASIGNACION_CODIGO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "pef_id")
    private Integer pefId;
    @Enumerated(EnumType.STRING)
    @Column(name = "pef_operaciones")
    private EnumTipoPermisos pefOperaciones;
    @Enumerated(EnumType.STRING)
    @Column(name = "pef_estado")
    private EnumEstadoRegistro pefEstado;
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
    @JoinColumn(name = "rol_id", referencedColumnName = "fun_id")
    @ManyToOne
    private Funcionalidades rolId;
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")
    @ManyToOne
    private Permisos perId;

    public AsignacionPermisos() {
    }

    public AsignacionPermisos(Integer pefId) {
        this.pefId = pefId;
    }

    public Integer getPefId() {
        return pefId;
    }

    public void setPefId(Integer pefId) {
        this.pefId = pefId;
    }

    public EnumTipoPermisos getPefOperaciones() {
        return pefOperaciones;
    }

    public void setPefOperaciones(EnumTipoPermisos pefOperaciones) {
        this.pefOperaciones = pefOperaciones;
    }

    public EnumEstadoRegistro getPefEstado() {
        return pefEstado;
    }

    public void setPefEstado(EnumEstadoRegistro pefEstado) {
        this.pefEstado = pefEstado;
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

    public Funcionalidades getRolId() {
        return rolId;
    }

    public void setRolId(Funcionalidades rolId) {
        this.rolId = rolId;
    }

    public Permisos getPerId() {
        return perId;
    }

    public void setPerId(Permisos perId) {
        this.perId = perId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pefId != null ? pefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionPermisos)) {
            return false;
        }
        AsignacionPermisos other = (AsignacionPermisos) object;
        if ((this.pefId == null && other.pefId != null) || (this.pefId != null && !this.pefId.equals(other.pefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.AsignacionPermisos[ pefId=" + pefId + " ]";
    }
    
    public Boolean esAsignacionValido() {
        return (!ComunUtil.esNulo(this.pefEstado));
    }
}
