package com.minjun.gamerecommend.domain.game;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public record RecommendGames(List<HashMap<String, String>> games) {
    public RecommendGames {
        if(games.isEmpty()){
            throw new IllegalArgumentException("게임을 못 찾았습니다.");
        }
    }

    public static RecommendGames from(List<HashMap<String, String>> games) {
        return new RecommendGames(games);
    }

    public List<String> findIds(){
        return games.stream()
                .map(game -> game.get("appid"))
                .collect(Collectors.toList());
    }
}
