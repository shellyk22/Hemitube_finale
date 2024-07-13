package com.example.youtubeproject.pages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;
import com.example.youtubeproject.viewmodels.VideoViewModel;

import java.io.IOException;

public class UploadVideoPage extends AppCompatActivity {

    private static final int PICK_VIDEO_REQUEST = 1;
    private static final int PICK_THUMBNAIL_REQUEST = 2;

    private EditText videoTitle, videoDescription;
    private Button selectVideoButton, selectThumbnailButton, uploadButton, backButton;
    private ImageView thumbnailPreview;
    private ProgressBar progressBar;

    private Uri videoUri, thumbnailUri;
    private VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video_page);

        videoTitle = findViewById(R.id.videoTitle);
        videoDescription = findViewById(R.id.videoDescription);
        selectVideoButton = findViewById(R.id.uploadVideoSelectVideoBtn);
        selectThumbnailButton = findViewById(R.id.uploadVidSelectPhotoBtn);
        uploadButton = findViewById(R.id.btnUpload);
        backButton = findViewById(R.id.backButton);
        thumbnailPreview = findViewById(R.id.imageViewVideoPic);
        progressBar = findViewById(R.id.progressBar);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        selectVideoButton.setOnClickListener(v -> openVideoChooser());
        selectThumbnailButton.setOnClickListener(v -> openThumbnailChooser());
        uploadButton.setOnClickListener(v -> uploadVideo());
        backButton.setOnClickListener(v -> onBackPressed());

        videoViewModel.getIsLoading().observe(this, isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        videoViewModel.getUploadResult().observe(this, result -> {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        });
    }

    private void openVideoChooser() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST);
    }

    private void openThumbnailChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Thumbnail"), PICK_THUMBNAIL_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == PICK_VIDEO_REQUEST) {
                videoUri = data.getData();
                VideoView videoView = findViewById(R.id.videoViewVideo);
                videoView.setVideoURI(videoUri);
                videoView.start();
            } else if (requestCode == PICK_THUMBNAIL_REQUEST) {
                thumbnailUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), thumbnailUri);
                    thumbnailPreview.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadVideo() {
        String title = videoTitle.getText().toString();
        String description = videoDescription.getText().toString();
        String publisher = SessionManager.getInstance().getLoggedUser().getId(); // Assuming this method retrieves the publisher ID

        if (videoUri != null && thumbnailUri != null) {
            videoViewModel.uploadVideo(videoUri, thumbnailUri, title, description, publisher, this);
        } else {
            Toast.makeText(this, "Please select both video and thumbnail", Toast.LENGTH_SHORT).show();
        }
    }
}
