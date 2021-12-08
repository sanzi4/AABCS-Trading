package com.example.aabcs_trading;

public class ArticleData {
    String name, URL, Author;

    public ArticleData(){}

    public ArticleData(String name, String URL, String Author) {
        this.name = name;
        this.URL = URL;
        this.Author = Author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getAuthor() { return Author; }

    public void setAuthor(String author) { Author = author; }
}
