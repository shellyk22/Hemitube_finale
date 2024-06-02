package com.example.youtubeproject.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.youtubeproject.R;

@Entity

public class Video {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String uploader;

    private  String content;

    private int likes;

    private int pic;

    public Video(){
        this.pic = R.drawable.img6;
    }

    public Video(String uploader, String content, int pic){
        this.uploader = uploader;
        this.content = content;
        this.pic = pic;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
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
}
