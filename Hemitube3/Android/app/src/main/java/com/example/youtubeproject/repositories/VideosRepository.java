package com.example.youtubeproject.repositories;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.youtubeproject.api.ApiService;
import com.example.youtubeproject.api.RetrofitClient;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.UserVideo;
import com.example.youtubeproject.entities.Video;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        Log.d("TAG", "amazinggggg2");

        RequestBody videoRequestBody = RequestBody.create(MediaType.parse("video/*"), videoFile);
        RequestBody thumbnailRequestBody = RequestBody.create(MediaType.parse("image/*"), thumbnailFile);

        MultipartBody.Part videoPart = MultipartBody.Part.createFormData("file", videoFile.getName(), videoRequestBody);
        MultipartBody.Part thumbnailPart = MultipartBody.Part.createFormData("thumbnail", thumbnailFile.getName(), thumbnailRequestBody);

        Log.d("TAG", "amazinggggg3");

        RequestBody titleRequestBody = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody descriptionRequestBody = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody publisherRequestBody = RequestBody.create(MediaType.parse("text/plain"), publisher);

        Log.d("TAG", "Upload video request prepared");

        String token = SessionManager.getInstance().getToken();

        Call<Void> call = apiService.uploadVideo("Bearer " + token, videoPart, thumbnailPart, titleRequestBody, descriptionRequestBody, publisherRequestBody);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue("Video uploaded successfully!");
                    Log.d("TAG", "Video uploaded successfully");
                } else {
                    result.setValue("Failed to upload video");
                    Log.d("TAG", "Failed to upload video: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue("Error: " + t.getMessage());
                Log.e("TAG", "Error uploading video", t);
            }
        });

        return result;
    }


    public MutableLiveData<List<Video>> getVideos() {
        MutableLiveData<List<Video>> videosData = new MutableLiveData<>();

        Log.d("TAG", "amazing");
        try {
            apiService.getVideos().enqueue(new Callback<List<Video>>() {
                @Override
                public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                    Log.d("TAG", "amazing2");
                    if (response.isSuccessful()) {
                        videosData.setValue(response.body());
                        Log.d("TAG", "Videos fetched: " + response.body().size());
                    } else {
                        Log.e("TAG", "Failed to fetch videos: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Video>> call, Throwable t) {
                    Log.e("TAG", "Error fetching videos", t);
                    videosData.setValue(null);
                }
            });
        } catch (Exception e) {
            Log.e("TAG", "Exception in enqueue", e);
        }
        return videosData;
    }

    public MutableLiveData<List<UserVideo>> getUserVideos(String username) {
        MutableLiveData<List<UserVideo>> videosData = new MutableLiveData<>();
        Log.d("TAG", "(repo) Start fetching user videos for: " + username);

        Call<List<UserVideo>> call = apiService.getUserVideos(username);

        // Log the URL
        Log.d("TAG", "(repo) Request URL: " + call.request().url());

        call.enqueue(new Callback<List<UserVideo>>() {
            @Override
            public void onResponse(Call<List<UserVideo>> call, Response<List<UserVideo>> response) {
                Log.d("TAG", "(repo) Response received");
                if (response.isSuccessful() && response.body() != null) {
                    videosData.setValue(response.body());
                    Log.d("TAG", "(repo) User videos fetched successfully: " + response.body().size());
                } else {
                    videosData.setValue(null);
                    Log.d("TAG", "(repo) Response body: " + response.body());
                    Log.e("TAG", "(repo) Failed to get user videos: " + response.message() + " - " + response.code());
                    try {
                        Log.e("TAG", "(repo) Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("TAG", "(repo) Error reading response body", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserVideo>> call, Throwable t) {
                videosData.setValue(null);
                Log.e("TAG", "(repo) Network request failed: " + t.getMessage(), t);
            }
        });
        return videosData;
    }




    private String getPathFromUri(Uri uri, Context context) {
        Log.d("TAG", "Getting path from URI: " + uri.toString());
        String path = null;

        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    path = Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                if (id.startsWith("raw:")) {
                    path = id.replaceFirst("raw:", "");
                } else {
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.parseLong(id));
                    path = getDataColumn(context, contentUri, null, null);
                }
            } else if (isMediaDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                String selection = "_id=?";
                String[] selectionArgs = new String[]{split[1]};

                path = getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            path = getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            path = uri.getPath();
        }

        Log.d("TAG", "Path from URI: " + path);
        return path;
    }

    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = MediaStore.MediaColumns.DATA;
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int columnIndex = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            Log.e("TAG", "Failed to get data column", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}


