package com.example.echonosenserest.models;

public class Coin {
    private int id;
    private String name;
    private String symbol;
    private String description;

    private String status;
    private String imageUrl;

    public Coin() {

    }

    public Coin(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coin(int id, String name, String symbol, String description, String status, String imageUrl) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
