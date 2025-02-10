package com.minjun.gamerecommend.service.score.process;

import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.service.recommend.RecommendGameTagsMapper;
import com.minjun.gamerecommend.service.tag.GameTagsResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.minjun.gamerecommend.global.util.Extractor.extractMaxValues;
import static com.minjun.gamerecommend.global.util.Extractor.extractMinValues;

public record ScoreCalculationTags(Tag tag) {
    public static ScoreCalculationTags from(RecommendGameTagsMapper recommendGameTagsMapper) {
        return new ScoreCalculationTags(Tag.of(calculationScore(recommendGameTagsMapper)));
    }

    public static ScoreCalculationTags from(List<String> recommendGameList){
        return new ScoreCalculationTags()
    }

    private static HashMap<String, Integer> calculationScore(RecommendGameTagsMapper recommendGameTagsMapper) {
        return recommendGameTagsMapper.list()
                .stream()
                .flatMap(gameTag -> gameTag.tags().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        HashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                Long::intValue
                        )
                ));
    }

    public ScoreCalculationTags highestScoreTag(GameTagsResult gameTagsResult){
        List<String> highestTag = extractMaxValues(tag.map());

        for (Tag tag : gameTagsResult.tags()) {
            String name = String.valueOf(tag.get("name"));
            if (highestTag.contains(name)) {
                highestTagList.add(tag.get("tagid"));
            }
        }

        return ScoreCalculationTags.from();
    }

    public ScoreCalculationTags lowestScoreTag(){
        List<String> lowestTag = extractMinValues(tag.map());

    }
}
