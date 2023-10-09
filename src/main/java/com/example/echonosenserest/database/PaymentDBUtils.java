package com.example.echonosenserest.database;



import com.example.echonosenserest.DatabaseConnection;
import com.example.echonosenserest.models.Payment;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDBUtils {

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

    public static List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Payments");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int paymentID = resultSet.getInt("PaymentID");
            int userID = resultSet.getInt("UserID");
            BigDecimal amount = resultSet.getBigDecimal("Amount");
            Date paymentDate = resultSet.getDate("PaymentDate");
            String paymentMethod = resultSet.getString("PaymentMethod");
            int subscriptionID = resultSet.getInt("SubscriptionID");
            String referenceID = resultSet.getString("referenceID");

            Payment payment = new Payment(paymentID, userID, amount, paymentDate, paymentMethod, subscriptionID,referenceID);
            payments.add(payment);
        }

        return payments;
    }

    public static boolean addPayment(Payment payment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Payments (UserID, Amount, PaymentDate, PaymentMethod, SubscriptionID, referenceID) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, payment.getUserID());
        statement.setBigDecimal(2, payment.getAmount());
        statement.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
        statement.setString(4, payment.getPaymentMethod());
        statement.setInt(5, payment.getSubscriptionID());
        statement.setString(6, payment.getReferenceID());

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    public static Payment getPaymentByID(int paymentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Payments WHERE PaymentID = ?");
        statement.setInt(1, paymentID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int userID = resultSet.getInt("UserID");
            BigDecimal amount = resultSet.getBigDecimal("Amount");
            Date paymentDate = resultSet.getDate("PaymentDate");
            String paymentMethod = resultSet.getString("PaymentMethod");
            int subscriptionID = resultSet.getInt("SubscriptionID");
            String referenceID = resultSet.getString("referenceID");

            return new Payment(paymentID, userID, amount, paymentDate, paymentMethod, subscriptionID,referenceID);
        }
        return null;
    }

    public static boolean updatePayment(int paymentID, Payment payment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Payments SET UserID = ?, Amount = ?, PaymentDate = ?, PaymentMethod = ?, SubscriptionID = ?, referenceID =? WHERE PaymentID = ?");
        statement.setInt(1, payment.getUserID());
        statement.setBigDecimal(2, payment.getAmount());
        statement.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
        statement.setString(4, payment.getPaymentMethod());
        statement.setInt(5, payment.getSubscriptionID());
        statement.setString(6, payment.getReferenceID());
        statement.setInt(7, paymentID);


        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    public static boolean deletePayment(int paymentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Payments WHERE PaymentID = ?");
        statement.setInt(1, paymentID);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }
    // Add more methods as needed...
}
