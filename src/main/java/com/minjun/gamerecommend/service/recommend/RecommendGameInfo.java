package com.minjun.gamerecommend.service.recommend;

import com.minjun.gamerecommend.service.user.UserId;
import lombok.Builder;

@Builder
public record RecommendGameInfo(UserId userId) {
    public static RecommendGameInfo from(String id){
        return new RecommendGameInfo(UserId.from(id));
    }
}
