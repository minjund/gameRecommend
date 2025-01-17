package com.minjun.gamerecommend.domain.game;

import java.util.HashMap;
import java.util.Map;

public record GameDetail(HashMap<String, HashMap<String,Object>> gameDetail) {
    public static GameDetail from(Map appids) {
        return new GameDetail((HashMap<String, HashMap<String, Object>>) appids);
    }
}
