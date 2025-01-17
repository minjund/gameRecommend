package com.minjun.gamerecommend.service.game.process;

import com.minjun.gamerecommend.domain.game.GameDetail;
import com.minjun.gamerecommend.domain.game.GameDetailToTag;
import com.minjun.gamerecommend.domain.SteamApiCaller;
import com.minjun.gamerecommend.domain.game.RecentlyPlayGame;
import com.minjun.gamerecommend.domain.game.RecommendGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.minjun.gamerecommend.global.util.ObjectToJson.*;

@Service
@RequiredArgsConstructor
public class GameFinder {

    private final SteamApiCaller steamApiCaller;

    public RecentlyPlayGameInfo findGameRecentlyPlay(String userId) {
        RecentlyPlayGame recentlyPlayGame = steamApiCaller.callRecentlyPlayedGameByUserId(userId);
        return RecentlyPlayGameInfo.from(recentlyPlayGame);
    }

    public List<GameDetailTagInfo> findGameDetailToTagByAppId(List<HashMap<String,String>> gameDetailList) {
        List<GameDetailToTag> gameDetailToTagParamList = new ArrayList<>();

        gameDetailList.forEach(game -> {
            gameDetailToTagParamList.add(steamApiCaller.callGameDetailToTagByAppId(game.get("appid")));
        });

        return GameDetailTagInfo.create(gameDetailToTagParamList);
    }

    public List<HashMap<String , Integer>> findEightGameTagFilter(GameRecommendCommand gameRecommendCommand) {
        String gameRecommendCommandToString = convert(gameRecommendCommand);
        RecommendGame recommendGame = steamApiCaller.callFiveGameByTag(gameRecommendCommandToString);

        return recommendGame.ids();
    }

    public List<GameDetailInfo> findGameDetailByAppId(List<HashMap<String, Integer>> gameDetailList) {

        gameDetailList.forEach(game -> {
            GameDetail appid = steamApiCaller.callGameDetailByAppId(game.get("appid"));
            System.out.println(appid);
        });

        return GameDetailInfo.from(null);
    }
}
