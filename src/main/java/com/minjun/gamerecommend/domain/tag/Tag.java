package com.minjun.gamerecommend.domain.tag;

import java.util.HashMap;
import java.util.Map;

public record Tag(HashMap<String, Integer> tags) {

    public Tag {
        if(tags.isEmpty()){
            throw new IllegalArgumentException("태그를 찾을 수 없습니다.");
        }
    }

    public static Tag of(HashMap<String, Integer> tags) {
        return new Tag(tags);
    }

    public static Tag ofString(HashMap<String, String> tags) {
        HashMap<String, Integer> convertTag = new HashMap<>();
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            convertTag.put(entry.getKey(), Integer.parseInt(entry.getValue()));
        }
        return new Tag(convertTag);
    }
}
