package com.minjun.gamerecommend.domain.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Game(HashMap<String, String> games) {

    public static Game of(List<HashMap<String, String>> games) {
        return new Game(games.getFirst());
    }

    public List<Object> findIds(){
        return games.entrySet().stream()
                .filter(e-> e.getKey().equals("appId"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Game add(HashMap<String, String> addGames){
        games.putAll(addGames);
        return this;
    }
}
