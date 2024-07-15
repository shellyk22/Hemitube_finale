package com.example.youtubeproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.youtubeproject.entities.User;
import com.example.youtubeproject.repositories.UsersRepository;

import java.util.concurrent.CompletableFuture;

public class UserViewModel extends ViewModel {
    private UsersRepository userRepository;

    private MutableLiveData<User> userLiveData;

    public UserViewModel() {
        userRepository = new UsersRepository();
        userLiveData = new MutableLiveData<>();
    }

    public void registerUser(User user) {
        userRepository.registerUser(user, userLiveData);
    }

    public void loginUser(User user) {
        userRepository.loginUser(user, userLiveData);
    }

    public LiveData<User> getUser(String username) {
        return userRepository.getUser(username);
    }


    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public CompletableFuture<Boolean> deleteUser(String username, String token) {
        return userRepository.deleteUser(username, token);
    }

    public CompletableFuture<Boolean> updateUser(String username, String newNickName, String newProfilePic) {
        return userRepository.updateUser(username, newNickName, newProfilePic);
    }

}
