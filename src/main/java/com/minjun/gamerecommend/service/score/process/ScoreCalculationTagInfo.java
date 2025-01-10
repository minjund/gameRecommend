package com.minjun.gamerecommend.service.score.process;

import java.util.LinkedHashMap;

public record ScoreCalculationTagInfo(Integer appId, LinkedHashMap<String, Integer> tags) { }
