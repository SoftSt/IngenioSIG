/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic;

import ec.com.newvi.sic.dto.SesionDto;
import ec.com.newvi.sic.enums.EnumEstadoRegistro;
import ec.com.newvi.sic.enums.EnumTipoPermisos;
import ec.com.newvi.sic.modelo.AsignacionPermisos;
import ec.com.newvi.sic.modelo.Usuarios;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Ignore;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

/**
 *
 * @author NEWVI
 */
@Ignore
public class GeneralTest {
    
    @Mock
    protected EntityManager em;
    @Mock
    protected Query q;
    
    public void setearValorPrivado(Object objetoBB, String nombrePropiedad, Object valorPropiedad){
        Whitebox.setInternalState(objetoBB, nombrePropiedad, valorPropiedad);
    }
    
    protected Usuarios maquetaUsuario() {
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setUsuNombres("Israel");
        nuevoUsuario.setUsuApellidos("Aviles");
        nuevoUsuario.setUsuCargo("Gerente");
        nuevoUsuario.setAudIngUsu("Done");
        nuevoUsuario.setUsuDepartamento("Sistemas");
        nuevoUsuario.setUsuDireccion("Manabi");
        nuevoUsuario.setUsuEmai("iaviles@animo.com");
        nuevoUsuario.setUsuFax("023333333");
        Date fechaIngreso = Calendar.getInstance().getTime();
        nuevoUsuario.setUsuFingreso(fechaIngreso);
        nuevoUsuario.setUsuFnacimiento(fechaIngreso);
        nuevoUsuario.setUsuFoto("");
        nuevoUsuario.setUsuNombres("iaviles123");
        nuevoUsuario.setUsuNumingreso(12);
        nuevoUsuario.setUsuPalabraclave("Sandia");
        nuevoUsuario.setUsuPassword("12345");
        nuevoUsuario.setUsuResponsable("Juan");
        nuevoUsuario.setUsuTelefono("222222222");
        nuevoUsuario.setUsuUsuario("iaviles123");
        nuevoUsuario.setUsuEstado(EnumEstadoRegistro.A);
        return nuevoUsuario;
    }
    
    protected AsignacionPermisos maquetaAsignacionPermiso() {
        Date hoy = Calendar.getInstance().getTime();
        AsignacionPermisos asignacionPermiso = new AsignacionPermisos();
        asignacionPermiso.setAudIngFec(hoy);
        asignacionPermiso.setAudIngIp("0.0.0.0");
        asignacionPermiso.setAudIngUsu("maqueta");
        asignacionPermiso.setAudModFec(hoy);
        asignacionPermiso.setAudModIp("0.0.0.0");
        asignacionPermiso.setAudModUsu("maqueta");
        asignacionPermiso.setPefEstado(EnumEstadoRegistro.A);
        asignacionPermiso.setPefId(0);
        asignacionPermiso.setPefOperaciones(EnumTipoPermisos.N);
        return asignacionPermiso;
    }
    
    protected SesionDto maquetaSesionDto(){
    SesionDto sesionDto = new SesionDto();
    Usuarios usuario = maquetaUsuario();
    
    sesionDto.setDireccionIP("192.168.56.1");
    sesionDto.setNombreServidor("");
    sesionDto.setUsuarioRegistrado(usuario);
    
    return sesionDto;
    }
    
    
}
