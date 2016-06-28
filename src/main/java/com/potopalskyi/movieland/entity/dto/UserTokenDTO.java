package com.potopalskyi.movieland.entity.dto;

import com.potopalskyi.movieland.entity.UserCredential;

import java.time.LocalDateTime;

public class UserTokenDTO {
    private String token;
    private UserCredential userCredential;
    private LocalDateTime connectionTime;

    public String getToken() {
        return token;
    }

    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setConnectionTime(LocalDateTime connectionTime) {
        this.connectionTime = connectionTime;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    @Override
    public String toString() {
        return "UserTokenDTO{" +
                "token='" + token + '\'' +
                ", userCredential=" + userCredential +
                ", connectionTime=" + connectionTime +
                '}';
    }
}
