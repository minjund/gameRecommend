package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.count.TotalCount;
import com.minjun.gamerecommend.domain.game.RecommendGames;

public record HaveGame(TotalCount totalCount, RecommendGames games) {
}
