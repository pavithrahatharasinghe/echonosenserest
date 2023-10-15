package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.News;
import com.example.echonosenserest.models.NewsArticle;



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
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("ticker"),
                    resultSet.getString("published_utc"),
                    resultSet.getString("article_url"),
                    resultSet.getString("image_url")
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
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("ticker"),
                    resultSet.getString("published_utc"),
                    resultSet.getString("article_url"),
                    resultSet.getString("image_url")
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
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDouble("polarity"),
                    resultSet.getString("impact"),
                    resultSet.getString("ticker"),
                    resultSet.getString("published_utc"),
                    resultSet.getString("article_url"),
                    resultSet.getString("image_url")
            );
            newsList.add(news);
        }
        return newsList;
    }

    // Other methods...

    public static List<News> getNewsByCoinAndImpact(String relatedCoin, String impact) throws SQLException {


        List<News> newsList = new ArrayList<>();
        PreparedStatement statement;

        if(impact.equals("all")){

            statement  =connection.prepareStatement("SELECT * FROM News WHERE ticker = ?");
            statement.setString(1, relatedCoin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("polarity"),
                        resultSet.getString("impact"),
                        resultSet.getString("ticker"),
                        resultSet.getString("published_utc"),
                        resultSet.getString("article_url"),
                        resultSet.getString("image_url")

                );
                newsList.add(news);
            }
            return newsList;
        }
        else{

            statement =connection.prepareStatement("SELECT * FROM News WHERE ticker = ? AND impact = ?");
            statement.setString(1, relatedCoin);
            statement.setString(2, impact);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("polarity"),
                        resultSet.getString("impact"),
                        resultSet.getString("ticker"),
                        resultSet.getString("published_utc"),
                        resultSet.getString("article_url"),
                        resultSet.getString("image_url")

                );
                newsList.add(news);
            }
            return newsList;
        }

    }

// Other methods...


    public static void saveNewsArticles(List<NewsArticle> articles) throws SQLException {
        String insertSQL = "INSERT INTO News (id, ticker, title, author, published_utc, article_url, image_url, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

        for (NewsArticle article : articles) {
            preparedStatement.setString(1, article.getId());
            preparedStatement.setString(2, article.getTicker());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getAuthor());

            // Inserting the date as it is without parsing
            preparedStatement.setString(5, article.getPublishedUtc());

            preparedStatement.setString(6, article.getArticleUrl());
            preparedStatement.setString(7, article.getImageUrl());
            preparedStatement.setString(8, article.getDescription());
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }



}
