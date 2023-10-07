package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<News> getAllNews() throws SQLException {
        List<News> newsList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM News");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            News news = new News(
                    resultSet.getInt("newsID"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("relatedCoin"),
                    resultSet.getDate("dateReleased"),
                    resultSet.getString("source")
            );
            newsList.add(news);
        }
        return newsList;
    }

    public static List<News> getNewsByCoin(String relatedCoin) throws SQLException {
        List<News> newsList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM News WHERE relatedCoin = ?");
        statement.setString(1, relatedCoin);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            News news = new News(
                    resultSet.getInt("newsID"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("relatedCoin"),
                    resultSet.getDate("dateReleased"),
                    resultSet.getString("source")
            );
            newsList.add(news);
        }
        return newsList;
    }

    public static List<News> getNewsByImpact(String impact) throws SQLException {
        List<News> newsList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM News WHERE impact = ?");
        statement.setString(1, impact);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            News news = new News(
                    resultSet.getInt("newsID"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("relatedCoin"),
                    resultSet.getDate("dateReleased"),
                    resultSet.getString("source")
            );
            newsList.add(news);
        }
        return newsList;
    }

    // Other methods...

    public static List<News> getNewsByCoinAndImpact(String relatedCoin, String impact) throws SQLException {
        List<News> newsList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM News WHERE relatedCoin = ? AND impact = ?");
        statement.setString(1, relatedCoin);
        statement.setString(2, impact);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            News news = new News(
                    resultSet.getInt("newsID"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("relatedCoin"),
                    resultSet.getDate("dateReleased"),
                    resultSet.getString("source")
            );
            newsList.add(news);
        }
        return newsList;
    }

// Other methods...


    // Add more methods as needed...
}
