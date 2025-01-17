package com.minjun.gamerecommend.service.user;

public record UserInfo(String userId) {
    public static UserInfo from(String saveUserId) {
        return new UserInfo(saveUserId);
    }
}
