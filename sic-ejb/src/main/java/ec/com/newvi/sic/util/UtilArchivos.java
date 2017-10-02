/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.util;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author israelavila
 */
public class UtilArchivos {

    public static String almacenarArchivoEnServidor(String nombreArchivo, String direccionArchivo, byte[] archivoEnBytes) throws NewviExcepcion {
        try {
            String nombreDireccionArchivo = direccionArchivo.concat("/").concat(nombreArchivo);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(nombreDireccionArchivo)));
            stream.write(archivoEnBytes);
            stream.close();
            return nombreDireccionArchivo;
        } catch (IOException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR207, ex);
        }
    }

}
