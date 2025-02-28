package com.minjun.gamerecommend.domain.calculation;

import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.service.recommend.query.dto.GameTagResult;

import java.util.ArrayList;
import java.util.List;

import static com.minjun.gamerecommend.global.util.Extractor.extractMinValues;

public record CalculationLowTag(List<String> lowTagList) {

    public static CalculationLowTag from(Tag tag, GameTagResult gameTagResult) {
        List<String> minValues = extractMinValues(tag.map());
        List<String> tagIds = new ArrayList<>();

        for (Tag t : gameTagResult.tags()) {
            if (minValues.contains(t.name())) {
                tagIds.add(t.tagId());
            }
        }

        return new CalculationLowTag(tagIds);
    }

    public List<String> list() {
        return lowTagList;
    }
}
