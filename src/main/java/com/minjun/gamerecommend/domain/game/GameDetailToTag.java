package com.minjun.gamerecommend.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameDetailToTag(@JsonProperty("appid")
                              Integer appId,
                              @JsonProperty("name")
                              String name,
                              @JsonProperty("developer")
                              String developer,
                              @JsonProperty("publisher")
                              String publisher,
                              @JsonProperty("score_rank")
                              String scoreRank,
                              @JsonProperty("positive")
                              Integer positive,
                              @JsonProperty("negative")
                              Integer negative,
                              @JsonProperty("userscore")
                              String userScore,
                              @JsonProperty("owners")
                              String owners,
                              @JsonProperty("average_forever")
                              Integer averageForever,
                              @JsonProperty("average_2weeks")
                              Integer averageTwoWeeks,
                              @JsonProperty("median_forever")
                              Integer medianForever,
                              @JsonProperty("median_2weeks")
                              Integer medianTwoweeks,
                              @JsonProperty("price")
                              String price,
                              @JsonProperty("initialprice")
                              String initialPrice,
                              @JsonProperty("discount")
                              String discount,
                              @JsonProperty("ccu")
                              Integer ccu,
                              @JsonProperty("genre")
                              String genre,
                              @JsonProperty("tag")
                              Object tags) {
}
