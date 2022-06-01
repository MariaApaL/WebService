package edu.fpdual.webservices.controller;


import edu.fpdual.webservices.model.dao.UserDao;
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
    public Response findUser(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        return Response.ok().entity(userService.findUser(name)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response validateUser(UserDao user) throws SQLException, ClassNotFoundException {
        if (user==null) {
            return Response.status(400).entity("Incorrect Parameters").build();
        } else {
            return Response.ok().entity(userService.validateUser(user)).build();
        }
    }

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("name") String name) {
        try {
            boolean deleteUser = userService.findUser(name);
            if (deleteUser!=false) {
                if (userService.deleteUser(name)) {
                    return Response.status(200).entity(deleteUser).build();
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
    public Response insertUserReg() {
        try {
            int registedUser = userService.insertUserReg();
            if (registedUser > 1) {
                return Response.status(201).entity(userService.findUser(name)).build();
            } else {
                return Response.status(500).entity("Internal Error During Creating New User").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @PUT
    @Path("/{password}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam("password") String password, @PathParam("name") String name) {
        try {
            boolean deletedUser = userService.findUser(name);
            if (deletedUser != false) {
                if (userService.updatePassword(password,name)) {
                    return Response.status(200).entity(userService.findUser(name)).build();
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

