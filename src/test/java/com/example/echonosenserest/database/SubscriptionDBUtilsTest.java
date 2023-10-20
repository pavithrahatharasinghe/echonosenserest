package com.example.echonosenserest.database;

import com.example.echonosenserest.models.Subscription;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionDBUtilsTest {

    @Test
    void getAllSubscriptions() {
        try {
            List<Subscription> subscriptions = SubscriptionDBUtils.getAllSubscriptions();
            assertNotNull(subscriptions); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving subscriptions: " + e.getMessage());
        }
    }

    @Test
    void addSubscription() {
        try {
            // Create a sample Subscription object for insertion
            Subscription subscription = new Subscription(
                    "Premium Plan",
                    "12 months",
                    new BigDecimal("99.99")
            );

            boolean isAdded = SubscriptionDBUtils.addSubscription(subscription);
            assertTrue(isAdded); // Ensure that the subscription was successfully added
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while adding subscription: " + e.getMessage());
        }
    }

    @Test
    void updateSubscription() {
        try {
            // Create a sample Subscription object for update
            Subscription subscription = new Subscription(
                    1, // Replace with an existing subscription ID
                    "Updated Plan",
                    "6 months",
                    new BigDecimal("49.99")
            );

            boolean isUpdated = SubscriptionDBUtils.updateSubscription(subscription);
            assertTrue(isUpdated); // Ensure that the subscription was successfully updated
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while updating subscription: " + e.getMessage());
        }
    }

    @Test
    void deleteSubscription() {
        try {
            int subscriptionID = 1; // Replace with an existing subscription ID
            boolean isDeleted = SubscriptionDBUtils.deleteSubscription(subscriptionID);
            assertTrue(isDeleted); // Ensure that the subscription was successfully deleted
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while deleting subscription: " + e.getMessage());
        }
    }


}
