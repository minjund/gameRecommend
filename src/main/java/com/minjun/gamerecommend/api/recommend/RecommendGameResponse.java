package com.minjun.gamerecommend.api.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGame;

public record RecommendGameResponse(RecommendGame recommendGameResponse) {
    public static RecommendGameResponse of(RecommendGame recommendGameResponse) {
        return new RecommendGameResponse(recommendGameResponse);
    }
}
