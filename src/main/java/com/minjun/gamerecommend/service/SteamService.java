package com.minjun.gamerecommend.service;

import com.minjun.gamerecommend.domain.score.ScoreCalculation;
import com.minjun.gamerecommend.domain.steam.FavoriteGameInfoParam;
import com.minjun.gamerecommend.domain.steam.SteamApiCaller;
import com.minjun.gamerecommend.domain.steam.RecentlyPlayGameParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

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

        recentlyPlayGameParam.response().games().forEach(parma -> {
            steamApiCaller.callGameDetail(parma.get("appid"));
        });

        int i = scoreCalculation.recentlyGameScore(recentlyPlayGameParam.response().games());

    }
}
