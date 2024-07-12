package com.example.youtubeproject.pages;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;

public class DetailsPage extends AppCompatActivity {

    private ImageView profilePic;
    private TextView usernameTextView;
    private TextView nicknameTextView;
    private TextView passwordTextView;

    private final SessionManager sessionManager = SessionManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        profilePic = findViewById(R.id.profilePic);
        usernameTextView = findViewById(R.id.username);
        nicknameTextView = findViewById(R.id.nickname);
        passwordTextView = findViewById(R.id.password);

        if (sessionManager.isLogedIn()) {
            User loggedUser = sessionManager.getLoggedUser();
            profilePic.setImageURI(Uri.parse(loggedUser.getProfilePic()));
            usernameTextView.setText("Username: " + loggedUser.getUsername());
            nicknameTextView.setText("Nickname: " + loggedUser.getNickname());
            passwordTextView.setText("Password: " + loggedUser.getPassword());
        }
    }
}
