/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.enums.EnumReporte;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author israelavila
 */
@Entity
@Table(name = "cat_adm_reportes", schema = "public")
public class Reporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "REPORTE_CODIGO_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "cat_adm_reportes_rep_cod_secuencial_seq", schema = "public")
    @GeneratedValue(generator = "REPORTE_CODIGO_GENERATOR")
    @Column(name = "rep_cod_secuencial")
    private Integer codReporte;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rep_nom_reporte")
    private EnumReporte nombreReporte;
    
    @Column(name = "rep_nom_archivo_reporte")
    private String archivoReporte;
    
    @Column(name = "rep_nom_titulo")
    private String tituloReporte;

    @Column(name = "rep_nom_archivo_descarga")
    private String archivoDescargaReporte;
    
    public Integer getCodReporte() {
        return codReporte;
    }

    public void setCodReporte(Integer codReporte) {
        this.codReporte = codReporte;
    }

    public EnumReporte getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(EnumReporte nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getArchivoReporte() {
        return archivoReporte;
    }

    public void setArchivoReporte(String archivoReporte) {
        this.archivoReporte = archivoReporte;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public String getArchivoDescargaReporte() {
        return archivoDescargaReporte;
    }

    public void setArchivoDescargaReporte(String archivoDescargaReporte) {
        this.archivoDescargaReporte = archivoDescargaReporte;
    }
    
}
