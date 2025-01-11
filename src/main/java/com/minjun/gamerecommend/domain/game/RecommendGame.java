package com.minjun.gamerecommend.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RecommendGame(@JsonProperty("ids") List<String> tags) {
}
