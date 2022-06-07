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



    public UserController() {

        this.userService = new UserService(new UserManagerImpl());

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response findByUser(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        return Response.ok().entity(userService.findByName(name)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        try {
            if (id == null) {
                return Response.status(400).entity("Incorrect Parameters").build();
            } else {
                return Response.ok().entity(userService.findById(id)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/validate")

    public Response validate(UserDao user) throws SQLException, ClassNotFoundException {
        if (user==null) {
            return Response.status(400).entity("Incorrect Parameters").build();
        } else {
            return Response.ok().entity(userService.validate(user.getIdplayer())).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(UserDao user) {
        try {
            boolean delete = userService.validate(user.getIdplayer());
            if (delete) {
                if (userService.delete(user) ){
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
    public Response insert(UserDao user) {
        try {
            int registedUser = userService.insert(user);
            if (registedUser > 1) {
                return Response.status(201).entity(userService.findByName(user.getPlayer_name())).build();
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
                UserDao userToDelete = userService.findById(user.getIdplayer());
                if (userToDelete != null) {
                    if (userService.update(user)) {
                        return Response.status(200).entity(userService.findById(user.getIdplayer())).build();
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



