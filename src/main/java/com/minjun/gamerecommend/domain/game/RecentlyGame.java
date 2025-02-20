package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.count.TotalCount;

import java.util.HashMap;
import java.util.List;

public record RecentlyGame(TotalCount totalCount, Game game) {
    public static RecentlyGame from(Integer totalCount, List<HashMap<String, String>> recentlyPlayGame) {
        return new RecentlyGame(
                TotalCount.of(totalCount),
                Game.of(recentlyPlayGame)
        );
    }



}
