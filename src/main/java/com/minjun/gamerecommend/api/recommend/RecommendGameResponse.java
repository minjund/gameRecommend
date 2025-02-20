package com.minjun.gamerecommend.api.recommend;

import com.minjun.gamerecommend.domain.game.RecommendGames;

public record RecommendGameResponse(RecommendGames recommendGameResponse) {
    public static RecommendGameResponse of(RecommendGames recommendGameResponse) {
        return new RecommendGameResponse(recommendGameResponse);
    }
}
