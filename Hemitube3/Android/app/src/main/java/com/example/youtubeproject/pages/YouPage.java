package com.example.youtubeproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.adapters.VideosListAdapter;
import com.example.youtubeproject.entities.SessionManager;

public class YouPage extends AppCompatActivity {

    private TextView textViewWelcome;
    private TextView textViewMyVideos;
    private Button uploadButton;
    private Button logOutButton;
    private Button btnSignIn;
     // private Button btnDetails;
    private ImageView profilePic;
    private final SessionManager sessionManager = SessionManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_page);

        textViewMyVideos = findViewById(R.id.userVideos);
        uploadButton = findViewById(R.id.btnAddVideo);
        logOutButton = findViewById(R.id.btnLogOut);
       // btnDetails = findViewById(R.id.btnDetails);
        btnSignIn = findViewById(R.id.btnSignIn);
        profilePic = findViewById(R.id.profilePic);

        uploadButton.setOnClickListener(v -> {
            Intent i = new Intent(this, UploadVideoPage.class);
            startActivity(i);
        });

        btnSignIn.setOnClickListener(v -> {
            Intent i = new Intent(this, LogInPage.class);
            startActivity(i);
        });

//        btnDetails.setOnClickListener(v -> {
//            Intent i = new Intent(this, DetailsPage.class);
//            startActivity(i);
//        });

        logOutButton.setOnClickListener(v -> {
            sessionManager.setLogedIn(false);
            sessionManager.setLoggedUser(null);
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        });

        RecyclerView lstVideos = findViewById(R.id.lstMyVideos);
        final VideosListAdapter adapter = new VideosListAdapter(this);
        lstVideos.setAdapter(adapter);
        lstVideos.setLayoutManager(new LinearLayoutManager(this));

        if (sessionManager.isLogedIn()) {
            textViewWelcome = findViewById(R.id.textViewWelcomeUser);
            String username = sessionManager.getLoggedUser().getUsername();
            textViewWelcome.setText("Welcome, " + username + "!");
            logOutButton.setVisibility(View.VISIBLE);
            btnSignIn.setVisibility(View.GONE);
            textViewMyVideos.setVisibility(View.VISIBLE);
            uploadButton.setVisibility(View.VISIBLE);
            profilePic.setVisibility(View.VISIBLE);
            // btnDetails.setVisibility(View.VISIBLE);

            // Set user profile picture and videos if available
            // profilePic.setImageURI(sessionManager.getLoggedUser().getImageUri());
            // List<Video> videos = SessionManager.getInstance().getLoggedUser().getMyVideos();
            // adapter.setVideos(videos);
        } else {
            logOutButton.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.VISIBLE);
            textViewMyVideos.setVisibility(View.GONE);
            uploadButton.setVisibility(View.GONE);
            profilePic.setVisibility(View.GONE);
             // btnDetails.setVisibility(View.GONE);
        }

        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
}
