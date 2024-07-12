package com.example.youtubeproject.api;


import com.example.youtubeproject.entities.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/users")
    Call<User> registerUser(@Body User user);


    @POST("/api/tokens")
    Call<User> loginUser(@Body User user);

    @GET("/api/users/{username}")
    Call<User> getUser(@Path("username") String username);
}
