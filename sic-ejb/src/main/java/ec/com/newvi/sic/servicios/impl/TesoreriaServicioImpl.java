/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.ConstantesDescuentosFacade;
import ec.com.newvi.sic.dao.ConstantesInteresMoraFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.CatConConstantesdescuentos;
import ec.com.newvi.sic.modelo.CatConConstantesinteresmora;
import ec.com.newvi.sic.servicios.TesoreriaServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author NEWVI
 */
@Stateless
@PermitAll
public class TesoreriaServicioImpl implements TesoreriaServicio {

    @EJB
    private ConstantesDescuentosFacade constantesDescuentosFacade;
    
    @EJB
    private ConstantesInteresMoraFacade constantesInteresMoraFacade;

    @Override
    public List<CatConConstantesdescuentos> consultarDescuentos() {
        return constantesDescuentosFacade.buscarDescuentos();
    }

    @Override
    public CatConConstantesdescuentos seleccionarDescuento(Integer idDescuento) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idDescuento)) {
            return constantesDescuentosFacade.find(idDescuento);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public void actualizarDescuento(CatConConstantesdescuentos catConConstantesdescuentos, SesionDto sesion) throws NewviExcepcion {
        
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando multa...", sesion);
        if (!catConConstantesdescuentos.esDescuentoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR301);
        }
        // Actualizar el descuento
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando multa...", sesion);
        
        catConConstantesdescuentos.setStsAnio(catConConstantesdescuentos.getStsAnio());
        catConConstantesdescuentos.setStsMes(catConConstantesdescuentos.getStsMes());
        catConConstantesdescuentos.setStsQuincena(catConConstantesdescuentos.getStsQuincena());
        catConConstantesdescuentos.setValValor(catConConstantesdescuentos.getValValor());
        
        constantesDescuentosFacade.edit(catConConstantesdescuentos);
    }

    @Override
    public void generarNuevoDescuentos(CatConConstantesdescuentos catConConstantesdescuentos, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando multa...", sesion);
        if (!catConConstantesdescuentos.esDescuentoValido()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR301);
        }
        // Actualizar el descuento
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando multa...", sesion);
        
        catConConstantesdescuentos.setStsAnio(catConConstantesdescuentos.getStsAnio());
        catConConstantesdescuentos.setStsMes(catConConstantesdescuentos.getStsMes());
        catConConstantesdescuentos.setStsQuincena(catConConstantesdescuentos.getStsQuincena());
        catConConstantesdescuentos.setValValor(catConConstantesdescuentos.getValValor());
        
        constantesDescuentosFacade.create(catConConstantesdescuentos);
    }

    @Override
    public List<CatConConstantesinteresmora> consultarMultas() {
        return constantesInteresMoraFacade.buscarMultas();
    }

    @Override
    public CatConConstantesinteresmora seleccionarMulta(Integer idMulta) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idMulta)) {
            return constantesInteresMoraFacade.find(idMulta);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public void actualizarDescuento(CatConConstantesinteresmora constantesInteresMora, SesionDto sesion) throws NewviExcepcion {
        
        // Actualizar el descuento
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando multa...", sesion);
        
        constantesInteresMora.setStsAnio(constantesInteresMora.getStsAnio());
        constantesInteresMora.setStsAnioaplica(constantesInteresMora.getStsAnioaplica());
        constantesInteresMora.setValMonto(constantesInteresMora.getValMonto());
        
        constantesInteresMoraFacade.edit(constantesInteresMora);
    }

    @Override
    public void generarNuevaMulta(CatConConstantesinteresmora constantesInteresMora, SesionDto sesion) throws NewviExcepcion {
        // Actualizar el descuento
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando multa...", sesion);
        
        constantesInteresMora.setStsAnio(constantesInteresMora.getStsAnio());
        constantesInteresMora.setStsAnioaplica(constantesInteresMora.getStsAnioaplica());
        constantesInteresMora.setValMonto(constantesInteresMora.getValMonto());
        
        constantesInteresMoraFacade.create(constantesInteresMora);
    }
}
