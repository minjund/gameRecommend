package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.service.tag.RecommendGameTag;
import com.minjun.gamerecommend.service.recommend.RecommendGameTagsMapper;
import com.minjun.gamerecommend.service.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.minjun.gamerecommend.global.util.ObjectToJson.convert;

@Service
@RequiredArgsConstructor
public class GameFinder {

    private final SteamGameExternal steamGameExternal;

    public RecentlyPlayGameInfo findGameRecentlyPlay(UserId userId) {
        RecentlyPlayGame recentlyPlayGame = steamGameExternal.callRecentlyPlayedGameByUserId(userId.value());
        return RecentlyPlayGameInfo.from(recentlyPlayGame);
    }

    public RecommendGameTagsMapper findGameDetailToTagByAppId(RecommendGames recommendGames) {
        List<RecommendGameTag> recommendGameTags = new ArrayList<>();

        recommendGames.findIds().forEach(game -> {
            GameDetailToTag gameDetailToTag = steamGameExternal.callGameDetailToTagByAppId(game);
            recommendGameTags.add(RecommendGameTag.of(gameDetailToTag.appId(), gameDetailToTag.tags()));
        });

        return RecommendGameTagsMapper.of(recommendGameTags);
    }

    public RecommendGame findGameListTagFilter(GameRecommendCondition gameRecommendCondition) {
        String gameRecommendCommandToString = convert(gameRecommendCondition);
        return steamGameExternal.callGameListByTag(gameRecommendCommandToString);
    }

    public List<GameDetailInfo> findGameDetailByAppId(RecommendGame gameDetailList) {
        List<GameDetailInfo> gameDetailToTagParamList = new ArrayList<>();

        // FIXME : 2초 넘게 걸려요..
        gameDetailList.gameList().forEach(game -> {
            GameDetail appid = steamGameExternal.callGameDetailByAppId((Integer) game.get("appid"));
            if (appid.gameDetail().isEmpty()) {
                return;
            }
            HashMap<String, Object> stringObjectHashMap = (HashMap<String, Object>) appid.gameDetail().get(String.valueOf(game.get("appid"))).get("data");
            gameDetailToTagParamList.add(GameDetailInfo.from(stringObjectHashMap));
        });

        return gameDetailToTagParamList;
    }
}
