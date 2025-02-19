package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;

public record RecentlyPlayGameResult(@JsonProperty("response") RecentlyPlayGame response) { }
