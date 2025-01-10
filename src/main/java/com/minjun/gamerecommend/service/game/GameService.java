package com.minjun.gamerecommend.service.game;

import com.minjun.gamerecommend.service.score.process.ScoreCalculationTagInfo;
import com.minjun.gamerecommend.service.score.process.ScoreCalculation;
import com.minjun.gamerecommend.service.game.process.GameDetailInfo;
import com.minjun.gamerecommend.service.game.process.RecentlyPlayGameInfo;
import com.minjun.gamerecommend.service.game.process.GameFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final ScoreCalculation scoreCalculation;
    private final GameFinder gameFinder;

    public ResponseEntity<String> callSteamLoginApi(){
        return gameFinder.callSteamLoginForm();
    }

    public void findRecentlyPlayedGameByUserId(String userId){
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.gameRecentlyPlay(userId);

        List<GameDetailInfo> gameDetailInfoList = gameFinder.callGameDetailByAppId(recentlyPlayGameInfo.games());

        List<ScoreCalculationTagInfo> scoreCalculationTagInfoList = gameDetailInfoList.stream()
                .map(gameDetailInfo -> new ScoreCalculationTagInfo(gameDetailInfo.appId(), gameDetailInfo.tags()))
                .collect(Collectors.toList());

        int score = scoreCalculation.scoreTagList(scoreCalculationTagInfoList);

        System.out.println();

    }
}
