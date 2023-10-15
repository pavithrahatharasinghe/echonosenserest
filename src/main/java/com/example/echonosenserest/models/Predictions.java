package com.example.echonosenserest.models;

public class Predictions {
    private int id;
    private String coinId;
    private double predictedClose;
    private String predictionDate;

    public Predictions(int id, String coinId, double predictedClose, String predictionDate) {
        this.id = id;
        this.coinId = coinId;
        this.predictedClose = predictedClose;
        this.predictionDate = predictionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public double getPredictedClose() {
        return predictedClose;
    }

    public void setPredictedClose(double predictedClose) {
        this.predictedClose = predictedClose;
    }

    public String getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(String predictionDate) {
        this.predictionDate = predictionDate;
    }
}
