package com.minjun.gamerecommend.service.recommend.query;

import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.domain.calculation.CalculationHighTag;
import com.minjun.gamerecommend.domain.calculation.CalculationLowTag;
import com.minjun.gamerecommend.domain.calculation.CalculationTag;
import com.minjun.gamerecommend.domain.tag.GameTagFinder;
import com.minjun.gamerecommend.domain.tag.GameTagsResult;
import com.minjun.gamerecommend.domain.game.RecommendGameResponse;
import com.minjun.gamerecommend.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendSearchService {
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;

    public RecommendGameResponse findGameList(UserId userId){
        GameTagsResult gameTagsResult = gameTagFinder.findTagList();

        //보유 중인 게임
        HaveGame haveGame = gameFinder.findGameHave(userId);
        RecentlyGame recentlyGame = gameFinder.findGameRecentlyPlay(userId);

        RecommendGameTagsMapper recommendGameTagsMapper = gameFinder.findGameDetailToTagByAppId(recentlyGame.recommendGames());

        CalculationTag calculationTag = CalculationTag.fromRecently(recommendGameTagsMapper);

        CalculationHighTag calculationHighTag = CalculationHighTag.of(calculationTag.tag(), gameTagsResult);
        CalculationLowTag calculationLowTag = CalculationLowTag.of(calculationTag.tag(), gameTagsResult);

        GameRecommendCondition gameRecommendCondition = GameRecommendCondition.create(calculationHighTag, calculationLowTag);

        return gameFinder.findGameListTagFilter(gameRecommendCondition);
    }


    // fixme : 게임 상세 추가해주세요
    public GameDetailResult findGameDetail() {

//        findGameDetailByAppId
        return null;
    }
}
