package edu.fpdual.webservices.controller;

import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.SuggestionsManager;
import edu.fpdual.webservices.model.manager.impl.SuggestionsManagerImpl;
import edu.fpdual.webservices.model.manager.impl.UserManagerImpl;
import edu.fpdual.webservices.service.SuggestionsService;
import edu.fpdual.webservices.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response InsertSuggestion () {
        try {
            boolean update = userService.validateUser(user.getPlayer_name(),user.getPassword());
            if (update) {
                if (userService.updatePassword(user,password)) {
                    return Response.status(200).entity(userService.findUser(user.getPlayer_name())).build();
                } else {
                    return Response.status(500).entity("Internal Error During User Update").build();
                }
            } else {
                return Response.status(404).entity("User Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }


}
