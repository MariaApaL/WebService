package edu.fpdual.webservices.controller;


import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.impl.SuggestionsManagerImpl;
import edu.fpdual.webservices.service.SuggestionsService;
import jakarta.ws.rs.*;
import edu.fpdual.webservices.service.UserService;
import edu.fpdual.webservices.model.manager.impl.UserManagerImpl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;


@Path("/user")

public class UserController{

    private final UserService userService;
   // private final SuggestionsService suggestionsService;


    public UserController() {

        this.userService = new UserService(new UserManagerImpl());
       // this.suggestionsService = new SuggestionsService((new SuggestionsManagerImpl()));
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response findUser(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        return Response.ok().entity(userService.findUser(name)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/validate")

    public Response validateUser(UserDao user) throws SQLException, ClassNotFoundException {
        if (user==null) {
            return Response.status(400).entity("Incorrect Parameters").build();
        } else {
            return Response.ok().entity(userService.validateUser(user.getPlayer_name(),user.getPassword())).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(UserDao user) {
        try {
            boolean delete = userService.validateUser(user.getPlayer_name(), user.getPassword());
            if (delete) {
                if (userService.deleteUser(user) ){
                    return Response.status(200).entity(user).build();
                } else {
                    return Response.status(304).entity("User Was Not Deleted").build();
                }
            } else {
                return Response.status(404).entity("User Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUserReg(UserDao user) {
        try {
            int registedUser = userService.insertUserReg(user);
            if (registedUser > 1) {
                return Response.status(201).entity(userService.findUser(user.getPlayer_name())).build();
            } else {
                return Response.status(500).entity("Internal Error During Creating New User").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(UserDao user) {
        try {
            UserDao userFound = userService.findUser(user.getPlayer_name());
            if (userFound!=null) {
                if (userService.update(user)) {
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

