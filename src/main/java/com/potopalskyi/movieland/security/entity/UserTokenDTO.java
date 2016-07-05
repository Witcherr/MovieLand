package com.potopalskyi.movieland.security.entity;

import com.potopalskyi.movieland.entity.enums.RoleType;

import java.time.LocalDateTime;

public class UserTokenDTO {
    private String token;
    private String login;
    private LocalDateTime connectionTime;
    private RoleType roleType;
    private int userId;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserTokenDTO{" +
                "token='" + token + '\'' +
                ", login='" + login + '\'' +
                ", connectionTime=" + connectionTime +
                ", roleType=" + roleType +
                ", userId=" + userId +
                '}';
    }
}
