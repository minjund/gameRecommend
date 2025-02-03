package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.user.UserResult;
import com.minjun.gamerecommend.global.infra.SteamApiType;
import org.springframework.web.client.RestClient;

import java.util.Map;

public interface SteamGameExternal {
    UserResult callSteamLoginDetail(String userId);

    RecentlyPlayGame callRecentlyPlayedGameByUserId(String userId);

    GameDetailToTag callGameDetailToTagByAppId(String appId);

    GameTagList callTagList();

    RecommendGame callGameListByTag(String gameRecommendCommandToString);

    GameDetail callGameDetailByAppId(Integer appId);

    Map callGameDetailReview(String appId);

    RestClient buildSteamApiUrl(SteamApiType steamApiType);
}
