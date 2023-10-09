package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Subscription;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Subscriptions");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int subscriptionID = resultSet.getInt("SubscriptionID");
            String planName = resultSet.getString("PlanName");
            String duration = resultSet.getString("Duration");
            BigDecimal price = resultSet.getBigDecimal("Price");

            Subscription subscription = new Subscription(subscriptionID, planName, duration, price);
            subscriptions.add(subscription);
        }

        return subscriptions;
    }

    public static boolean addSubscription(Subscription subscription) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Subscriptions (PlanName, Duration, Price) VALUES (?, ?, ?)");
        statement.setString(1, subscription.getPlanName());
        statement.setString(2, subscription.getDuration());
        statement.setBigDecimal(3, subscription.getPrice());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }
    public static boolean updateSubscription(Subscription subscription) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Subscriptions SET PlanName = ?, Duration = ?, Price = ? WHERE SubscriptionID = ?");
        statement.setString(1, subscription.getPlanName());
        statement.setString(2, subscription.getDuration());
        statement.setBigDecimal(3, subscription.getPrice());
        statement.setInt(4, subscription.getSubscriptionID());

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    public static boolean deleteSubscription(int subscriptionID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Subscriptions WHERE SubscriptionID = ?");
        statement.setInt(1, subscriptionID);

        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }

    public static Subscription getSubscriptionPlanById(int subscriptionPlanId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Subscriptions WHERE SubscriptionID = ?");
        statement.setInt(1, subscriptionPlanId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int planID = resultSet.getInt("SubscriptionID");
            String planName = resultSet.getString("PlanName");
            String duration = resultSet.getString("Duration");
            BigDecimal price = resultSet.getBigDecimal("Price");

            return new Subscription(planID, planName, duration, price);
        }

        return null; // Return null if the plan with the specified ID is not found
    }


    // Add methods for editing and deleting subscriptions as needed...
}
