package com.example.echonosenserest.services;

import com.example.echonosenserest.database.PredictionDBUtils;
import com.example.echonosenserest.models.Predictions;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/coin-predictions")
public class PredictionService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCoinPredictions() throws SQLException {
        List<Predictions> predictionList = PredictionDBUtils.getAllCoinPredictions();
        return Response.ok(predictionList).build();
    }

    @GET
    @Path("/{SYMBOL}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPredictionBySymbol(@PathParam("SYMBOL") String symbol) {
        try {
            Predictions prediction = PredictionDBUtils.getPredictionBySymbol(symbol);

            if (prediction != null) {
                return Response.ok(prediction).build();
            } else {
                // Return a 404 Not Found response if the prediction with the specified SYMBOL doesn't exist.
                return Response.status(Response.Status.NOT_FOUND).entity("Prediction not found").build();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching prediction").build();
        }
    }


}
