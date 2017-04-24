/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import ec.com.newvi.sic.util.ComunUtil;
import java.lang.reflect.Field;

/**
 *
 * @author israelavila
 */
public class Base {

    @Override
    public String toString() {
        String cadenaObjetoJSON = "{";
        cadenaObjetoJSON = cadenaObjetoJSON.concat("'type':'").concat(this.getClass().getSimpleName()).concat("',");
        Field[] listaCampos = this.getClass().getDeclaredFields();
        for (Field atributo : listaCampos) {
            atributo.setAccessible(true);
            Object valor = new Object();
            try {
                valor = atributo.get(this);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
            String nombre = atributo.getName();
            cadenaObjetoJSON = cadenaObjetoJSON.concat("'").concat(nombre).concat("':");
            if (ComunUtil.esNulo(valor)) {
                cadenaObjetoJSON = cadenaObjetoJSON.concat("''");
            } else {
                if (valor instanceof String) {
                    cadenaObjetoJSON = cadenaObjetoJSON.concat("'").concat(valor.toString()).concat("'");
                } else {
                    cadenaObjetoJSON = cadenaObjetoJSON.concat(valor.toString());
                }
            }
            cadenaObjetoJSON = cadenaObjetoJSON.concat(",");
        }
        cadenaObjetoJSON = cadenaObjetoJSON.substring(0, cadenaObjetoJSON.length() - 1);
        cadenaObjetoJSON = cadenaObjetoJSON.concat("}");
        return cadenaObjetoJSON;
    }
}
