package com.minjun.gamerecommend.service.recommend.query;

import com.minjun.gamerecommend.api.recommend.RecommendGameResponse;
import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.domain.calculation.CalculationHighTag;
import com.minjun.gamerecommend.domain.calculation.CalculationLowTag;
import com.minjun.gamerecommend.domain.calculation.CalculationTag;
import com.minjun.gamerecommend.domain.tag.GameTagFinder;
import com.minjun.gamerecommend.domain.tag.RecommendGameTags;
import com.minjun.gamerecommend.service.recommend.query.dto.GameTagResult;
import com.minjun.gamerecommend.domain.user.UserId;
import com.minjun.gamerecommend.service.recommend.query.dto.GameDetailResult;
import com.minjun.gamerecommend.service.recommend.query.dto.GameRecommendCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendSearchService {
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;

    public RecommendGameResponse findGameList(UserId userId){
        GameTagResult gameTagResult = gameTagFinder.findTagList();

        Games games = gameFinder.findUserGame(userId);
        RecommendGameTags recommendGameTags = gameFinder.findGameDetailToTagByAppId(games);

        CalculationTag calculationTag = CalculationTag.from(recommendGameTags);

        GameRecommendCondition gameRecommendCondition = GameRecommendCondition.create(
                calculationTag.highTag(gameTagResult),
                calculationTag.lowTag(gameTagResult)
        );

        RecommendGame gameListTagFilter = gameFinder.findGameListTagFilter(gameRecommendCondition);

        return RecommendGameResponse.from(gameListTagFilter);
    }


    // fixme : 게임 상세 추가해주세요
    public GameDetailResult findGameDetail() {

//        findGameDetailByAppId
        return null;
    }
}
