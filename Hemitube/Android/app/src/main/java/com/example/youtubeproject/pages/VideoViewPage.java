package com.example.youtubeproject.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.Video;

public class VideoViewPage extends AppCompatActivity {


    VideoView videoView;

    Video video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_page);

        Intent intent = getIntent();
        String videoId = intent.getStringExtra("video_id");

        video = findVideoById(videoId);



         TextView title = findViewById(R.id.videoTitle);
         TextView uploader = findViewById(R.id.videoUploader);
         TextView views = findViewById(R.id.videoViews);
         TextView timePassed = findViewById(R.id.videoPassedTime);

         title.setText(video.getTitle());
         uploader.setText(video.getUploader() + " . ");
         views.setText(video.getViews() + " views . ");
         timePassed.setText(video.getTimePassedFromUpload() + " ago");




        videoView = findViewById(R.id.videoPlayer);
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+video.getResourceId()));
        videoView.start();


        ImageButton btnYou = findViewById(R.id.btnYou);
        btnYou.setOnClickListener(v -> {
            Intent i = new Intent(this, YouPage.class);
            startActivity(i);
        });


        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

    }



    protected Video findVideoById(String id){
        Video video = SessionManager.getInstance().getVideos().get(Integer.parseInt(id) - 1);
        return video;
    }



}