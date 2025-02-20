package com.minjun.gamerecommend.domain.tag;

import java.util.ArrayList;
import java.util.List;

public record RecommendGameTags(List<RecommendGameTag> values) {
    public RecommendGameTags(List<RecommendGameTag> values) {
        this.values = new ArrayList<>(values);
    }

    public static RecommendGameTags of(List<RecommendGameTag> values){
        return new RecommendGameTags(values);
    }

    public List<RecommendGameTag> list(){
        return values;
    }
}
