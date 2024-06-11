package com.example.youtubeproject.entities;

import android.net.Uri;
import android.util.Log;

import com.example.youtubeproject.R;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager ourInstance;

    public SessionManager() {

    }

    private boolean isNightModeOn = false;

    private boolean isLogedIn = false;

    private List<User> usersList = new ArrayList<>();

    private User loggedUser;

    List<Video> videos;


    public static SessionManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new SessionManager();
            ourInstance.resetVideos();
        }

        return ourInstance;
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

    public List<User> getUsersList() {
        return usersList;
    }

    public void addUser(User user) {
        this.usersList.add(user);
    }

    public boolean isNightModeOn() {
        return isNightModeOn;
    }

    public void setNightModeOn(boolean nightModeOn) {
        isNightModeOn = nightModeOn;
    }


    public User isUserExists(String username) {
        for (int i = 0; i < this.usersList.size(); i++) {
            if (username.equals(this.usersList.get(i).getUsername())) {
                return usersList.get(i);
            }
        }
        return null;
    }

    public List<Video> getVideos() {
        return videos;
    }


    public void addVideo(Video video) {
        videos.add(video);
    }

    private Uri getResourceVideoUri(int rawVideo) {
        return Uri.parse("android.resource://com.example.youtubeproject" + "/" + rawVideo);
    }

    private Uri getResourceImageUri(int rawImage) {
        return Uri.parse("android.resource://com.example.youtubeproject" + "/" + rawImage);
//        Log.i("i", this.context.getResources().toString());

    }

    private void resetVideos() {
        videos = new ArrayList<>();
        videos.add(new Video("1", "Finding the most Dangerous Secret", "Alice", "Cool Vid", "74k", "3 days", getResourceImageUri(R.drawable.img6), getResourceVideoUri(R.raw.sample_vid)));
        videos.add(new Video("2", "Searching for the most Expensive diamond", "Foo", "Cool Vid2", "999", "1 month", getResourceImageUri(R.drawable.img5), getResourceVideoUri(R.raw.sample_vid2)));
        videos.add(new Video("3", "24 hours in one room with 100 people", "Bar", "Cool Vid3", "1M", "10 months", getResourceImageUri(R.drawable.img4), getResourceVideoUri(R.raw.sample_vid3)));
        videos.add(new Video("4", "Finding the most Dangerous Secret", "Alice", "Cool Vid", "74k", "3 days", getResourceImageUri(R.drawable.img3), getResourceVideoUri(R.raw.sample_vid2)));
        videos.add(new Video("5", "Flying to the world cup final", "Foo", "Cool Vid2", "999", "1 month", getResourceImageUri(R.drawable.img2), getResourceVideoUri(R.raw.sample_vid)));
        videos.add(new Video("6", "100 days challenge", "Bar", "Cool Vid3", "1M", "10 months", getResourceImageUri(R.drawable.img6), getResourceVideoUri(R.raw.sample_vid3)));
        videos.add(new Video("7", "Finding the most Dangerous Secret", "Alice", "Cool Vid", "74k", "3 days", getResourceImageUri(R.drawable.img6), getResourceVideoUri(R.raw.sample_vid2)));
        videos.add(new Video("8", "Finding the most Dangerous Secret2", "Foo", "Cool Vid2", "999", "1 month", getResourceImageUri(R.drawable.img5), getResourceVideoUri(R.raw.sample_vid)));
        videos.add(new Video("9", "Finding the most Dangerous Secret3", "Bar", "Cool Vid3", "1M", "10 months", getResourceImageUri(R.drawable.img2), getResourceVideoUri(R.raw.sample_vid2)));
        videos.add(new Video("10", "Finding the most Dangerous Secret3", "Bar", "Cool Vid3", "1M", "10 months", getResourceImageUri(R.drawable.img4), getResourceVideoUri(R.raw.sample_vid3)));
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


    public void addComment(Comment comment, Video video) {
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getId().equals(video.getId())) {
                Log.i("i", video.getId());
                videos.get(i).addComment(comment);
            }
        }
    }

    public void deleteComment(Comment comment, Video video) {
        List<Comment> comments = video.getComments();
        comments.remove(comment);
        video.setComments(comments);

    }

    public void replaceVideo(Video oldVideo, Video video){
            videos.remove(oldVideo);
            videos.add(video);
        }

}