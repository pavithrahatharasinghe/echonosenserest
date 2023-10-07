package com.example.echonosenserest.models;

import java.util.Date;

public class Notification {
    private int notificationID;
    private int userID;
    private String message;
    private Date date;



    public Notification(int notificationID, int userID, String message, Date date) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.date = date;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
