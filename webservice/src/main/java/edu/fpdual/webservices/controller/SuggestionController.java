package edu.fpdual.webservices.controller;

import edu.fpdual.webservices.model.dao.Suggestion;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.SuggestionsManager;
import edu.fpdual.webservices.model.manager.impl.SuggestionsManagerImpl;
import edu.fpdual.webservices.model.manager.impl.UserManagerImpl;
import edu.fpdual.webservices.service.SuggestionsService;
import edu.fpdual.webservices.service.UserService;
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertSuggestion (@FormParam("name") String name , @FormParam("suggestion") String suggestion) {
        try {
            int suggest=suggestionsService.insertSuggestion(name, suggestion);
            if(suggest>0) {

                    return Response.status(200).entity(suggestionsService.findSuggestion(name)).build();
                } else {
                    return Response.status(500).entity("Internal error").build();
                }

            }

         catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }

    }


}
