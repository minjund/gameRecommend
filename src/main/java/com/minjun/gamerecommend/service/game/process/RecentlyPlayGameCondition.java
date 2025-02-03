package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.service.recommend.RecommendGameInfo.UserId;
import lombok.Builder;

@Builder
public record RecentlyPlayGameCondition(UserId userId) {
    public String userIdValue() {
        return userId.userId();
    }
}
