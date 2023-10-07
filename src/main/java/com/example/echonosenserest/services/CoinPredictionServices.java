package com.example.echonosenserest.services;

import com.example.echonosenserest.database.CoinPredictionDBUtils;
import com.example.echonosenserest.models.CoinPrediction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/coinPredictions")
public class CoinPredictionServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPredictions() throws SQLException {
        List<CoinPrediction> predictions = CoinPredictionDBUtils.getAllPredictions();
        return Response.ok(predictions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrediction(CoinPrediction prediction) throws SQLException {
        boolean isAdded = CoinPredictionDBUtils.addPrediction(prediction);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(prediction).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding prediction.").build();
        }
    }

    // Add more methods as needed...
}
