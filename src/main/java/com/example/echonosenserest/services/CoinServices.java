package com.example.echonosenserest.services;

import com.example.echonosenserest.models.Coin;
import com.example.echonosenserest.database.CoinDBUtils;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCoin(Coin coin) throws SQLException {
        boolean isAdded = CoinDBUtils.addCoin(coin);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(coin).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding coin.").build();
        }
    }

    @PUT
    @Path("/{coinId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCoin(@PathParam("coinId") int coinId, Coin coin) throws SQLException {
        boolean isUpdated = CoinDBUtils.updateCoin(coinId, coin);
        if (isUpdated) {
            return Response.ok(coin).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Coin not found.").build();
        }
    }

    @DELETE
    @Path("/{coinId}")
    public Response deleteCoin(@PathParam("coinId") int coinId) throws SQLException {
        boolean isDeleted = CoinDBUtils.deleteCoin(coinId);
        if (isDeleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Coin not found.").build();
        }
    }

    @PUT
    @Path("/toggleStatus/{coinId}")
    public Response toggleCoinStatus(@PathParam("coinId") int coinId) throws SQLException {
        boolean isToggled = CoinDBUtils.toggleCoinStatus(coinId);
        if (isToggled) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Coin not found.").build();
        }
    }
}
