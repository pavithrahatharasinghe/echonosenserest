package com.example.echonosenserest.services;

import com.example.echonosenserest.database.CoinDBUtils;
import com.example.echonosenserest.models.Coin;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;
@Path("/coins")
public class CoinServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCoins() throws SQLException {
        List<Coin> coins = CoinDBUtils.getAllCoins();
        return Response.ok(coins).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCoin(Coin newCoin) throws SQLException {
        // Insert the new coin into the database
        boolean insertionSuccessful = CoinDBUtils.insertCoin(newCoin);

        if (insertionSuccessful) {
            return Response.status(Response.Status.CREATED).entity("Coin added successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add the coin").build();
        }
    }
    // Implement other methods for adding, updating, and deleting coins as needed
}