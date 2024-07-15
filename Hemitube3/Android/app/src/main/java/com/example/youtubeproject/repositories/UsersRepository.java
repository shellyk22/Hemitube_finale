package com.example.youtubeproject.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.youtubeproject.api.ApiService;
import com.example.youtubeproject.api.RetrofitClient;
import com.example.youtubeproject.api.UpdateUserRequest;
import com.example.youtubeproject.entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

import com.example.youtubeproject.entities.SessionManager;

import java.util.concurrent.CompletableFuture;

public class UsersRepository {
    private static UsersRepository instance;

    private ApiService apiService;
    private MutableLiveData<User> userLiveData;
    private final SessionManager sessionManager = SessionManager.getInstance();


    public UsersRepository() {

        apiService = RetrofitClient.getClient().create(ApiService.class);
        userLiveData = new MutableLiveData<>();
    }

    public static synchronized UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();
        }
        return instance;
    }

    public void registerUser(User user, MutableLiveData<User> userLiveData) {
        Call<User> call = apiService.registerUser(user);
        Log.d("TAG", "Request URL: " + call.request().url());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    Log.e("TAG", "Error in registerUser: " + response.message() + " - " + response.code());
                    userLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "Failure in registerUser: " + t.getMessage());
                userLiveData.setValue(null);
            }
        });
    }

    public void loginUser(User user, MutableLiveData<User> userLiveData) {
        Call<User> call = apiService.loginUser(user);
        Log.d("TAG", "Request URL: " + call.request().url());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    Log.e("TAG", "Error in loginUser: " + response.message() + " - " + response.code());
                    userLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "Failure in loginUser: " + t.getMessage());
                userLiveData.setValue(null);
            }
        });
    }


    public LiveData<User> getUser(String username) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();

        Call<User> call = apiService.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    userLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userLiveData.setValue(null);
            }
        });

        return userLiveData;
    }

    public CompletableFuture<Boolean> updateUser(String username, String newNickName, String newProfilePic) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(newNickName, newProfilePic);

        String token = sessionManager.getToken();
        Log.d("TAG", "Updating user with username: " + username +  " nickname " + newNickName);
        Log.d("TAG", "Updating user with nickname: " + updateUserRequest.getNickname());
        apiService.updateUser(username, "Bearer " + token, updateUserRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Repository", "User updated successfully");
                    future.complete(true);
                    LiveData<com.example.youtubeproject.entities.User> shellyUser = getUser(username);
                    Log.d("Repository", "my user" + shellyUser.toString());

                } else {
                    Log.e("Repository", "Failed to update user: " + response.message());
                    future.complete(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Repository", "Error updating user: " + t.getMessage());
                future.completeExceptionally(t);
            }
        });
        return future;
    }



    public CompletableFuture<Boolean> deleteUser(String username, String token) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        apiService.deleteUser(username, "Bearer " + token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                future.complete(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }


}


