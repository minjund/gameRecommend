package com.minjun.gamerecommend.application.user.dto;

import com.minjun.gamerecommend.service.user.UserInfo;

public record SteamLoginCallBackResponse(String steamId) {
    public static SteamLoginCallBackResponse from(UserInfo userSaveCommand) {
        return new SteamLoginCallBackResponse(userSaveCommand.userId());
    }
}
