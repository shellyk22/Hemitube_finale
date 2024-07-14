package com.example.youtubeproject.repositories;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.youtubeproject.api.ApiService;
import com.example.youtubeproject.api.RetrofitClient;
import com.example.youtubeproject.entities.SessionManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosRepository {

    private final ApiService apiService;

    public VideosRepository() {
        String token = SessionManager.getInstance().getToken(); // Fetch the token from SessionManager
        apiService = RetrofitClient.getAuthenticatedApiService(token);
    }

    public LiveData<String> uploadVideo(Uri videoUri, Uri thumbnailUri, String title, String description, String publisher, Context context) {
        MutableLiveData<String> result = new MutableLiveData<>();

        File videoFile = new File(getPathFromUri(videoUri, context));
        File thumbnailFile = new File(getPathFromUri(thumbnailUri, context));

        RequestBody videoRequestBody = RequestBody.create(MediaType.parse("video/*"), videoFile);
        RequestBody thumbnailRequestBody = RequestBody.create(MediaType.parse("image/*"), thumbnailFile);

        MultipartBody.Part videoPart = MultipartBody.Part.createFormData("file", videoFile.getName(), videoRequestBody);
        MultipartBody.Part thumbnailPart = MultipartBody.Part.createFormData("thumbnail", thumbnailFile.getName(), thumbnailRequestBody);

        RequestBody titleRequestBody = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody descriptionRequestBody = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody publisherRequestBody = RequestBody.create(MediaType.parse("text/plain"), publisher);

        String token = SessionManager.getInstance().getToken(); // Fetch the token from SessionManager

        Call<Void> call = apiService.uploadVideo("Bearer " + token, videoPart, thumbnailPart, titleRequestBody, descriptionRequestBody, publisherRequestBody);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue("Video uploaded successfully!");
                } else {
                    result.setValue("Failed to upload video");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue("Error: " + t.getMessage());
            }
        });

        return result;
    }

    private String getPathFromUri(Uri uri, Context context) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return null;
    }
}
