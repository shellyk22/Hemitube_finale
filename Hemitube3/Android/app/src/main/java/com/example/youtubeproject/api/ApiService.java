package com.example.youtubeproject.api;


import com.example.youtubeproject.entities.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/users") // Ensure this matches your server route
    Call<User> registerUser(@Body User user);

    @POST("/api/tokens")
    Call<User> loginUser(@Body User user);

}
