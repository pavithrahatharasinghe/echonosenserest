package com.example.echonosenserest.database;

import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDBUtils {

    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Notification> getAllNotifications() throws SQLException {
        List<Notification> notifications = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Notifications");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int notificationID = resultSet.getInt("NotificationID");
            int userID = resultSet.getInt("UserID");
            String message = resultSet.getString("Message");
            Date date = resultSet.getDate("Date");

            Notification notification = new Notification(notificationID, userID, message, date);
            notifications.add(notification);
        }

        return notifications;
    }

    public static List<Notification> getAllNotificationsForUser(int userId) throws SQLException {
        List<Notification> notifications = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Notifications WHERE UserID = ?");
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int notificationID = resultSet.getInt("NotificationID");
            int userID = resultSet.getInt("UserID");
            String message = resultSet.getString("Message");
            Date date = resultSet.getDate("Date");

            Notification notification = new Notification(notificationID, userID, message, date);
            notifications.add(notification);
        }

        return notifications;
    }

    public static boolean addNotification(Notification notification) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Notifications (UserID, Message, Date) VALUES (?, ?, ?)");
        statement.setInt(1, notification.getUserID());
        statement.setString(2, notification.getMessage());
        statement.setDate(3, new java.sql.Date(notification.getDate().getTime()));

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    public static boolean updateNotification(int notificationId, Notification notification) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Notifications SET Message = ?, Date = ? WHERE NotificationID = ?");
        statement.setString(1, notification.getMessage());
        statement.setDate(2, new java.sql.Date(notification.getDate().getTime()));
        statement.setInt(3, notificationId);

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    public static boolean deleteNotification(int notificationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Notifications WHERE NotificationID = ?");
        statement.setInt(1, notificationId);

        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }
}
