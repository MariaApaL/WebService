package edu.fpdual.webservices.controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import edu.fpdual.webservices.service.UserService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;


@Path("/user")

public class UserController{

    private final UserService userService;

    public UserController() {

        this.UserService = new UserService(new UserManagerImpl());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(userService.
    }


    }

