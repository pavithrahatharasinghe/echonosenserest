package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Predictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredictionDBUtils {
    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Predictions> getAllCoinPredictions() throws SQLException {
        List<Predictions> predictionList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM coin_predictions");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Predictions prediction = new Predictions(
                    resultSet.getInt("ID"),
                    resultSet.getString("CoinId"),
                    resultSet.getDouble("Predicted_Close"),
                    resultSet.getString("Prediction_Date")
            );
            predictionList.add(prediction);
        }
        return predictionList;
    }

    public static Predictions getPredictionById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM coin_predictions WHERE CoinId = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Predictions(
                    resultSet.getInt("ID"),
                    resultSet.getString("CoinId"),
                    resultSet.getDouble("Predicted_Close"),
                    resultSet.getString("Prediction_Date")
            );
        }

        return null; // Return null if the prediction with the specified ID doesn't exist.
    }

    public static Predictions getPredictionBySymbol(String CoinId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM predictions WHERE CoinId = ? ORDER BY Prediction_Date DESC LIMIT 1");
        statement.setString(1, CoinId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Predictions(
                    resultSet.getInt("ID"),
                    resultSet.getString("CoinId"),
                    resultSet.getDouble("Predicted_Close"),
                    resultSet.getString("Prediction_Date")
            );
        }

        return null; // Return null if there are no predictions for the specified CoinId.
    }


    // Add more methods for specific queries as needed...
}
