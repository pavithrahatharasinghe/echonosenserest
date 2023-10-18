package com.example.echonosenserest.database;

import com.example.echonosenserest.models.Price;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceDBUtilsTest {

    @Test
    void getAllPrices() {
        try {
            List<Price> prices = PriceDBUtils.getAllPrices();
            assertNotNull(prices); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving prices: " + e.getMessage());
        }
    }

    @Test
    void getPriceBySymbol() {
        try {
            String symbol = "BTC"; // Replace with an existing symbol
            Price price = PriceDBUtils.getPriceBySymbol(symbol);
            assertNotNull(price); // Ensure that the retrieved price is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving price by symbol: " + e.getMessage());
        }
    }

    @Test
    void getLast60PricesByCoin() {
        try {
            String coinSymbol = "BTC"; // Replace with an existing coin symbol
            List<Price> prices = PriceDBUtils.getLast60PricesByCoin(coinSymbol);
            assertNotNull(prices); // Ensure that the list of prices is not null
            assertTrue(prices.size() <= 60); // Ensure that the list contains at most 60 prices
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving last 60 prices for a coin: " + e.getMessage());
        }
    }

    // Add more test cases for specific queries as needed...
}
