/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import modelo.dao.EmpleadoDAO;
import modelo.pojos.Empleado;
import modelo.pojos.Respuesta;
import modelo.pojos.SesionToken;
import seguridad.AutorizacionTokenJWT;

/**
 * REST Web Service
 *
 * @author Panther
 */
@Path("bsc/acceso")
public class AccesoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AccesoWS
     */
    public AccesoWS() {
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta login(
            @FormParam("email") String email,
            @FormParam("contrasena") String contrasena
    ) {
        Respuesta res = new Respuesta();
        //--------VALIDAR PARAMETROS DE ENTRADA--------------//
        if (email == null || email.trim().isEmpty()) {
            res.setError(true);
            res.setMensaje("El email es un dato requerido...");
            return res;
        } else if (contrasena == null || contrasena.trim().isEmpty()) {
            res.setError(true);
            res.setMensaje("La contraseña es un dato requerido...");
            return res;
        }
        //--------VALIDAR CREDENCIALES DEL EMPLEADO----------//
        Empleado e = EmpleadoDAO.login(email, contrasena);
        if (e == null) {
            res.setError(true);
            res.setMensaje("No se encontró ningún empleado con esas credenciales...");
            return res;
        }
        //------GENERAR TOKEN CON JWT Y DEVOLVERLO-----------//
        SesionToken s = new SesionToken();
        s.setId(e.getId());
        s.setNombre(e.getNombres());
        s.setEmail(e.getEmail());
        s = AutorizacionTokenJWT.generarToken(s);
        if (s == null || s.getTokenacceso() == null || s.getTokenacceso().isEmpty()) {
            res.setError(true);
            res.setMensaje("No se puede generar el token de acceso...");
        } else {
            res.setError(false);
            res.setMensaje("Bienvenido: " + s.getNombre());
            res.setSesiontoken(s);
        }
        //---------------------------------------------------//
        return res;
    }

    @POST
    @Path("valiartoken")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta validarToken(
            @FormParam("token") String token
    ) {
        Respuesta res = new Respuesta();
        //--------VALIDAR PARAMETROS DE ENTRADA--------------//
        if (token == null || token.trim().isEmpty()) {
            res.setError(true);
            res.setMensaje("El token es un dato requerido...");
            return res;
        }
        //--------------VALIDAR TOKEN DE JWT-----------------//
        res = AutorizacionTokenJWT.validarToken(token);
        //---------------------------------------------------//
        return res;
    }
}
