package com.example.echonosenserest.services;


import com.example.echonosenserest.database.UserDBUtils;
import com.example.echonosenserest.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/users")
public class UserServices {

    private static final Logger LOGGER = Logger.getLogger(UserServices.class.getName());


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



    @GET
    @Path("/{id}/password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPasswordById(@PathParam("id") int userId) throws SQLException {
        String password = UserDBUtils.getUserPasswordById(userId);
        if (password != null) {
            return Response.ok(password).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
    }
    @PUT
    @Path("/{id}/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserPassword(@PathParam("id") int userId, String newPassword) throws SQLException {
        if (UserDBUtils.updateUserPassword(userId, newPassword)) {
            return Response.ok().entity("Password updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error updating password").build();
    }
    @GET
    @Path("/{id}/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserNameById(@PathParam("id") int userId) throws SQLException {
        String[] name = UserDBUtils.getUserNameById(userId);
        if (name != null) {
            // You can return the first name and last name as separate fields in a JSON object
            return Response.ok(new NameResponse(name[0], name[1])).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
    }

    // Create a simple NameResponse class to represent the first name and last name
    private static class NameResponse {
        private final String firstName;
        private final String lastName;

        public NameResponse(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    @PUT
    @Path("/{id}/name")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserName(@PathParam("id") int userId, UserNameUpdateRequest nameUpdateRequest) throws SQLException {
        if (UserDBUtils.updateUserName(userId, nameUpdateRequest.getFirstName(), nameUpdateRequest.getLastName())) {
            return Response.ok().entity("Name updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error updating name").build();
    }
    public class UserNameUpdateRequest {
        private String firstName;
        private String lastName;

        // Getter and setter methods for firstName and lastName

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @GET
    @Path("/byemail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserIdByEmail(@PathParam("email") String email) throws SQLException {
        int userId = UserDBUtils.getUserIdByEmail(email);
        if (userId != -1) {
            return Response.ok(new UserIdResponse(userId)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found with the provided email").build();
    }

    // Create a simple UserIdResponse class to represent the user's ID
    private static class UserIdResponse {
        private final int userId;

        public UserIdResponse(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLogin(User user) throws SQLException {
        // Log the email (Avoid logging passwords, even in development environments!)
        LOGGER.log(Level.INFO, "Attempting login with Email: {0}", user.getEmail());

        User loggedInUser = UserDBUtils.getUserByEmailAndPassword(user.getEmail(), user.getPassword());

        if (loggedInUser != null) {
            // Return success response with the logged-in user's data
            return Response.ok(loggedInUser).build();
        } else {
            // Return error response
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }







}
