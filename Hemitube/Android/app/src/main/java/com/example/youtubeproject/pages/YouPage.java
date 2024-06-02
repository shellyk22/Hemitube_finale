package com.example.youtubeproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.MainActivity;
import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.SessionManager;

import java.util.List;

public class YouPage extends AppCompatActivity {

    List<String> list;


    private TextView textViewWelcome;
    private Button logOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_page);

        logOutButton = findViewById(R.id.btnLogOut);

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> {
            Intent i = new Intent(this, LogInPage.class);
            startActivity(i);
        });


        logOutButton.setOnClickListener(v -> {
            SessionManager.getInstance().setLogedIn(false);
            Intent i = new Intent(this, YouPage.class);
            startActivity(i);
        });


        if(SessionManager.getInstance().isLogedIn()){
            textViewWelcome = findViewById(R.id.textViewWelcomeUser);
            String username = SessionManager.getInstance().getUsername();
            textViewWelcome.setText("Welcome, " + username + "!");
            logOutButton.setVisibility(View.VISIBLE);
            btnSignIn.setVisibility(View.GONE);
        }
        else {
            logOutButton.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.VISIBLE);
        }



        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity2", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity2", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity2", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity2", "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity2", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity2", "onRestart");
    }


}