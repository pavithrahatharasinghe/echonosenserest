package com.example.echonosenserest.database;

import com.example.echonosenserest.models.Predictions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PredictionDBUtilsTest {

    @Test
    void getAllCoinPredictions() {
        try {
            List<Predictions> predictions = PredictionDBUtils.getAllCoinPredictions();
            assertNotNull(predictions); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving predictions: " + e.getMessage());
        }
    }

    @Test
    void getPredictionById() {
        try {
            int predictionId = 1; // Replace with an existing prediction ID
            Predictions prediction = PredictionDBUtils.getPredictionById(predictionId);
            assertNotNull(prediction); // Ensure that the retrieved prediction is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving prediction by ID: " + e.getMessage());
        }
    }

    @Test
    void getPredictionBySymbol() {
        try {
            String coinSymbol = "BTC"; // Replace with an existing coin symbol
            Predictions prediction = PredictionDBUtils.getPredictionBySymbol(coinSymbol);
            assertNotNull(prediction); // Ensure that the retrieved prediction is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving prediction by symbol: " + e.getMessage());
        }
    }

    // Add more test cases for specific queries as needed...
}
