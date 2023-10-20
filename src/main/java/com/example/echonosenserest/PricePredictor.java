package com.example.echonosenserest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Properties;

public class PricePredictor {

    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    private String SYMBOL;

    public PricePredictor() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        try (InputStream input = PricePredictor.class.getResourceAsStream("/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.user");
            DB_PASSWORD = properties.getProperty("db.password");
            // Load the SYMBOL from the configuration
            SYMBOL = properties.getProperty("symbol");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countNews(String polarity) {
        int newsCount = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT COUNT(*) AS news_count FROM news " +
                    "WHERE DATE(published_utc) = ? AND ticker = ? AND polarity = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, Timestamp.from(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant()));
            statement.setString(2, SYMBOL);
            statement.setString(3, polarity);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    newsCount = resultSet.getInt("news_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsCount;
    }

    private double getPrice(String priceType) {
        double price = 0.0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "";
            switch (priceType) {
                case "high":
                    sql = "SELECT MAX(price) AS price FROM prices " +
                            "WHERE DATE(fetch_time) = ? AND symbol = ?";
                    break;
                case "low":
                    sql = "SELECT MIN(price) AS price FROM prices " +
                            "WHERE DATE(fetch_time) = ? AND symbol = ?";
                    break;
                case "open":
                    sql = "SELECT price AS price FROM prices " +
                            "WHERE DATE(fetch_time) = ? AND symbol = ? " +
                            "ORDER BY fetch_time ASC LIMIT 1";
                    break;

                default:
                    break;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setString(2, SYMBOL +"USDT");




            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    price = resultSet.getDouble("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public String[] getPriceAndNewsCount() {
        String[] values = new String[6];

        // Negative News Count
        values[0] = String.valueOf(countNews("negative"));
        // Positive News Count
        values[1] = String.valueOf(countNews("positive"));


        // Neutral News Count
        values[2] = String.valueOf(countNews("neutral"));
        // Opening Price
        values[3] = String.valueOf(getPrice("open"));
        // Highest Price
        values[4] = String.valueOf(getPrice("high"));
        // Lowest Price
        values[5] = String.valueOf(getPrice("low"));

        return values;
    }

    private void savePredictionToDatabase(String predictedClose) {
        // Define a SQL query to insert the data into your table
        String sql = "INSERT INTO predictions (CoinId, Predicted_Close, Prediction_Date) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, SYMBOL);
            statement.setDouble(2, Double.parseDouble(predictedClose));
            statement.setTimestamp(3, Timestamp.from(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant()));

            // Execute the INSERT statement
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        PricePredictor pricePredictor = new PricePredictor();
        String[] values = pricePredictor.getPriceAndNewsCount();

        System.out.println("{ \"features\": [");
        System.out.println("1,");
        System.out.println("1,");
        System.out.println("5,");
        System.out.println(values[3] + ",");
        System.out.println(values[4] + ",");
        System.out.println(values[5]);
        System.out.println("]");

        try {
            // Create a connection to the prediction API
            URL url = new URL("http://127.0.0.1:8000/predict_price");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable input and output streams
            connection.setDoOutput(true);

            // Set the request content type to JSON
            connection.setRequestProperty("Content-Type", "application/json");

            // Create the JSON payload with the generated array
            String jsonInputString = "{ \"features\": [1, 1, 5, " +
                    values[3] + ", " +
                    values[4] + ", " +
                    values[5] + "] }";

            // Write the JSON payload to the request
            try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response from the server
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String responseLine;
                StringBuilder response = new StringBuilder();
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine);
                }

                // Parse the JSON response
                JsonObject jsonResponse = new Gson().fromJson(response.toString(), JsonObject.class);
                double predictedClose = jsonResponse.get("predicted_close").getAsDouble();

                // Print the predicted close price
                System.out.println("Predicted Close Price: " + predictedClose);

                // Save the response to the database
                pricePredictor.savePredictionToDatabase(String.valueOf(predictedClose));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
