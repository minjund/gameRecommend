package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.tag.GameDetailToTag;
import com.minjun.gamerecommend.domain.tag.GameTag;
import com.minjun.gamerecommend.domain.user.UserDetail;
import com.minjun.gamerecommend.domain.user.UserId;

import java.util.Map;

public interface SteamGameExternal {
    UserDetail callSteamLoginDetail(String userId);

    RecentlyGame callRecentlyPlayedGameByUserId(String userId);

    GameDetailToTag callGameDetailToTagByAppId(String appId);

    GameTag callTagList();

    RecommendGame callGameListByTag(String gameRecommendCommandToString);

    GameDetail callGameDetailByAppId(Integer appId);

    Map callGameDetailReview(String appId);

    HaveGame callHaveGameList(UserId userId);
}
