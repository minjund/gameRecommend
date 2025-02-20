package com.minjun.gamerecommend.domain.game;

import com.minjun.gamerecommend.domain.tag.GameDetailToTag;
import com.minjun.gamerecommend.domain.tag.RecommendGameTag;
import com.minjun.gamerecommend.service.recommend.query.GameRecommendCondition;
import com.minjun.gamerecommend.service.recommend.query.RecommendGameTagsMapper;
import com.minjun.gamerecommend.domain.user.UserId;
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

    public RecentlyGame findGameRecentlyPlay(UserId userId) {
        RecentlyPlayGame recentlyPlayGame = steamGameExternal.callRecentlyPlayedGameByUserId(userId.value());
        return RecentlyGame.from(recentlyPlayGame);
    }

    // fixme : 2초 걸려요..
    public RecommendGameTagsMapper findGameDetailToTagByAppId(RecommendGames recommendGames) {
        List<RecommendGameTag> recommendGameTags = new ArrayList<>();

        recommendGames.findIds().forEach(game -> {
            GameDetailToTag gameDetailToTag = steamGameExternal.callGameDetailToTagByAppId(game.toString());
            recommendGameTags.add(RecommendGameTag.of(gameDetailToTag.appId(), gameDetailToTag.tags()));
        });

        return RecommendGameTagsMapper.of(recommendGameTags);
    }

    public RecommendGames findGameListTagFilter(GameRecommendCondition gameRecommendCondition) {
        String gameRecommendCommandToString = convert(gameRecommendCondition);
        return steamGameExternal.callGameListByTag(gameRecommendCommandToString);
    }

//    public List<GameDetailResult> findGameDetailByAppId(RecommendGameResponse gameDetailList) {
//        List<GameDetailResult> gameDetailToTagParamList = new ArrayList<>();
//
//        gameDetailList.gameList().forEach(game -> {
//            GameDetail appid = steamGameExternal.callGameDetailByAppId((Integer) game.get("appid"));
//            if (appid.gameDetail().isEmpty()) {
//                return;
//            }
//            HashMap<String, Object> stringObjectHashMap = (HashMap<String, Object>) appid.gameDetail().get(String.valueOf(game.get("appid"))).get("data");
//            gameDetailToTagParamList.add(GameDetailResult.from(stringObjectHashMap));
//        });
//
//        return gameDetailToTagParamList;
//    }

    public HaveGame findGameHave(UserId userId) {
        return steamGameExternal.callHaveGameList(userId);
    }
}
