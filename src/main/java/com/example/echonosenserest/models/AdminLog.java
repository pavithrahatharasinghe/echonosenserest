package com.example.echonosenserest.models;

import java.util.Date;

public class AdminLog {

    private int logID;
    private int adminUserID;
    private String action;
    private Date date;

    public AdminLog() {
    }

    public AdminLog(int logID, int adminUserID, String action, Date date) {
        this.logID = logID;
        this.adminUserID = adminUserID;
        this.action = action;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
