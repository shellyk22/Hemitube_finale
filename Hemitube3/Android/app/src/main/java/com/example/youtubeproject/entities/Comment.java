package com.example.youtubeproject.entities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.R;

public class Comment extends AppCompatActivity {

    private String id;
    private String uploader;
    private String timePassedFromUpload;
    private String content;
    private Video video;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
    }


    public Comment(String id, String uploader, String timePassedFromUpload, String content, Video video){
        this.id = id;
        this.uploader = uploader;
        this.content = content;
        this.timePassedFromUpload = timePassedFromUpload;
        this.video = video;
    }
    public void setTimePassedFromUpload(String timePassedFromUpload) {
        this.timePassedFromUpload = timePassedFromUpload;
    }

    public String getTimePassedFromUpload() {
        return timePassedFromUpload;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

}