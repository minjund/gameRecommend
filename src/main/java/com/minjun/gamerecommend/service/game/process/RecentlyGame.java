package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import com.minjun.gamerecommend.domain.game.RecommendGames;
import com.minjun.gamerecommend.domain.count.TotalCount;

public record RecentlyGame(TotalCount totalCount, RecommendGames recommendGames) {
    public static RecentlyGame from(RecentlyPlayGame recentlyPlayGame) {
        return new RecentlyGame(
                TotalCount.from(recentlyPlayGame.totalCount()),
                RecommendGames.from(recentlyPlayGame.games())
        );
    }
}
