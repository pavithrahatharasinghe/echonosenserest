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

    // Add other methods for adding, updating, and deleting coins as needed
}
