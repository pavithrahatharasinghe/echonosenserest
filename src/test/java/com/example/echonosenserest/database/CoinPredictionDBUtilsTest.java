package com.example.echonosenserest.database;

import com.example.echonosenserest.models.CoinPrediction;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinPredictionDBUtilsTest {

    @Test
    void getAllPredictions() {
        try {
            List<CoinPrediction> predictions = CoinPredictionDBUtils.getAllPredictions();
            assertNotNull(predictions); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving predictions: " + e.getMessage());
        }
    }

    @Test
    void addPrediction() {
        try {
            // Create a sample CoinPrediction object for insertion
            CoinPrediction prediction = new CoinPrediction(
                    0,
                    "TestCoin",
                    new java.util.Date(),
                    100.0,
                    110.0,
                    95.0,
                    105.0,
                    10000.0,
                    0.5,
                    0.6,
                    0.7
            );

            boolean isAdded = CoinPredictionDBUtils.addPrediction(prediction);
            assertTrue(isAdded); // Ensure that the prediction was successfully added
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while adding prediction: " + e.getMessage());
        }
    }
}
