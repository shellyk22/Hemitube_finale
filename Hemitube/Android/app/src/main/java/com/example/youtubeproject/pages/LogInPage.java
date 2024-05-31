package com.example.youtubeproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;

public class LogInPage extends AppCompatActivity {


    private EditText  editTextUsername, editTextPassword;

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
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });
        btnLogUp.setOnClickListener(v -> {
            Intent i = new Intent(this, RegistrationPage.class);
            startActivity(i);
        });
    }



    private boolean validateInput() {



        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();




        if (!username.equals(SessionManager.getInstance().getUsername())) {
            editTextUsername.setError("there is no user with this username");
            return false;
        }

        if (!password.equals(SessionManager.getInstance().getPassword())) {
            editTextPassword.setError("Incorrect Password");
            return false;
        }

        return true;
    }




}