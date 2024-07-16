package com.example.youtubeproject.viewmodels;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.youtubeproject.entities.UserVideo;
import com.example.youtubeproject.entities.Video;
import com.example.youtubeproject.repositories.VideosRepository;

import com.example.youtubeproject.viewmodels.VideoViewModel;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> uploadResult;
    private final VideosRepository videosRepository;


    private MutableLiveData<List<Video>> videos;


    private MutableLiveData<List<UserVideo>> userVideos;


    public VideoViewModel(@NonNull Application application) {
        super(application);
        isLoading = new MutableLiveData<>();
        uploadResult = new MutableLiveData<>();
        videosRepository = new VideosRepository();
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getUploadResult() {
        return uploadResult;
    }

    public void uploadVideo(Uri videoUri, Uri thumbnailUri, String title, String description, String publisher, Context context) {
        isLoading.setValue(true);
        LiveData<String> result = videosRepository.uploadVideo(videoUri, thumbnailUri, title, description, publisher, context);
        result.observeForever(uploadResult::setValue);
        isLoading.setValue(false);
        Log.d("TAG", "Upload video initiated");
    }

    public LiveData<List<Video>> getVideos() {
        if (videos == null) {

            videos = videosRepository.getVideos();
        }
        return videos;
    }

    public MutableLiveData<List<UserVideo>> getUserVideos(String username) {
        if (userVideos == null) {
            userVideos = videosRepository.getUserVideos(username);
        }
        return userVideos;
    }
}





