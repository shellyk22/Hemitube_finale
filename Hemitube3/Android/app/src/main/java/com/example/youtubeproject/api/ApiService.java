package com.example.youtubeproject.api;


import com.example.youtubeproject.entities.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/users")
    Call<User> registerUser(@Body User user);


    @POST("/api/tokens")
    Call<User> loginUser(@Body User user);

    @GET("/api/users/{username}")
    Call<User> getUser(@Path("username") String username);


    @DELETE("/api/users/{username}")
    Call<Void> deleteUser(@Path("username") String username, @Header("Authorization") String token);

    @PUT("api/users/{username}")
    Call<Void> updateUser(@Header("Authorization") String token, @Path("username") String username, @Body UserUpdateRequest updateRequest);

    @Multipart
    @POST("/api/users/{username}/videos")
    Call<Void> uploadVideo(
            @Header("Authorization") String token,
            @Part MultipartBody.Part video,
            @Part MultipartBody.Part thumbnail,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("publisher") RequestBody publisher
    );
}
