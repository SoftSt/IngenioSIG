/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.impl;

import ec.com.newvi.sic.dto.UsuarioDto;
import ec.com.newvi.sic.GeneralTest;
import ec.com.newvi.sic.dao.UsuariosFacade;
import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.modelo.Usuarios;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.*;
import org.mockito.Mockito;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.easymock.PowerMock.mockStatic;

/**
 *
 * @author NEWVI
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SeguridadesServicioImpl.class, UsuariosFacade.class})
public class SeguridadesServicioImplTest extends GeneralTest {
    
    @Mock
    private UsuariosFacade usuariosFacade;
    
    private SeguridadesServicioImpl seguridadesServicioImpl;
    
    @Before
    public void setUp() {
        seguridadesServicioImpl = PowerMockito.mock(SeguridadesServicioImpl.class, Mockito.CALLS_REAL_METHODS);
        usuariosFacade = PowerMockito.mock(UsuariosFacade.class, Mockito.RETURNS_MOCKS);
        setearValorPrivado(seguridadesServicioImpl, "usuariosFacade", usuariosFacade);
    }
    
    /**
     * Test of generarNuevoUsuario method, of class SeguridadesServicioImpl.
     */
    @Test
    public void testGenerarNuevoUsuario() throws NewviExcepcion {
        mockStatic(System.class);
        System.out.println("generarNuevoUsuario");
        Usuarios nuevoUsuario = maquetaUsuario();
        SesionDto nuevaSesionDto =maquetaSesionDto();
        String expResult = "iaviles123";
        String result = seguridadesServicioImpl.generarNuevoUsuario(nuevoUsuario, nuevaSesionDto);
        assertEquals(expResult, result);
    }
    
}
