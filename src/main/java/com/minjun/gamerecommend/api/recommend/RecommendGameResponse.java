package com.minjun.gamerecommend.api.recommend;


public record RecommendGameResponse(
        com.minjun.gamerecommend.domain.game.RecommendGameResponse recommendGameResponse) {
    public static RecommendGameResponse of(com.minjun.gamerecommend.domain.game.RecommendGameResponse recommendGameResponse) {
        return new RecommendGameResponse(recommendGameResponse);
    }
}
