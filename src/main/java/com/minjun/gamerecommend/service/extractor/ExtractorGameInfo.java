package com.minjun.gamerecommend.service.extractor;

public record ExtractorGameInfo(String userId, String appId, String tags) {
    public static ExtractorGameInfo create(String userId, String appId, String tags) {
        return new ExtractorGameInfo(userId, appId, tags);
    }

}
