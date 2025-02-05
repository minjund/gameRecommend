package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.tag.Tag;

import java.util.HashMap;
import java.util.List;

public record GameTagResult(List<Tag> tags) {
    public static GameTagResult from(List<HashMap<String , String>> tags) {
        return new GameTagResult(
                tags.stream()
                .map(Tag::ofString)
                .toList()
        );
    }
}
