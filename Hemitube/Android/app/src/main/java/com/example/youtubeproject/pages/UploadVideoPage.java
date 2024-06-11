package com.example.youtubeproject.pages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;
import com.example.youtubeproject.entities.Video;

public class UploadVideoPage extends AppCompatActivity {


    private Button backButton;
    private Button uploadButton;
    private EditText editTextTitle, editTextContent;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_VIDEO_REQUEST = 2;
    private ImageView imageViewThumbnail;
    private VideoView videoViewVideo;
    private Uri videoUri;
    private Uri imageUri;
    private final SessionManager sessionManager = SessionManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video_page);


        backButton = findViewById(R.id.backButton);
        uploadButton = findViewById(R.id.btnUpload);

        editTextTitle = findViewById(R.id.editTextUploadVidTitle);
        editTextContent = findViewById(R.id.editTextUploadVidContent);
        imageViewThumbnail = findViewById(R.id.imageViewVideoPic);
        videoViewVideo = findViewById(R.id.videoViewVideo);


        Button buttonSelectPhoto = findViewById(R.id.uploadVidSelectPhotoBtn);
        Button buttonSelectVideo = findViewById(R.id.uploadVideoSelectVideoBtn);


        backButton.setOnClickListener(v -> {
            Intent i = new Intent(this, YouPage.class);
            startActivity(i);
        });


        buttonSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonSelectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideoChooser();
            }
        });

        uploadButton.setOnClickListener(v -> {
            uploadVideo();
            Intent i = new Intent(this, YouPage.class);
            startActivity(i);
        });
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        return;
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void openVideoChooser() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                imageUri = data.getData();
                imageViewThumbnail.setImageURI(imageUri);
            } else if (requestCode == PICK_VIDEO_REQUEST) {
                videoUri = data.getData();
                videoViewVideo.setVideoURI(videoUri);
                videoViewVideo.start();
            }
        }
    }

    private void uploadVideo() {
        if (imageUri == null || videoUri == null) {
            Toast.makeText(this, "Please select an image and a video", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("i", String.valueOf(sessionManager.getVideos().size()));
        String id = String.valueOf(sessionManager.getVideos().size() + 1);
        Log.i("i", "hello2");
        User user = sessionManager.getLoggedUser();
        Log.i("i", imageUri.toString());
        Video video = new Video(id, editTextTitle.getText().toString(), user.getUsername(), editTextContent.getText().toString(), "0", "1 sec", imageUri, videoUri);
        Log.i("i", imageUri.toString());
        sessionManager.addVideo(video);

    }


}