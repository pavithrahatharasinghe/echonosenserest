package com.example.echonosenserest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PriceFetcher {

    private String API_URL;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    private List<String> SELECTED_COINS;

    public PriceFetcher() {
        // Load configuration properties
        loadConfiguration();
    }

    private void loadConfiguration() {
        try (InputStream input = PriceFetcher.class.getResourceAsStream("/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            API_URL = properties.getProperty("api.prices");
            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.user");
            DB_PASSWORD = properties.getProperty("db.password");

            // Load the list of selected coins as a comma-separated string and convert to a list
            String selectedCoinsString = properties.getProperty("selected.coins");
            SELECTED_COINS = Arrays.asList(selectedCoinsString.split(","));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchPricesAndInsert() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONArray pricesArray = new JSONArray(response.toString());
        LocalDateTime fetchTime = LocalDateTime.now();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO prices (symbol, price, fetch_time) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < pricesArray.length(); i++) {
                JSONObject price = pricesArray.getJSONObject(i);
                String symbol = price.optString("symbol", "N/A");

                if (SELECTED_COINS.contains(symbol)) {
                    statement.setString(1, symbol);
                    statement.setString(2, price.optString("price", "0"));
                    statement.setObject(3, fetchTime);
                    statement.addBatch();

                    System.out.println(symbol);
                }
            }

            statement.executeBatch();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        PriceFetcher fetcher = new PriceFetcher();

        // Schedule the task to run every 15 minutes
        scheduler.scheduleAtFixedRate(() -> {
            try {
                fetcher.fetchPricesAndInsert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 15, TimeUnit.SECONDS);
    }
}
