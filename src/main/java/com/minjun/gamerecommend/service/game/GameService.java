package com.minjun.gamerecommend.service.game;

import com.minjun.gamerecommend.service.game.process.*;
import com.minjun.gamerecommend.service.score.process.ScoreCalculationTagInfo;
import com.minjun.gamerecommend.service.score.process.ScoreCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.minjun.gamerecommend.global.util.Extractor.extractMaxValues;
import static com.minjun.gamerecommend.global.util.Extractor.extractMinValues;

@Service
@RequiredArgsConstructor
public class GameService {
    private final ScoreCalculation scoreCalculation;
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;
    private final GameTagMatch gameTagMatch;

    public ResponseEntity<String> callSteamLoginApi(){
        return gameFinder.callSteamLoginForm();
    }

    public List<HashMap<String, Integer>> findRecentlyPlayedGameByUserId(String userId){
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.findGameRecentlyPlay(userId);

        // FIXME: api 호출 할 때 너무 오래 걸려서 수정 필요
        List<GameDetailInfo> gameDetailInfoList = gameFinder.findGameDetailByAppId(recentlyPlayGameInfo.games());

        List<ScoreCalculationTagInfo> scoreCalculationTagInfoList = gameDetailInfoList.stream()
                .map(gameDetailInfo -> new ScoreCalculationTagInfo(gameDetailInfo.tags()))
                .collect(Collectors.toList());

        HashMap<String, Integer> scoreTagInfo = scoreCalculation.ScoreTagId(scoreCalculationTagInfoList);

        GameTagParam tagList = gameTagFinder.findTagList();
        List<List<String>> tagIdList = gameTagMatch.matchTagIdList(scoreTagInfo, tagList);

        GameRecommendCommand gameRecommendCommand = GameRecommendCommand.createGameRecommendCommand(tagIdList);

        return gameFinder.findFiveGameTagFilter(gameRecommendCommand);
    }
}
