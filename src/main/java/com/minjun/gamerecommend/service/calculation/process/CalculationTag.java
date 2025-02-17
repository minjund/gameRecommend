package com.minjun.gamerecommend.service.calculation.process;

import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.service.recommend.RecommendGameTagsMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public record CalculationTag(Tag tag) {
    public static CalculationTag fromRecently(RecommendGameTagsMapper recommendGameTagsMapper) {
        return new CalculationTag(Tag.of(calculationRecommendScore(recommendGameTagsMapper,2)));
    }

    public static CalculationTag fromHaveGame(RecommendGameTagsMapper recommendGameTagsMapper) {
        return new CalculationTag(Tag.of(calculationRecommendScore(recommendGameTagsMapper,1)));
    }

    private static HashMap<String, Integer> calculationRecommendScore(RecommendGameTagsMapper recommendGameTagsMapper, Integer addScore) {
        return recommendGameTagsMapper.list()
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
