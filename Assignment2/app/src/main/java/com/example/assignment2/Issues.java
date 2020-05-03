package com.example.assignment2;

public class Issues {

    private  final String title;
    private final String author;
    private  final String authorUrl;
    private  final String level;
    private final String info;
    private final String url;
    private  final String cover;

    public Issues(String title, String author, String authorUrl, String level, String info, String url,String cover) {
        this.title = title;
        this.author = author;
        this.authorUrl=authorUrl;
        this.level=level;
        this.info=info;
        this.url=url;
        this.cover=cover;
    }
    public String getCover()
    {
        return cover;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public String getLevel() {
        return level;
    }

    public String getInfo() {
        return info;
    }

    public String getUrl() {
        return url;
    }

}