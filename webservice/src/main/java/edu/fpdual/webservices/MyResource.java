package edu.fpdual.webservices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */

//localhost:8080/FpDualWebsevices/
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        return "Got it!";
    }

    @GET
    @Path("nombre")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNombre(@QueryParam("name") String nombre) {

        return "Got it "+nombre+"!";
    }


    @POST
    @Path("nombre/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNombrePorPath(@PathParam("name") String nombre) {
        return "Got it "+nombre+"!";
    }
}

