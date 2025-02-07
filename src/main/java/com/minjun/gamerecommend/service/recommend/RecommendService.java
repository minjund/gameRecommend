package com.minjun.gamerecommend.service.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;
import com.minjun.gamerecommend.domain.tag.GameTags;
import com.minjun.gamerecommend.service.game.process.*;
import com.minjun.gamerecommend.service.game.process.ScoreCalculationTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class RecommendService {
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;
    private final GameTagMatch gameTagMatch;

    public RecommendGame findGameList(
            RecommendGameInfo recommendGameInfo
    ){
        RecentlyPlayGameCondition recentlyPlayGameCondition = new RecentlyPlayGameCondition(recommendGameInfo.userId());
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.findGameRecentlyPlay(recentlyPlayGameCondition);

        // 태그 정보 추출
        List<GameTags> gameTags = gameFinder.findGameDetailToTagByAppId(recentlyPlayGameInfo.games());
        GameTagResult tagList = gameTagFinder.findTagList();

        // 추천 된 게임 리스트
        ScoreCalculationTags scoreCalculationTags = ScoreCalculationTags.from(gameTags);
        List<List<String>> tagIdList = gameTagMatch.matchTagIdList(scoreCalculationTags, tagList);

        GameRecommendCondition gameRecommendCondition = GameRecommendCondition.create(tagIdList);

        //https://shared.fastly.steamstatic.com/store_item_assets/
        return gameFinder.findGameListTagFilter(gameRecommendCondition);
    }


    // fixme : 게임 상세 추가해주세요
    public GameDetailInfo findGameDetail() {

//        findGameDetailByAppId
        return null;
    }
}
