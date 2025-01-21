package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.service.recommend.RecommendRecentlyInfo.UserId;
import lombok.Builder;

@Builder
public record RecentlyPlayGameCommand(UserId userId) {
    public String userIdValue() {
        return userId.userId();
    }
}
