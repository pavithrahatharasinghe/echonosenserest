package com.example.echonosenserest.services;

import com.example.echonosenserest.models.Price;
import com.example.echonosenserest.database.PriceDBUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/prices")
public class PriceService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrices() throws SQLException {
        List<Price> priceList = PriceDBUtils.getAllPrices();
        return Response.ok(priceList).build();
    }

    @GET
    @Path("/{symbol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPriceBySymbol(@PathParam("symbol") String symbol) throws SQLException {
        Price price = PriceDBUtils.getPriceBySymbol(symbol);
        return Response.ok(price).build();
    }

    @GET
    @Path("/coin/{coinSymbol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLast60PricesByCoin(@PathParam("coinSymbol") String coinSymbol) {
        try {
            List<Price> priceList = PriceDBUtils.getLast60PricesByCoin(coinSymbol);
            return Response.ok(priceList).build();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching prices").build();
        }
    }


    // Add more methods as needed...
}
