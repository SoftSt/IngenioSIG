/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico.source;

import ec.com.newvi.faces.visorgeografico.Base;
import static ec.com.newvi.faces.visorgeografico.Base.getAllFields;
import ec.com.newvi.sic.util.ComunUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author israelavila
 */
public class TileWMS extends Source {

    public class Params extends Base {

        private String layers;
        private Boolean tiled;

        public Params(String layers, Boolean tiled) {
            this.layers = layers;
            this.tiled = tiled;
        }

        public String getLayers() {
            return layers;
        }

        public void setLayers(String layers) {
            this.layers = layers;
        }

        public Boolean getTiled() {
            return tiled;
        }

        public void setTiled(Boolean tiled) {
            this.tiled = tiled;
        }

    }

    public enum ServerType {
        GEOSERVER("geoserver");

        private final String serverType;

        private ServerType(String serverType) {
            this.serverType = serverType;
        }

        @Override
        public String toString() {
            return "'".concat(this.serverType).concat("'");
        }
    }

    private String url;
    private Params params;
    private ServerType serverType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

}
