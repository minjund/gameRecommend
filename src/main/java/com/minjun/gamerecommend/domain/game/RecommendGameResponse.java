package com.minjun.gamerecommend.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record RecommendGameResponse(@JsonProperty("store_items") List<HashMap<String,Object>> gameList) { }
