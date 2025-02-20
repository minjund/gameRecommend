package com.minjun.gamerecommend.domain.tag;

public record GameDetailToTag(Integer appId,
                              Object tags) {

    public static GameDetailToTag of(Integer appId, Object tags) {
        return new GameDetailToTag(appId, tags);
    }
}
