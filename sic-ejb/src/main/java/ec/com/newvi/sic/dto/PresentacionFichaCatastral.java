/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.modelo.Bloques;
import ec.com.newvi.sic.modelo.PisoDetalle;
import ec.com.newvi.sic.modelo.Pisos;
import ec.com.newvi.sic.modelo.Predios;
import ec.com.newvi.sic.modelo.Servicios;
import ec.com.newvi.sic.modelo.Terreno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class PresentacionFichaCatastral {
    
    private FichaCatastralDto fichaCatastral;
    private List<Pisos> listaPisos;
    private List<Bloques> listaBloques;
    private List<PisoDetalle> listaDetallesPiso;
    private List<Servicios> listaServicios;
    private List<Terreno> listaDescripcionTerreno;
    
    public PresentacionFichaCatastral(Predios predio) {
        this.fichaCatastral = new FichaCatastralDto(predio);
        this.listaDescripcionTerreno = (List<Terreno>)predio.getCaracteristicasTerreno();
        this.listaServicios = (List<Servicios>)predio.getServicios();
        obtenerCaracteristicasEdificacion(predio);
    }
    public PresentacionFichaCatastral() {
    }

    public FichaCatastralDto getFichaCatastral() {
        return fichaCatastral;
    }

    public void setFichaCatastral(FichaCatastralDto fichaCatastral) {
        this.fichaCatastral = fichaCatastral;
    }

    public List<Pisos> getListaPisos() {
        return listaPisos;
    }

    public void setListaPisos(List<Pisos> listaPisos) {
        this.listaPisos = listaPisos;
    }

    public List<Bloques> getListaBloques() {
        return listaBloques;
    }

    public void setListaBloques(List<Bloques> listaBloques) {
        this.listaBloques = listaBloques;
    }

    public List<PisoDetalle> getListaDetallesPiso() {
        return listaDetallesPiso;
    }

    public void setListaDetallesPiso(List<PisoDetalle> listaDetallesPiso) {
        this.listaDetallesPiso = listaDetallesPiso;
    }

    public List<Servicios> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<Terreno> getListaDescripcionTerreno() {
        return listaDescripcionTerreno;
    }

    public void setListaDescripcionTerreno(List<Terreno> listaDescripcionTerreno) {
        this.listaDescripcionTerreno = listaDescripcionTerreno;
    }

    private void obtenerCaracteristicasEdificacion(Predios predio) {
        this.listaBloques = (List<Bloques>)predio.getBloques();
        /*this.listaPisos = new ArrayList<>();
        
        for (Bloques bloque : this.listaBloques) {
            //this.listaPisos.add(bloque.getP)
        }*/
    }
}
