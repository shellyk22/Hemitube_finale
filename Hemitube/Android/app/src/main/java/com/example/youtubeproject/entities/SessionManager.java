package com.example.youtubeproject.entities;

import android.widget.ImageView;

public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    private boolean isLogedIn = false;

    private String username;

    private String password;

    private String nickname;

    private ImageView image;

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }


    public boolean isLogedIn() {
        return isLogedIn;
    }

    public void setLogedIn(boolean logedIn) {
        isLogedIn = logedIn;
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
