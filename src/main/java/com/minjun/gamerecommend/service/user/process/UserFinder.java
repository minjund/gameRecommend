package com.minjun.gamerecommend.service.user.process;

import com.minjun.gamerecommend.global.infra.SteamApiCaller;
import com.minjun.gamerecommend.domain.user.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserFinder {

    private final SteamApiCaller steamApiCaller;

    public HashMap<String, Object> findUserDetail(String userId) {
        UserResult userResult = steamApiCaller.callSteamLoginDetail(userId);

        return userResult.players().getFirst();
    }
}
