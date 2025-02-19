package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.count.TotalCount;
import com.minjun.gamerecommend.domain.game.HaveGame;
import com.minjun.gamerecommend.domain.game.RecommendGameResponse;
import com.minjun.gamerecommend.domain.game.RecommendGames;

public record HaveGameResult(@JsonProperty("response") HaveGame response) {
}
