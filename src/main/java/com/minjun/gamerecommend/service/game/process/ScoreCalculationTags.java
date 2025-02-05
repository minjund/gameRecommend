package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.tag.GameTags;
import com.minjun.gamerecommend.domain.tag.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ScoreCalculationTags(Tag tag) {

    public static ScoreCalculationTags from(List<GameTags> gameTags) {
        return new ScoreCalculationTags(Tag.of(calculationScore(gameTags)));
    }

    private static HashMap<String, Integer> calculationScore(List<GameTags> gameTags) {
        return gameTags.stream()
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
}
