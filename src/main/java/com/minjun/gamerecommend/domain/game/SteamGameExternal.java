package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.user.UserResult;
import com.minjun.gamerecommend.service.user.UserId;

import java.util.Map;

public interface SteamGameExternal {
    UserResult callSteamLoginDetail(String userId);

    RecentlyPlayGame callRecentlyPlayedGameByUserId(String userId);

    GameDetailToTag callGameDetailToTagByAppId(String appId);

    GameTagList callTagList();

    RecommendGame callGameListByTag(String gameRecommendCommandToString);

    GameDetail callGameDetailByAppId(Integer appId);

    Map callGameDetailReview(String appId);
}
