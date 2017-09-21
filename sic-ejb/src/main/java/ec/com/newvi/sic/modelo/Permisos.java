/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "cat_seg_permisos", schema = "public")
public class Permisos implements Serializable {

    /*@Column(name = "per_estado")
    private Character perEstado;
    @OneToMany(mappedBy = "perId")
    private Collection<AsignacionFuncionalidades> asignacionFuncionalidadesCollection;

    /*@Column(name = "per_estado")
    private Character perEstado;
    @OneToMany(mappedBy = "perId")
    private Collection<Usuarios> usuariosCollection;*/
    private static final long serialVersionUID = -1L;

    @Id
    @SequenceGenerator(name = "PERMISOS_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_seg_permisos_per_id_seq", schema = "public")
    @GeneratedValue(generator = "PERMISOS_CODIGO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "per_id")
    private Integer idPermiso;

    @Size(max = 50)
    @Column(name = "per_grupo")
    private String grupoPermiso;

    @Column(name = "per_descripcion")
    private String descripcionPermiso;

    @Size(max = 100)
    @Column(name = "per_operaciones")
    private String operacionesPermiso;

    @Enumerated(EnumType.STRING)
    @Column(name = "per_estado")
    private EnumEstadoRegistro estadoPermiso;

    @Size(max = 50)
    @Column(name = "aud_ing_usu")
    private String audIngresoUsuario;

    @Column(name = "aud_ing_fec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audIngresoFecha;

    @Size(max = 30)
    @Column(name = "aud_ing_ip")
    private String audIngresoIp;

    @Size(max = 50)
    @Column(name = "aud_mod_usu")
    private String audModificacionUsuario;

    @Column(name = "aud_mod_fec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audModificacionFecha;

    @Size(max = 30)
    @Column(name = "aud_mod_ip")
    private String audModificacionIp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perId", fetch = FetchType.EAGER)
    private List<AsignacionPermisos> listaAsignacionPermisos;

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getGrupoPermiso() {
        return grupoPermiso;
    }

    public void setGrupoPermiso(String grupoPermiso) {
        this.grupoPermiso = grupoPermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public String getOperacionesPermiso() {
        return operacionesPermiso;
    }

    public void setOperacionesPermiso(String operacionesPermiso) {
        this.operacionesPermiso = operacionesPermiso;
    }

    public EnumEstadoRegistro getEstadoPermiso() {
        return estadoPermiso;
    }

    public void setEstadoPermiso(EnumEstadoRegistro estadoPermiso) {
        this.estadoPermiso = estadoPermiso;
    }

    public String getAudIngresoUsuario() {
        return audIngresoUsuario;
    }

    public void setAudIngresoUsuario(String audIngresoUsuario) {
        this.audIngresoUsuario = audIngresoUsuario;
    }

    public Date getAudIngresoFecha() {
        return (Date) audIngresoFecha.clone();
    }

    public void setAudIngresoFecha(Date audIngresoFecha) {
        this.audIngresoFecha = (Date) audIngresoFecha.clone();
    }

    public String getAudIngresoIp() {
        return audIngresoIp;
    }

    public void setAudIngresoIp(String audIngresoIp) {
        this.audIngresoIp = audIngresoIp;
    }

    public String getAudModificacionUsuario() {
        return audModificacionUsuario;
    }

    public void setAudModificacionUsuario(String audModificacionUsuario) {
        this.audModificacionUsuario = audModificacionUsuario;
    }

    public Date getAudModificacionFecha() {
        return (Date) audModificacionFecha.clone();
    }

    public void setAudModificacionFecha(Date audModificacionFecha) {
        this.audModificacionFecha = (Date) audModificacionFecha.clone();
    }

    public String getAudModificacionIp() {
        return audModificacionIp;
    }

    public void setAudModificacionIp(String audModificacionIp) {
        this.audModificacionIp = audModificacionIp;
    }

    public List<AsignacionPermisos> getListaAsignacionPermisos() {
        return listaAsignacionPermisos;
    }

    public void setListaAsignacionPermisos(List<AsignacionPermisos> listaAsignacionPermisos) {
        this.listaAsignacionPermisos = listaAsignacionPermisos;
    }
    
    

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        return !((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso)));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idPermiso);
        hash = 97 * hash + Objects.hashCode(this.grupoPermiso);
        hash = 97 * hash + Objects.hashCode(this.estadoPermiso);
        return hash;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.Permisos[ idPermiso=" + idPermiso + " ]";
    }

    public Boolean esPermisoValido() {
        return (!ComunUtil.esCadenaVacia(this.grupoPermiso)
                && !ComunUtil.esNulo(this.estadoPermiso));
    }

    public List<AsignacionPermisos> getListaAsignacionPermisosActivas() {
        List<AsignacionPermisos> listaAsignacionPermisosActivos = new ArrayList<AsignacionPermisos>();
        for (AsignacionPermisos permiso : listaAsignacionPermisos) {
            if (permiso.getPefEstado().equals(EnumEstadoRegistro.A)) {
                listaAsignacionPermisosActivos.add(permiso);
            }
        }
        return listaAsignacionPermisosActivos;
    }

}
