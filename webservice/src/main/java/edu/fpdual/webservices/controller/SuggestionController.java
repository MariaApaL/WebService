package edu.fpdual.webservices.controller;

import edu.fpdual.webservices.model.dao.Suggestion;

import edu.fpdual.webservices.model.manager.impl.SuggestionsManagerImpl;

import edu.fpdual.webservices.service.SuggestionsService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/suggest")
public class SuggestionController {

    private final SuggestionsService suggestionsService;


    public SuggestionController() {

        this.suggestionsService = new SuggestionsService((new SuggestionsManagerImpl()));
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findSuggestion(@PathParam("id") Integer id) {
        try {
            if (id == null) {
                return Response.status(400).entity("Incorrect Parameters").build();
            } else {
                return Response.ok().entity(suggestionsService.findSuggestion(id)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertSuggestion(@FormParam("name") String name, @FormParam("suggestion") String suggestion) {
        try {
            int suggest = suggestionsService.insertSuggestion(name, suggestion);
            if (suggest > 0) {

                return Response.status(200).entity(suggestionsService.findSuggestion(suggest)).build();
            } else {
                return Response.status(500).entity("Internal error").build();
            }

        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/validate")

    public Response validate(Suggestion sugu) throws SQLException, ClassNotFoundException {
        if (sugu==null) {
            return Response.status(400).entity("Incorrect Parameters").build();
        } else {
            return Response.ok().entity(suggestionsService.validate(sugu.getIdSuggestion())).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Suggestion sugu) {
        try {
            boolean delete = suggestionsService.validate(sugu.getIdSuggestion());
            if (delete) {
                if (suggestionsService.delete(sugu) ){
                    return Response.status(200).entity(sugu).build();
                } else {
                    return Response.status(304).entity("sugu Was Not Deleted").build();
                }
            } else {
                return Response.status(404).entity("sugu Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Suggestion sugu) {
        try {
            Suggestion suguToDelete = suggestionsService.findSuggestion(sugu.getIdSuggestion());
            if (suguToDelete != null) {
                if (suggestionsService.update(sugu)) {
                    return Response.status(200).entity(suggestionsService.findSuggestion(sugu.getIdSuggestion())).build();
                } else {
                    return Response.status(500).entity("Internal Error During City Update").build();
                }
            } else {
                return Response.status(404).entity("City Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

}
