package com.example.echonosenserest.models;

import java.math.BigDecimal;

public class Subscription {
    private int subscriptionID;
    private String planName;
    private String duration;
    private BigDecimal price;

    public Subscription(int subscriptionID, String planName, String duration, BigDecimal price) {
        this.subscriptionID = subscriptionID;
        this.planName = planName;
        this.duration = duration;
        this.price = price;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
