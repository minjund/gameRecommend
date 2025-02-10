package com.minjun.gamerecommend.service.calculation.process;

import com.minjun.gamerecommend.domain.tag.Tag;
import com.minjun.gamerecommend.service.tag.GameTagsResult;

import java.util.ArrayList;
import java.util.List;

import static com.minjun.gamerecommend.global.util.Extractor.extractMinValues;

public record CalculationLowTag(List<String> lowTagList) {

    public static CalculationLowTag of(Tag tag, GameTagsResult gameTagsResult) {
        List<String> minValues = extractMinValues(tag.map());
        List<String> tagIds = new ArrayList<>();

        for (Tag t : gameTagsResult.tags()) {
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
