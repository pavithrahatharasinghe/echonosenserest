package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Coin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoinDBUtils {
    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Coin> getAllCoins() throws SQLException {
        List<Coin> coins = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM coins");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String symbol = resultSet.getString("symbol");
            String description = resultSet.getString("description");

            String status = resultSet.getString("status");
            String imageUrl = resultSet.getString("imageUrl");

            Coin coin = new Coin(id, name, symbol, description,  status, imageUrl);
            coins.add(coin);
        }
        return coins;
    }
    // Add a method to insert a new coin into the database
    public static boolean insertCoin(Coin coin) {
        String insertQuery = "INSERT INTO coins (name, symbol, description, status, imageUrl) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, coin.getName());
            preparedStatement.setString(2, coin.getSymbol());
            preparedStatement.setString(3, coin.getDescription());
            preparedStatement.setString(4, coin.getStatus());
            preparedStatement.setString(5, coin.getImageUrl());

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            return false;
        }
    }
    // Add other methods for adding, updating, and deleting coins as needed
}
