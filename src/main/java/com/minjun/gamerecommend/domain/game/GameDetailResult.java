package com.minjun.gamerecommend.domain.game;

import lombok.Builder;

import java.util.HashMap;

@Builder
public record GameDetailResult(HashMap<String, Object> gameDetail) {
    public static GameDetailResult from(HashMap<String, Object> gameDetail) {
        return new GameDetailResult(gameDetail);
    }
}
