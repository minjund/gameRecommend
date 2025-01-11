package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.GameDetail;
import com.minjun.gamerecommend.domain.SteamApiCaller;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import com.minjun.gamerecommend.domain.game.RecommendGame;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.minjun.gamerecommend.global.util.ObjectToJson.*;

@Service
@RequiredArgsConstructor
public class GameFinder {

    private final SteamApiCaller steamApiCaller;

    public ResponseEntity<String> callSteamLoginForm(){
        return steamApiCaller.callSteamLoginForm();
    }

    public RecentlyPlayGameInfo findGameRecentlyPlay(String userId) {
        RecentlyPlayGame recentlyPlayGame = steamApiCaller.callRecentlyPlayedGameByUserId(userId);
        return RecentlyPlayGameInfo.from(recentlyPlayGame);
    }

    public List<GameDetailInfo> findGameDetailByAppId(List<HashMap<String,String>> gameDetailList) {
        List<GameDetail> gameDetailParamList = new ArrayList<>();

        gameDetailList.forEach(game -> {
            gameDetailParamList.add(steamApiCaller.callGameDetailByAppId(game.get("appid")));
        });

        return GameDetailInfo.create(gameDetailParamList);
    }

    public List<HashMap<String , Integer>> findFiveGameTagFilter(GameRecommendCommand gameRecommendCommand) {
        String gameRecommendCommandToString = convert(gameRecommendCommand);
        RecommendGame recommendGame = steamApiCaller.callFiveGameByTag(gameRecommendCommandToString);

        return recommendGame.ids();
    }
}
