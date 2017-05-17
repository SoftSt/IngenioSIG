/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Contribuyentes;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Propietario;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author israelavila
 */
public class FichaCatastralDto {

    private Predios predio;
    private Propietario propietarioActual;
    private Contribuyentes contribuyentePropietario;

    public FichaCatastralDto() {
        predio = new Predios();
        contribuyentePropietario = obtenerContribuyenteDesconocido();
        propietarioActual = new Propietario();
        propietarioActual.setPropietario(contribuyentePropietario);
    }

    public FichaCatastralDto(Predios predio) {
        this.predio = predio;
        this.propietarioActual = obtenerPropietarioActual(predio);
        if (ComunUtil.esNulo(propietarioActual) || ComunUtil.esNulo(propietarioActual.getPropietario())) {
            this.propietarioActual = obtenerPropietarioDesconocido();
        }
        contribuyentePropietario = this.propietarioActual.getPropietario();
    }

    private Propietario obtenerPropietarioActual(Predios predioABuscar) {
        for (Propietario propietario : predioABuscar.getHistoricoPropietarios()) {
            if (EnumEstadoRegistro.A.equals(propietario.getProEstado())
                    && !ComunUtil.esNulo(propietario.getPropietario())) {
                return propietario;
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

    public Propietario getPropietario() {
        return propietarioActual;
    }

    public void setPropietario(Propietario propietarioActual) {
        this.propietarioActual = propietarioActual;
    }

    public Contribuyentes getContribuyentePropietario() {
        return contribuyentePropietario;
    }

    public void setContribuyentePropietario(Contribuyentes contribuyentePropietario) {
        this.contribuyentePropietario = contribuyentePropietario;
    }

    private Propietario obtenerPropietarioDesconocido() {
        Propietario propietarioDesconocido = new Propietario();
        propietarioDesconocido.setPropietario(obtenerContribuyenteDesconocido());
        return propietarioDesconocido;
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
