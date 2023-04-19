/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.dao.EmpleadoDAO;
import modelo.pojos.Empleado;
import modelo.pojos.Respuesta;

/**
 * REST Web Service
 *
 * @author Panther
 */
@Path("empleado")
public class EmpleadoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpleadoWS
     */
    public EmpleadoWS() {
    }

    @GET
    @Path("buscarporid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta getPorId(
            @PathParam("id") Integer id
    ) {
        Respuesta res = new Respuesta();
        try {
            Empleado e = EmpleadoDAO.getEmpleadoPrueba();
            if (e != null) {
                res.setEmpleado(e);
                res.setError(false);
                res.setMensaje("OK");
            } else {
                res.setError(true);
                res.setMensaje("No se encontró el empleado con ese ID");
            }
        } catch (Exception ex) {
            res.setError(true);
            res.setMensaje(ex.getMessage());
        }
        return res;
    }

    @GET
    @Path("buscartodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta getTodos() {
        Respuesta res = new Respuesta();
        try {
            List<Empleado> l = EmpleadoDAO.getEmpleadosPrueba(500);
            if (l != null && !l.isEmpty()) {
                res.setLista(l);
                res.setError(false);
                res.setMensaje("OK");
            } else {
                res.setError(true);
                res.setMensaje("No se puede consultar a los empleados...");
            }
        } catch (Exception ex) {
            res.setError(true);
            res.setMensaje(ex.getMessage());
        }
        return res;
    }
}
