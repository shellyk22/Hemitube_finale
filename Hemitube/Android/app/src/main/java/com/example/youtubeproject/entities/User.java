package com.example.youtubeproject.entities;

import android.widget.ImageView;

public class User {

    private static final User ourInstance = new User();
    private String username;

    private String password;

    private String nickname;

    private ImageView image;

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
}
