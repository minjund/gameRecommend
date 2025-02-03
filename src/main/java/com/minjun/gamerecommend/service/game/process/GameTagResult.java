package com.minjun.gamerecommend.service.game.process;

import java.util.HashMap;
import java.util.List;

public record GameTagResult(List<HashMap<String, String>> tags) {
    public static GameTagResult from(List<HashMap<String , String>> tags) {
        return new GameTagResult(tags);
    }
}
