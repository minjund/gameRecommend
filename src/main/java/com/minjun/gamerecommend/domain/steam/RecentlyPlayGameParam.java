package com.minjun.gamerecommend.domain.steam;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecentlyPlayGameParam(@JsonProperty("response")
                                       RecentlyPlayGameInfo response) {
}
