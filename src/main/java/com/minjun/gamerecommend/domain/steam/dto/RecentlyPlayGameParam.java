package com.minjun.gamerecommend.domain.steam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecentlyPlayGameParam(@JsonProperty("response")
                                       RecentlyPlayGameInfo response) {
}
