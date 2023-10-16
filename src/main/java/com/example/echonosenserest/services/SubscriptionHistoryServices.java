package com.example.echonosenserest.services;

import com.example.echonosenserest.database.subscriptionhistoryDBUtils;
import com.example.echonosenserest.models.SubscriptionHistory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/subscriptionhistories")
public class SubscriptionHistoryServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSubscriptionHistories() throws SQLException {
        List<SubscriptionHistory> histories = subscriptionhistoryDBUtils.getAllSubscriptionHistories();
        return Response.ok(histories).build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriptionHistoriesByUserId(@PathParam("userId") int userId) throws SQLException {
        List<SubscriptionHistory> histories = subscriptionhistoryDBUtils.getSubscriptionHistoriesByUserId(userId);

        if (!histories.isEmpty()) {
            // Return the histories with a 200 OK response
            return Response.status(Response.Status.OK).entity(histories).build();
        } else {
            // Return a 404 Not Found response if no histories are found for the user
            return Response.status(Response.Status.NOT_FOUND).entity("No subscription histories found for user ID: " + userId).build();
        }
    }
}
