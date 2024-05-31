package com.example.youtubeproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.pages.YouPage;

public class MainActivity extends AppCompatActivity {


    private TextView textViewWelcome;
    private Button logOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logOutButton = findViewById(R.id.btnLogOut);


        if(SessionManager.getInstance().isLogedIn()){
            textViewWelcome = findViewById(R.id.textViewWelcomeUser);
            String username = SessionManager.getInstance().getUsername();
            textViewWelcome.setText("Welcome, " + username + "!");
            logOutButton.setVisibility(View.VISIBLE);
        }
        else {
            logOutButton.setVisibility(View.GONE);
        }


        ImageButton btnYou = findViewById(R.id.btnYou);
        btnYou.setOnClickListener(v -> {
            Intent i = new Intent(this, YouPage.class);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }
}





