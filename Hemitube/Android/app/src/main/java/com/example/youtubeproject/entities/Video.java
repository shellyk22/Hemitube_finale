package com.example.youtubeproject.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Video {

    @PrimaryKey(autoGenerate = true)
    private String id;

    private String title;

    private String uploader;

    private  String content;

    private String views;

    private String timePassedFromUpload;

    private int likes;

    private int pic;
    private int resourceId;


    public Video(String id, String title, String uploader, String content, String views,
                 String timePassedFromUpload,  int pic, int resourceId){
        this.id = id;
        this.title = title;
        this.uploader = uploader;
        this.content = content;
        this.views = views;
        this.timePassedFromUpload = timePassedFromUpload;
        this.pic = pic;
        this.resourceId = resourceId;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUploader(){
        return uploader;
    }

    public void setUploader(String uploader){
        this.uploader = uploader;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
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

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
