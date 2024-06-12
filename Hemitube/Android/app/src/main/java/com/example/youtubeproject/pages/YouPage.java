package com.example.youtubeproject.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.adapters.VideosListAdapter;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.Video;

import java.util.List;

public class YouPage extends AppCompatActivity {

    private TextView textViewWelcome;

    private TextView textViewMyVideos;

    private Button uploadButton;
    private Button logOutButton;

    private Button btnSignIn;
    private final SessionManager sessionManager = SessionManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_page);

        textViewMyVideos = findViewById(R.id.userVideos);

        uploadButton = findViewById(R.id.btnAddVideo);


        logOutButton = findViewById(R.id.btnLogOut);

        btnSignIn = findViewById(R.id.btnSignIn);



        uploadButton.setOnClickListener(v -> {
            Intent i = new Intent(this, UploadVideoPage.class);
            startActivity(i);
        });


        btnSignIn.setOnClickListener(v -> {
            Intent i = new Intent(this, LogInPage.class);
            startActivity(i);
        });


        logOutButton.setOnClickListener(v -> {
            sessionManager.setLogedIn(false);
            sessionManager.setLoggedUser(null);
            Intent i = new Intent(this, YouPage.class);
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        RecyclerView lstVideos = findViewById(R.id.lstMyVideos);
        final VideosListAdapter adapter = new VideosListAdapter(this);
        lstVideos.setAdapter(adapter);
        lstVideos.setLayoutManager(new LinearLayoutManager(this));


        if(sessionManager.isLogedIn()){
            textViewWelcome = findViewById(R.id.textViewWelcomeUser);
            String username = sessionManager.getLoggedUser().getUsername();
            textViewWelcome.setText("Welcome, " + username + "!");
            logOutButton.setVisibility(View.VISIBLE);
            btnSignIn.setVisibility(View.GONE);
            textViewMyVideos.setVisibility(View.VISIBLE);
            uploadButton.setVisibility(View.VISIBLE);
            List<Video> videos = SessionManager.getInstance().getLoggedUser().getMyVideos();
            adapter.setVideos(videos);
        }
        else {
            logOutButton.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.VISIBLE);
            textViewMyVideos.setVisibility(View.GONE);
            uploadButton.setVisibility(View.GONE);
        }

        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });



    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        return;
    }


}