package com.example.youtubeproject.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;
import com.example.youtubeproject.viewmodels.UserViewModel;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CompletableFuture;

public class DetailsPage extends AppCompatActivity {

    private static final String TAG = "DetailsPage";

    private ImageView profilePic;
    private TextView usernameTextView;
    private EditText nicknameEditText;
    private TextView userIdTextView;
    private Button updateButton, deleteButton;
    private Uri profilePicUri;

    private UserViewModel userViewModel;
    private final SessionManager sessionManager = SessionManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        profilePic = findViewById(R.id.profilePic);
        usernameTextView = findViewById(R.id.username);
        nicknameEditText = findViewById(R.id.nickname);
        userIdTextView = findViewById(R.id.userId);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (sessionManager.isLogedIn()) {
            Log.d(TAG, "User is logged in");
            User loggedUser = sessionManager.getLoggedUser();
            if (loggedUser.getProfilePic() != null && !loggedUser.getProfilePic().isEmpty()) {
                profilePicUri = Uri.parse(loggedUser.getProfilePic());
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
            nicknameEditText.setText(loggedUser.getNickname());
            userIdTextView.setText(loggedUser.getId());
        }

        updateButton.setOnClickListener(view -> {
            String username = sessionManager.getLoggedUser().getUsername();
            String nickname = nicknameEditText.getText().toString();
            String profilePicBase64 = convertImageViewToBase64WithPrefix(profilePic);

            userViewModel.updateUser(username, nickname, profilePicBase64);
            Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show();
        });

        deleteButton.setOnClickListener(view -> {
            String username = sessionManager.getLoggedUser().getUsername();
            String token = sessionManager.getToken();
            CompletableFuture<Boolean> deleteFuture = userViewModel.deleteUser(username, token);

            deleteFuture.thenAccept(success -> runOnUiThread(() -> {
                if (success) {
Log.d(TAG, "USER DELETED");
                    Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
                    sessionManager.setLogedIn(false);
                    sessionManager.setToken(null);
                    sessionManager.setLoggedUser(null);
                    // Navigate to login page
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }));
        });


        userViewModel.getUserLiveData().observe(this, user -> {
            if (user != null) {
                sessionManager.setLoggedUser(user);
                Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "User update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String convertImageViewToBase64WithPrefix(ImageView imageView) {
        // Extract Bitmap from ImageView
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();

        // Convert Bitmap to Base64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream); // Use PNG or JPEG as needed
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String base64String = Base64.encodeToString(byteArray, Base64.DEFAULT);

        // Add prefix to Base64 string
        return "data:image/png;base64," + base64String; // Adjust MIME type if needed
    }
}
