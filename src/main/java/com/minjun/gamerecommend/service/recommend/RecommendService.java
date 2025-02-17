package com.minjun.gamerecommend.service.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;
import com.minjun.gamerecommend.service.calculation.process.CalculationHighTag;
import com.minjun.gamerecommend.service.calculation.process.CalculationLowTag;
import com.minjun.gamerecommend.service.game.process.*;
import com.minjun.gamerecommend.service.calculation.process.CalculationTag;
import com.minjun.gamerecommend.service.tag.*;
import com.minjun.gamerecommend.service.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private final GameFinder gameFinder;
    private final GameTagFinder gameTagFinder;

    public RecommendGame findGameList(UserId userId){
        GameTagsResult gameTagsResult = gameTagFinder.findTagList();

        //보유중인 게임
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
    public GameDetailInfo findGameDetail() {

//        findGameDetailByAppId
        return null;
    }
}
