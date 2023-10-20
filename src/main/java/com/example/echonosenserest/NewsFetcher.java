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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsFetcher {

    // Define configuration properties
    private String API_URL;
    private String APIKEY;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    private String SENTIMENT_API_URL;
    private List<String> tickers;
    private static final Logger LOGGER = Logger.getLogger(NewsFetcher.class.getName());

    public NewsFetcher() {
        // Load configuration properties
        loadConfiguration();
        loadQuerry();
    }

    private void loadConfiguration() {
        try (InputStream input = NewsFetcher.class.getResourceAsStream("/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            API_URL = properties.getProperty("api.host");
            // Load the list of tickers as a comma-separated string and convert to a list
            String tickersString = properties.getProperty("tickers");
            tickers = List.of(tickersString.split(","));
            // Load other configuration properties
            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.user");
            DB_PASSWORD = properties.getProperty("db.password");
            SENTIMENT_API_URL = properties.getProperty("sentiment.api.url");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading configuration", e);
        }
    }

    private void loadQuerry() {
        try (InputStream input = NewsFetcher.class.getResourceAsStream("/query.properties")) {
            Properties properties = new Properties();
            properties.load(input);


            // Load other configuration properties
           // ORDER = properties.getProperty("order");
            //LIMIT = properties.getProperty("limit");
            APIKEY = properties.getProperty("apiKey");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading configuration", e);
        }
    }

    private String buildDynamicApiUrl() {
        LocalDateTime oneHourAgo = LocalDateTime.now(ZoneId.of("UTC")).minusHours(1);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));

        String oneHourAgoUtc = oneHourAgo.atZone(ZoneOffset.UTC).toInstant().toString();
        String nowUtc = now.atZone(ZoneOffset.UTC).toInstant().toString();



        // Build the API URL with the dynamic values
        return String.format("%s?published_utc.gt=%s&published_utc.lt=%s&apiKey=%s",
                API_URL, oneHourAgoUtc, nowUtc,APIKEY);
    }

    public void fetchNewsAndInsert() throws Exception {
        String dynamicApiUrl = buildDynamicApiUrl();

        System.out.println(dynamicApiUrl);

        URL url = new URL(dynamicApiUrl);
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
                List<String> newsTickers = news.getJSONArray("tickers").toList().stream().map(Object::toString).collect(Collectors.toList());

                // Check if the news contains any of the selected tickers
                if (newsTickers.stream().anyMatch(tickers::contains)) {
                    statement.setString(1, news.optString("id", "N/A"));
                    statement.setString(2, newsTickers.get(0)); // Assuming the first ticker is the primary one
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
            }

            statement.executeBatch();
        }
    }

    private JSONObject fetchSentimentPrediction(String description) throws Exception {
        URL url = new URL(SENTIMENT_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Create the JSON payload
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("text", description);
        String payload = payloadJson.toString();

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

        // Schedule the task to run every 1 hour
        scheduler.scheduleAtFixedRate(() -> {
            try {
                fetcher.fetchNewsAndInsert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.HOURS   );
    }
}
