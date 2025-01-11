package com.minjun.gamerecommend.service.score.process;

import java.util.HashMap;

public record ScoreHighLowerInfo(Integer appId, HashMap<String, Integer> highTagId, HashMap<String, Integer> lowerTagId) {
}
