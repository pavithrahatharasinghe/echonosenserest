package com.example.echonosenserest.models;

import java.util.Date;

public class CoinPrediction {
    private int coinID;
    private String coinName;
    private Date date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double polarity;
    private double subjectivity;
    private double compoundScore;

    public CoinPrediction(int coinID, String coinName, Date date, double open, double high, double low, double close, double volume, double polarity, double subjectivity, double compoundScore) {
        this.coinID = coinID;
        this.coinName = coinName;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.polarity = polarity;
        this.subjectivity = subjectivity;
        this.compoundScore = compoundScore;
    }

    public int getCoinID() {
        return coinID;
    }

    public void setCoinID(int coinID) {
        this.coinID = coinID;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPolarity() {
        return polarity;
    }

    public void setPolarity(double polarity) {
        this.polarity = polarity;
    }

    public double getSubjectivity() {
        return subjectivity;
    }

    public void setSubjectivity(double subjectivity) {
        this.subjectivity = subjectivity;
    }

    public double getCompoundScore() {
        return compoundScore;
    }

    public void setCompoundScore(double compoundScore) {
        this.compoundScore = compoundScore;
    }
}
