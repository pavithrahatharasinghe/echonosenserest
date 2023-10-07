package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.CoinPrediction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoinPredictionDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CoinPrediction> getAllPredictions() throws SQLException {
        // Implementation to retrieve all coin predictions from the database
    }

    public static boolean addPrediction(CoinPrediction prediction) throws SQLException {
        // Implementation to add a new coin prediction to the database
    }

    // Add more methods as needed...
}
