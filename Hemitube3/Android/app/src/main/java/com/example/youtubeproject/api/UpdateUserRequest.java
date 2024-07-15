package com.example.youtubeproject.api;

public class UpdateUserRequest {
    private String nickname;
    private String profilePic;

    public UpdateUserRequest(String nickname, String profilePic) {
        this.nickname = nickname;
        this.profilePic = profilePic;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
