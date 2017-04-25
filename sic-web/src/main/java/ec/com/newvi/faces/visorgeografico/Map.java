/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import ec.com.newvi.faces.visorgeografico.layer.LayerCollection;


/**
 *
 * @author israelavila
 */
public class Map extends Base {
    private View view;
    private LayerCollection layers;

    public Map() {
        this.view = new View();
        this.layers = new LayerCollection();
    }
    
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public LayerCollection getLayers() {
        return layers;
    }

    public void setLayers(LayerCollection layers) {
        this.layers = layers;
    }
    
    
}
