package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.RecommendGames;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import com.minjun.gamerecommend.domain.count.TotalCount;

public record RecentlyPlayGameInfo(TotalCount totalCount, RecommendGames recommendGames) {
    public static RecentlyPlayGameInfo from(RecentlyPlayGame recentlyPlayGame) {
        return new RecentlyPlayGameInfo(
                TotalCount.from(recentlyPlayGame.totalCount()),
                RecommendGames.from(recentlyPlayGame.games())
        );
    }
}
