package com.example.echonosenserest.services;


import com.example.echonosenserest.database.PaymentDBUtils;
import com.example.echonosenserest.models.Payment;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/payments")
public class PaymentServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPayments() throws SQLException {
        List<Payment> payments = PaymentDBUtils.getAllPayments();
        return Response.ok(payments).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(Payment payment) throws SQLException {
        boolean isAdded = PaymentDBUtils.addPayment(payment);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(payment).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding payment.").build();
        }
    }

    @GET
    @Path("/{paymentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentByID(@PathParam("paymentID") int paymentID) throws SQLException {
        Payment payment = PaymentDBUtils.getPaymentByID(paymentID);
        if (payment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Payment not found.").build();
        }
        return Response.ok(payment).build();
    }

    @PUT
    @Path("/{paymentID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePayment(@PathParam("paymentID") int paymentID, Payment payment) throws SQLException {
        boolean isUpdated = PaymentDBUtils.updatePayment(paymentID, payment);
        if (isUpdated) {
            return Response.ok().entity("Payment updated successfully.").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating payment.").build();
        }
    }

    @DELETE
    @Path("/{paymentID}")
    public Response deletePayment(@PathParam("paymentID") int paymentID) throws SQLException {
        boolean isDeleted = PaymentDBUtils.deletePayment(paymentID);
        if (isDeleted) {
            return Response.ok().entity("Payment deleted successfully.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Payment not found.").build();
        }
    }

    // Add more methods as needed...
}
