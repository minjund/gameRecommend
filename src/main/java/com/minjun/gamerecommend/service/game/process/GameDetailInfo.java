package com.minjun.gamerecommend.service.game.process;

import lombok.Builder;

import java.util.HashMap;

@Builder
public record GameDetailInfo(HashMap<String, Object> gameDetail) {
    public static GameDetailInfo from(HashMap<String, Object> gameDetail) {
        return new GameDetailInfo(gameDetail);
    }
}
