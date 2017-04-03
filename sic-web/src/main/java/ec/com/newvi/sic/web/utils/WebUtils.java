/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.web.utils;

import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author israelavila
 */
public class WebUtils {

    public WebUtils() {
    }

    /**
     * Devuelve el contexto de FacesConext.
     *
     * @return contexto
     */
    public static FacesContext obtenerContexto() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Devuelve la petición recibida del contexto FacesContext.
     *
     * @return petición HTTP
     */
    public static HttpServletRequest obtenerPeticion() {
        return (HttpServletRequest) obtenerContexto().getExternalContext().getRequest();
    }

    /**
     * Genera un árbol de tipo DefaultTreeNode, dado un conjunto de elementos,
     * un objeto raíz, y el método para obtener los hijos del objeto.
     *
     * @param elementos Conjunto de elementos a colocar en la raíz
     * @param padre Elemento raíz del árbol
     * @param nombreAtributoListado Cadena que indica el nombre del método que
     * contiente los hijos del objeto.
     * @return Objeto de tipo DefaultTreeNode con los objetos en forma de árbol.
     * @throws NewviExcepcion
     */
    public static TreeNode generarArbol(List elementos, TreeNode padre, String nombreAtributoListado) throws NewviExcepcion {
        for (Object o : elementos) {

            TreeNode t = new DefaultTreeNode(o, padre);
            try {
                List hijos = (List) o.getClass().getMethod(nombreAtributoListado).invoke(o);
                if (hijos != null
                        && !((List) hijos).isEmpty()) {
                    generarArbol(hijos, t, nombreAtributoListado);
                }
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
                throw new NewviExcepcion(EnumNewviExcepciones.ERR020, ex);
            }
        }
        return padre;
    }

    /**
     * Devuelve una cadena encriptada con el algoritmo SHA1.
     *
     * @param clave Cadena que contiene la clave a encriptar.
     * @return Cadena encriptada en SHA1
     * @throws NewviExcepcion
     */
    public static String encriptarCadenaEnSHA1(String clave) throws NewviExcepcion {
        try {
            byte[] buffer = clave.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(buffer);
            byte[] digest = md.digest();
            String valorHash = "";
            for (byte aux : digest) {
                int b = aux & 0xff;
                if (Integer.toHexString(b).length() == 1) {
                    valorHash += "0";
                }
                valorHash += Integer.toHexString(b);
            }
            return valorHash;
        } catch (NoSuchAlgorithmException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR321, ex);
        }
    }

    /**
     * Redireccionar a una URL dada.
     *
     * @param url Cadena que contiene la URL al que se va a redireccionar.
     * @throws NewviExcepcion
     */
    public static void redireccionar(String url) throws NewviExcepcion {
        try {
            FacesContext faces = FacesContext.getCurrentInstance();
            faces.getExternalContext().redirect(faces.getExternalContext().getRequestContextPath() + url);
        } catch (IOException ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR101, ex);
        } catch (Exception ex) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, ex);
        }
    }

}
