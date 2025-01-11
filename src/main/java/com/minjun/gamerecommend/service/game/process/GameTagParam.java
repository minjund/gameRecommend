package com.minjun.gamerecommend.service.game.process;

import java.util.HashMap;
import java.util.List;

public record GameTagParam(List<HashMap<String, String>> tags) {
    public static GameTagParam from(List<HashMap<String , String>> tags) {
        return new GameTagParam(tags);
    }
}
