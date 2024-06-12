package com.example.youtubeproject.entities;


import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Video {

    @PrimaryKey(autoGenerate = true)
    private String id;

    private String title;

    private String uploader;

    private String views;

    private String timePassedFromUpload;
    private String content;

    private Uri picUri;
    private Uri resourceUri;

    private List<Comment> comments;


    public Video(String id, String title, String uploader, String content, String views,
                 String timePassedFromUpload, Uri picUri, Uri resourceUri) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.uploader = uploader;
        this.views = views;
        this.timePassedFromUpload = timePassedFromUpload;
        this.picUri = picUri;
        this.resourceUri = resourceUri;
        this.comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Uri getPicUri() {
        return picUri;
    }

    public void setPicUri(Uri picUri) {
        this.picUri = picUri;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getTimePassedFromUpload() {
        return timePassedFromUpload;
    }

    public void setTimePassedFromUpload(String timePassedFromUpload) {
        this.timePassedFromUpload = timePassedFromUpload;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Uri getResourceUri() {
        return this.resourceUri;
    }

    public void setResourceUri(Uri resourceUri) {
        this.resourceUri = resourceUri;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
