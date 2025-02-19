package com.minjun.gamerecommend.service.recommend.query;

import com.minjun.gamerecommend.domain.tag.RecommendGameTag;

import java.util.ArrayList;
import java.util.List;

public class RecommendGameTagsMapper {
    private final List<RecommendGameTag> values;

    private RecommendGameTagsMapper(List<RecommendGameTag> values) {
        this.values = new ArrayList<>(values);
    }

    public static RecommendGameTagsMapper of(List<RecommendGameTag> values){
        return new RecommendGameTagsMapper(values);
    }

    public List<RecommendGameTag> list(){
        return values;
    }
}
