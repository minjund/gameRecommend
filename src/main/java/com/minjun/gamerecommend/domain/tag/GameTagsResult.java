package com.minjun.gamerecommend.domain.tag;

import java.util.HashMap;
import java.util.List;

public record GameTagsResult(List<Tag> tags) {
    public static GameTagsResult from(List<HashMap<String , String>> tags) {
        return new GameTagsResult(
                tags.stream()
                .map(Tag::convert)
                .toList()
        );
    }
}
