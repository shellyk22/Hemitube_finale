package com.example.youtubeproject.viewmodels;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.youtubeproject.repositories.VideosRepository;

public class VideoViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isLoading;
    private LiveData<String> uploadResult;
    private VideosRepository videosRepository;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        isLoading = new MutableLiveData<>();
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
        uploadResult = videosRepository.uploadVideo(videoUri, thumbnailUri, title, description, publisher, context);
        isLoading.setValue(false);
    }
}

