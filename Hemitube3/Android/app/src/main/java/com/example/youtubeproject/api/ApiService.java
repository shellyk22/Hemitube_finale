package com.example.youtubeproject.api;


import com.example.youtubeproject.entities.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/users")
    Call<User> registerUser(@Body User user);


    @POST("/api/tokens")
    Call<User> loginUser(@Body User user);

    @GET("/api/users/{username}")
    Call<User> getUser(@Path("username") String username);

    @PUT("/api/users/{username}")
    Call<User> updateUser(@Path("username") String username, @Body User user);

    @DELETE("/api/users/{username}")
    Call<Void> deleteUser(@Path("username") String username);
}
