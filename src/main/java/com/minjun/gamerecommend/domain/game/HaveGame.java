package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.count.TotalCount;

public record HaveGame(TotalCount totalCount, RecommendGames games) {
}
