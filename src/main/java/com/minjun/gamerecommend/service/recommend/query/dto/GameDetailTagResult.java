package com.minjun.gamerecommend.service.recommend.query.dto;

import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GameDetailTagResult(Integer appId,
                                  String name,
                                  String developer,
                                  String publisher,
                                  String scoreRank,
                                  Integer positive,
                                  Integer negative,
                                  String userScore,
                                  String owners,
                                  Integer averageForever,
                                  Integer averageTwoWeeks,
                                  Integer medianForever,
                                  Integer medianTwoweeks,
                                  String price,
                                  String initialPrice,
                                  String discount,
                                  Integer ccu,
                                  String genre,
                                  LinkedHashMap<String, Integer> tags) {

    public static List<GameDetailTagResult> create(List<GameDetailToTagResult> gameDetailToTagResultParamList) {
        return gameDetailToTagResultParamList.stream()
                .map(detail -> {
                            Object tagInfo = detail.tags();
                            LinkedHashMap<String, Integer> convertedTags = (tagInfo instanceof LinkedHashMap) ? (LinkedHashMap<String, Integer>) tagInfo : new LinkedHashMap<>();

                            return GameDetailTagResult.builder()
                                    .appId(detail.appId())
                                    .name(detail.name())
                                    .developer(detail.developer())
                                    .publisher(detail.publisher())
                                    .scoreRank(detail.scoreRank())
                                    .positive(detail.positive())
                                    .negative(detail.negative())
                                    .userScore(detail.userScore())
                                    .owners(detail.owners())
                                    .averageForever(detail.averageForever())
                                    .averageTwoWeeks(detail.averageTwoWeeks())
                                    .medianForever(detail.medianForever())
                                    .medianTwoweeks(detail.medianTwoweeks())
                                    .price(detail.price())
                                    .initialPrice(detail.initialPrice())
                                    .discount(detail.discount())
                                    .ccu(detail.ccu())
                                    .genre(detail.genre())
                                    .tags(convertedTags)
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }
}
