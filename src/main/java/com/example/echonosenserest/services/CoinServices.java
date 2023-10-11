package com.example.echonosenserest.services;

import com.example.echonosenserest.database.CoinDBUtils;
import com.example.echonosenserest.models.Coin;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

    // Implement other methods for adding, updating, and deleting coins as needed
}