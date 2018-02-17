/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.sic.web.sesion;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author israelavila
 */
public class SesionFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        revisarSesionFinalizada(request, response, chain);
        
        chain.doFilter(req, res);
    }

    private void revisarSesionFinalizada(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && !session.isNew()) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("publico/login.xhtml");
        }
    }
    
    @Override
    public void destroy() {
    }

}
