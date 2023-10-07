package com.example.echonosenserest.services;



import com.example.echonosenserest.database.UserDBUtils;
import com.example.echonosenserest.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UserServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws SQLException {
        List<User> users = UserDBUtils.getAllUsers();
        return Response.ok(users).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws SQLException {
        if (UserDBUtils.addUser(user)) {
            return Response.status(Response.Status.CREATED).entity("User added successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error adding user").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int userId) throws SQLException {
        User user = UserDBUtils.getUserById(userId);
        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int userId, User user) throws SQLException {
        user.setUserId(userId); // Ensure the ID is set correctly
        if (UserDBUtils.updateUser(user)) {
            return Response.ok().entity("User updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error updating user").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int userId) throws SQLException {
        if (UserDBUtils.deleteUser(userId)) {
            return Response.ok().entity("User deleted successfully").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
    }

    @PUT
    @Path("/status/{id}/{status}")
    public Response changeUserStatus(@PathParam("id") int userId, @PathParam("status") String status) throws SQLException {
        if (UserDBUtils.changeUserStatus(userId, status)) {
            return Response.ok().entity("User status changed successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error changing user status").build();
    }
}
