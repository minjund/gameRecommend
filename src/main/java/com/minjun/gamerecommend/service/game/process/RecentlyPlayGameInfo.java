package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.Games;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import com.minjun.gamerecommend.domain.count.TotalCount;

public record RecentlyPlayGameInfo(TotalCount totalCount, Games games) {
    public static RecentlyPlayGameInfo from(RecentlyPlayGame recentlyPlayGame) {
        return new RecentlyPlayGameInfo(TotalCount.from(recentlyPlayGame.totalCount()), Games.from(recentlyPlayGame.games()));
    }
}
