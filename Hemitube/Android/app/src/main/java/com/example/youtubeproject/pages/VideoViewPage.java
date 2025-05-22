package com.example.youtubeproject.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_page);

        Intent intent = getIntent();
        String videoId = intent.getStringExtra("video_id");

        video = findVideoById(videoId);


        TextView title = findViewById(R.id.videoTitle);
        TextView content = findViewById(R.id.videoContent);
        TextView uploader = findViewById(R.id.videoUploader);
        TextView views = findViewById(R.id.videoViews);
        TextView timePassed = findViewById(R.id.videoPassedTime);

        title.setText(video.getTitle());
        content.setText(video.getContent());
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
        List<Comment> comments = video.getComments();
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


        Button btnDelete = findViewById(R.id.videoDeleteBtn);
        btnDelete.setOnClickListener(v -> {
            if (sessionManager.isLogedIn()) {
                if (sessionManager.getLoggedUser().getUsername().equals(video.getUploader())) {
                    List<Video> videos = sessionManager.getVideos();
                    videos.remove(video);
                    sessionManager.setVideos(videos);
                    Intent i = new Intent(this, YouPage.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "User can Only Delete his Uploads!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Sign In In Order To Upload/Delete Videos!", Toast.LENGTH_SHORT).show();
            }
        });


        Button btnEdit = findViewById(R.id.videoEditBtn);
        btnEdit.setOnClickListener(v -> {
            if (sessionManager.getLoggedUser().getUsername().equals(video.getUploader())) {
                showEditDialog();
            } else {
                Toast.makeText(this, "In Order To Edit Video you must be his uploader", Toast.LENGTH_SHORT).show();
            }
        });


        ImageButton btnLike = findViewById(R.id.btnLike);
        btnLike.setOnClickListener(v -> {
            btnLike.setImageResource(R.drawable.ic_pushed_like);
        });

        ImageButton btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

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


        if (sessionManager.isLogedIn()) {
            if (sessionManager.getLoggedUser().getUsername().equals(video.getUploader())) {
                btnDelete.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.VISIBLE);
            } else {
                btnDelete.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
            }
        } else {
            btnDelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);

        }


    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        return;
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


    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_edit, null))
                .setPositiveButton("Edit", (dialog, id) -> {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    EditText videoTitleInput = alertDialog.findViewById(R.id.editVideoTitle);
                    EditText videoContentInput = alertDialog.findViewById(R.id.editVideoContent);
                    String titleText = videoTitleInput.getText().toString().trim();
                    String contentText = videoContentInput.getText().toString().trim();
                    if (!titleText.isEmpty()) {
                        changeVideoDetails(titleText, contentText);
                    } else {
                        Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        builder.create().show();
    }

    private void addComment(String commentText) {
        Comment newComment = new Comment(String.valueOf(video.getComments().size()) + 1, sessionManager.getLoggedUser().getUsername(), "1 sec", commentText, video);
        sessionManager.addComment(newComment, video);
        adapter.setComments(video.getComments());
        adapter.notifyDataSetChanged();
    }

    private void changeVideoDetails(String titleText, String cotentText) {
        Video oldVideo = video;
        video.setTitle(titleText);
        video.setContent(cotentText);
        sessionManager.replaceVideo(oldVideo, video);
        TextView title = findViewById(R.id.videoTitle);
        title.setText(titleText);
        finish();
        startActivity(getIntent());
    }


}