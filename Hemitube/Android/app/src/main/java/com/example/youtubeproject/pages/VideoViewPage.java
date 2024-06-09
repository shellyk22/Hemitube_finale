package com.example.youtubeproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.adapters.CommentsListAdapter;
import com.example.youtubeproject.entities.Comment;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.Video;

import java.util.List;

public class VideoViewPage extends AppCompatActivity {


    private VideoView videoView;
    private CommentsListAdapter adapter;

    private Video video;
    private final SessionManager sessionManager = SessionManager.getInstance();


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
        videoView.setVideoURI(video.getResourceUri());
        videoView.start();


        RecyclerView lstComments = findViewById(R.id.lstComments);
        adapter = new CommentsListAdapter(this, video);
        lstComments.setAdapter(adapter);
        lstComments.setLayoutManager(new LinearLayoutManager(this));
        List<Comment> comments = sessionManager.getCommentsForVideo(video);
        Log.i("i", comments.toString());
        adapter.setComments(comments);


        ImageButton btnComment = findViewById(R.id.btnComment);
        btnComment.setOnClickListener(v -> {
            if (sessionManager.isLogedIn()) {
                showCommentDialog();
            } else {
                Toast.makeText(this, "In Order To Comment You Must Log-In", Toast.LENGTH_SHORT).show();
            }
        });


        ImageButton btnLike = findViewById(R.id.btnLike);
        btnLike.setOnClickListener(v -> {
            btnLike.setImageResource(R.drawable.ic_pushed_like);
        });


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


    protected Video findVideoById(String id) {
        Video video = sessionManager.getVideos().get(Integer.parseInt(id) - 1);
        return video;
    }

    private void showCommentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_comment, null))
                .setPositiveButton("Comment", (dialog, id) -> {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    EditText commentInput = alertDialog.findViewById(R.id.editComment);
                    String commentText = commentInput.getText().toString().trim();
                    if (!commentText.isEmpty()) {
                        addComment(commentText);
                    } else {
                        Toast.makeText(this, "Comment cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        builder.create().show();
    }

    private void addComment(String commentText) {
        Comment newComment = new Comment(String.valueOf(sessionManager.getCommentsForVideo(video).size()) + 1, sessionManager.getLoggedUser().getUsername(), "1 sec", commentText, video);
        Log.i("i", "push now");
        sessionManager.addComment(newComment, video);
        adapter.setComments(sessionManager.getCommentsForVideo(video));
        adapter.notifyDataSetChanged();
    }


}