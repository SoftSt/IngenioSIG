/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.servicios.facade;

import ec.com.newvi.sic.dao.UsuariosFacade;
import ec.com.newvi.sic.GeneralTest;
import ec.com.newvi.sic.modelo.Usuarios;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author NEWVI
 */
@RunWith(PowerMockRunner.class)
public class UsuariosFacadeTest extends GeneralTest {

    @InjectMocks
    private UsuariosFacade usuariosFacade;

    public UsuariosFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            MockitoAnnotations.initMocks(this);
            when(usuariosFacade.getEntityManager().createQuery(anyString())).thenReturn(q);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBuscarUsuarios() {
        List<Usuarios> listaUsuariosEsperado = new ArrayList<Usuarios>();
        when(usuariosFacade.getEntityManager().createQuery(anyString()).getResultList()).thenReturn(listaUsuariosEsperado);
        List<Usuarios> listaUsuariosRecibido = usuariosFacade.buscarUsuarios();
        assertEquals(listaUsuariosEsperado.size(), listaUsuariosRecibido.size());
    }

}
