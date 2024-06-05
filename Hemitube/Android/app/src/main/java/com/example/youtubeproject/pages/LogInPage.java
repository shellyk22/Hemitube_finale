package com.example.youtubeproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;

public class LogInPage extends AppCompatActivity {


    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        editTextUsername = findViewById(R.id.editTextLogInUsername);
        editTextPassword = findViewById(R.id.editTextLogInPassword);


        Button btnLogIn = findViewById(R.id.btnLogIn);
        Button btnLogUp = findViewById(R.id.btnJoin);


        btnLogIn.setOnClickListener(v -> {
            if (validateInput()) {
                SessionManager.getInstance().setLogedIn(true);
                User user = SessionManager.getInstance().isUserExists(editTextUsername.getText().toString());
                SessionManager.getInstance().setLoggedUser(user);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });
        btnLogUp.setOnClickListener(v -> {
            Intent i = new Intent(this, RegistrationPage.class);
            startActivity(i);
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


    private boolean validateInput() {


        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        User user = SessionManager.getInstance().isUserExists(username);

        if (user != null) {
            if (!password.equals(user.getPassword())) {
                editTextPassword.setError("Incorrect Password");
                return false;
            } else {
                return true;
            }
        } else {
            editTextUsername.setError("There is no user with this Username");
            return false;
        }
    }

}