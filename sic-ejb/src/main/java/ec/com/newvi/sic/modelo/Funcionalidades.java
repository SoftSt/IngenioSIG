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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NEWVI
 */
@Entity
@Table(name = "cat_seg_funcionalidades")

public class Funcionalidades implements Serializable {

    @OneToMany(mappedBy = "rolId")
    private Collection<AsignacionPermisos> asignacionPermisosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "FUNCIONALIDADES_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_seg_funcionalidades_fun_id_seq", schema = "public")
    @GeneratedValue(generator = "FUNCIONALIDADES_CODIGO_GENERATOR")
    @Basic(optional = false)
    @Column(name = "fun_id")
    private Integer funId;
    @JoinColumn(name = "fun_id_padre", referencedColumnName = "fun_id")
    @ManyToOne
    private Funcionalidades funIdPadre;
    @Size(max = 50)
    @Column(name = "fun_nombre")
    private String funNombre;
    @Size(max = 2147483647)
    @Column(name = "fun_descripcion")
    private String funDescripcion;
    @Size(max = 250)
    @Column(name = "fun_menu")
    private String funMenu;
    @Size(max = 30)
    @Column(name = "fun_menu_icono")
    private String funMenuIcono;
    @Size(max = 500)
    @Column(name = "fun_ruta")
    private String funRuta;
    @Column(name = "fun_orden")
    private Integer funOrden;
    @Enumerated(EnumType.STRING)
    @Column(name = "fun_estado")
    private EnumEstadoRegistro funEstado;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funIdPadre", fetch = FetchType.EAGER)
    private List<Funcionalidades> listaSubFuncionalidades;

    public Funcionalidades() {
    }

    public Funcionalidades(Integer funId) {
        this.funId = funId;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunNombre() {
        return funNombre;
    }

    public Funcionalidades getFunIdPadre() {
        return funIdPadre;
    }

    public void setFunIdPadre(Funcionalidades funIdPadre) {
        this.funIdPadre = funIdPadre;
    }

    public void setFunNombre(String funNombre) {
        this.funNombre = funNombre;
    }

    public String getFunDescripcion() {
        return funDescripcion;
    }

    public void setFunDescripcion(String funDescripcion) {
        this.funDescripcion = funDescripcion;
    }

    public String getFunMenu() {
        return funMenu;
    }

    public void setFunMenu(String funMenu) {
        this.funMenu = funMenu;
    }

    public String getFunMenuIcono() {
        return funMenuIcono;
    }

    public void setFunMenuIcono(String funMenuIcono) {
        this.funMenuIcono = funMenuIcono;
    }

    public String getFunRuta() {
        return funRuta;
    }

    public void setFunRuta(String funRuta) {
        this.funRuta = funRuta;
    }

    public Integer getFunOrden() {
        return funOrden;
    }

    public void setFunOrden(Integer funOrden) {
        this.funOrden = funOrden;
    }

    public EnumEstadoRegistro getFunEstado() {
        return funEstado;
    }

    public void setFunEstado(EnumEstadoRegistro funEstado) {
        this.funEstado = funEstado;
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

    public List<Funcionalidades> getListaSubFuncionalidades() {
        return listaSubFuncionalidades;
    }

    public void setListaSubFuncionalidades(List<Funcionalidades> listaSubFuncionalidades) {
        this.listaSubFuncionalidades = listaSubFuncionalidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funId != null ? funId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionalidades)) {
            return false;
        }
        Funcionalidades other = (Funcionalidades) object;
        if ((this.funId == null && other.funId != null) || (this.funId != null && !this.funId.equals(other.funId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Funcionalidades[ funId=" + funId + " ]";
    }

    public Boolean esFuncionalidadValido() {
        return (!ComunUtil.esNulo(this.funEstado));
    }

    @XmlTransient
    public Collection<AsignacionPermisos> getAsignacionPermisosCollection() {
        return asignacionPermisosCollection;
    }

    public void setAsignacionPermisosCollection(Collection<AsignacionPermisos> asignacionPermisosCollection) {
        this.asignacionPermisosCollection = asignacionPermisosCollection;
    }

    public List<Funcionalidades> getListaSubFuncionalidadesActivas() {
        List<Funcionalidades> listaFuncionalidadesActivas = new ArrayList<Funcionalidades>();
        for (Funcionalidades subfuncionalidad : listaSubFuncionalidades) {
            if (subfuncionalidad.getFunEstado().equals(EnumEstadoRegistro.A)) {
                listaFuncionalidadesActivas.add(subfuncionalidad);
            }
        }
        
        return listaFuncionalidadesActivas;
    }

}
