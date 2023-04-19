/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad.filtros;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import seguridad.AutorizacionBasica;

/**
 *
 * @author Panther
 */
public class FiltroAuthBasica implements javax.servlet.Filter {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String PREFIX = "Basic ";//NOTA QUE HAY UN ESPACIO AL FINAL, NO LO ELIMINES

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        boolean validauth = false;
        //--------VERIFICA SI ES UNA PETICIÓN HTTP VÁLIDA-----------//
        if (request instanceof HttpServletRequest) {
            //------OBTIENE LOS DATOS DE LA CABECERA AUTHORIZATION--------------//
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authheader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
            //----VERIFICA QUE LA CABECERA NO SEA NULL Y QUE INICIE CON EL PREFIJO INDICADO-----//
            if (authheader != null && authheader.startsWith(PREFIX)) {
                //-----EXTRAE LAS CREDENCIALES EN BASE64---------------//
                String credencialesBase64 = authheader.replaceFirst(PREFIX, "");
                //-----VERIFICA LAS CREDENCIALES ENVIAS----------------//
                if (AutorizacionBasica.autenticar(credencialesBase64)) {
                    validauth = true;
                    //----PASA LA PETICIÓN AL SIGUIENTE FILTRO DE VALIDACIÓN EN LA PILA DE FILTROS----//
                    //----SI YA NO HAY MAS FILTROS EN LA PILA PASA A LA EJECUCIÓN DEL RECURSO---------//
                    chain.doFilter(request, response);
                }
            }
            //--SI NO SE OBTIENE UNA AUTENTICACIÓN VÁLIDA EL FILTRO DEVUELVE UN CÓDIGO DE RESPUESTA 401 NO AUTORIZADO----//
            if (!validauth) {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");
                    httpServletResponse.getWriter().write("<h1>401 NO AUTORIZADO</h1>");
                }
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
