package com.minjun.gamerecommend.api.recommend;

import com.minjun.gamerecommend.service.recommend.query.RecommendGameResult;


public record RecommendGameResponse(RecommendGameResult recommendGameResult) {
    public static RecommendGameResponse of(RecommendGameResult recommendGameResult) {
        return new RecommendGameResponse(recommendGameResult);
    }
}
