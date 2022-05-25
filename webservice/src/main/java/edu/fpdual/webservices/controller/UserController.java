package edu.fpdual.webservices.controller;


import jakarta.ws.rs.*;
import edu.fpdual.webservices.service.UserService;
import edu.fpdual.webservices.model.manager.impl.UserManagerImpl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;


@Path("/user")

public class UserController{

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = new UserService(new UserManagerImpl());
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(userService.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response findUserByName(@PathParam("name") String player_name) {
        try {
            if (player_name.equals("") || player_name.isEmpty()) {
                return Response.status(400).entity("Incorrect Parameters").build();
            } else {
                return Response.ok().entity(userService.findUserByName(player_name)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        try {
            UserDao deleteId = userService.findById(id);
            if (deleteId != null) {
                if (userService.delete(id)) {
                    return Response.status(200).entity(deleteId).build();
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
    public Response register(UserDao userDao) {
        try {
            int registedUser = userService.register(userDao);
            if (registedUser > 0) {
                return Response.status(201).entity(userService.findById(registedUser)).build();
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
    public Response update(UserDao userDao) {
        try {
            UserDao deletedId = userService.findById(userDao.getIdplayer());
            if (deletedId != null) {
                if (userService.update(userDao)) {
                    return Response.status(200).entity(userService.findById(userDao.getIdplayer())).build();
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer idplayer) {
        try {
            if (idplayer == null) {
                return Response.status(400).entity("Incorrect Parameters").build();
            } else {
                return Response.ok().entity(userService.findById(idplayer)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    }

