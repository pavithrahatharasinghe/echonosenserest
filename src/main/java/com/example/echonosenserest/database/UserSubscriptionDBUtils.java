package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.UserSubscription;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSubscriptionDBUtils {


    // ... connection setup ...
    private static Connection connection;

    public static List<UserSubscription> getAllUserSubscriptions() throws SQLException {
        List<UserSubscription> userSubscriptions = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserSubscriptions");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int userSubscriptionID = resultSet.getInt("UserSubscriptionID");
            int userID = resultSet.getInt("UserID");
            int subscriptionID = resultSet.getInt("SubscriptionID");
            Date startDate = resultSet.getDate("StartDate");
            Date endDate = resultSet.getDate("EndDate");

            UserSubscription userSubscription = new UserSubscription(userSubscriptionID, userID, subscriptionID, startDate, endDate);
            userSubscriptions.add(userSubscription);
        }

        return userSubscriptions;
    }

    public static boolean addUserSubscription(UserSubscription userSubscription) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO UserSubscriptions (UserID, SubscriptionID, StartDate, EndDate) VALUES (?, ?, ?, ?)");
        statement.setInt(1, userSubscription.getUserID());
        statement.setInt(2, userSubscription.getSubscriptionID());
        statement.setDate(3, new java.sql.Date(userSubscription.getStartDate().getTime()));
        statement.setDate(4, new java.sql.Date(userSubscription.getEndDate().getTime()));

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    // ... other methods for update, delete, etc. ...
}
