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

    // Define a method to get a subscription plan by ID
    @GET
    @Path("/{subscriptionPlanId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriptionPlanById(@PathParam("subscriptionPlanId") int subscriptionPlanId) throws SQLException {
        // Replace this with your actual logic to retrieve the subscription plan from your data source
        Subscription plan = SubscriptionDBUtils.getSubscriptionPlanById(subscriptionPlanId);

        if (plan != null) {
            // Return the plan with a 200 OK response
            return Response.status(Response.Status.OK).entity(plan).build();
        } else {
            // Return a 404 Not Found response if the plan is not found
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



    // Add more methods for editing and deleting subscriptions as needed...
}
