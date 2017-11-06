/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propiedad;
import ec.com.newvi.sic.util.ComunUtil;

/**
 *
 * @author israelavila
 */
public class FichaCatastralDto {

    private Predios predio;
    private Propiedad propiedadActual;
    private Contribuyentes contribuyentePropiedad;

    public FichaCatastralDto() {
        predio = new Predios();
        contribuyentePropiedad = obtenerContribuyenteDesconocido();
        propiedadActual = new Propiedad();
        propiedadActual.setPropiedad(contribuyentePropiedad);
    }

    public FichaCatastralDto(Predios predio) {
        this.predio = predio;
        this.propiedadActual = obtenerPropiedadActual(predio);
        if (ComunUtil.esNulo(propiedadActual) || ComunUtil.esNulo(propiedadActual.getPropiedad())) {
            this.propiedadActual = obtenerPropiedadDesconocido();
        }
        contribuyentePropiedad = this.propiedadActual.getPropiedad();
    }

    private Propiedad obtenerPropiedadActual(Predios predioABuscar) {
        for (Propiedad propiedad : predioABuscar.getHistoricoPropiedad()) {
            if (EnumEstadoRegistro.A.equals(propiedad.getProEstado())
                    && !ComunUtil.esNulo(propiedad.getPropiedad())) {
                return propiedad;
            }
        }
        return null;
    }

    public Predios getPredio() {
        return predio;
    }

    public void setPredio(Predios predio) {
        this.predio = predio;
    }

    public Propiedad getPropiedad() {
        return propiedadActual;
    }

    public void setPropiedad(Propiedad propiedadActual) {
        this.propiedadActual = propiedadActual;
    }

    public Contribuyentes getContribuyentePropiedad() {
        return contribuyentePropiedad;
    }

    public void setContribuyentePropiedad(Contribuyentes contribuyentePropiedad) {
        this.contribuyentePropiedad = contribuyentePropiedad;
    }

    private Propiedad obtenerPropiedadDesconocido() {
        Propiedad propiedadDesconocido = new Propiedad();
        propiedadDesconocido.setPropiedad(obtenerContribuyenteDesconocido());
        return propiedadDesconocido;
    }

    private Contribuyentes obtenerContribuyenteDesconocido() {
        Contribuyentes contribuyenteDesconocido = new Contribuyentes();
        contribuyenteDesconocido.setCodCedularuc("N/D");
        contribuyenteDesconocido.setNomApellidos("N/D");
        contribuyenteDesconocido.setNomNombres("N/D");
        contribuyenteDesconocido.setNomRazonsocial("N/D");
        return contribuyenteDesconocido;
    }
}
