package com.example.uchiha.lokaldemo.modelpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recycler_model {

    public  Recycler_model(){


    }
    public Recycler_model(String format, Integer width, Integer height, String filename, Integer id, String author, String authorUrl, String postUrl) {
        this.format = format;
        this.width = width;
        this.height = height;
        this.filename = filename;
        this.id = id;
        this.author = author;
        this.authorUrl = authorUrl;
        this.postUrl = postUrl;
    }

    @SerializedName("format")

    private String format;
    @SerializedName("width")

    private Integer width;
    @SerializedName("height")

    private Integer height;
    @SerializedName("filename")

    private String filename;
    @SerializedName("id")

    private Integer id;
    @SerializedName("author")

    private String author;
    @SerializedName("author_url")

    private String authorUrl;
    @SerializedName("post_url")

    private String postUrl;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
}
