package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.user.UserResult;
import com.minjun.gamerecommend.service.recommend.query.RecommendGameResult;

import java.util.Map;

public interface SteamGameExternal {
    UserResult callSteamLoginDetail(String userId);

    RecentlyPlayGame callRecentlyPlayedGameByUserId(String userId);

    GameDetailToTag callGameDetailToTagByAppId(String appId);

    GameTagList callTagList();

    RecommendGameResult callGameListByTag(String gameRecommendCommandToString);

    GameDetail callGameDetailByAppId(Integer appId);

    Map callGameDetailReview(String appId);
}
