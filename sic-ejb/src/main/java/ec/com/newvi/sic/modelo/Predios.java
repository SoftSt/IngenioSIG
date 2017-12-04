/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.enums.EnumZonaInfluencia;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
 * @author Andrés
 */
@Entity
@Table(name = "cat_cat_predios", schema = "public")

public class Predios implements Serializable {

    @OneToMany(mappedBy = "codCatastral")
    private Collection<Titulos> titulosCollection;
    @ElementCollection
    @OneToMany(mappedBy = "codCatastral")
    private Collection<DetallesAvaluo> detallesAvaluoCollection;

    @Size(max = 100)
    @Column(name = "nom_predio")
    private String nomPredio;
    @Size(max = 25)
    @Column(name = "sts_urbanomarginal")
    private String stsUrbanomarginal;
    @Size(max = 25)
    @Column(name = "sts_sectorhomogeneo")
    private String stsSectorhomogeneo;
    @Size(max = 25)
    @Column(name = "sts_planospredio")
    private String stsPlanospredio;
    @Column(name = "val_otro1")
    private BigDecimal valOtro1;
    @Column(name = "val_otro2")
    private BigDecimal valOtro2;
    @Size(max = 50)
    @Column(name = "cat_casosespeciales")
    private String catCasosespeciales;
    @Size(max = 2147483647)
    @Column(name = "txt_observacion")
    private String txtObservacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "cat_estado")
    private EnumEstadoRegistro catEstado;
    @Enumerated(EnumType.STRING)
    @Column(name = "sts_ubicacioninfluencia")
    private EnumZonaInfluencia codZonaInfluencia;
    @OneToMany(mappedBy = "codCatastral")
    private Collection<Avaluo> avaluoCollection;

    private static final long serialVersionUID = -1L;

    @Id
    @SequenceGenerator(name = "PREDIO_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_cat_predios_cod_catastral_seq", schema = "public")
    @GeneratedValue(generator = "PREDIO_CODIGO_GENERATOR")
    @Column(name = "cod_catastral")
    private Integer codCatastral;

    @Size(max = 25)
    @Column(name = "sts_tipo")
    private String stsTipo;
    @Size(max = 25)
    @Column(name = "nom_codigocatastral")
    private String nomCodigocatastral;
    @Size(max = 100)
    @Column(name = "nom_codigocatastralanterior")
    private String nomCodigocatastralanterior;
    @Size(max = 6)
    @Column(name = "cod_dpa")
    private String codDpa;
    @Size(max = 2)
    @Column(name = "cod_zona")
    private String codZona;
    @Size(max = 2)
    @Column(name = "cod_sector")
    private String codSector;
    @Size(max = 3)
    @Column(name = "cod_manzana")
    private String codManzana;
    @Size(max = 4)
    @Column(name = "cod_predio")
    private String codPredio;
    @Size(max = 2)
    @Column(name = "cod_regimentenencia")
    private String codRegimentenencia;
    @Size(max = 3)
    @Column(name = "cod_horizontal")
    private String codHorizontal;
    @Size(max = 150)
    @Column(name = "sts_barrio")
    private String stsBarrio;
    @Size(max = 100)
    @Column(name = "txt_direccion")
    private String txtDireccion;
    @Size(max = 20)
    @Column(name = "nom_numero")
    private String nomNumero;
    @Size(max = 100)
    @Column(name = "txt_ubicacion")
    private String txtUbicacion;
    @Size(max = 50)
    @Column(name = "nom_cartografia")
    private String nomCartografia;
    @Size(max = 50)
    @Column(name = "nom_fotoaerea")
    private String nomFotoAerea;
    @Size(max = 50)
    @Column(name = "nom_cartootros")
    private String nomcCartografiaOtros;
    @Column(name = "val_coordenadaeste")
    private BigDecimal valCoordenadaEste;
    @Column(name = "val_coordenadanorte")
    private BigDecimal valCoordenadaNorte;
    @Column(name = "val_terreno")
    private BigDecimal valTerreno;
    @Size(max = 100)
    @Column(name = "txt_norte")
    private String txtNorte;
    @Size(max = 100)
    @Column(name = "txt_sur")
    private String txtSur;
    @Size(max = 100)
    @Column(name = "txt_este")
    private String txtEste;
    @Size(max = 100)
    @Column(name = "txt_oeste")
    private String txtOeste;
    @Size(max = 25)
    @Column(name = "txt_dominio")
    private String txtDominio;
    @Size(max = 50)
    @Column(name = "nom_informante")
    private String nomInformante;
    @Size(max = 50)
    @Column(name = "nom_intervenido")
    private String nomIntervenido;
    @Column(name = "val_areafrente")
    private BigDecimal valAreaFrente;
    @Column(name = "val_areafondo")
    private BigDecimal valAreaFondo;
    @Column(name = "val_areaconstruccion")
    private BigDecimal valAreaConstruccion;
    @Column(name = "val_areapredio")
    private BigDecimal valAreaPredio;
    @Column(name = "val_edifica")
    private BigDecimal valEdifica;
    @Column(name = "val_predio")
    private BigDecimal valPredio;
    @Column(name = "val_cem")
    private BigDecimal valCem;
    @Column(name = "val_bomberos")
    private BigDecimal valBomberos;
    @Column(name = "val_emision")
    private BigDecimal valEmision;
    @Column(name = "val_noedifica")
    private BigDecimal valNoEdificacion;
    @Column(name = "val_ambientales")
    private BigDecimal valAmbientales;
    @Column(name = "val_impuesto")
    private BigDecimal valImpuesto;
    @Column(name = "val_imppredial")
    private BigDecimal valImppredial;
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

    //@ElementCollection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private List<Terreno> caracteristicasTerreno;
    //@ElementCollection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private List<Servicios> servicios;
    //@ElementCollection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private List<Bloques> bloques;
    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCatastral", fetch = FetchType.LAZY)
    private Collection<Propiedad> historicoPropiedad;

    public Predios() {
    }

    public Predios(Integer codCatastral) {
        this.codCatastral = codCatastral;
    }

    public Integer getCodCatastral() {
        return codCatastral;
    }

    public void setCodCatastral(Integer codCatastral) {
        this.codCatastral = codCatastral;
    }

    public String getStsTipo() {
        return stsTipo;
    }

    public void setStsTipo(String stsTipo) {
        this.stsTipo = stsTipo;
    }

    public String getNomCodigocatastral() {
        return nomCodigocatastral;
    }

    public void setNomCodigocatastral(String nomCodigocatastral) {
        this.nomCodigocatastral = nomCodigocatastral;
    }

    public String getNomCodigocatastralanterior() {
        return nomCodigocatastralanterior;
    }

    public void setNomCodigocatastralanterior(String nomCodigocatastralanterior) {
        this.nomCodigocatastralanterior = nomCodigocatastralanterior;
    }

    public String getCodDpa() {
        return codDpa;
    }

    public void setCodDpa(String codDpa) {
        this.codDpa = codDpa;
    }

    public String getCodZona() {
        return codZona;
    }

    public void setCodZona(String codZona) {
        this.codZona = codZona;
    }

    public String getCodSector() {
        return codSector;
    }

    public void setCodSector(String codSector) {
        this.codSector = codSector;
    }

    public String getCodManzana() {
        return codManzana;
    }

    public void setCodManzana(String codManzana) {
        this.codManzana = codManzana;
    }

    public String getCodPredio() {
        return codPredio;
    }

    public void setCodPredio(String codPredio) {
        this.codPredio = codPredio;
    }

    public String getCodRegimentenencia() {
        return codRegimentenencia;
    }

    public void setCodRegimentenencia(String codRegimentenencia) {
        this.codRegimentenencia = codRegimentenencia;
    }

    public String getCodHorizontal() {
        return codHorizontal;
    }

    public void setCodHorizontal(String codHorizontal) {
        this.codHorizontal = codHorizontal;
    }

    public String getStsBarrio() {
        return stsBarrio;
    }

    public void setStsBarrio(String stsBarrio) {
        this.stsBarrio = stsBarrio;
    }

    public String getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public String getNomNumero() {
        return nomNumero;
    }

    public void setNomNumero(String nomNumero) {
        this.nomNumero = nomNumero;
    }

    public String getTxtUbicacion() {
        return txtUbicacion;
    }

    public void setTxtUbicacion(String txtUbicacion) {
        this.txtUbicacion = txtUbicacion;
    }

    public String getNomCartografia() {
        return nomCartografia;
    }

    public void setNomCartografia(String nomCartografia) {
        this.nomCartografia = nomCartografia;
    }

    public String getNomFotoAerea() {
        return nomFotoAerea;
    }

    public void setNomFotoAerea(String nomFotoAerea) {
        this.nomFotoAerea = nomFotoAerea;
    }

    public String getNomcCartografiaOtros() {
        return nomcCartografiaOtros;
    }

    public void setNomcCartografiaOtros(String nomcCartografiaOtros) {
        this.nomcCartografiaOtros = nomcCartografiaOtros;
    }

    public BigDecimal getValCoordenadaEste() {
        return valCoordenadaEste;
    }

    public void setValCoordenadaEste(BigDecimal valCoordenadaEste) {
        this.valCoordenadaEste = valCoordenadaEste;
    }

    public BigDecimal getValCoordenadaNorte() {
        return valCoordenadaNorte;
    }

    public void setValCoordenadaNorte(BigDecimal valCoordenadaNorte) {
        this.valCoordenadaNorte = valCoordenadaNorte;
    }

    public BigDecimal getValTerreno() {
        return valTerreno;
    }

    public void setValTerreno(BigDecimal valTerreno) {
        this.valTerreno = valTerreno;
    }

    public BigDecimal getValEdifica() {
        return valEdifica;
    }

    public void setValEdifica(BigDecimal valEdifica) {
        this.valEdifica = valEdifica;
    }

    public BigDecimal getValPredio() {
        return valPredio;
    }

    public void setValPredio(BigDecimal valPredio) {
        this.valPredio = valPredio;
    }

    public BigDecimal getValCem() {
        return valCem;
    }

    public void setValCem(BigDecimal valCem) {
        this.valCem = valCem;
    }

    public BigDecimal getValBomberos() {
        return valBomberos;
    }

    public void setValBomberos(BigDecimal valBomberos) {
        this.valBomberos = valBomberos;
    }

    public BigDecimal getValEmision() {
        return valEmision;
    }

    public void setValEmision(BigDecimal valEmision) {
        this.valEmision = valEmision;
    }

    public BigDecimal getValNoEdificacion() {
        return valNoEdificacion;
    }

    public void setValNoEdificacion(BigDecimal valNoEdificacion) {
        this.valNoEdificacion = valNoEdificacion;
    }

    public BigDecimal getValAmbientales() {
        return valAmbientales;
    }

    public void setValAmbientales(BigDecimal valAmbientales) {
        this.valAmbientales = valAmbientales;
    }

    public BigDecimal getValImpuesto() {
        return valImpuesto;
    }

    public void setValImpuesto(BigDecimal valImpuesto) {
        this.valImpuesto = valImpuesto;
    }

    public BigDecimal getValImppredial() {
        return valImppredial;
    }

    public void setValImppredial(BigDecimal valImppredial) {
        this.valImppredial = valImppredial;
    }

    public String getTxtNorte() {
        return txtNorte;
    }

    public void setTxtNorte(String txtNorte) {
        this.txtNorte = txtNorte;
    }

    public String getTxtSur() {
        return txtSur;
    }

    public void setTxtSur(String txtSur) {
        this.txtSur = txtSur;
    }

    public String getTxtEste() {
        return txtEste;
    }

    public void setTxtEste(String txtEste) {
        this.txtEste = txtEste;
    }

    public String getTxtOeste() {
        return txtOeste;
    }

    public void setTxtOeste(String txtOeste) {
        this.txtOeste = txtOeste;
    }

    public String getTxtDominio() {
        return txtDominio;
    }

    public void setTxtDominio(String txtDominio) {
        this.txtDominio = txtDominio;
    }

    public String getNomInformante() {
        return nomInformante;
    }

    public void setNomInformante(String nomInformante) {
        this.nomInformante = nomInformante;
    }

    public String getNomIntervenido() {
        return nomIntervenido;
    }

    public void setNomIntervenido(String nomIntervenido) {
        this.nomIntervenido = nomIntervenido;
    }

    public BigDecimal getValAreaFrente() {
        return valAreaFrente;
    }

    public void setValAreaFrente(BigDecimal valAreaFrente) {
        this.valAreaFrente = valAreaFrente;
    }

    public BigDecimal getValAreaConstruccion() {
        return valAreaConstruccion;
    }

    public void setValAreaConstruccion(BigDecimal valAreaConstruccion) {
        this.valAreaConstruccion = valAreaConstruccion;
    }

    public BigDecimal getValAreaPredio() {
        return valAreaPredio;
    }

    public void setValAreaPredio(BigDecimal valAreaPredio) {
        this.valAreaPredio = valAreaPredio;
    }

    public BigDecimal getValAreaFondo() {
        return valAreaFondo;
    }

    public void setValAreaFondo(BigDecimal valAreaFondo) {
        this.valAreaFondo = valAreaFondo;
    }

    public EnumEstadoRegistro getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(EnumEstadoRegistro catEstado) {
        this.catEstado = catEstado;
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

    public Collection<Terreno> getCaracteristicasTerreno() {
        return caracteristicasTerreno;
    }

    public void setCaracteristicasTerreno(List<Terreno> caracteristicasTerreno) {
        this.caracteristicasTerreno = caracteristicasTerreno;
    }

    public Collection<Servicios> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicios> servicios) {
        this.servicios = servicios;
    }

    public Collection<Bloques> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloques> bloques) {
        this.bloques = bloques;
    }

    public Collection<Propiedad> getHistoricoPropiedad() {
        return historicoPropiedad;
    }

    public void setHistoricoPropiedad(Collection<Propiedad> historicoPropiedad) {
        this.historicoPropiedad = historicoPropiedad;
    }
    public EnumZonaInfluencia getCodZonaInfluencia() {
        return codZonaInfluencia;
    }

    public void setCodZonaInfluencia(EnumZonaInfluencia codZonaInfluencia) {
        this.codZonaInfluencia = codZonaInfluencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCatastral != null ? codCatastral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predios)) {
            return false;
        }
        Predios other = (Predios) object;
        if ((this.codCatastral == null && other.codCatastral != null) || (this.codCatastral != null && !this.codCatastral.equals(other.codCatastral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.newvi.sic.modelo.Predios[ codCatastral=" + codCatastral + " ]";
    }

    public Boolean esPredioValido() {
        return (!ComunUtil.esNulo(this.catEstado));
    }

    public void actualizarCodigoPredio() {
        this.nomCodigocatastral = this.codDpa.concat(this.codZona).concat(this.codSector).concat(this.codManzana).concat(this.codPredio).concat(this.codRegimentenencia).concat(this.codHorizontal);
    }

    public String getNomPredio() {
        return nomPredio;
    }

    public void setNomPredio(String nomPredio) {
        this.nomPredio = nomPredio;
    }

    public String getStsUrbanomarginal() {
        return stsUrbanomarginal;
    }

    public void setStsUrbanomarginal(String stsUrbanomarginal) {
        this.stsUrbanomarginal = stsUrbanomarginal;
    }

    public String getStsSectorhomogeneo() {
        return stsSectorhomogeneo;
    }

    public void setStsSectorhomogeneo(String stsSectorhomogeneo) {
        this.stsSectorhomogeneo = stsSectorhomogeneo;
    }

    public String getStsPlanospredio() {
        return stsPlanospredio;
    }

    public void setStsPlanospredio(String stsPlanospredio) {
        this.stsPlanospredio = stsPlanospredio;
    }

    public BigDecimal getValOtro1() {
        return valOtro1;
    }

    public void setValOtro1(BigDecimal valOtro1) {
        this.valOtro1 = valOtro1;
    }

    public BigDecimal getValOtro2() {
        return valOtro2;
    }

    public void setValOtro2(BigDecimal valOtro2) {
        this.valOtro2 = valOtro2;
    }

    public String getCatCasosespeciales() {
        return catCasosespeciales;
    }

    public void setCatCasosespeciales(String catCasosespeciales) {
        this.catCasosespeciales = catCasosespeciales;
    }

    public String getTxtObservacion() {
        return txtObservacion;
    }

    public void setTxtObservacion(String txtObservacion) {
        this.txtObservacion = txtObservacion;
    }

    public Collection<Avaluo> getAvaluoCollection() {
        return avaluoCollection;
    }

    public void setAvaluoCollection(Collection<Avaluo> avaluoCollection) {
        this.avaluoCollection = avaluoCollection;
    }

    public Collection<DetallesAvaluo> getDetallesAvaluoCollection() {
        return detallesAvaluoCollection;
    }

    public void setDetallesAvaluoCollection(Collection<DetallesAvaluo> detallesAvaluoCollection) {
        this.detallesAvaluoCollection = detallesAvaluoCollection;
    }

    private String generarGetter(String atributo) {
        return "get".concat(atributo.substring(0, 1).toUpperCase().concat(atributo.substring(1, atributo.length())));
    }

    private Boolean esColeccion(String atributo) {
        return atributo.contains("Collection");
    }

    public List<Method> filtrarMetodos(Method[] listaMetodos) {
        List<Method> listaMetodosFiltrada = new ArrayList<>();
        for (Method metodo : listaMetodos) {
            if (metodo.getName().startsWith("get")) {
                listaMetodosFiltrada.add(metodo);
            }
        }
        return listaMetodosFiltrada;
    }

    public Object buscarAtributo(String nombreMetodo, String nombreMetodoBuscado, Object objetoAVerificar, Method metodo) throws NewviExcepcion {
        if (nombreMetodo.equals(generarGetter(nombreMetodoBuscado))) {
            try {
                return metodo.invoke(objetoAVerificar);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new NewviExcepcion(EnumNewviExcepciones.ERR020, ex);
            }
        }
        return null;
    }

    public Boolean esAtributoNulo(Object atributo) {
        return ComunUtil.esNulo(atributo);
    }

    public Object retornarAtributo(Object objetoAVerificar, String nombreMetodoBuscado) throws NewviExcepcion {
        Object atributo = null;
        for (Method metodo : filtrarMetodos(objetoAVerificar.getClass().getMethods())) {
            String nombreMetodo = metodo.getName();
            if (nombreMetodo.startsWith("get") && (metodo.getName().length() == (nombreMetodoBuscado.length() + 3))) {
                atributo = buscarAtributo(nombreMetodo, nombreMetodoBuscado, objetoAVerificar, metodo);
                if (!ComunUtil.esNulo(atributo)) {
                    if (!(atributo.getClass().getName()).contains("BigDecimal")) {
                        return atributo;
                    } else {
                        return ((BigDecimal) atributo).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                }
            }
        }
        return null;
    }

    public String generarLog(Object objetoBase, Object objetoAverificar, String nombreMetodoBuscado) throws NewviExcepcion {
        String log = "";
        Object objetoBaseGenerado = retornarAtributo(objetoBase, nombreMetodoBuscado);
        Object objetoAverificarGenerado = retornarAtributo(objetoAverificar, nombreMetodoBuscado);

        if (!ComunUtil.esNulo(objetoBaseGenerado) && !ComunUtil.esNulo(objetoAverificarGenerado)) {
            if (!objetoBaseGenerado.toString().contains(objetoAverificarGenerado.toString())) {
                log = "\nExistió un cambio en el atributo '" + nombreMetodoBuscado + "' de '" + objetoBaseGenerado.toString().trim() + "' a '" + objetoAverificarGenerado.toString().trim() + "'";
                //log = String.format("\nExistió un cambio en el atributo {0} de {1} a {2}",nombreMetodoBuscado,objetoBaseGenerado.trim(),objetoAverificarGenerado.trim());
            }
        }

        return log;
    }

    public String esPredioIgual(Object objetoAVerificar, Object objetoBase) throws NewviExcepcion {
        String log = "";
        Class claseObjeto = objetoAVerificar.getClass();
        Field[] metodosClase = claseObjeto.getDeclaredFields();
        String nombreMetodoBuscado = "";
        for (Field metodo : metodosClase) {
            nombreMetodoBuscado = metodo.getName();
            if (!esColeccion(metodo.getType().getName())) {
                log += generarLog(objetoBase, objetoAVerificar, nombreMetodoBuscado);
            } else {
                Object prueba = retornarAtributo(objetoAVerificar, nombreMetodoBuscado);

                for (Object objeto : (List) prueba) {
                    String a = objeto.getClass().getName();
                    a.trim();
                }

            }
        }
        return log;
    }

    public List<Terreno> getCaracteristicasTerrenoActivas() {
        List<Terreno> caracteristicasTerrenoActivas = new ArrayList<>();
        for (Terreno terreno : caracteristicasTerreno) {
            if (!ComunUtil.esNulo(terreno.getTerEstado()) && terreno.getTerEstado().equals(EnumEstadoRegistro.A)) {
                caracteristicasTerrenoActivas.add(terreno);
            }
        }
        return caracteristicasTerrenoActivas;
    }

    public List<Servicios> getServicosActivos() {
        List<Servicios> serviciosActivos = new ArrayList<>();
        for (Servicios servicio : servicios) {
            if (!ComunUtil.esNulo(servicio.getSerEstado()) && servicio.getSerEstado().equals(EnumEstadoRegistro.A)) {
                serviciosActivos.add(servicio);
            }
        }
        return serviciosActivos;
    }

    public List<Bloques> getBloquesActivos() {
        List<Bloques> bloquesActivos = new ArrayList<>();

        for (Bloques bloque : bloques) {
            if (!ComunUtil.esNulo(bloque.getBloEstado()) && bloque.getBloEstado().equals(EnumEstadoRegistro.A)) {
                bloquesActivos.add(bloque);
            }
        }
        return bloquesActivos;
    }

    public Collection<Titulos> getTitulosCollection() {
        return titulosCollection;
    }

    public void setTitulosCollection(Collection<Titulos> titulosCollection) {
        this.titulosCollection = titulosCollection;
    }

}
