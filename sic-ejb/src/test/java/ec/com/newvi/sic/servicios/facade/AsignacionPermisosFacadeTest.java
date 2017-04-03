/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.facade;

import ec.com.newvi.sic.GeneralTest;
import ec.com.newvi.sic.dao.AsignacionPermisosFacade;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Funcionalidades;
import ec.com.newvi.sic.modelo.Permisos;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author NEWVI
 */
@RunWith(PowerMockRunner.class)
public class AsignacionPermisosFacadeTest extends GeneralTest {

    @InjectMocks
    private AsignacionPermisosFacade asignacionPermisosFacade;

    @Before
    public void setUp() {
        try {
            MockitoAnnotations.initMocks(this);
            when(asignacionPermisosFacade.getEntityManager().createQuery(anyString())).thenReturn(q);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    public void testBuscarAsignacionesPermisoPorPermisoFuncionalidad() throws NewviExcepcion {
        Permisos permiso = new Permisos();
        Funcionalidades funcionalidad = new Funcionalidades();
        AsignacionPermisos resultadoEsperado = maquetaAsignacionPermiso();
        when(asignacionPermisosFacade.getEntityManager().createQuery(anyString()).getSingleResult()).thenReturn(maquetaAsignacionPermiso());
        AsignacionPermisos resultadoRecibido = asignacionPermisosFacade.buscarAsignacionesPermisoPorPermisoFuncionalidad(permiso, funcionalidad, null);
        assertEquals(resultadoEsperado.getPefId(), resultadoRecibido.getPefId());
    }

}
