package com.minjun.gamerecommend.domain.tag;

import java.util.HashMap;

public record Tag(HashMap<String, Integer> tags) {
    public Tag {
        if(tags.isEmpty()){
            throw new IllegalArgumentException("태그를 찾을 수 없습니다.");
        }
    }

    public static Tag of(HashMap<String, Integer> tags) {
        return new Tag(tags);
    }

    public static Tag convert(HashMap<String, String> tags) {
        HashMap<String, Integer> convertTag = new HashMap<>();
        convertTag.put(tags.get("name"), Integer.parseInt(tags.get("tagid")));
        return new Tag(convertTag);
    }

    public HashMap<String, Integer> map(){
        return tags;
    }

    public String name(){
        return tags.keySet().iterator().next();
    }

    public String tagId(){
        return String.valueOf(tags.values().iterator().next());
    }
}
