/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import ec.com.newvi.sic.util.ComunUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author israelavila
 */
public class Base {

    @Override
    public String toString() {
        String cadenaObjetoJSON = "{";
        cadenaObjetoJSON = cadenaObjetoJSON.concat("'type':'").concat(this.getClass().getSimpleName()).concat("',");
        List<Field> listaCampos = new ArrayList<>();
        getAllFields(listaCampos, this.getClass());
        for (Field atributo : listaCampos) {
            try {
                atributo.setAccessible(true);
                Object valor = atributo.get(this);
                String nombre = atributo.getName();
                if (!ComunUtil.esNulo(valor) && !nombre.contentEquals("this$0")) {
                    cadenaObjetoJSON = cadenaObjetoJSON.concat("'").concat(nombre).concat("':");
                    if (valor instanceof String) {
                        cadenaObjetoJSON = cadenaObjetoJSON.concat("'").concat(valor.toString()).concat("'");
                    } else {
                        cadenaObjetoJSON = cadenaObjetoJSON.concat(valor.toString());
                    }
                    cadenaObjetoJSON = cadenaObjetoJSON.concat(",");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        cadenaObjetoJSON = cadenaObjetoJSON.substring(0, cadenaObjetoJSON.length() - 1);
        cadenaObjetoJSON = cadenaObjetoJSON.concat("}");
        return cadenaObjetoJSON;
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }
    
}
