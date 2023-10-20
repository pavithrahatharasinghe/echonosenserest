package com.example.echonosenserest.database;

import com.example.echonosenserest.models.News;
import com.example.echonosenserest.models.NewsArticle;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsDBUtilsTest {

    @Test
    void getAllNews() {
        try {
            List<News> newsList = NewsDBUtils.getAllNews();
            assertNotNull(newsList); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving news: " + e.getMessage());
        }
    }



    @Test
    void getNewsByImpact() {
        try {
            String impactLevel = "High"; // Replace with an existing impact level
            List<News> newsList = NewsDBUtils.getNewsByImpact(impactLevel);
            assertNotNull(newsList); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving news by impact: " + e.getMessage());
        }
    }

    @Test
    void getNewsByCoinAndImpact() {
        try {
            String coinSymbol = "ETH"; // Replace with an existing coin symbol
            String impactLevel = "Medium"; // Replace with an existing impact level
            List<News> newsList = NewsDBUtils.getNewsByCoinAndImpact(coinSymbol, impactLevel);
            assertNotNull(newsList); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving news by coin and impact: " + e.getMessage());
        }
    }

    @Test
    void saveNewsArticles() {
        try {
            List<NewsArticle> articles = new ArrayList<>();
            // Create test NewsArticle objects and add them to the list

            NewsDBUtils.saveNewsArticles(articles);

            // Add assertions to verify that the articles were successfully saved
        } catch (SQLException e) {
            fail("Exception thrown while saving news articles: " + e.getMessage());
        }
    }
}
