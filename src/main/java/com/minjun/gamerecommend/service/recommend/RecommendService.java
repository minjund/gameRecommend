package com.minjun.gamerecommend.service.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;
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

    public RecommendGame findGameList(RecommendGameInfo recommendGameInfo){
        RecentlyPlayGameCondition recentlyPlayGameCommand = new RecentlyPlayGameCondition(recommendGameInfo.userId());
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.findGameRecentlyPlay(recentlyPlayGameCommand);

        List<GameDetailTagInfo> gameDetailTagInfoList = gameFinder.findGameDetailToTagByAppId(recentlyPlayGameInfo.games());

        // 태그 정보 추출
        List<ScoreCalculationTagInfo> scoreCalculationTagInfoList = gameDetailTagInfoList.stream()
                .map(gameDetailTagInfo -> new ScoreCalculationTagInfo(gameDetailTagInfo.tags()))
                .collect(Collectors.toList());

        // 스코어 계산
        HashMap<String, Integer> scoreTagInfo = scoreCalculation.ScoreTagId(scoreCalculationTagInfoList);

        // 태그 리스트
        GameTagResult tagList = gameTagFinder.findTagList();
        List<List<String>> tagIdList = gameTagMatch.matchTagIdList(scoreTagInfo, tagList);

        // 추천 된 게임 리스트
        GameRecommendCondition gameRecommendCondition = GameRecommendCondition.create(tagIdList);

        return gameFinder.findGameListTagFilter(gameRecommendCondition);
    }

    public GameDetailInfo findGameDetail() {


        return null;
    }
}
