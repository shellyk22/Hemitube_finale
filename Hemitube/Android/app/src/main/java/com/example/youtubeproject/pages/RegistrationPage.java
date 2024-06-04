package com.example.youtubeproject.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;

import java.io.IOException;

public class RegistrationPage extends AppCompatActivity {

    private EditText editTextPasswordRepeat, editTextNickname, editTextUsername, editTextPassword;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        editTextUsername = findViewById(R.id.editTextLogUpUsername);
        editTextPassword = findViewById(R.id.editTextLogUpPassword);
        editTextPasswordRepeat = findViewById(R.id.editTextLogUpPasswordRepeat);
        editTextNickname = findViewById(R.id.editTextLogUpNickname);

        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture);
        Button buttonSelectPhoto = findViewById(R.id.buttonSelectPhoto);

        buttonSelectPhoto.setOnClickListener(v -> openImagePicker());



        Button btnLogUp = findViewById(R.id.btnLogUp);
        btnLogUp.setOnClickListener(view -> {
            if (validateInput()) {
                // Proceed with registration
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                User user = new User();
                user.setUsername(editTextUsername.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                user.setNickname(editTextNickname.getText().toString());
                user.setImage(imageViewProfilePicture);

                // Adding the user to the session
                SessionManager.getInstance().addUser(user);
                SessionManager.getInstance().setLoggedUser(user);
                SessionManager.getInstance().setLogedIn(true);

                Intent i = new Intent(this, LogInPage.class);
                startActivity(i);
            }
        });
    }


    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewProfilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateInput() {


        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String repeatPassword = editTextPasswordRepeat.getText().toString().trim();
        String nickname = editTextNickname.getText().toString().trim();


        if (TextUtils.isEmpty(username) || username.length() < 4 || username.contains(" ")) {
            editTextUsername.setError("Username must be at least 4 characters long and contain no spaces");
            return false;
        }


        if (TextUtils.isEmpty(password) || password.length() < 8 || !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
            editTextPassword.setError("Password must be at least 8 characters long, and include uppercase, lowercase and a digit");
            return false;
        }

        if(TextUtils.isEmpty(repeatPassword) || !password.equals(repeatPassword)){
            editTextPasswordRepeat.setError("Passwords does not matching!");
            return false;
        }

        if(TextUtils.isEmpty(nickname)){
            editTextPasswordRepeat.setError("You Must enter Nickname!");
            return false;
        }

        return true;
    }
}