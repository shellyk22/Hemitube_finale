package com.example.youtubeproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.youtubeproject.entities.User;
import com.example.youtubeproject.repositories.UsersRepository;

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

    public void getUser(String username) {
        userRepository.getUser(username, userLiveData);
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
