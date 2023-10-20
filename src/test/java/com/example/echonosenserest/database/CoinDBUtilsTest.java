package com.example.echonosenserest.database;

import com.example.echonosenserest.models.Coin;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinDBUtilsTest {

    @Test
    void getAllCoins() {
        try {
            List<Coin> coins = CoinDBUtils.getAllCoins();
            assertNotNull(coins); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving coins: " + e.getMessage());
        }
    }

    @Test
    void insertCoin() {
        // Create a sample Coin object for insertion
        Coin coin = new Coin( 0,"TestCoins", "TSTs", "Test coin description", "Active", "testcoin.png");

        boolean isInserted = CoinDBUtils.insertCoin(coin);
        assertTrue(isInserted); // Ensure that the coin was successfully inserted
        // Add more specific assertions as needed
    }
}
