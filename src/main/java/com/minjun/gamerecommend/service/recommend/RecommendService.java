package com.minjun.gamerecommend.service.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;
import com.minjun.gamerecommend.service.game.process.*;
import com.minjun.gamerecommend.service.score.process.ScoreCalculationTags;
import com.minjun.gamerecommend.service.tag.*;
import com.minjun.gamerecommend.service.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class RecommendService {
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;
    private final GameTagMatch gameTagMatch;

    public RecommendGame findGameList(UserId userId){
        GameTagsResult gameTagsResult = gameTagFinder.findTagList();
        RecentlyPlayGameInfo recentlyPlayGameInfo = gameFinder.findGameRecentlyPlay(userId);

        RecommendGameTagsMapper recommendGameTagsMapper = gameFinder.findGameDetailToTagByAppId(recentlyPlayGameInfo.recommendGames());

        ScoreCalculationTags scoreCalculationTags = ScoreCalculationTags.from(recommendGameTagsMapper);
        List<List<String>> tagIdList = gameTagMatch.matchTagIdList(scoreCalculationTags, gameTagsResult);

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
