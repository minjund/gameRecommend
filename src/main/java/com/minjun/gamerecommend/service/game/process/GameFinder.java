package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.*;
import com.minjun.gamerecommend.domain.tag.GameTags;
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

    public RecentlyPlayGameInfo findGameRecentlyPlay(RecentlyPlayGameCondition recentlyPlayGameCommand) {
        RecentlyPlayGame recentlyPlayGame = steamGameExternal.callRecentlyPlayedGameByUserId(recentlyPlayGameCommand.userIdValue());
        return RecentlyPlayGameInfo.from(recentlyPlayGame);
    }

    public List<GameTags> findGameDetailToTagByAppId(Games games) {
        List<GameTags> gameTags = new ArrayList<>();
        games.findIds().forEach(game -> {
            GameDetailToTag gameDetailToTag = steamGameExternal.callGameDetailToTagByAppId(game);
            gameTags.add(GameTags.of(gameDetailToTag.appId(), gameDetailToTag.tags()));
        });

        return gameTags;
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
