package com.wx.entity;


public class NewsListItem {

    private String title;

    private int newsId;

    private String username;

    public String getTitle() {
        return title;
    }

    public void setTitle(String newsTitle) {
        this.title = newsTitle;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
