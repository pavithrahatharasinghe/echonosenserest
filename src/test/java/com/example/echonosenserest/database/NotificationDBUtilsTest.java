package com.example.echonosenserest.database;

import com.example.echonosenserest.models.Notification;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationDBUtilsTest {

    @Test
    void getAllNotifications() {
        try {
            List<Notification> notifications = NotificationDBUtils.getAllNotifications();
            assertNotNull(notifications); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving notifications: " + e.getMessage());
        }
    }

    @Test
    void getAllNotificationsForUser() {
        try {
            int userId = 1; // Replace with an existing user ID
            List<Notification> notifications = NotificationDBUtils.getAllNotificationsForUser(userId);
            assertNotNull(notifications); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving user-specific notifications: " + e.getMessage());
        }
    }

    @Test
    void addNotification() {
        try {
            // Create a sample Notification object for insertion
            Notification notification = new Notification(
                    0,
                    1, // Replace with an existing user ID
                    "Test message",
                    new Date(),
                    new Date()
            );

            boolean isAdded = NotificationDBUtils.addNotification(notification);
            assertTrue(isAdded); // Ensure that the notification was successfully added
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while adding notification: " + e.getMessage());
        }
    }

    @Test
    void updateNotification() {
        try {
            // Create a sample Notification object for updating
            Notification notification = new Notification(
                    1, // Replace with an existing notification ID
                    1, // Replace with an existing user ID
                    "Updated message",
                    new Date(),
                    new Date()
            );

            int notificationId = 1; // Replace with an existing notification ID
            boolean isUpdated = NotificationDBUtils.updateNotification(notificationId, notification);
            assertTrue(isUpdated); // Ensure that the notification was successfully updated
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while updating notification: " + e.getMessage());
        }
    }

    @Test
    void deleteNotification() {
        try {
            int notificationId = 1; // Replace with an existing notification ID
            boolean isDeleted = NotificationDBUtils.deleteNotification(notificationId);
            assertTrue(isDeleted); // Ensure that the notification was successfully deleted
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while deleting notification: " + e.getMessage());
        }
    }
}
