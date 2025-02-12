package com.minjun.gamerecommend.domain.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record RecommendGames(HashMap<String, String> games) {
    public RecommendGames {
        if(games.isEmpty()){
            throw new IllegalArgumentException("게임을 못 찾았습니다.");
        }
    }

    public static RecommendGames from(List<HashMap<String, String>> games) {
        return new RecommendGames(games.getFirst());
    }

    public RecommendGames add(HashMap<String, String> addGames){
        games.putAll(addGames);

        return this;
    }

    public List<String> findIds(){
        return games.entrySet().stream()
                .filter(e-> e.getKey().equals("appId"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
