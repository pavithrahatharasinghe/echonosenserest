package com.example.echonosenserest.services;

import com.example.echonosenserest.models.Subscription;
import com.example.echonosenserest.database.SubscriptionDBUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/subscriptions")
public class SubscriptionServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = SubscriptionDBUtils.getAllSubscriptions();
        return Response.ok(subscriptions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSubscription(Subscription subscription) throws SQLException {
        boolean isAdded = SubscriptionDBUtils.addSubscription(subscription);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(subscription).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding subscription.").build();
        }
    }
    @PUT
    @Path("/{subscriptionID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSubscription(@PathParam("subscriptionID") int subscriptionID, Subscription subscription) throws SQLException {
        boolean isUpdated = SubscriptionDBUtils.updateSubscription(subscription);
        if (isUpdated) {
            return Response.ok(subscription).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating subscription.").build();
        }
    }

    @DELETE
    @Path("/{subscriptionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSubscription(@PathParam("subscriptionID") int subscriptionID) throws SQLException {
        boolean isDeleted = SubscriptionDBUtils.deleteSubscription(subscriptionID);
        if (isDeleted) {
            return Response.ok().entity("Subscription deleted successfully.").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error deleting subscription.").build();
        }
    }


    // Add more methods for editing and deleting subscriptions as needed...
}
