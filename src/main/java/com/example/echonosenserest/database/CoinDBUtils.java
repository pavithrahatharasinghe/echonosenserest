package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Coin;

import java.sql.*;
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
            double price = resultSet.getDouble("price");
            String status = resultSet.getString("status");
            String imageUrl = resultSet.getString("imageUrl");

            Coin coin = new Coin(id, name, symbol, description, price, status, imageUrl);
            coins.add(coin);
        }
        return coins;
    }

    public static boolean addCoin(Coin coin) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Coins (name, symbol, description, price, status, imageUrl) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, coin.getName());
        statement.setString(2, coin.getSymbol());
        statement.setString(3, coin.getDescription());
        statement.setDouble(4, coin.getPrice());
        statement.setString(5, coin.getStatus());
        statement.setString(6, coin.getImageUrl());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    public static boolean updateCoin(int coinId, Coin coin) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Coins SET name=?, symbol=?, description=?, price=?, status=?, imageUrl=? WHERE id=?");
        statement.setString(1, coin.getName());
        statement.setString(2, coin.getSymbol());
        statement.setString(3, coin.getDescription());
        statement.setDouble(4, coin.getPrice());
        statement.setString(5, coin.getStatus());
        statement.setString(6, coin.getImageUrl());
        statement.setInt(7, coinId);

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    public static boolean deleteCoin(int coinId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Coins WHERE id=?");
        statement.setInt(1, coinId);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }

    public static boolean toggleCoinStatus(int coinId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Coins SET status=CASE WHEN status='Enabled' THEN 'Disabled' ELSE 'Enabled' END WHERE id=?");
        statement.setInt(1, coinId);
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }
}
