package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.RecommendGameResponse;

public record RecommendGameResult(@JsonProperty("response") RecommendGameResponse response) { }

