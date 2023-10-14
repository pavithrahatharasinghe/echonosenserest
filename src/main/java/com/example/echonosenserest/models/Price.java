package com.example.echonosenserest.models;

public class Price {
    private int id;
    private String symbol;
    private double price;
    private String fetchTime;

    public Price(int id, String symbol, double price, String fetchTime) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.fetchTime = fetchTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }
}
