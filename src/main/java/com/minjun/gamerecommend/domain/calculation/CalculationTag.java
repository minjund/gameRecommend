package com.minjun.gamerecommend.domain.calculation;

import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.domain.tag.RecommendGameTags;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public record CalculationTag(Tag tag) {
    public static CalculationTag fromRecently(RecommendGameTags recommendGameTags) {
        return new CalculationTag(Tag.of(calculationRecommendScore(recommendGameTags,2)));
    }

    public static CalculationTag fromHaveGame(RecommendGameTags recommendGameTags) {
        return new CalculationTag(Tag.of(calculationRecommendScore(recommendGameTags,1)));
    }

    private static HashMap<String, Integer> calculationRecommendScore(RecommendGameTags recommendGameTags, Integer addScore) {
        return recommendGameTags.list()
                .stream()
                .flatMap(gameTag -> gameTag.tags().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        HashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                count -> count.intValue() + addScore
                        )
                ));
    }
}
