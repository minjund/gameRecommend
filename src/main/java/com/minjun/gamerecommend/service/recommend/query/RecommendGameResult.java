package com.minjun.gamerecommend.service.recommend.query;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record RecommendGameResult(@JsonProperty("store_items") List<HashMap<String,Object>> gameList) { }
