package com.minjun.gamerecommend.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record GameTagList(@JsonProperty("tags") List<HashMap<String , String>> tags) {
}
