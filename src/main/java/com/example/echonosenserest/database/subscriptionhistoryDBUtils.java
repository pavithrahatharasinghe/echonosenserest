package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.SubscriptionHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class subscriptionhistoryDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<SubscriptionHistory> getAllSubscriptionHistories() throws SQLException {
        List<SubscriptionHistory> histories = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM subscriptionhistory");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int historyId = resultSet.getInt("history_id");
            int userId = resultSet.getInt("user_id");
            int subscriptionId = resultSet.getInt("subscription_id");
            Date startDate = resultSet.getDate("start_date");
            Date endDate = resultSet.getDate("end_date");
            String status = resultSet.getString("status");



            SubscriptionHistory history = new SubscriptionHistory(historyId, userId, subscriptionId, dateFormat.format(startDate), dateFormat.format(endDate), status);
            histories.add(history);
        }

        return histories;
    }


    public static List<SubscriptionHistory> getSubscriptionHistoriesByUserId(int userId) throws SQLException {
        List<SubscriptionHistory> histories = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM subscriptionhistory WHERE user_id = ?");
        statement.setInt(1, userId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int historyId = resultSet.getInt("history_id");
            int subscriptionId = resultSet.getInt("subscription_id");
            Date startDate = resultSet.getDate("start_date");
            Date endDate = resultSet.getDate("end_date");
            String status = resultSet.getString("status");

            SubscriptionHistory history = new SubscriptionHistory(historyId, userId, subscriptionId, dateFormat.format(startDate), dateFormat.format(endDate), status);
            histories.add(history);
        }

        return histories;
    }
}
