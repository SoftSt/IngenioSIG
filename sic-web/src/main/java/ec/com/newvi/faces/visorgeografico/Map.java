/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;


/**
 *
 * @author israelavila
 */
public class Map extends Base {
    private View view;
    private Collection layers;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Collection getLayers() {
        return layers;
    }

    public void setLayers(Collection layers) {
        this.layers = layers;
    }
    
    
}
