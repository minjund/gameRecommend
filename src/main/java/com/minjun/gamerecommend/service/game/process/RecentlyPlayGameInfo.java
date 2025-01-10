package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;

import java.util.HashMap;
import java.util.List;

public record RecentlyPlayGameInfo(Integer totalCount, List<HashMap<String,String>> games) {
    public static RecentlyPlayGameInfo toInfo(RecentlyPlayGame recentlyPlayGame) {
        return new RecentlyPlayGameInfo(recentlyPlayGame.totalCount(), recentlyPlayGame.games());
    }
}
