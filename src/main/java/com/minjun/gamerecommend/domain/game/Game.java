package com.minjun.gamerecommend.domain.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Game(List<HashMap<String, Object>> games) {
    public Game {
        if(games.isEmpty()){
            throw new IllegalArgumentException("게임을 찾을 수 없습니다.");
        }
    }

    public static Game of(List<HashMap<String, Object>> games) {
        return new Game(games);
    }

    public List<Object> findIds() {
        return games.stream()
                .filter(game -> game.containsKey("appid"))
                .map(game -> game.get("appid"))
                .collect(Collectors.toList());
    }

    public Game add(Game addGames){
        games.addAll(addGames.games());
        return this;
    }
}
