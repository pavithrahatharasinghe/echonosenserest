package com.example.echonosenserest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataInserter {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/echonosense";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    private static final List<String> SELECTED_COINS = Arrays.asList("BTCUSDT", "ETHUSDT", "LTCUSDT");

    public void insertDataIntoMainTable() throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Fetch the first, highest, and lowest prices for each coin for today
            String priceQuery = "SELECT symbol, MIN(price) AS low, MAX(price) AS high, " +
                    "(SELECT price FROM prices WHERE symbol = p.symbol ORDER BY fetch_time ASC LIMIT 1) AS open " +
                    "FROM prices p WHERE DATE(fetch_time) = CURDATE() AND symbol IN (?) GROUP BY symbol";

            PreparedStatement priceStatement = connection.prepareStatement(priceQuery);
            priceStatement.setString(1, String.join(",", SELECTED_COINS));
            ResultSet priceResults = priceStatement.executeQuery();

            // Fetch the count of positive, negative, and neutral news impacts for each coin for today
            String newsQuery = "SELECT ticker, " +
                    "SUM(CASE WHEN impact = 'negative' THEN 1 ELSE 0 END) AS count_negatives, " +
                    "SUM(CASE WHEN impact = 'positive' THEN 1 ELSE 0 END) AS count_positives, " +
                    "SUM(CASE WHEN impact = 'neutral' THEN 1 ELSE 0 END) AS count_neutrals " +
                    "FROM news WHERE DATE(published_utc) = CURDATE() AND ticker IN (?) GROUP BY ticker";

            PreparedStatement newsStatement = connection.prepareStatement(newsQuery);
            newsStatement.setString(1, String.join(",", SELECTED_COINS));
            ResultSet newsResults = newsStatement.executeQuery();

            // Prepare the insert statement for the main table
            String insertQuery = "INSERT INTO your_main_table_name (Date, CoinName, Open, High, Low, Count_Negatives, Count_Positives, Count_Neutrals) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            // Iterate over the price results and populate the data
            while (priceResults.next()) {
                String symbol = priceResults.getString("symbol");
                double open = priceResults.getDouble("open");
                double high = priceResults.getDouble("high");
                double low = priceResults.getDouble("low");

                // Fetch the corresponding news counts
                newsResults.beforeFirst(); // Reset the cursor
                int countNegatives = 0, countPositives = 0, countNeutrals = 0;
                while (newsResults.next()) {
                    if (newsResults.getString("ticker").equals(symbol)) {
                        countNegatives = newsResults.getInt("count_negatives");
                        countPositives = newsResults.getInt("count_positives");
                        countNeutrals = newsResults.getInt("count_neutrals");
                        break;
                    }
                }

                // Populate the insert statement
                insertStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                insertStatement.setString(2, symbol);
                insertStatement.setDouble(3, open);
                insertStatement.setDouble(4, high);
                insertStatement.setDouble(5, low);
                insertStatement.setInt(6, countNegatives);
                insertStatement.setInt(7, countPositives);
                insertStatement.setInt(8, countNeutrals);
                insertStatement.addBatch();
            }

            // Execute the batch insert
            insertStatement.executeBatch();
        }
    }


    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        DataInserter inserter = new DataInserter();

        // Schedule the task to run every minute
        scheduler.scheduleAtFixedRate(() -> {
            try {
                inserter.insertDataIntoMainTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
