package com.example.echonosenserest.models;


import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private int paymentID;
    private int userID;
    private BigDecimal amount;
    private Date paymentDate;
    private String paymentMethod;
    private int subscriptionID;

    public Payment(int paymentID, int userID, BigDecimal amount, Date paymentDate, String paymentMethod, int subscriptionID) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.subscriptionID = subscriptionID;
    }

    public Payment(int userID, BigDecimal amount, Date paymentDate, String paymentMethod, int subscriptionID) {
        this.userID = userID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.subscriptionID = subscriptionID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }
}
