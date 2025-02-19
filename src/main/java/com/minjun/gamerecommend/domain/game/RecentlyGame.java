package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.count.TotalCount;

public record RecentlyGame(TotalCount totalCount, RecommendGames recommendGames) {
    public static RecentlyGame from(RecentlyPlayGame recentlyPlayGame) {
        return new RecentlyGame(
                TotalCount.of(recentlyPlayGame.totalCount()),
                RecommendGames.from(recentlyPlayGame.games())
        );
    }
}
