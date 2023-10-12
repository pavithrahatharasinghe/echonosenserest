package com.example.echonosenserest.models;


import java.sql.Timestamp;

public class NewsArticle {
    private String id;
    private String ticker;
    private String title;
    private String author;
    private String publishedUtc;
    private String articleUrl;
    private String imageUrl;
    private String description;

    public NewsArticle(String id, String ticker, String title, String author, String publishedUtc, String articleUrl, String imageUrl, String description) {
        this.id = id;
        this.ticker = ticker;
        this.title = title;
        this.author = author;
        this.publishedUtc = publishedUtc;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public NewsArticle() {

    }

    // Getters and setters for all fields...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedUtc() {
        return publishedUtc;
    }

    public void setPublishedUtc(String publishedUtc) {
        this.publishedUtc = publishedUtc;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
