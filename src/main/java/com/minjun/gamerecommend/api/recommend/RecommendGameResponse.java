package com.minjun.gamerecommend.api.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;


public record RecommendGameResponse(RecommendGame recommendGame) {
    public static RecommendGameResponse of(RecommendGame recommendGame) {
        return new RecommendGameResponse(recommendGame);
    }
}
