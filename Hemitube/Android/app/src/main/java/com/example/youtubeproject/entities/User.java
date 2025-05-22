package com.example.youtubeproject.entities;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class User {

    private static final User ourInstance = new User();
    private String username;

    private String password;

    private String nickname;

    private Uri imageUri;
    private final SessionManager sessionManager = SessionManager.getInstance();


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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
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
        List<Video> videos = sessionManager.getVideos();
        List<Video> newList = new ArrayList<>();
        for(int i = 0; i < videos.size(); i++){
            if(videos.get(i).getUploader().equals(this.getUsername())){
                newList.add(videos.get(i));
            }
        }
        return newList;
    }

}
