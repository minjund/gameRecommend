package com.minjun.gamerecommend.service.game;

import com.minjun.gamerecommend.service.game.process.*;
import com.minjun.gamerecommend.service.score.process.ScoreCalculationTagInfo;
import com.minjun.gamerecommend.service.score.process.ScoreCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecommendService {
    private final ScoreCalculation scoreCalculation;
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;
    private final GameTagMatch gameTagMatch;

    public List<GameDetailInfo> findRecentlyPlayedGameByUserId(String userId){
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.findGameRecentlyPlay(userId);

        // FIXME: api 호출 할 때 너무 오래 걸려서 수정 필요
        List<GameDetailTagInfo> gameDetailTagInfoList = gameFinder.findGameDetailToTagByAppId(recentlyPlayGameInfo.games());

        // 태그 정보 추출
        List<ScoreCalculationTagInfo> scoreCalculationTagInfoList = gameDetailTagInfoList.stream()
                .map(gameDetailTagInfo -> new ScoreCalculationTagInfo(gameDetailTagInfo.tags()))
                .collect(Collectors.toList());

        // 스코어 계산
        HashMap<String, Integer> scoreTagInfo = scoreCalculation.ScoreTagId(scoreCalculationTagInfoList);

        // 태그 리스트
        GameTagParam tagList = gameTagFinder.findTagList();
        List<List<String>> tagIdList = gameTagMatch.matchTagIdList(scoreTagInfo, tagList);

        // 추천 된 게임 리스트
        GameRecommendCommand gameRecommendCommand = GameRecommendCommand.createGameRecommendCommand(tagIdList);
        List<HashMap<String, Integer>> eightGameTag = gameFinder.findEightGameTagFilter(gameRecommendCommand);

        return gameFinder.findGameDetailByAppId(eightGameTag);
    }
}
