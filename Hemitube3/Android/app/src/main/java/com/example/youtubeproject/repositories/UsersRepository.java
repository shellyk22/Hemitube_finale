package com.example.youtubeproject.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.youtubeproject.api.ApiService;
import com.example.youtubeproject.api.RetrofitClient;
import com.example.youtubeproject.entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

import java.util.concurrent.CompletableFuture;

public class UsersRepository {
    private static UsersRepository instance;

    private ApiService apiService;
    private MutableLiveData<User> userLiveData;


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



    public void updateUser(String username, String nickname, String profilePic) {
        User user = new User(null, null, null, null, null);
        user.setNickname(nickname);
        user.setProfilePic(profilePic);

        apiService.updateUser(username, user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userLiveData.setValue(response.body());
                    Log.d("UsersRepository", "User updated successfully.");
                } else {
                    Log.d("UsersRepository", "Failed to update user.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("UsersRepository", "Failed to update user: " + t.getMessage());
            }
        });
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


