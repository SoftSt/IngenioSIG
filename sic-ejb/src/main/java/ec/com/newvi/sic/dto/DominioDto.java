/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.dto;

import ec.com.newvi.sic.dao.DominiosFacade;
import ec.com.newvi.sic.modelo.Dominios;
import ec.com.newvi.sic.servicios.ParametrosServicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés
 */
public class DominioDto {

    private Dominios dominio;
    private List<DominioDto> hijos;
    private String descripcion;

    //public DominioDto(Dominios dominio, DominiosFacade dominiosFacade) {
    public DominioDto(Dominios dominio, ParametrosServicio parametrosServicio) {
        this.dominio = dominio;
        obtenerHijos(parametrosServicio);
        this.descripcion=dominio.getDomiDescripcion();
    }

    public Dominios getDominio() {
        return dominio;
    }

    public void setDominio(Dominios dominio) {
        this.dominio = dominio;
    }

    public List<DominioDto> getHijos() {
        return hijos;
    }

    public void setHijos(List<DominioDto> hijos) {
        this.hijos = hijos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    private void obtenerHijos(ParametrosServicio parametrosServicio) {
        this.hijos = new ArrayList<>();
        //List<Dominios> dominiosHijos = dominiosFacade.buscarHijos(this.dominio);
        List<Dominios> dominiosHijos = parametrosServicio.consultarHijos(this.dominio);
        for (Dominios dominioHijo : dominiosHijos) {
            this.hijos.add(new DominioDto(dominioHijo, parametrosServicio));
            //this.hijos.add(new DominioDto(dominioHijo, dominiosFacade));
        }
    }
}
