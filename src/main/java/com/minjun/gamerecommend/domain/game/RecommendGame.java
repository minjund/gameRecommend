package com.minjun.gamerecommend.domain.game;

import java.util.HashMap;
import java.util.List;

public record RecommendGame(List<HashMap<String, Object>> games) {
    public RecommendGame {
        if(games.isEmpty()){
            throw new IllegalArgumentException("게임을 못 찾았습니다.");
        }
    }

    public static RecommendGame of(List<HashMap<String, Object>> games) {
        return new RecommendGame(games);
    }
}
