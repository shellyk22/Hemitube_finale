package com.example.youtubeproject.pages;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;

public class DetailsPage extends AppCompatActivity {

    private static final String TAG = "DetailsPage";

    private ImageView profilePic;
    private TextView usernameTextView;
    private TextView nicknameTextView;
    private TextView userIdTextView;

    private final SessionManager sessionManager = SessionManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        profilePic = findViewById(R.id.profilePic);
        usernameTextView = findViewById(R.id.username);
        nicknameTextView = findViewById(R.id.nickname);
        userIdTextView = findViewById(R.id.userId);

        if (sessionManager.isLogedIn()) {
            Log.d(TAG, "User is logged in");
            User loggedUser = sessionManager.getLoggedUser();
            if (loggedUser.getProfilePic() != null && !loggedUser.getProfilePic().isEmpty()) {
                Uri profilePicUri = Uri.parse(loggedUser.getProfilePic());
                Log.d(TAG, "Profile picture URI: " + profilePicUri.toString());
                Glide.with(this)
                        .load(profilePicUri)
                        .placeholder(R.drawable.ic_logo)
                        .error(R.drawable.ic_logo)
                        .into(profilePic);
            } else {
                Log.d(TAG, "Profile picture URL is null or empty, showing placeholder");
                profilePic.setImageResource(R.drawable.ic_logo);
            }
            usernameTextView.setText(loggedUser.getUsername());
            nicknameTextView.setText(loggedUser.getNickname());
            userIdTextView.setText(loggedUser.getId());
        }
    }
}

