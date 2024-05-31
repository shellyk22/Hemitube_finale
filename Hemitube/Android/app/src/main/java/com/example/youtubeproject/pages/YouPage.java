package com.example.youtubeproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeproject.R;

import java.util.List;

public class YouPage extends AppCompatActivity {

    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_page);

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> {
            Intent i = new Intent(this, LogInPage.class);
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