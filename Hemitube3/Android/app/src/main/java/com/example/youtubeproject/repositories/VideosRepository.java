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


