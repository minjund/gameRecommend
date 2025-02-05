package com.minjun.gamerecommend.domain.tag;

import java.util.LinkedHashMap;

public record GameTags(Integer gameId, LinkedHashMap<String, Integer> tags) {

    public static GameTags of(Integer gameId, Object tags) {
        return new GameTags(gameId, convertTags(tags));
    }

    private static LinkedHashMap<String, Integer> convertTags(Object tags){
        return (tags instanceof LinkedHashMap)
                ? (LinkedHashMap<String, Integer>) tags
                : new LinkedHashMap<>();
    }

}
