package com.example.echonosenserest.services;

import com.example.echonosenserest.database.UserSubscriptionDBUtils;
import com.example.echonosenserest.models.UserSubscription;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
@Path("/user-subscriptions")
public class UserSubscriptionServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserSubscriptions() throws SQLException {
        List<UserSubscription> userSubscriptions = UserSubscriptionDBUtils.getAllUserSubscriptions();
        return Response.ok(userSubscriptions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserSubscription(UserSubscription userSubscription) throws SQLException {
        boolean isAdded = UserSubscriptionDBUtils.addUserSubscription(userSubscription);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(userSubscription).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding user subscription.").build();
        }
    }

    // ... other endpoints for update, delete, etc. ...
}
