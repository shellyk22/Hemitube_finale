package com.example.youtubeproject.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.youtubeproject.api.ApiService;
import com.example.youtubeproject.api.RetrofitClient;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class UsersRepository {
    private ApiService apiService;

    public UsersRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
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
                    User loggedInUser = response.body();
                    if (loggedInUser != null) {
                        Log.d("TAG", "Login successful");
                        SessionManager.getInstance().setLoggedUser(loggedInUser);
                        userLiveData.setValue(loggedInUser);
                    } else {
                        Log.e("TAG", "Logged in user is null");
                        userLiveData.setValue(null);
                    }
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
                    Log.d("TAG", "Response body: " + response.body());
                    userLiveData.setValue(response.body());
                } else {
                    Log.e("TAG", "Error response: " + response.message());
                    userLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "Failure response: " + t.getMessage());
                userLiveData.setValue(null);
            }
        });

        return userLiveData;
    }

    public void updateUser(String username, User user, MutableLiveData<User> userLiveData) {
        Call<User> call = apiService.updateUser(username, user);
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
    }

    public void deleteUser(String username, MutableLiveData<Boolean> successLiveData) {
        Call<Void> call = apiService.deleteUser(username);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    successLiveData.setValue(true);
                } else {
                    successLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                successLiveData.setValue(false);
            }
        });
    }
}
