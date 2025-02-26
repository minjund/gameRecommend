package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.count.TotalCount;

import java.util.HashMap;
import java.util.List;


public record HaveGame(TotalCount totalCount, Games games) {
    public static HaveGame from(Integer totalCount, List<HashMap<String, Object>> games){
        return new HaveGame(
                TotalCount.of(totalCount),
                Games.of(games)
        );
    }
}
