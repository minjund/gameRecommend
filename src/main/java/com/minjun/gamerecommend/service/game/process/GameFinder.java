package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.GameDetail;
import com.minjun.gamerecommend.domain.game.SteamApiDao;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameFinder {

    private final SteamApiDao steamApiDao;

    public ResponseEntity<String> callSteamLoginForm(){
        return steamApiDao.callSteamLoginForm();
    }

    public RecentlyPlayGameInfo gameRecentlyPlay(String userId) {
        RecentlyPlayGame recentlyPlayGame = steamApiDao.callRecentlyPlayedGameByUserId(userId);
        return RecentlyPlayGameInfo.toInfo(recentlyPlayGame);
    }

    public List<GameDetailInfo> callGameDetailByAppId(List<HashMap<String,String>> gameDetailList) {
        List<GameDetail> gameDetailParamList = new ArrayList<>();
        gameDetailList.forEach(game -> {
            gameDetailParamList.add(steamApiDao.callGameDetailByAppId(game.get("appid")));
        });

        return GameDetailInfo.create(gameDetailParamList);
    }
}
