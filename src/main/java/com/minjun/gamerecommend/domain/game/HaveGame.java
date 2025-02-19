package com.minjun.gamerecommend.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record HaveGame(@JsonProperty("game_count")
                       Integer totalCount,
                       @JsonProperty("games")
                       List<HashMap<String,Object>> games) {
}
