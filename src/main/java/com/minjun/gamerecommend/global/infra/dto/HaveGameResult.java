package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.HaveGame;

import java.util.HashMap;
import java.util.List;

public record HaveGameResult(@JsonProperty("response") HaveGameResponse response) {
    public record HaveGameResponse(@JsonProperty("game_count")
                           Integer totalCount,
                           @JsonProperty("games")
                           List<HashMap<String,Object>> games) {
    }
}
