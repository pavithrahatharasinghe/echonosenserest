package com.example.echonosenserest.models;


public class User {

    private int userId;
    private String fname;
    private String lName;
    private String email;
    private String password; // Always handle passwords securely (hashing + salting)
    private String role;
    private String status;   // Assuming the status field for enabling/disabling the user

    // Constructors
    public User() {
    }

    public User(int userId, String fname, String lName, String email, String password, String role, String status) {
        this.userId = userId;
        this.fname = fname;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
