package com.minjun.gamerecommend.infra.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record RecentlyPlayGameResult(@JsonProperty("response") RecentlyPlayGameResponse response) {

    public record RecentlyPlayGameResponse(@JsonProperty("total_count")
                                   Integer totalCount,
                                           @JsonProperty("games")
                                   List<HashMap<String,Object>> games) {

    }
}
