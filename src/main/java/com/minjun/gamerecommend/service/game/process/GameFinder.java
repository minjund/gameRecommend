package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.minjun.gamerecommend.global.util.ObjectToJson.*;

@Service
@RequiredArgsConstructor
public class GameFinder {

    private final SteamGameExternal steamGameExternal;

    public RecentlyPlayGameInfo findGameRecentlyPlay(RecentlyPlayGameCondition recentlyPlayGameCommand) {
        RecentlyPlayGame recentlyPlayGame = steamGameExternal.callRecentlyPlayedGameByUserId(recentlyPlayGameCommand.userIdValue());
        return RecentlyPlayGameInfo.from(recentlyPlayGame);
    }

    public List<GameDetailTagInfo> findGameDetailToTagByAppId(List<HashMap<String,String>> gameDetailList) {
        List<GameDetailToTag> gameDetailToTagParamList = new ArrayList<>();

        gameDetailList.forEach(game -> {
            gameDetailToTagParamList.add(steamGameExternal.callGameDetailToTagByAppId(game.get("appid")));
        });

        return GameDetailTagInfo.create(gameDetailToTagParamList);
    }

    public RecommendGame findGameListTagFilter(GameRecommendCondition gameRecommendCondition) {
        String gameRecommendCommandToString = convert(gameRecommendCondition);
        return steamGameExternal.callGameListByTag(gameRecommendCommandToString);
    }

    public List<GameDetailInfo> findGameDetailByAppId(RecommendGame gameDetailList) {
        List<GameDetailInfo> gameDetailToTagParamList = new ArrayList<>();

        // FIXME : 2초 넘게 걸려요..
        gameDetailList.gameList().forEach(game -> {
            GameDetail appid = steamGameExternal.callGameDetailByAppId(game.get("appid"));
            if (appid.gameDetail().isEmpty()) {
                return;
            }
            HashMap<String, Object> stringObjectHashMap = (HashMap<String, Object>) appid.gameDetail().get(String.valueOf(game.get("appid"))).get("data");
            gameDetailToTagParamList.add(GameDetailInfo.from(stringObjectHashMap));
        });

        return gameDetailToTagParamList;
    }
}
