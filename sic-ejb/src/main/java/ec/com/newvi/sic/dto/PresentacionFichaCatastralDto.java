/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Servicios;
import ec.com.newvi.sic.modelo.Terreno;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class PresentacionFichaCatastralDto {

    private String stsTipo;
    private String nomCodigocatastral;
    private String nomCodigocatastralanterior;
    private String codDpa;
    private String codZona;
    private String codSector;
    private String codManzana;
    private String codPredio;
    private String codRegimentenencia;
    private String codHorizontal;
    private String stsBarrio;
    private String txtDireccion;
    private BigDecimal valAreaPredio;
    private BigDecimal valAreaFrente;
    private BigDecimal valAreaFondo;
    private BigDecimal valAreaConstruccion;
    private String txtNorte;
    private String txtSur;
    private String txtEste;
    private String txtOeste;

    private String stsTenencia;
    private String stsTenenciaotro;
    private String stsTransferenciadominio;
    private BigDecimal valPredioAreaEscritura;
    private String txtNotaria;
    private String txtCiudad;
    private String txtRegistroNumero;
    private String txtDetalleRegistro;
    private String txtInformante;
    private String txtInformanteRelacion;
    private String stsSituacion;
    private String stsEscritura;
    private String fecInscripcion;
    private String fecEscritura;
    private String fecRegistro;
    private String stsEstado;

    private String codCedularuc;
    private String nomApellidos;
    private String nomNombres;
    
    private List<Terreno> listaDescripcionTerreno;
    
    private List<Bloques> listaBloques;
    
    private List<Servicios> listaServicios;

    public List<Bloques> getListaBloques() {
        return listaBloques;
    }

    public void setListaBloques(List<Bloques> listaBloques) {
        this.listaBloques = listaBloques;
    }

    public List<Servicios> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }
    
    public String getNomNombres() {
        return nomNombres;
    }

    public void setNomNombres(String nomNombres) {
        this.nomNombres = nomNombres;
    }
    
    public String getCodCedularuc() {
        return codCedularuc;
    }

    public void setCodCedularuc(String codCedularuc) {
        this.codCedularuc = codCedularuc;
    }

    public String getNomApellidos() {
        return nomApellidos;
    }

    public void setNomApellidos(String nomApellidos) {
        this.nomApellidos = nomApellidos;
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

    public BigDecimal getValAreaPredio() {
        return valAreaPredio;
    }

    public void setValAreaPredio(BigDecimal valAreaPredio) {
        this.valAreaPredio = valAreaPredio;
    }

    public BigDecimal getValAreaFrente() {
        return valAreaFrente;
    }

    public void setValAreaFrente(BigDecimal valAreaFrente) {
        this.valAreaFrente = valAreaFrente;
    }

    public BigDecimal getValAreaFondo() {
        return valAreaFondo;
    }

    public void setValAreaFondo(BigDecimal valAreaFondo) {
        this.valAreaFondo = valAreaFondo;
    }

    public BigDecimal getValAreaConstruccion() {
        return valAreaConstruccion;
    }

    public void setValAreaConstruccion(BigDecimal valAreaConstruccion) {
        this.valAreaConstruccion = valAreaConstruccion;
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

    public String getStsTenencia() {
        return stsTenencia;
    }

    public void setStsTenencia(String stsTenencia) {
        this.stsTenencia = stsTenencia;
    }

    public String getStsTenenciaotro() {
        return stsTenenciaotro;
    }

    public void setStsTenenciaotro(String stsTenenciaotro) {
        this.stsTenenciaotro = stsTenenciaotro;
    }

    public String getStsTransferenciadominio() {
        return stsTransferenciadominio;
    }

    public void setStsTransferenciadominio(String stsTransferenciadominio) {
        this.stsTransferenciadominio = stsTransferenciadominio;
    }

    public BigDecimal getValPredioAreaEscritura() {
        return valPredioAreaEscritura;
    }

    public void setValPredioAreaEscritura(BigDecimal valPredioAreaEscritura) {
        this.valPredioAreaEscritura = valPredioAreaEscritura;
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

    public String getStsSituacion() {
        return stsSituacion;
    }

    public void setStsSituacion(String stsSituacion) {
        this.stsSituacion = stsSituacion;
    }

    public String getStsEscritura() {
        return stsEscritura;
    }

    public void setStsEscritura(String stsEscritura) {
        this.stsEscritura = stsEscritura;
    }

    public String getFecInscripcion() {
        return fecInscripcion;
    }

    public void setFecInscripcion(String fecInscripcion) {
        this.fecInscripcion = fecInscripcion;
    }

    public String getFecEscritura() {
        return fecEscritura;
    }

    public void setFecEscritura(String fecEscritura) {
        this.fecEscritura = fecEscritura;
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

    public List<Terreno> getListaDescripcionTerreno() {
        return listaDescripcionTerreno;
    }

    public void setListaDescripcionTerreno(List<Terreno> listaDescripcionTerreno) {
        this.listaDescripcionTerreno = listaDescripcionTerreno;
    }

    
    public PresentacionFichaCatastralDto(Predios predio) {
        FichaCatastralDto fichaCatastralDto = new FichaCatastralDto(predio);
        setearDatosPredio(fichaCatastralDto.getPredio());
        setearDatosContribuyente(fichaCatastralDto.getContribuyentePropiedad());
        this.listaDescripcionTerreno = (List<Terreno>)fichaCatastralDto.getPredio().getCaracteristicasTerreno();
        this.listaBloques = (List<Bloques>)fichaCatastralDto.getPredio().getBloques();
        this.listaServicios = (List<Servicios>)fichaCatastralDto.getPredio().getServicios();

    }

    private void setearDatosPredio(Predios predio) {
        this.codDpa = predio.getCodDpa();
        this.codHorizontal = predio.getCodHorizontal();
        this.codManzana = predio.getCodManzana();
        this.codPredio = predio.getCodPredio();
        this.codRegimentenencia = predio.getCodRegimentenencia();
        this.codSector = predio.getCodSector();
        this.codZona = predio.getCodZona();
        this.nomCodigocatastral = predio.getNomCodigocatastral();
        this.nomCodigocatastralanterior = predio.getNomCodigocatastralanterior();
        this.stsBarrio = predio.getStsBarrio();
        this.stsTipo = predio.getStsTipo();
        this.txtDireccion = predio.getTxtDireccion();
        this.txtEste = predio.getTxtEste();
        this.txtNorte = predio.getTxtNorte();
        this.txtOeste = predio.getTxtOeste();
        this.txtSur = predio.getTxtSur();
    }

    private void setearDatosContribuyente(Contribuyentes contribuyentePropiedad) {
        this.codCedularuc = contribuyentePropiedad.getCodCedularuc();
        this.nomApellidos = contribuyentePropiedad.getNomApellidos();
        this.nomNombres = contribuyentePropiedad.getNomNombres();
    }

}
