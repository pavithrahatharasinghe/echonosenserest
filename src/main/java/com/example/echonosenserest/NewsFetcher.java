package com.example.echonosenserest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewsFetcher {

    private static final String API_URL = "https://api.polygon.io/v2/reference/news?ticker=LTC&published_utc,gt=2022-10-11T13:10:00Z&order=desc&limit=100&apiKey=OTQk8uULaAD0AKpHU_8BPhMg40Dp8Voh";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/echonosense";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    // Add the URL of the sentiment prediction API
    private static final String SENTIMENT_API_URL = "http://127.0.0.1:5000/predict_sentiment";

    public void fetchNewsAndInsert() throws Exception {
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

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray newsArray = jsonResponse.getJSONArray("results");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO news (id, ticker, title, author, published_utc, article_url, image_url, description, polarity, impact) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject news = newsArray.getJSONObject(i);
                statement.setString(1, news.optString("id", "N/A"));
                statement.setString(2, "LTC");
                statement.setString(3, news.optString("title", "N/A"));
                statement.setString(4, news.optString("author", "N/A"));
                statement.setString(5, news.optString("published_utc", "N/A"));
                statement.setString(6, news.optString("article_url", "N/A"));
                statement.setString(7, news.optString("image_url", "N/A"));
                statement.setString(8, news.optString("description", "No description available"));

                // Get sentiment prediction from the API
                JSONObject sentimentResponse = fetchSentimentPrediction(news.optString("title") + " " + news.optString("description"));
                statement.setString(9, sentimentResponse.optString("polarity", "0"));
                statement.setString(10, sentimentResponse.optString("sentiment_class", "not-set"));

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    // Method to fetch sentiment prediction
    // Method to fetch sentiment prediction
    private JSONObject fetchSentimentPrediction(String description) throws Exception {
        URL url = new URL(SENTIMENT_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Create the JSON payload
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("text", description);
        String payload = payloadJson.toString();

        System.out.println(payload);
        conn.setRequestProperty("Content-Type", "application/json");

        // Write the payload to the request output stream
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }



    public static void main(String[] args) {
        NewsFetcher fetcher = new NewsFetcher();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 1 minute
        scheduler.scheduleAtFixedRate(() -> {
            try {
                fetcher.fetchNewsAndInsert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
