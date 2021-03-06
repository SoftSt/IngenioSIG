/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import ec.com.newvi.sic.util.ComunUtil;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

/**
 *
 * @author israelavila
 */
@FacesRenderer(componentFamily = VisorGeografico.COMPONENT_FAMILY, rendererType = VisorGeograficoRenderer.RENDERER_TYPE)
public class VisorGeograficoRenderer extends CoreRenderer {

    public static final String RENDERER_TYPE = "ec.com.newvi.faces.VisorGeograficoRenderer";

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        VisorGeografico visorGeografico = (VisorGeografico) component;
        encodeMarkup(context, visorGeografico);
        encodeScript(context, visorGeografico);
    }

    protected void encodeMarkup(FacesContext context, VisorGeografico visor) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", visor);
        writer.writeAttribute("id", visor.getClientId(), null);
        writer.writeAttribute("style", this.generarEstiloMapa(visor), null);
        writer.endElement("div");
    }

    protected String generarEstiloMapa(VisorGeografico visor) {
        String ancho = visor.getWidth();
        String alto = visor.getHeight();
        return "width: ".concat(ancho).concat(";height:").concat(alto).concat(";");
    }

    protected void encodeScript(FacesContext context, VisorGeografico visor) throws IOException {

        String clientId = visor.getClientId();
        String widgetVar = visor.resolveWidgetVar();

        WidgetBuilder wb = getWidgetBuilder(context);

        wb.init("VisorGeografico", widgetVar, clientId);

        if (visor.getWidth() != null) {
            wb.attr("width", visor.getWidth());
        }

        if (visor.getHeight() != null) {
            wb.attr("height", visor.getHeight());
        }

        if (!ComunUtil.esNulo(visor.getMap())) {
            wb.attr("map", visor.getMap().toString());
        }

        wb.finish();
    }
}
