package com.example.youtubeproject.entities;


import android.util.Log;

import java.io.Serializable;


public class User implements Serializable {
    private String username;
    private String nickName;
    private String password;
    private String profilePic;
    private String token;

    public User(String username, String nickName, String password, String profilePic) {
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.profilePic = profilePic;
    }


    // Constructor for login
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getNickname() {
        return nickName;
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    ///------------token------------
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        Log.d("User", "Setting token in User: " + token);
    }

}
