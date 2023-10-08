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
        List<CoinPrediction> predictions = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM CoinPredictions");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int coinID = resultSet.getInt("CoinID");
            String coinName = resultSet.getString("CoinName");
            Date date = resultSet.getDate("Date");
            double open = resultSet.getDouble("Open");
            double high = resultSet.getDouble("High");
            double low = resultSet.getDouble("Low");
            double close = resultSet.getDouble("Close");
            double volume = resultSet.getDouble("Volume");
            double polarity = resultSet.getDouble("Polarity");
            double subjectivity = resultSet.getDouble("Subjectivity");
            double compoundScore = resultSet.getDouble("Compound_Score");

            CoinPrediction prediction = new CoinPrediction(coinID, coinName, date, open, high, low, close, volume, polarity, subjectivity, compoundScore);
            predictions.add(prediction);
        }

        return predictions;
    }

    public static boolean addPrediction(CoinPrediction prediction) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CoinPredictions (CoinName, Date, Open, High, Low, Close, Volume, Polarity, Subjectivity, Compound_Score) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, prediction.getCoinName());
        statement.setDate(2, new java.sql.Date(prediction.getDate().getTime()));
        statement.setDouble(3, prediction.getOpen());
        statement.setDouble(4, prediction.getHigh());
        statement.setDouble(5, prediction.getLow());
        statement.setDouble(6, prediction.getClose());
        statement.setDouble(7, prediction.getVolume());
        statement.setDouble(8, prediction.getPolarity());
        statement.setDouble(9, prediction.getSubjectivity());
        statement.setDouble(10, prediction.getCompoundScore());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    // Add more methods as needed...
}
