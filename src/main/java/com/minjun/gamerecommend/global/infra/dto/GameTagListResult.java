package com.minjun.gamerecommend.global.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minjun.gamerecommend.domain.game.GameTagList;

public record GameTagListResult(@JsonProperty("response") GameTagList response) { }
