package com.minjun.gamerecommend.infra.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record RecommendGameResult(@JsonProperty("response")
                                  RecommendGameResponse response) {
    public record RecommendGameResponse(
            @JsonProperty("store_items")
            List<HashMap<String,Object>> gameList
    ) { }
}

