/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.Avaluo;
import ec.com.newvi.sic.modelo.Titulos;
import ec.com.newvi.sic.servicios.CatastroServicio;
import ec.com.newvi.sic.servicios.ContribuyentesServicio;
import ec.com.newvi.sic.servicios.RentasServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author israelavila
 */
@Stateless
@PermitAll
public class RentasServicioImpl implements RentasServicio {

    @EJB
    private ContribuyentesServicio contribuyentesServicio;

    @Override
    public List<Titulos> generarTitulosDesdeAvaluos(List<Avaluo> listadoAvaluos, SesionDto sesion) throws NewviExcepcion {
        
        List<Titulos> listaTitulosGenerados = new ArrayList<>();
        
        for (Avaluo avaluo : listadoAvaluos) {
            Titulos nuevoTitulo = obtenerTituloDesdeAvaluo(avaluo);
            
            // Registrar datos del nuevo titulo
            nuevoTitulo.setFecEmision(ComunUtil.hoy());

            //Registramos la auditoria de ingreso
            nuevoTitulo.setAudIngIp(sesion.getDireccionIP());
            nuevoTitulo.setAudIngUsu(sesion.getUsuarioRegistrado().getUsuPalabraclave().trim());
            nuevoTitulo.setAudIngFec(ComunUtil.hoy());
            
            listaTitulosGenerados.add(nuevoTitulo);
            
        }
        
        return listaTitulosGenerados;
    }

    private Titulos obtenerTituloDesdeAvaluo(Avaluo avaluo) throws NewviExcepcion {
        Titulos nuevoTitulo = new Titulos();
        nuevoTitulo.setCodCatastral(avaluo.getCodCatastral());
        nuevoTitulo.setNomCodigocatastral(avaluo.getNomCodigocatastral());
        try {
            nuevoTitulo.setCodPropietarios(contribuyentesServicio.consultarUltimoPropiedad(avaluo.getCodCatastral()));
        } catch (NewviExcepcion ex) {
            Map<String, String> variables = new HashMap<>();
            variables.put("predio", avaluo.getCodCatastral().getNomCodigocatastral());
            throw new NewviExcepcion(EnumNewviExcepciones.ERR601, variables, ex);
        }
        nuevoTitulo.setValAreaterreno(avaluo.getValAreapredio());
        nuevoTitulo.setValValorterreno(avaluo.getValAreapredio());
        nuevoTitulo.setValAreaconstruccion(avaluo.getValAreaconstruccion());
        nuevoTitulo.setValConstruccion(avaluo.getValEdifica());
        nuevoTitulo.setValBaseimponible(avaluo.getValPredio());
        nuevoTitulo.setValImpuestopredial(avaluo.getValImppredial());
        nuevoTitulo.setValBomberos(avaluo.getValBomberos());
        nuevoTitulo.setValCem(avaluo.getValCem());
        nuevoTitulo.setValNoconstruido(avaluo.getValNoEdificacion());
        nuevoTitulo.setValTotalapagar(avaluo.getValImpuesto());

        return nuevoTitulo;
    }

}
