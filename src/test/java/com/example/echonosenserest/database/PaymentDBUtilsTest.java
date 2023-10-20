package com.example.echonosenserest.database;

import com.example.echonosenserest.models.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDBUtilsTest {

    @Test
    void getAllPayments() {
        try {
            List<Payment> payments = PaymentDBUtils.getAllPayments();
            assertNotNull(payments); // Ensure that the list is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving payments: " + e.getMessage());
        }
    }

    @Test
    void addPayment() {
        try {
            // Create a sample Payment object for insertion
            Payment payment = new Payment(
                    0, // Auto-generated ID
                    1, // Replace with an existing user ID
                    new BigDecimal("100.00"),
                    new Date(),
                    "Credit Card",
                    1, // Replace with an existing subscription ID
                    "REF123456"
            );

            boolean isAdded = PaymentDBUtils.addPayment(payment);
            assertTrue(isAdded); // Ensure that the payment was successfully added
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while adding payment: " + e.getMessage());
        }
    }

    @Test
    void getPaymentByID() {
        try {
            int paymentID =5; // Replace with an existing payment ID
            Payment payment = PaymentDBUtils.getPaymentByID(paymentID);
            assertNotNull(payment); // Ensure that the retrieved payment is not null
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while retrieving payment by ID: " + e.getMessage());
        }
    }

    @Test
    void updatePayment() {
        try {
            // Create a sample Payment object for updating
            Payment payment = new Payment(
                    5, // Replace with an existing payment ID
                    1, // Replace with an existing user ID
                    new BigDecimal("110.00"),
                    new Date(),
                    "Credit Card",
                    1, // Replace with an existing subscription ID
                    "REF123456"
            );

            int paymentID = 1; // Replace with an existing payment ID
            boolean isUpdated = PaymentDBUtils.updatePayment(paymentID, payment);
            assertTrue(isUpdated); // Ensure that the payment was successfully updated
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while updating payment: " + e.getMessage());
        }
    }

    @Test
    void deletePayment() {
        try {
            int paymentID = 6; // Replace with an existing payment ID
            boolean isDeleted = PaymentDBUtils.deletePayment(paymentID);
            assertTrue(isDeleted); // Ensure that the payment was successfully deleted
            // Add more specific assertions as needed
        } catch (SQLException e) {
            fail("Exception thrown while deleting payment: " + e.getMessage());
        }
    }
}
