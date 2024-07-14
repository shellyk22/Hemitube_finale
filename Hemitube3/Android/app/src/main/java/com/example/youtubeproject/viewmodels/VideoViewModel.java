package com.example.youtubeproject.viewmodels;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.youtubeproject.repositories.VideosRepository;

public class VideoViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> uploadResult;
    private final VideosRepository videosRepository;

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

    public void uploadVideo(Uri videoUri, Uri thumbnailUri, String title, String description, String username, Context context) {
        isLoading.setValue(true);
        Log.d("TAG", "amazinggggggg");
        LiveData<String> result = videosRepository.uploadVideo(videoUri, thumbnailUri, title, description, username, context);
        result.observeForever(uploadResult::setValue);
        isLoading.setValue(false);
        Log.d("TAG", "Upload video initiated");
    }
}



