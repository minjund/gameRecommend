package com.minjun.gamerecommend.domain.calculation;

import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.service.recommend.query.dto.GameTagResult;

import java.util.ArrayList;
import java.util.List;

import static com.minjun.gamerecommend.global.util.Extractor.extractMaxValues;

public record CalculationHighTag(List<String> higtTagList) {

    public static CalculationHighTag of(Tag tag, GameTagResult gameTagResult) {
        List<String> highestTag = extractMaxValues(tag.map());
        List<String> tagIds = new ArrayList<>();

        for (Tag t : gameTagResult.tags()) {
            if (highestTag.contains(t.name())) {
                tagIds.add(t.tagId());
            }
        }

        return new CalculationHighTag(tagIds);
    }

    public List<String> list() {
        return higtTagList;
    }
}
