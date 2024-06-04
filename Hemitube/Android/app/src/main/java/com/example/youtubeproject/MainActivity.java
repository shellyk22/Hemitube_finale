package com.example.youtubeproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.adapters.VideosListAdapter;
import com.example.youtubeproject.entities.Video;
import com.example.youtubeproject.pages.YouPage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView lstVideos = findViewById(R.id.lstVideos);
        final VideosListAdapter adapter = new VideosListAdapter(this);
        lstVideos.setAdapter(adapter);
        lstVideos.setLayoutManager(new LinearLayoutManager(this));
        List<Video> videos = new ArrayList<>();
        videos.add(new Video("Finding the most Dangerous Secret","Alice", "Cool Vid","74k", "3 days", R.drawable.img6));
        videos.add(new Video("Finding the most Dangerous Secret2", "Foo", "Cool Vid2","999", "1 month", R.drawable.img5));
        videos.add(new Video("Finding the most Dangerous Secret3", "Bar", "Cool Vid3","1M", "10 months", R.drawable.img4));
        videos.add(new Video("Finding the most Dangerous Secret","Alice", "Cool Vid","74k", "3 days", R.drawable.img3));
        videos.add(new Video("Finding the most Dangerous Secret2", "Foo", "Cool Vid2","999", "1 month", R.drawable.img2));
        videos.add(new Video("Finding the most Dangerous Secret3", "Bar", "Cool Vid3","1M", "10 months", R.drawable.img6));
        adapter.setVideos(videos);

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





