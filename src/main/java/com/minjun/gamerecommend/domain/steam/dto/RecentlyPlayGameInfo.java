package com.minjun.gamerecommend.domain.steam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record RecentlyPlayGameInfo(@JsonProperty("total_count")
                                   Integer totalCount,
                                   @JsonProperty("games")
                                   List<HashMap<String,String>> games) {
}
