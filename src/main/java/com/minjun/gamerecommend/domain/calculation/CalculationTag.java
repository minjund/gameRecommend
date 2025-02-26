package com.minjun.gamerecommend.domain.calculation;

import com.minjun.gamerecommend.domain.score.Score;
import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.domain.tag.RecommendGameTags;
import com.minjun.gamerecommend.service.recommend.query.dto.GameTagResult;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public record CalculationTag(Tag tag) {
    public static CalculationTag from(RecommendGameTags recommendGameTags) {
        return new CalculationTag(Tag.of(calculationRecommendScore(recommendGameTags, Score.of(2))));
    }

    public CalculationHighTag highTag(GameTagResult gameTagResult) {
        return CalculationHighTag.of(tag, gameTagResult);
    }

    public CalculationLowTag lowTag(GameTagResult gameTagResult) {
        return CalculationLowTag.of(tag, gameTagResult);
    }

    private static HashMap<String, Integer> calculationRecommendScore(RecommendGameTags recommendGameTags, Score score) {
        return recommendGameTags.list()
                .stream()
                .flatMap(gameTag -> gameTag.tags().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        HashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                count -> count.intValue() + score.increment()
                        )
                ));
    }
}
