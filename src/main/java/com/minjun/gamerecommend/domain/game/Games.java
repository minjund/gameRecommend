package com.minjun.gamerecommend.domain.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public record Games(List<HashMap<String, Object>> games) {
    public Games {
        if(games.isEmpty()){
            throw new IllegalArgumentException("게임을 찾을 수 없습니다.");
        }
    }

    public static Games of(List<HashMap<String, Object>> games) {
        return new Games(games);
    }

    public List<Object> findAllIds() {
        return games.stream()
                .filter(game -> game.containsKey("appid"))
                .map(game -> game.get("appid"))
                .collect(Collectors.toList());
    }

    public Games combine(Games addGames){
        List<HashMap<String, Object>> combinedGames = new ArrayList<>(games);
        combinedGames.addAll(addGames.games());

        return new Games(combinedGames);
    }
}
