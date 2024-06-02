package com.example.youtubeproject.entities;

import java.util.ArrayList;

public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    private boolean isLogedIn = false;

    private ArrayList<User> usersList = new ArrayList<>();

    private User loggedUser;


    public static SessionManager getInstance() {
        return ourInstance;
    }


    public SessionManager() {

    }


    public User getLoggedUser() {
        return loggedUser;
    }


    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    public boolean isLogedIn() {
        return isLogedIn;
    }

    public void setLogedIn(boolean logedIn) {
        isLogedIn = logedIn;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void addUser(User user) {
        this.usersList.add(user);
    }


    public User isUserExists(String username){
        for (int i = 0; i < this.usersList.size(); i++){
            if(username.equals(this.usersList.get(i).getUsername())){
                return usersList.get(i);
            }
        }
        return null;
    }
}