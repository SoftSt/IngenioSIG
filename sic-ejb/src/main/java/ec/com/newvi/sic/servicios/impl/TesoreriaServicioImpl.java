/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dao.CabeceraTituloFacade;
import ec.com.newvi.sic.dao.ConstantesDescuentosFacade;
import ec.com.newvi.sic.dao.ConstantesInteresMoraFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.modelo.CabeceraTitulo;
import ec.com.newvi.sic.modelo.ConstantesDescuentos;
import ec.com.newvi.sic.modelo.ConstantesInteresMora;
import ec.com.newvi.sic.servicios.TesoreriaServicio;
import ec.com.newvi.sic.util.ComunUtil;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import ec.com.newvi.sic.util.logs.LoggerNewvi;
import java.util.Calendar;
import java.util.Date;
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

    @EJB
    private CabeceraTituloFacade cabeceraTituloFacade;

    @Override
    public List<ConstantesDescuentos> consultarDescuentos() {
        return constantesDescuentosFacade.buscarDescuentos();
    }

    @Override
    public ConstantesDescuentos seleccionarDescuento(Integer idDescuento) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idDescuento)) {
            return constantesDescuentosFacade.find(idDescuento);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public void actualizarDescuento(ConstantesDescuentos catConConstantesdescuentos, SesionDto sesion) throws NewviExcepcion {

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
    public void generarNuevoDescuentos(ConstantesDescuentos catConConstantesdescuentos, SesionDto sesion) throws NewviExcepcion {
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
    public ConstantesDescuentos buscarDescuentoRecargoPorMesYQuincena(String mes, String quincena) {
        return constantesDescuentosFacade.buscarDescuentoRecargoPorMesYQuincena(mes, quincena);
    }

    @Override
    public List<ConstantesInteresMora> consultarMultas() {
        return constantesInteresMoraFacade.buscarMultas();
    }

    @Override
    public ConstantesInteresMora seleccionarMulta(Integer idMulta) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(idMulta)) {
            return constantesInteresMoraFacade.find(idMulta);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public void actualizarDescuento(ConstantesInteresMora constantesInteresMora, SesionDto sesion) throws NewviExcepcion {

        // Actualizar el descuento
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando multa...", sesion);

        constantesInteresMora.setStsAnio(constantesInteresMora.getStsAnio());
        constantesInteresMora.setStsAnioaplica(constantesInteresMora.getStsAnioaplica());
        constantesInteresMora.setValMonto(constantesInteresMora.getValMonto());

        constantesInteresMoraFacade.edit(constantesInteresMora);
    }

    @Override
    public void generarNuevaMulta(ConstantesInteresMora constantesInteresMora, SesionDto sesion) throws NewviExcepcion {
        // Actualizar el descuento
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando multa...", sesion);

        constantesInteresMora.setStsAnio(constantesInteresMora.getStsAnio());
        constantesInteresMora.setStsAnioaplica(constantesInteresMora.getStsAnioaplica());
        constantesInteresMora.setValMonto(constantesInteresMora.getValMonto());

        constantesInteresMoraFacade.create(constantesInteresMora);
    }

    @Override
    public ConstantesInteresMora buscarInteresPorNumeroAnios(Integer numeroAnios) {
        return constantesInteresMoraFacade.buscarInteresPorNumeroAnios(numeroAnios);
    }

    @Override
    public Integer generarNuevaCabeceraTitulo(CabeceraTitulo nuevaCabeceraTitulo, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando cabecera del título...", sesion);
        if (!nuevaCabeceraTitulo.esCabeceraTituloValida()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR338);
        }
        // Crear el cabecera del título
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Creando cabecera del título...", sesion);

        cabeceraTituloFacade.create(nuevaCabeceraTitulo);
        // Si todo marcha bien enviar nombre del cabecera título
        return nuevaCabeceraTitulo.getSecTitulo();
    }

    @Override
    public CabeceraTitulo seleccionarCabeceraTitulo(Integer secTitulo) throws NewviExcepcion {
        if (ComunUtil.esNumeroPositivo(secTitulo)) {
            return cabeceraTituloFacade.find(secTitulo);
        } else {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR011);
        }
    }

    @Override
    public Integer actualizarCabeceraTitulo(CabeceraTitulo cabeceraTitulo, SesionDto sesion) throws NewviExcepcion {
        // Validar que los datos no sean incorrectos
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Validando cabecera del título...", sesion);
        if (!cabeceraTitulo.esCabeceraTituloValida()) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR338);
        }
        // Editar la cabecera del título
        LoggerNewvi.getLogNewvi(this.getClass()).debug("Editando cabecera del título...", sesion);

        cabeceraTituloFacade.edit(cabeceraTitulo);

        // Si todo marcha bien enviar nombre de la cabecera del título
        return cabeceraTitulo.getSecTitulo();
    }

    @Override
    public List<CabeceraTitulo> consultarCabeceraTitulos() {
        return cabeceraTituloFacade.buscarListaCabeceraTitulos();
    }
}
