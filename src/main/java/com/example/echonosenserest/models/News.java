package com.example.echonosenserest.models;

import java.util.Date;

public class News {
    private String newsID;
    private String title;
    private String content;
    private double polarity;
    private String impact;
    private String relatedCoin;
    private String dateReleased;
    private String source;

    private String imageUrl;

    // Constructors, getters, setters, and other methods...


    public News(String newsID, String title, String content, double polarity, String impact, String relatedCoin, String dateReleased, String source, String imageUrl) {
        this.newsID = newsID;
        this.title = title;
        this.content = content;
        this.polarity = polarity;
        this.impact = impact;
        this.relatedCoin = relatedCoin;
        this.dateReleased = dateReleased;
        this.source = source;
        this.imageUrl = imageUrl;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPolarity() {
        return polarity;
    }

    public void setPolarity(double polarity) {
        this.polarity = polarity;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getRelatedCoin() {
        return relatedCoin;
    }

    public void setRelatedCoin(String relatedCoin) {
        this.relatedCoin = relatedCoin;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public void String(String dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDateReleased(String dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
