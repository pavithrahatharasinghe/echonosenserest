package com.example.echonosenserest.models;

import java.util.Date;

public class News {
    private int newsID;
    private String title;
    private String content;
    private double polarity;
    private String impact;
    private String relatedCoin;
    private Date dateReleased;
    private String source;

    // Constructors, getters, setters, and other methods...

    public News(int newsID, String title, String content, double polarity, String impact, String relatedCoin, Date dateReleased, String source) {
        this.newsID = newsID;
        this.title = title;
        this.content = content;
        this.polarity = polarity;
        this.impact = impact;
        this.relatedCoin = relatedCoin;
        this.dateReleased = dateReleased;
        this.source = source;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
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

    public Date getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(Date dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
