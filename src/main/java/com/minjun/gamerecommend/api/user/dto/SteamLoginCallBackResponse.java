package com.minjun.gamerecommend.api.user.dto;

import com.minjun.gamerecommend.domain.user.UserId;

public record SteamLoginCallBackResponse(String steamId) {
    public static SteamLoginCallBackResponse from(UserId userSaveCommand) {
        return new SteamLoginCallBackResponse(userSaveCommand.userId());
    }
}
