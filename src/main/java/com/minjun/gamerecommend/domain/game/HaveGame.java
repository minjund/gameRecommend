package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.count.TotalCount;

import java.util.HashMap;
import java.util.List;


public record HaveGame(TotalCount totalCount, Game game) {
    public static HaveGame from(Integer totalCount, List<HashMap<String, String>> games){
        return new HaveGame(
                TotalCount.of(totalCount),
                Game.of(games)
        );
    }
}
