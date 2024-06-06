package com.example.youtubeproject.entities;

import android.widget.ImageView;

import java.util.List;

public class User {

    private static final User ourInstance = new User();
    private String username;

    private String password;

    private String nickname;

    private ImageView image;

    private List<Video> myVideos;

    public static User getInstance() {
        return ourInstance;
    }

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Video> getMyVideos() {
        return myVideos;
    }

    public void setMyVideos(List<Video> myVideos) {
        this.myVideos = myVideos;
    }

    public void addVideo(Video video){
        myVideos.add(video);
    }
}
