package com.minjun.gamerecommend.domain.user;

import lombok.Builder;

import java.util.HashMap;

@Builder
public record UserSaveCommand(String userId, String nickname) {
    public static UserSaveCommand from(HashMap<String, Object> oneUserInfo) {
        return new UserSaveCommand(
                String.valueOf(oneUserInfo.get("steamid")),
                String.valueOf(oneUserInfo.get("personaname")));
    }
}
