package com.example.echonosenserest.models;

import java.util.Date;

public class AdminLog {

    private int logID;
    private int adminUserID;
    private String action;
    private String description;
    private Date createdAt;

    public AdminLog() {
    }

    public AdminLog(int logID, int adminUserID, String action, String description, Date createdAt) {
        this.logID = logID;
        this.adminUserID = adminUserID;
        this.action = action;
        this.description = description;
        this.createdAt = createdAt;
    }

    public AdminLog(int logID, int adminUserID, String action, Date date) {
        this.logID = logID;
        this.adminUserID = adminUserID;
        this.action = action;
        this.createdAt = date;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getAdminUserID() {
        return adminUserID;
    }

    public void setAdminUserID(int adminUserID) {
        this.adminUserID = adminUserID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDate() {
        return createdAt;
    }

    public void setDate(Date date) {
        this.createdAt = date;
    }
}
