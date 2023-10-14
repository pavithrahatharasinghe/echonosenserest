package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Price> getAllPrices() throws SQLException {
        List<Price> priceList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM prices");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Price price = new Price(
                    resultSet.getInt("id"),
                    resultSet.getString("symbol"),
                    resultSet.getDouble("price"),
                    resultSet.getString("fetch_time")
            );
            priceList.add(price);
        }
        return priceList;
    }

    public static Price getPriceBySymbol(String symbol) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM prices WHERE symbol = ?");
        statement.setString(1, symbol);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Price(
                    resultSet.getInt("id"),
                    resultSet.getString("symbol"),
                    resultSet.getDouble("price"),
                    resultSet.getString("fetch_time")
            );
        }
        return null;
    }

    public static List<Price> getLast60PricesByCoin(String coinSymbol) throws SQLException {
        List<Price> priceList = new ArrayList<>();
        String query = "SELECT * FROM prices WHERE symbol = ? ORDER BY fetch_time ASC LIMIT 60";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, coinSymbol);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Price price = new Price(
                            resultSet.getInt("id"),
                            resultSet.getString("symbol"),
                            resultSet.getDouble("price"),
                            resultSet.getString("fetch_time")
                    );
                    priceList.add(price);
                }
            }
        }
        return priceList;
    }


    // Add more methods...
}
