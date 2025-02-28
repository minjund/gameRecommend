package com.minjun.gamerecommend.domain.user;

import com.minjun.gamerecommend.infra.external.SteamApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserFinder {

    private final SteamApiCaller steamApiCaller;

    public HashMap<String, Object> findUserDetail(String userId) {
        UserDetail userResponse = steamApiCaller.callSteamLoginDetail(userId);

        return userResponse.players().getFirst();
    }
}
