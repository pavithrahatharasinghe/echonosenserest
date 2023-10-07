package com.example.echonosenserest.services;

import com.example.echonosenserest.database.NotificationDBUtils;
import com.example.echonosenserest.models.Notification;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/notifications")
public class NotificationServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotifications() throws SQLException {
        List<Notification> notifications = NotificationDBUtils.getAllNotifications();
        return Response.ok(notifications).build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotificationsForUser(@PathParam("userId") int userId) throws SQLException {
        List<Notification> notifications = NotificationDBUtils.getAllNotificationsForUser(userId);
        return Response.ok(notifications).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNotification(Notification notification) throws SQLException {
        boolean isAdded = NotificationDBUtils.addNotification(notification);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(notification).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding notification.").build();
        }
    }

    @PUT
    @Path("/{notificationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNotification(@PathParam("notificationId") int notificationId, Notification notification) throws SQLException {
        boolean isUpdated = NotificationDBUtils.updateNotification(notificationId, notification);
        if (isUpdated) {
            return Response.ok(notification).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating notification.").build();
        }
    }

    @DELETE
    @Path("/{notificationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNotification(@PathParam("notificationId") int notificationId) throws SQLException {
        boolean isDeleted = NotificationDBUtils.deleteNotification(notificationId);
        if (isDeleted) {
            return Response.ok().entity("Notification deleted successfully.").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error deleting notification.").build();
        }
    }
}
