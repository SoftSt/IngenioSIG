/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumTenencia;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumSiNo;
import ec.com.newvi.sic.enums.EnumSitActual;
import ec.com.newvi.sic.enums.EnumTraslacion;
import ec.com.newvi.sic.util.ComunUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author Andrés
 */
@Entity
@Table(name = "cat_ciu_propietario", schema = "public")

public class Propiedad implements Serializable {

    @OneToMany(mappedBy = "codPropietarios")
    private List<Tenencia> tenenciaList;

    @OneToMany(mappedBy = "codPropietarios")
    private Collection<Titulos> titulosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PROPIETARIO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_ciu_propietario_copia_cod_propietarios_seq", schema = "public")
    @GeneratedValue(generator = "PROPIETARIO_CODIGO_GENERATOR")
    @Column(name = "cod_propietarios")
    private Integer codPropiedad;

    @JoinColumn(name = "cod_catastral", referencedColumnName = "cod_catastral")
    @ManyToOne(cascade = CascadeType.MERGE)/*(cascade = CascadeType.ALL, fetch = FetchType.EAGER)*/
    private Predios codCatastral;

    @JoinColumn(name = "cod_personeria", referencedColumnName = "cod_personeria")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Contribuyentes contribuyente;

    @Column(name = "sts_tenencia")
    private String stsTenencia;

    @Column(name = "sts_transferenciadominio")
    private String stsTransferenciadominio;

    @Size(max = 100)
    @Column(name = "txt_notaria")
    private String txtNotaria;
    @Size(max = 50)
    @Column(name = "txt_ciudad")
    private String txtCiudad;
    @Size(max = 150)
    @Column(name = "txt_registronumero")
    private String txtRegistroNumero;
    @Size(max = 50)
    @Column(name = "txt_detalleregistro")
    private String txtDetalleRegistro;
    @Size(max = 50)
    @Column(name = "txt_informante")
    private String txtInformante;
    @Size(max = 50)
    @Column(name = "txt_informanterelacion")
    private String txtInformanteRelacion;

    @Column(name = "sts_situacion")
    private String stsSituacion;

    @Column(name = "sts_escritura")
    private String stsEscritura;

    @Size(max = 10)
    @Column(name = "fec_inscripcion")
    private String fecInscripcion;
    @Size(max = 20)
    @Column(name = "fec_escritura")
    private String fecEscritura;
    @Size(max = 10)
    @Column(name = "fec_registro")
    private String fecRegistro;
    @Size(max = 20)
    @Column(name = "sts_estado")
    private String stsEstado;
    @Enumerated(EnumType.STRING)
    @Column(name = "pro_estado")
    private EnumEstadoRegistro proEstado;
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

    public Integer getCodPropiedad() {
        return codPropiedad;
    }

    public void setCodPropiedad(Integer codPropiedad) {
        this.codPropiedad = codPropiedad;
    }

    public Predios getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Predios codCatastral) {
        this.codCatastral = codCatastral;
    }

    public Contribuyentes getPropiedad() {
        return contribuyente;
    }

    public void setPropiedad(Contribuyentes contribuyente) {
        this.contribuyente = contribuyente;
    }

    public EnumTenencia getStsTenencia() {
        //return stsTenencia;.trim()
        return EnumTenencia.obtenerTenencia(stsTenencia);
    }

    public void setStsTenencia(EnumTenencia stsTenencia) {
        this.stsTenencia = stsTenencia.toString();
    }

    public EnumTraslacion getStsTransferenciadominio() {
        return EnumTraslacion.obtenerTraslacion(stsTransferenciadominio);
    }

    public void setStsTransferenciadominio(EnumTraslacion stsTransferenciadominio) {
        this.stsTransferenciadominio = stsTransferenciadominio.toString();
    }

    public String getTxtNotaria() {
        return txtNotaria;
    }

    public void setTxtNotaria(String txtNotaria) {
        this.txtNotaria = txtNotaria;
    }

    public String getTxtCiudad() {
        return txtCiudad;
    }

    public void setTxtCiudad(String txtCiudad) {
        this.txtCiudad = txtCiudad;
    }

    public String getTxtRegistroNumero() {
        return txtRegistroNumero;
    }

    public void setTxtRegistroNumero(String txtRegistroNumero) {
        this.txtRegistroNumero = txtRegistroNumero;
    }

    public String getTxtDetalleRegistro() {
        return txtDetalleRegistro;
    }

    public void setTxtDetalleRegistro(String txtDetalleRegistro) {
        this.txtDetalleRegistro = txtDetalleRegistro;
    }

    public String getTxtInformante() {
        return txtInformante;
    }

    public void setTxtInformante(String txtInformante) {
        this.txtInformante = txtInformante;
    }

    public String getTxtInformanteRelacion() {
        return txtInformanteRelacion;
    }

    public void setTxtInformanteRelacion(String txtInformanteRelacion) {
        this.txtInformanteRelacion = txtInformanteRelacion;
    }

    public EnumSitActual getStsSituacion() {
        //return stsSituacion;
        return EnumSitActual.obtenerSituacionActual(stsSituacion);
    }

    public void setStsSituacion(EnumSitActual stsSituacion) {
        this.stsSituacion = stsSituacion.toString();
    }

    public EnumSiNo getStsEscritura() {
        //return stsEscritura;
        return EnumSiNo.obtenerEscritura(stsEscritura);
    }

    public void setStsEscritura(EnumSiNo stsEscritura) {
        this.stsEscritura = stsEscritura.toString();
    }

    public String getFecInscripcion() {
        return fecInscripcion;
    }

    public void setFecInscripcion(String fecInscripcion) {
        this.fecInscripcion = fecInscripcion.toString();
    }

    public String getFecEscritura() {
        return fecEscritura;
    }

    public void setFecEscritura(String fecEscritura) {
        this.fecEscritura = fecEscritura.toString();
    }

    public String getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(String fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getStsEstado() {
        return stsEstado;
    }

    public void setStsEstado(String stsEstado) {
        this.stsEstado = stsEstado;
    }

    public EnumEstadoRegistro getProEstado() {
        return proEstado;
    }

    public void setProEstado(EnumEstadoRegistro proEstado) {
        this.proEstado = proEstado;
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

    public Contribuyentes getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyentes contribuyente) {
        this.contribuyente = contribuyente;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Propiedad[ cod_propietarios=" + codPropiedad + " ]";
    }

    public Boolean esPropiedadValido() {
        return (!ComunUtil.esNulo(this.proEstado));
    }

    public Propiedad() {
    }

    public Collection<Titulos> getTitulosCollection() {
        return titulosCollection;
    }

    public void setTitulosCollection(Collection<Titulos> titulosCollection) {
        this.titulosCollection = titulosCollection;
    }

    public List<Tenencia> getTenenciaList() {
        return tenenciaList;
    }

    public void setTenenciaList(List<Tenencia> tenenciaList) {
        this.tenenciaList = tenenciaList;
    }

    public List<Tenencia> getTenenciasActivas() {
        List<Tenencia> tenenciaActivas = new ArrayList<>();
        if (!ComunUtil.esNulo(tenenciaList)) {
            for (Tenencia tenencia : tenenciaList) {
                if (!ComunUtil.esNulo(tenencia.getTenEstado()) && tenencia.getTenEstado().equals(EnumEstadoRegistro.A)) {
                    tenenciaActivas.add(tenencia);
                }
            }
        }
        return tenenciaActivas;
    }

}
