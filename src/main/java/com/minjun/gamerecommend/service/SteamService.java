package com.minjun.gamerecommend.service;

import com.minjun.gamerecommend.domain.score.ScoreCalculation;
import com.minjun.gamerecommend.domain.steam.SteamApiCaller;
import com.minjun.gamerecommend.domain.steam.dto.RecentlyPlayGameParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SteamService {
    private final ScoreCalculation scoreCalculation;
    private final SteamApiCaller steamApiCaller;

    public ResponseEntity<String> callSteamLoginApi(){
        return steamApiCaller.callSteamLoginForm();
    }

    public void findRecentlyPlayedGameByUserId(String userId){
        RecentlyPlayGameParam recentlyPlayGameParam = steamApiCaller.callRecentlyPlayedGameByUserId(userId);

        scoreCalculation.recentlyGameScore(recentlyPlayGameParam.response().games());

    }
}
