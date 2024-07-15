package com.example.youtubeproject.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.youtubeproject.api.ApiService;
import com.example.youtubeproject.api.RetrofitClient;
import com.example.youtubeproject.api.UserUpdateRequest;
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

    public LiveData<Void> updateUser(String username, UserUpdateRequest updateRequest) {
        final MutableLiveData<Void> data = new MutableLiveData<>();
        String token = "Bearer " + SessionManager.getInstance().getToken();

        Log.d("TAG", "(repository)Updating user: " + username + " with token: " + token);
        apiService.updateUser(token, username, updateRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "(repository)User updated successfully: " + response.message());
                    data.setValue(null); // Indicate success
                } else {
                    Log.d("TAG", "(repository)Failed to update user: " + response.message());
                    Log.e("TAG", "(repo)Error in updateUser: " + response.message() + " - " + response.code());
                    try {
                        Log.d("TAG", "(repo)Response body string: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.e("TAG", "(repo)Error reading response body", e);
                    }
                    data.setValue(null); // Indicate failure
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("TAG", "Error updating user", t);
                data.setValue(null); // Indicate error
            }
        });

        return data;
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


