package com.example.echonosenserest.models;

import java.util.Date;

public class UserSubscription {

    private int userSubscriptionID;
    private int userID;
    private int subscriptionID;
    private Date startDate;
    private Date endDate;

    public UserSubscription() {
    }

    public UserSubscription(int userSubscriptionID, int userID, int subscriptionID, Date startDate, Date endDate) {
        this.userSubscriptionID = userSubscriptionID;
        this.userID = userID;
        this.subscriptionID = subscriptionID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getUserSubscriptionID() {
        return userSubscriptionID;
    }

    public void setUserSubscriptionID(int userSubscriptionID) {
        this.userSubscriptionID = userSubscriptionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
