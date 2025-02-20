package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.HaveGame;

public record HaveGameResult(@JsonProperty("response") HaveGame response) {
}
